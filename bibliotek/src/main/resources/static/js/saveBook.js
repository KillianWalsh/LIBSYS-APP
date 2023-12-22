async function saveBook() {

    const data = {
        'id': null,
        'title': document.getElementById('TitleText').value,
        'author': document.getElementById('AuthorText').value,
        'genre': document.getElementById('Genre').value,
        'publisher': document.getElementById('PublisherText').value,
        'publicationDate': document.getElementById('PublicationDateText').value,
        'isbn': document.getElementById('ISBNText').value,
        'status': document.getElementById('BookStatus').value
    }
    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    const url = 'http://localhost:8080/book/save';

    const request = new Request(url, {
        method: 'POST',
        redirect: 'follow',
        body: JSON.stringify(data),
        headers: myHeaders
    });

    console.log(request);

    await fetch(request)
        .then(response => response.json())
        .then(response => console.log(response))
        .catch((error) => console.error('Error ', error)
        )

    eraseText()
}

function isFilled() {
    if (document.getElementById('TitleText').value !== "" &&
        document.getElementById('AuthorText').value !== "" &&
        document.getElementById('PublisherText').value !== "" &&
        document.getElementById('PublicationDateText').value !== "" &&
        document.getElementById('ISBNText').value !== "") {
    } else {
        alert("Vänligen fyll i alla nödvändig fält!")
    }
}

function eraseText() {
    document.getElementById("TitleText").value = "";
    document.getElementById("AuthorText").value = "";
    document.getElementById("PublisherText").value = "";
    document.getElementById("PublicationDateText").value = "";
    document.getElementById("ISBNText").value = "";
}