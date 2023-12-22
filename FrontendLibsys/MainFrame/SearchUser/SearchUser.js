async function getUserByEmail(){
    const data = $("#searchInput").val();
    let url = 'http://localhost:8080/borrower/findbyemail/?email='+data;
    let response = await fetch(url,{
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}

async function renderData(){
    var borrowId;
    var loans;
    //first get the data from borrower
    await getUserByEmail()
            .then( function(data){
                console.log(data);
                $('#NameHeader').html(data.firstname +" "+ data.lastname);
                $('#IdHeader').html("Id: "+ data.id);
                $("#UserMail").html(data.email);
                $("#SocialNumber").html("Social Number: " + data.socialNumber);
                borrowId = data.id;
            })
            .catch(error => alert("something went wrong!"));

    //then get data from loan
    await getLoanById(borrowId)
        .then(function (data){
            loans = data;
        });

    var body = document.getElementById("BookListTableBody");
    var rowCount = body.rows.length;
    if(rowCount > 1){
        for(var i = 1; i < rowCount; i++){
            body.deleteRow(1);
        }
    }
    

    //build a row for every loan  
    loans.forEach(async function(loan) {
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
        let textLoan = createText(loan.dateOfBookLoanDay[0]+"-"+loan.dateOfBookLoanDay[1]+"-"+loan.dateOfBookLoanDay[2]);
        tdLoan.appendChild(textLoan);
        row.appendChild(tdLoan);

        let tdReturn = createNode("td");
        let textReturn = createText(loan.dateOfBookReturn[0]+"-"+loan.dateOfBookReturn[1]+"-"+loan.dateOfBookReturn[2]);
        tdReturn.appendChild(textReturn);
        row.appendChild(tdReturn);

        body.append(row);
    });            
    $("#searchInput").val("");                  
}

function createNode(element){
    return document.createElement(element);
}

function createText(text){
    return document.createTextNode(text);
}

async function getLoanById(id){
    let url = 'http://localhost:8080/bookloans/findallbyid/?id='+id;
    let response = await fetch(url,{
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}

async function getBookById(id){
    let url = 'http://localhost:8080/book/findbookbyid/?id='+id;
    let response = await fetch(url,{
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}

