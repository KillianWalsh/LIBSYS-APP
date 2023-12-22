async function renderData() {
    //add some function to get the right data!
    let data = await getLastTen();
    var body = document.getElementById("tableBody");
    console.log(data);

    data.forEach(element => {

        var row = createNode("tr");

        var tdTitle = createNode("td");
        var textTitle = createText(element.title);
        tdTitle.appendChild(textTitle)
        row.appendChild(tdTitle);

        var tdAuthor = createNode("td");
        var textAuthor = createText(element.author);
        tdAuthor.appendChild(textAuthor)
        row.appendChild(tdAuthor);

        var tdISBN = createNode("td");
        var textISBN = createText(element.isbn);
        tdISBN.appendChild(textISBN)
        row.appendChild(tdISBN);

        var tdQuantity = createNode("td");
        var textQuantity = createText(element.numberOfWishes);
        tdQuantity.appendChild(textQuantity)
        row.appendChild(tdQuantity);

        body.appendChild(row);
    });
}

function createNode(nodeName) {
    return document.createElement(nodeName);
}

function createText(text) {
    return document.createTextNode(text);
}

async function getLastTen() {
    var url = "http://localhost:8080/wish/getlastten";
    var response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json',
        }
    });
    return response.json();
}