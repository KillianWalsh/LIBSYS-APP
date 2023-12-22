// _______________________FETCH DATA_______________________
async function getAllUsers() {
    let url = 'http://localhost:8080/borrower/getAll';
    let response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}

async function getBookOfStatus(status) {
    let url = 'http://localhost:8080/book/findstatus/?status=' + status;
    let response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}


//______________COMFORT FUNCTIONS___________________________

async function checkRadioButtons() {
    if (document.getElementById("borrower").checked) {
        const data = await getAllUsers();
        return data;
    } else if (document.getElementById("borrowed").checked) {
        const data = await getBookOfStatus("ON_LOAN");
        return data;
    } else if (document.getElementById("instock").checked) {
        const data = await getBookOfStatus("IN_STOCK");
        return data;
    }
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

//____________________RENDER DATA____________________________

async function renderData() {
    var data = await checkRadioButtons();

    if (document.getElementById("borrower").checked) {
        document.getElementById("bookInformationTable").style.display = "none";
        document.getElementById("userInformationTable").style.display = "block";
        createUserTable(data);
    } else if (document.getElementById("borrowed").checked || document.getElementById("instock").checked) {
        document.getElementById("userInformationTable").style.display = "none";
        document.getElementById("bookInformationTable").style.display = "block";
        createBookTable(data);
    }
}

function createUserTable(data) {
    var body = document.getElementById("userTableBody"); //table body
    console.log(data);
    clearTable(body);
    data.forEach((user) => {
        var row = createNode("tr");

        var tdID = createNode("td");
        var textID = createText(user.id);
        tdID.appendChild(textID);
        row.appendChild(tdID);

        var tdFirst = createNode("td");
        var textFirst = createText(user.firstname);
        tdFirst.appendChild(textFirst);
        row.appendChild(tdFirst);

        var tdLast = createNode("td");
        var textLast = createText(user.lastname);
        tdLast.appendChild(textLast);
        row.appendChild(tdLast);

        var tdMail = createNode("td");
        var textMail = createText(user.email);
        tdMail.appendChild(textMail);
        row.appendChild(tdMail);

        body.appendChild(row);
    });
}

function createBookTable(data) {
    var body = document.getElementById("bookTableBody"); //table body
    clearTable(body);
    data.forEach((book) => {
        var row = createNode("tr");

        var tdID = createNode("td");
        var textID = createText(book.id);
        tdID.appendChild(textID);
        row.appendChild(tdID);

        var tdTitle = createNode("td");
        var textTitle = createText(book.title);
        tdTitle.appendChild(textTitle);
        row.appendChild(tdTitle);

        var tdStatus = createNode("td");
        var textStatus = createText(book.status);
        tdStatus.appendChild(textStatus);
        row.appendChild(tdStatus);

        body.appendChild(row);
    });
}