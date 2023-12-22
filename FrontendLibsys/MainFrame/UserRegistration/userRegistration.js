
async function registerUser() {

    if (document.getElementById('PasswordText') !== document.getElementById('PasswordVerifyText')) {

        const data = {
            'id': null,
            'firstname': document.getElementById('NameText').value,
            'lastname': document.getElementById('SurNameText').value,
            'email': document.getElementById('EmailText').value,
            'socialNumber': document.getElementById('PersonNummerText').value,
            'password': document.getElementById('PasswordText').value
        }
        const myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');

        const url = 'http://localhost:8080/borrower/register';

        const request = new Request(url, {
            method: 'POST',
            redirect: 'follow',
            body: JSON.stringify(data),
            headers: myHeaders,
            mode: 'no-cors'
        });

        console.log(request);

        await fetch(request)
            .then(response => response.json())
            .then(response => console.log(response))
            .catch((error) => console.error('Error ', error))

        eraseText()
    }
    else {
        alert("Password have to match!")
    }
}

function eraseText() {
    document.getElementById("NameText").value = "";
    document.getElementById("SurNameText").value = "";
    document.getElementById("EmailText").value = "";
    document.getElementById("PersonNummerText").value = "";
    document.getElementById("PasswordText").value = "";
    document.getElementById("PasswordVerifyText").value = "";
}