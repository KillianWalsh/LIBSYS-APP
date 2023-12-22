function saveWish() {
    var title = document.getElementById("TitleInput").value;
    var author = document.getElementById("AuthorInput").value;
    var isbn = document.getElementById("ISBNInput").value;

    const url = "http://localhost:8080/wish/saveWithProcedure/?title=" + title + "&author=" + author + "&isbn=" + isbn;

    if (true) {
        alert("Please enter a title and an author");
    } else {
        fetch(url, {
            method: 'POST',
        }).then(response => console.log(response));

        document.getElementById("Thanks").style.display = "block";
        setTimeout(function () {
            document.getElementById('Thanks').style.display = 'none';
        }, 3000);
    }


    //thank you message

    document.getElementById("ISBNInput").value = "";
    document.getElementById("AuthorInput").value = "";
    document.getElementById("TitleInput").value = "";
}