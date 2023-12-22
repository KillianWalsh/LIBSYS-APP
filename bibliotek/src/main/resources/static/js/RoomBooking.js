//___________________________Buttons_______________________________________//
function showBooking() {
    document.getElementById("DecisionDiv").style.display = "none";
    document.getElementById("Rooms").style.display = "block";
    document.getElementById("TitleText").innerHTML = "Välj ett rum:"
}

function showCancel() {
    document.getElementById("DecisionDiv").style.display = "none";
    document.getElementById("CancelDiv").style.display = "block";
    document.getElementById("TitleText").innerHTML = "Leta efter användarens ID"
}

function backToBeginning() {
    document.getElementById("DecisionDiv").style.display = "block";
    document.getElementById("Rooms").style.display = "none";
    document.getElementById("CancelDiv").style.display = "none";
    document.getElementById("TitleText").innerHTML = "Vad ska du göra?"
    clearTable(document.getElementById("InfoTableBody"));
}

function setRoomNumber(number) {
    document.getElementById("RoomTD").innerHTML = number;
    document.getElementById("Rooms").style.display = "none";
    document.getElementById("CalenderDiv").style.display = "block";
    document.getElementById("TitleText").innerHTML = "Välj en dag:"
    showCalendar(new Date(2022, 0));
}

function backToRooms() {
    document.getElementById("Rooms").style.display = "block";
    document.getElementById("CalenderDiv").style.display = "none";
    document.getElementById("TitleText").innerHTML = "Välj ett rum:"
}

function continueToBook() {
    document.getElementById("BookingDiv").style.display = "block";
    document.getElementById("CalenderDiv").style.display = "none";
    document.getElementById("TitleText").innerHTML = "Please enter your ID!"
}

function backToCalender() {
    document.getElementById("BookingDiv").style.display = "none";
    document.getElementById("CalenderDiv").style.display = "block";
    document.getElementById("TitleText").innerHTML = "Välj en dag:"
    showCalendar(new Date(2022, 0));
}

function saveDate(id, month, year) {
    const dateContainer = document.getElementById("DayTD");
    const pressedButton = document.getElementById(id);
    dateContainer.innerHTML = year + "-" + month + "-" + pressedButton.id;
}

//___________________________Fetch Data_______________________________________//

function updateDatabase(roomNumber, bookedDays, month) {
    if (roomNumber === "1") {
        var url = "http://localhost:8080/RoomBooking/updateRoom1/?month=" + bookedDays + "&id=" + month;
        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            }
        });
    } else if (roomNumber === "2") {
        var url = "http://localhost:8080/RoomBooking/updateRoom2/?month=" + bookedDays + "&id=" + month;
        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            }
        });
    }
}

async function getBookedDays(id) {
    var url = "http://localhost:8080/RoomBooking/findById/?id=" + id;
    var response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json',
        }
    });
    return response.json();
}

async function getAllBookings() {
    var url = "http://localhost:8080/RoomBooking/findAll";
    var response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json',
        }
    });
    return response.json();
}

//_____________________________comfort methods_____________________________________//

function convertToString(customArray) {
    var bookingString = "";
    customArray.forEach(element => bookingString = bookingString + element + ",");
    bookingString = bookingString.slice(0, -1);
    return bookingString;
}

function getAllIndexes(arr, val) {
    var indexes = [], i;
    for (i = 0; i < arr.length; i++)
        if (arr[i] === val)
            indexes.push(i);
    return indexes;
}

function createNode(element) {
    return document.createElement(element);
}

function createText(text) {
    return document.createTextNode(text);
}

function clearTable(tableBody) {
    var rowCount = tableBody.rows.length;
    if (rowCount > 1) {
        for (var i = 1; i < rowCount; i++) {
            tableBody.deleteRow(1);
        }
    }
}

//_____________________________Cancel Room_____________________________________//

async function cancelBooking(month, roomNumber, day) {
    var roomsForMonth;
    var tempArray;
    var updateData;

    await getBookedDays(month)
        .then(data => roomsForMonth = data);

    if (roomNumber === "1") {
        tempArray = roomsForMonth.room1.split(",");
        tempArray[day] = 0;
    } else if (roomNumber === "2") {
        tempArray = roomsForMonth.room2.split(",");
        tempArray[day] = 0;
    }

    updateData = convertToString(tempArray);
    updateDatabase(roomNumber, updateData, month);
}

async function fillTable() {
    //get the value
    const userID = document.getElementById("SearchInputField").value;
    document.getElementById("SearchInputField").value = "";

    //Get the data
    var allBookings;
    await getAllBookings()
        .then(data => allBookings = data);

    //clean the table
    var body = document.getElementById("InfoTableBody");
    clearTable(body);

    allBookings.forEach((element) => {
        //first look at all Bookings in room1
        var tempArray1 = element.room1.split(",");
        if (tempArray1.includes(userID)) {

            //find the indexes of the bookings from current user
            var indexes1 = getAllIndexes(tempArray1, userID);

            //create table entries for each booking with the current userID
            generateRows(indexes1, 1, userID, element);
        }
        var tempArray2 = element.room2.split(",");
        if (tempArray2.includes(userID)) {

            //find the indexes of the bookings from current user
            var indexes2 = getAllIndexes(tempArray2, userID);

            //create table entries for each booking with the current userID
            generateRows(indexes2, 2, userID, element);
        }
    })
}

function generateRows(indexes, roomNumber, userID, currentMonth) {
    var element = currentMonth;
    var body = document.getElementById("InfoTableBody");
    indexes.forEach((index) => {

        var row = createNode("tr");

        const IdTd = createNode("td");
        const IdText = createText(userID);
        IdTd.appendChild(IdText);
        row.appendChild(IdTd);

        const dateTd = createNode("td");
        const dateText = createText("2022" + "-" + (element.id) + "-" + (index + 1));
        dateTd.appendChild(dateText);
        row.appendChild(dateTd);

        const roomTd = createNode("td");
        const roomText = createText(roomNumber);
        roomTd.appendChild(roomText);
        row.appendChild(roomTd);

        const button = createNode("button");
        button.innerHTML = "Cancel";
        button.classList.add("CancelBooking");
        //save month, day, room
        button.value = element.id + "," + index + "," + roomNumber;
        //add functionality to cancel button
        button.onclick = function () {
            var valueArray = button.value.split(",");
            cancelBooking(valueArray[0], valueArray[2], valueArray[1]);
            alert("Booking canceled");
            $(this).closest('tr').remove();
        };
        row.appendChild(button);

        body.appendChild(row);
    })
}

//_____________________________Book Room_____________________________________//

async function bookRoom() {
    const room = document.getElementById("RoomTD").innerHTML;
    const bookingDateArray = document.getElementById("DayTD").innerHTML.split("-");
    const dayToBook = bookingDateArray[2];
    const currentMonth = bookingDateArray[1];
    const userID = document.getElementById("IdInput").value;

    console.log(room + dayToBook + userID);

    var bookedDays;
    await getBookedDays(currentMonth)
        .then((data) => {
            if (room === "1") {
                bookedDays = data.room1.split(",");
            } else if (room === "2") {
                bookedDays = data.room2.split(",");
            }
        });
    if (!isNaN(userID)) {
        //prepare the string for database
        bookedDays[dayToBook - 1] = userID;

        var bookingString = "";
        bookedDays.forEach(element => bookingString = bookingString + element + ",");
        bookingString = bookingString.slice(0, -1);

        //send update request
        updateDatabase(room, bookingString, currentMonth);

        //clean Up
        document.getElementById("IdInput").value = "";
        alert("You sucessfully booked room " + room + " on the " + document.getElementById("DayTD").innerHTML);
        backToCalender();
    } else {
        alert("Please insert a number!");
    }
}


//_____________________________Calender Functionality_____________________________________//

//functions taken from or build with reference to: https://medium.com/allenhwkim/how-to-build-a-calendar-widget-7cf397fe3de5
function getLeadingDays(date, staDay = 0) { // 0, sunday, 1: monday
    const ret = [];
    const year = date.getFullYear();
    const month = date.getMonth();
    const firstWeekday = new Date(year, month, 1).getDay();
    const days = (firstWeekday + 7) - (staDay + 7) - 1; // 2 days become 1 for [1, 0]
    for (let i = days * -1; i <= 0; i++) {
        ret.push(new Date(year, month, i).getDate());
    }
    return ret;
}

function getMonthDays(date) {
    const ret = [];
    const year = date.getFullYear();
    const month = date.getMonth();
    const lastDay = new Date(year, month + 1, 0).getDate();
    for (let i = 1; i <= lastDay; i++) ret.push(i);
    return ret;
}

function getTrailingDays(leadingDays, monthDays) {
    const ret = [];
    const days = 42 - (leadingDays.length + monthDays.length);
    for (let i = 1; i <= days; i++) ret.push(i);
    return ret;
}

const monthNames = [
    'January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'
];

window.showCalendar = async function (date) {
    const daysPrevMonth = getLeadingDays(date);
    const daysThisMonth = getMonthDays(date);
    const daysNextMonth = getTrailingDays(daysPrevMonth, daysThisMonth);

    const daysEl = document.getElementById('days');
    const monthEl = document.getElementById('month');
    const yearEl = document.getElementById('year');

    const currentRoom = document.getElementById("RoomTD").innerHTML;
    var currentMonth;

    monthEl.innerHTML = monthNames[date.getMonth()];
    yearEl.innerHTML = date.getFullYear();
    daysEl.innerHTML = '';

    //fetch data from backend to show booked days
    for (var i = 0; i < monthNames.length; i++) {
        if (monthNames[i] === monthEl.innerHTML)
            currentMonth = i + 1;
    }
    ;

    var bookedDays;
    await getBookedDays(currentMonth)
        .then((data) => {
            console.log(currentRoom);
            if (currentRoom === "1") {
                bookedDays = data.room1.split(",");
            } else if (currentRoom === "2") {
                bookedDays = data.room2.split(",");
            }
        });

    daysPrevMonth.forEach(num => {
        daysEl.insertAdjacentHTML('beforeend', `<button id="prevMonths" disabled>${num}</button>`);
    });

    daysThisMonth.forEach(num => {
        //currently only for 2022 until I found a solution in database
        if (yearEl.innerHTML === "2022") {
            if (bookedDays[num - 1] != 0) {
                var button = document.createElement("button");
                button.setAttribute("disabled", "");
                button.setAttribute("id", "bookedDay");
                button.innerHTML = num;
                daysEl.appendChild(button);
            } else {
                var button = document.createElement("button");
                button.setAttribute("id", num);
                button.innerHTML = num;
                button.classList.add("thisMonths");
                button.onclick = function () {
                    saveDate(button.innerHTML, currentMonth, 2022);
                    continueToBook();
                };
                daysEl.appendChild(button);
            }
        } else {
            daysEl.insertAdjacentHTML('beforeend', `<button id="nextMonths" disabled>${num}</button>`);
        }
    });

    daysNextMonth.forEach(num => {
        daysEl.insertAdjacentHTML('beforeend', `<button id="nextMonths" disabled>${num}</button>`);
    });

};
  