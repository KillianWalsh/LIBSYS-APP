async function getBookByTitle(title){
   var data = title;
   if(data.length == 0){
       return;
   }

    var url = "http://localhost:8080/book/findbytitle/?title=" + data;

    var response = await fetch(url,{

        mode:'cors',

        headers:{
            'Content-Type': 'application/json',
        }
    });

    return response.json();
}

async function getBookByAuthor(){
    var data = document.getElementById("SearchInput").value;
    if(data.length == 0){
        return;
    }
 
     var url = "http://localhost:8080/book/findbyauthor/?author=" + data;
 
     var response = await fetch(url,{
 
         mode:'cors',
 
         headers:{
             'Content-Type': 'application/json',
         }
     });
 
     return response.json();
 }

 async function getBookByGenre(bookGenre){
    var data = bookGenre;
    if(data.length == 0){
        return;
    }
 
     var url = "http://localhost:8080/book/findbygenre/?genre=" + data;
 
     var response = await fetch(url,{
 
         mode:'cors',
 
         headers:{
             'Content-Type': 'application/json',
         }
     });
 
     return response.json();
 }

function createNode(element){
    return document.createElement(element);
}

function createText(text){
    return document.createTextNode(text);
}

async function renderData(){
    var books;
    //check radioButtons to get the right data
    var buttonTitle = document.getElementById("TitleCheck");
    var buttonAuthor = document.getElementById("AuthorCheck");
    var buttonGenre = document.getElementById("GenreCheck");

    if(buttonTitle.checked){
        await getBookByTitle(document.getElementById("SearchInput").value)
        .then(function (data){
            books = data;
        });
    }

    if(buttonAuthor.checked){
        await getBookByAuthor()
        .then(function (data){
            books = data;
        });
    }

    if(buttonGenre.checked){
        await getBookByGenre(document.getElementById("SearchInput").value)
        .then(function (data){
            books = data;
        });
    }
    
    //render data from the function we called 
        var body = document.getElementById("TableBody");

        //delete all unnessecary rows
        var rowCount = body.rows.length;
        for(var i = 1; i < rowCount; i++){
            body.deleteRow(1);
        }
        
        books.forEach( book =>{
            var row = createNode("tr");
            //fill in rows again
            var tdTitle = createNode("td");
            var titleText = createText(book.title);
            tdTitle.appendChild(titleText);
            row.appendChild(tdTitle);

            var tdAuthor = createNode("td");
            var authorText = createText(book.author);
            tdAuthor.appendChild(authorText);
            row.appendChild(tdAuthor);

            var tdGenre = createNode("td");
            var genreText = createText(book.genre);
            tdGenre.appendChild(genreText);
            row.appendChild(tdGenre);

            var tdPub = createNode("td");
            var pubText = createText(book.publisher);
            tdPub.appendChild(pubText);
            row.appendChild(tdPub);

            var tdPubDate = createNode("td");
            var pubDateText = createText(book.publicationDate);
            tdPubDate.appendChild(pubDateText);
            row.appendChild(tdPubDate);

            var tdISBN = createNode("td");
            var isbnText = createText(book.isbn);
            tdISBN.appendChild(isbnText);
            row.appendChild(tdISBN);

            var detailButton = createNode("button");
            var tdButton = createNode("td");
            tdButton.appendChild(detailButton);
            detailButton.innerHTML = "Details";
            detailButton.value = book.title;
            console.log(book.title);
            detailButton.onclick = function(){
                toggleIFrame(); 
                document.getElementById("SecretSave").innerHTML = detailButton.value;
                console.log(book.title);
                populateData(book.title);
            };
            row.appendChild(tdButton);

            body.appendChild(row);
        });
}

function checkOutTitle(){
    document.getElementById("TitleCheck").checked = true;
}

function toggleIFrame(){
    var iframe = document.getElementById("iframeDiv");
    var table = document.getElementById("container");
    var header = document.getElementById("Headerdiv");
    var input = document.getElementById("inputclass");
    var wish = document.getElementById("WishList");

    if(iframe.style.display != "block"){
        wish.style.display = "none";
        table.style.display = "none";
        header.style.display = "none";
        input.style.display = "none";
        iframe.style.display = "block";
    }else if(table.style.display != "block"){
        wish.style.display = "block";
        table.style.display = "block";
        header.style.display = "block";
        input.style.display = "block";
        iframe.style.display = "none";
    }
}

//method for wishList
function saveWish(){
    var title = document.getElementById("TitleInput").value;
    var author = document.getElementById("AuthorInput").value;
    var isbn = document.getElementById("ISBNInput").value;
  
    const url = "http://localhost:8080/wish/saveWithProcedure/?title="+title+"&author="+author+"&isbn="+isbn;
  
    if(title.length >0 && author.length>0){
        fetch(url,{
            method: 'POST',
          }).then(response => console.log(response));
        
          //thank you message
          document.getElementById("Thanks").style.display = "block";
            setTimeout(function() {
                document.getElementById('Thanks').style.display = 'none';
              }, 3000);
    }else{
        alert("please enter a title and an author!");
    }
    
      document.getElementById("ISBNInput").value = "";
      document.getElementById("AuthorInput").value = "";
      document.getElementById("TitleInput").value = "";
  }

//__________________methods for recommendations________________________________
   
function next(){
    document.getElementById("Slide2").style.transform = "translateX(0%)";
    document.getElementById("Slide1").style.transform = "translateX(-100%)";
}

function prev(){
    document.getElementById("Slide2").style.transform = "translateX(100%)";
    document.getElementById("Slide1").style.transform = "translateX(0%)";
}

async function fillRecommendations(){
    var books;
    await getBookByGenre(document.getElementById("genrevalue").innerHTML)
    .then(function(data){
        books = data;
    })

    var recommendationArray = document.getElementsByClassName("BookTitle");
    //clean up
    for(var i = 0; i< recommendationArray.length; i++){
        recommendationArray[i].innerHTML = "";
        recommendationArray[i].value = "";
    }
    //populate
    for(var i = 0; i< recommendationArray.length; i++){
        if(books[i] != undefined){
            recommendationArray[i].innerHTML = books[i].title;
            recommendationArray[i].value = books[i].title;
        }
    }
}


//____________________everything for bookdetails that is now included in searchBook______________________//

  async function populateData(title){
    const bookTitle = title;
    console.log("inPopulateData:"+bookTitle);
    var bookData;
    await getBookByTitle(bookTitle)
        .then(function(data){
            bookData = data;
        });

    console.log("my data: "+bookData);
  
    document.getElementById("titlevalue").innerHTML = bookData[0].title;
    document.getElementById("genrevalue").innerHTML = bookData[0].genre;
    document.getElementById("authorvalue").innerHTML = bookData[0].author;
    document.getElementById("datevalue").innerHTML = bookData[0].publicationDate;
    document.getElementById("ISBNvalue").innerHTML = bookData[0].isbn;
    //document.getElementById("").innerHTML = bookData[0].description;

   
    const tableBody = document.getElementById("LoanTableBody");
    //clean table
    clearTable(tableBody);
     //populate the table
    bookData.forEach(book => {
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

        if(book.status != "IN_STOCK"){
            var QButton = createNode("button");
            var tdButton = createNode("td");
            tdButton.appendChild(QButton);
            QButton.innerHTML = "Queue";
            QButton.onclick = function(){
                console.log("Queue for Book");
                
            }
            row.appendChild(QButton);
        }else{
            var loanButton = createNode("button");
            var tdButton = createNode("td");
            tdButton.appendChild(loanButton);
            loanButton.innerHTML = "Loan";
            loanButton.value = book.id;
            loanButton.onclick = function(){
                console.log("Loan Book");
                saveLoan(book.id);
                //update book in database
                //populate data again
            }
            row.appendChild(loanButton);
        }

        tableBody.appendChild(row);
    });
    fillRecommendations();
}


 async function callSaveBookLoan(userID, bookId){
    let url = 'http://localhost:8080/bookloans/save/?borrowerId='+userID+"&bookId="+bookId;
     await fetch(url,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

//find the current users id
async function getCurrentUserID(){
    //add the correct endpoint for the call here
    //also add parameters if needed
    let url = 'http://localhost:8080/';
    let response = await fetch(url,{
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return response.json();
}


async function saveLoan(bookId){ 
    var bookID = bookId;
    
    /*var borrowerId;
    await getCurrentUserID()
        .then(function(data){
            borrowerId = data.id;
        })
        */

    borrowerId = 1;
    
    if(borrowerId <= 0){
        alert("Please log in to loan a book!");
        return;
    }

    var response = await callSaveBookLoan(borrowerId, bookID);

    //check the status code of the response instead for "true", if its 200/ok alert
    if(true){
        alert("You successfully reserved this book!");
    }
    
}

function clearTable(tableBody){
    var rowCount = tableBody.rows.length;
    if(rowCount > 1){
        for(var i = 1; i < rowCount; i++){
            tableBody.deleteRow(1);
        }
    }
  }

