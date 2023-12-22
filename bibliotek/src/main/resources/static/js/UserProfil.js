//toggle function to insert email adress
function toggleButtons() {
    var change = document.getElementById("change");
    var save = document.getElementById("save");
    var emailInput = document.getElementById("emailInput");

    if (save.style.display != "block") {
        change.style.display = "none";
        save.style.display = "block";
        emailInput.removeAttribute("disabled");
    } else if (change.style.display != "block") {
        save.style.display = "none";
        change.style.display = "block";
        emailInput.setAttribute("disabled", "")
    }
}

//for making our project even cooler
async function updateEmail() {
    console.log("Function to update Email of user in database");
    alert("Successfully changed your email! (NOT DONE YET)");
}

async function getLoanById(id) {
    let url = 'http://localhost:8080/bookloans/findallbyid/?id=' + id;
    let response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}

async function getUserByID(id) {
    let url = 'http://localhost:8080/borrower/findbyId/?id=' + id;
    let response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}

// async function getUserByEmail(email){
//     let url = 'http://localhost:8080/borrower/findbyemail/?email='+email;
//     let response = await fetch(url,{
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     });
//     console.log(response)
//     return response.json();
//     // return response;
// }

async function getUserEmail() {

    let url = 'http://localhost:8080/username';
    let response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
    // console.log(response)
    return response.json();

    // var borrowerID = window.location.search.split("=")[1];
    // var requestOptions = {
    //     method: 'GET',
    //     redirect: 'follow'
    // };
    // const response = await fetch("http://localhost:8080/borrower/findbyemail/?email=" + borrowerID, requestOptions)
    //
    // let staff = await response.json();
    //
    // populateHTML(staff);
    //
    // function populateHTML(borrower)
    // {
    //     var borrowerEmailInput = document.getElementById("email")
    //     staffEmailInput.value = borrower.email
    // }


    // fetch('http://localhost:8080/username')
    //     .then(response => response.json())
    //     .then(data => {
    //         console.log(data[0])
    //         return JSON.stringify(data[0])
    //     })


}

async function getBookById(id) {
    let url = 'http://localhost:8080/book/findbookbyid/?id=' + id;
    let response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}

async function renderData() {

    // var userinfo = JSON.stringify(getUserEmail())
    // var userinfo = getUserEmail()
    // var userinfo = getUserByEmail(JSON.stringify(getUserEmail()))
    // console.log(userinfo)
    // var borrowId = userinfo[Object.keys(userinfo)[0]]
    // console.log(borrowId)
    // var borrowId = 4;
    // var userinfo = {data: getUserByEmail(getUserEmail())}
    // var events = JSON.parse(await userinfo.data)
    // var borrowId = events[0]
    // console.log(borrowId)

    var borrower;
    var borrowId;
    await getUserEmail()
        .then(function (data) {
            // console.log(data)
            borrower = data;
            borrowId = data[Object.keys(data)[0]]
            console.log(borrowId)
        });
    var loans;
    var body = document.getElementById("BookListTableBody");

    //get the user
    await getUserByID(borrowId)
        .then(function (data) {
            borrower = data;
        });

    document.getElementById("IDCell").innerHTML = borrowId;
    document.getElementById("NameCell").innerHTML = borrower.firstname + " " + borrower.lastname;
    document.getElementById("SocialNumberCell").innerHTML = borrower.socialNumber;
    document.getElementById("emailInput").value = borrower.email;

    //get the loans
    await getLoanById(borrowId)
        .then(function (data) {
            loans = data;
        });

    //build a row for each loan  
    loans.forEach(async function (loan) {
        //get the information abut the book
        var book = await getBookById(loan.bookId);
        let row = createNode("tr");

        let tdID = createNode("td");
        let textId = createText(book.id);
        tdID.appendChild(textId);
        row.appendChild(tdID);

        let tdTitle = createNode("td");
        let textTitle = createText(book.title);
        tdTitle.appendChild(textTitle);
        row.appendChild(tdTitle);

        let tdAuthor = createNode("td");
        let textAuthor = createText(book.author);
        tdAuthor.appendChild(textAuthor);
        row.appendChild(tdAuthor);

        let tdPub = createNode("td");
        let textPub = createText(book.publisher);
        tdPub.appendChild(textPub);
        row.appendChild(tdPub);

        let tdLoan = createNode("td");
        // let textLoan = createText(loan.dateOfBookLoanDay[0]+"-"+loan.dateOfBookLoanDay[1]+"-"+loan.dateOfBookLoanDay[2]);
        let textLoan = createText(loan.dateOfBookLoanDay[0] + loan.dateOfBookLoanDay[1] + loan.dateOfBookLoanDay[2] + loan.dateOfBookLoanDay[3] + "-" + loan.dateOfBookLoanDay[5] + loan.dateOfBookLoanDay[6] + "-" + loan.dateOfBookLoanDay[8] + loan.dateOfBookLoanDay[9]);
        tdLoan.appendChild(textLoan);
        row.appendChild(tdLoan);

        let tdReturn = createNode("td");
        // let textReturn = createText(loan.dateOfBookReturn[0]+"-"+loan.dateOfBookReturn[1]+"-"+loan.dateOfBookReturn[2]);
        let textReturn = createText(loan.dateOfBookReturn[0] + loan.dateOfBookReturn[1] + loan.dateOfBookReturn[2] + loan.dateOfBookReturn[3] + "-" + loan.dateOfBookReturn[5] + loan.dateOfBookReturn[6] + "-" + loan.dateOfBookReturn[8] + loan.dateOfBookReturn[9]);
        tdReturn.appendChild(textReturn);
        row.appendChild(tdReturn);

        //add buttons here
        var extendButton = createNode("button");
        var tdButton = createNode("td");
        tdButton.appendChild(extendButton);
        tdButton.classList.add("buttonTd");
        extendButton.innerHTML = "Extend";
        extendButton.value = book.title;
        extendButton.onclick = function () {
            console.log("Function to update Loan in BookLoans if there is no one in line")
        };
        extendButton.classList.add("ExtendButton");
        row.appendChild(tdButton);

        body.appendChild(row);
    });
}

function createNode(element) {
    return document.createElement(element);
}

function createText(text) {
    return document.createTextNode(text);
}