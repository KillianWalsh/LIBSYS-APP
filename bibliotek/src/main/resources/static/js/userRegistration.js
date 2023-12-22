async function registerUser() {
    // if (isFilled()) {
    //     if (document.getElementById('PasswordText') !== document.getElementById('PasswordVerifyText')) {
    // if (passwordVerifier() === true) {
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
        // mode: 'no-cors'
    });

    console.log(request);

    await fetch(request)
        .then(response => response.json())
        .then(response => console.log(response))
        .catch((error) => console.error('Error ', error))

    eraseText()

    // }
    // let timeout;
    // function timeOutFunction() {
    //     timeout = setTimeout(eraseText, 3000);
    // }
    // timeOutFunction()
    // saveInput()

    // } else {
    //     alert("Password have to match!")
    // }
    // } else {
    //     alert("Please fill in the required fields")
    // }
    //     saveInput()
}

// let timeout;
// function timeOutFunction() {
//     timeout = setTimeout(eraseText, 3000);
// }

function eraseText() {
    // timeOutFunction()
    document.getElementById("NameText").value = "";
    document.getElementById("SurNameText").value = "";
    document.getElementById("EmailText").value = "";
    document.getElementById("PersonNummerText").value = "";
    document.getElementById("PasswordText").value = "";
    document.getElementById("PasswordVerifyText").value = "";
}

function isFilled() {

    if (document.getElementById('NameText').value !== "" &&
        document.getElementById('SurNameText').value !== "" &&
        document.getElementById('EmailText').value !== "" &&
        document.getElementById('PersonNummerText').value !== "" &&
        document.getElementById('PasswordText').value !== "" &&
        document.getElementById('PasswordVerifyText').value !== ""
        // passwordVerifier() &&
        // nameCheck() &&
        // emailValidation() &&
        // validatePersonNumber()
    ) {
        // openPopup()
        return true
    } else {
        alert("Vänligen fyll i all nödvändig information!")
    }

}

function validInformation() {
    if (passwordVerifier() &&
        nameCheck() &&
        emailValidation() &&
        validatePersonNumber()) {
        return true;
    }
}

function check() {
    if (isFilled() && validInformation()) {
        openPopup()
        return true
    }
}

function passwordVerifier() {

    const password = document.getElementById('PasswordText').value;
    const verifypassword = document.getElementById('PasswordVerifyText').value;
    const passwordValidation = /^(?=.*\d)(?=.*[a-zåäö])(?=.*[A-ZÅÄÖ])[0-9a-zA-Z]{8,}$/;

    if (!password.match(passwordValidation)) {
        alert("Lösenordet måste innehålla minst en stor bokstav & minst en siffra & vara minst 8 tecken långt")
        return false;
    }
    if (password !== verifypassword) {
        alert("Lösenorden måste matcha!")
        return false;
    } else {
        return true;
    }
}

function emailValidation() {
    const validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    // const validRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    const email = document.getElementById('EmailText').value;
    console.log(email)
    if (email.match(validRegex)) {
        return true;
    }
    alert("Ogiltig email!")
    return false;
}

function nameCheck() {
    const name = document.getElementById('NameText').value;
    const lastname = document.getElementById('SurNameText').value;
    var letters = /^[A-ZÅÄÖa-zåäö]+$/;

    console.log(name)
    console.log(lastname)

    if (name.match(letters) && lastname.match(letters)) {
        return true
    }
    alert("Namn och Efternamn måste vara bokstäver")
    return false

    // if (!isNaN(name) && !isNaN(lastname)){
    //     alert("Namn och Efternamn får endast innehålla bokstäver")
    //
    //   return false;
    // } else {
    //     return true
    // }

}


// function validatePersonNumber(input) {
//     // Check valid length & form
//     if (!input) { return false; }
//
//     if (input.indexOf('-') === -1) {
//         if (input.length === 10) {
//             input = input.slice(0, 6) + "-" + input.slice(6);
//         } else {
//             input = input.slice(0, 8) + "-" + input.slice(8);
//         }
//     }
//     if (!input.match(/^(\d{2})(\d{2})(\d{2})\-(\d{4})|(\d{4})(\d{2})(\d{2})\-(\d{4})$/)) {
//         return false
//     }
//
//     // Clean input
//     input = input.replace('-', '');
//     if (input.length === 12) {
//         input = input.substring(2);
//     }
//
//     // Declare variables
//     var d = new Date(((!!RegExp.$1) ? RegExp.$1 : RegExp.$5), (((!!RegExp.$2) ? RegExp.$2 : RegExp.$6)-1), ((!!RegExp.$3) ? RegExp.$3 : RegExp.$7)),
//         sum = 0,
//         numdigits = input.length,
//         parity = numdigits % 2,
//         i,
//         digit;
//
//     // Check valid date
//     if (Object.prototype.toString.call(d) !== "[object Date]" || isNaN(d.getTime()))
//         return false;
//
//     // Check luhn algorithm
//     for (i = 0; i < numdigits; i = i + 1) {
//         digit = parseInt(input.charAt(i), 10);
//         if (i % 2 === parity) { digit *= 2; }
//         if (digit > 9) { digit -= 9; }
//         sum += digit;
//     }
//     return (sum % 10) === 0;
// }

// function validatePersonNumber() {
//
//     let personnummer = document.getElementById('PersonNummerText').value
//
//     // console.log("Personnummer: " + personnummer)
//
//     if (personnummer.length > 13 && personnummer.length < 12) {
//         console.log("För långt elr för kort")
//         alert("Felaktigt personnummer")
//         return false
//     }
//     if (personnummer.indexOf('-') === -1) {
//         if (personnummer.length === 10) {
//             personnummer = personnummer.slice(0, 6) + "-" + personnummer.slice(6);
//         } else {
//             personnummer = personnummer.slice(0, 8) + "-" + personnummer.slice(8);
//         }
//         // console.log("Personnummer: " + personnummer)
//
//         if (!personnummer.match(/^(\d{2})(\d{2})(\d{2})\-(\d{4})|(\d{4})(\d{2})(\d{2})\-(\d{4})$/)) {
//             console.log("Felaktigt regex")
//             alert("Felaktigt personnummer")
//             return false
//         }
//
//         personnummer = personnummer.replace('-', '');
//         if (personnummer.length === 12) {
//             personnummer= personnummer.substring(2);
//         }
//
//         var d = new Date(((!!RegExp.$1) ? RegExp.$1 : RegExp.$5), (((!!RegExp.$2) ? RegExp.$2 : RegExp.$6) - 1), ((!!RegExp.$3) ? RegExp.$3 : RegExp.$7)),
//             sum = 0,
//             numdigits = personnummer.length,
//             parity = numdigits % 2,
//             i,
//             digit;
//
//         // console.log("variabel d: " + d)
//         // console.log("numdigits:" + numdigits)
//         // console.log("parity:" + parity)
//
//         if (Object.prototype.toString.call(d) !== "[object Date]" || isNaN(d.getTime())) {
//             // console.log("Felaktigt datum")
//             alert("Felaktigt personnummer")
//             return false;
//         }
//
//         for (i = 0; i < numdigits; i = i + 1) {
//             digit = parseInt(personnummer.charAt(i), 10);
//             if (i % 2 === parity) {
//                 digit *= 2;
//             }
//             if (digit > 9) {
//                 digit -= 9;
//             }
//             sum += digit;
//             console.log("sum" + sum);
//         }
//         console.log("sum" + sum);
//         // console.log("digit" + digit)
//         if ((sum % 10 === 0)) {
//             console.log("test")
//             console.log("sum" + sum)
//             return true;
//
//         } else
//             console.log("Summan är felaktig")
//             alert("Felaktigt personnummer")
//             return false;
//         }
//
//         // return (sum % 10) === 0;
// }

//TODO DENNA FUNKAR
//----------------------------------------------------------------------------------------------------------------------
function validatePersonNumber() {

    let personnummer = document.getElementById('PersonNummerText').value;
    console.log(personnummer)

    //195505055555
    var personnummerRegex = /^\d{6,8}[-|(\s)]?\d{4}$/;
    //19550505-5555
    // var personnummerRegex = /^\d{6,8}[-|(\s)]?\d{4}$/;

    // var personnummerRegex2 = /^\d{6,8}[-|(\s)]{0,1}\d{4}$/;

    var personnummerRegex3 = /^\d{6,8}[-|(\s)]?\d{4}$/.test("19550505-5555");

    var personnummerRegex4 = /^(\d{6}|\d{8})[-|(\s)]{0,1}\d{4}$/;


    var personnummerRegex5 = /^(19|20)?(\d{6}(-|\s)\d{4}|(?!19|20)\d{10})$/;

    //man kan lägga till plus tecken för dem som är över 100
    var personnummerRegexOver100 = /^(19|20)?(\d{6}([-+]|\s)\d{4}|(?!19|20)\d{10})$/;

    console.log(personnummer)

    if (personnummer.match(personnummerRegex)) {
        return true
    } else {
        alert("Felaktigt personnummer")
        return false
    }

}

//----------------------------------------------------------------------------------------------------------------------

// function validatePersonNumber(sPNum) {
//     var numbers = sPNum.match(/^(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)$/);
//     var checkSum = 0;
//
//     var d = new Date();
//     if (!isDate(sPNum.substring(0,4),sPNum.substring(4,6),sPNum.substring(6,8))) {
//         alert("Personnumret " + sPNum.substring(0,8) + " är inte korrekt.");
//         return false;
//     }
//
//     if (numbers == null) {
//         return false;
//     }
//
//     var n;
//     for (var i = 3; i <= 12; i++) {
//         n=parseInt(numbers[i]);
//         if (i % 2 === 0) {
//             checkSum+=n;
//         } else {
//             checkSum+=(n*2)%9+Math.floor(n/9)*9
//         }
//     }
//
//     if (checkSum%10===0) {
//         return true;
//     }
//         return false;
// }

// function validatePersonNumber() {
//
//     //giltigt = 20000101-3200
//     //ogiltigt = 20000101-3201
//
//     var personnummer = document.getElementById('PersonNummerText').value
//     console.log(personnummer)
//     // var numbers = personnummer.match(/^(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)(\d)$/);
//     personnummer = personnummer.replace('-', '');
//
//     var numbers = personnummer.match(/^\d{6,8}[-|(\s)]?\d{4}$/);
//
//
//     var checkSum = 0;
//
//     var d = new Date();
//     if (!isDate(personnummer.substring(0,4),personnummer.substring(4,6),personnummer.substring(6,8))) {
//         alert("Personnumret " + personnummer.substring(0,8) + " är inte korrekt.");
//         return false;
//     }
//
//     if (numbers == null) {
//         return false;
//     }
//
//     var n;
//     for (var i = 3; i <= 12; i++) {
//         n=parseInt(numbers[i]);
//         if (i % 2 === 0) {
//             checkSum+=n;
//         } else {
//             checkSum+=(n*2)%9+Math.floor(n/9)*9
//         }
//     }
//
//     if (checkSum%10===0) {
//         return true;
//     }
//     return false;
// }
//
// function getYear(y) {
// return (y < 1000) ? y + 1900 : y;
// }
//
// function isDate(year, month, day) {
// month = month - 1;
// var tmpDate = new Date(year,month,day);
// if ( (getYear(tmpDate.getFullYear()) === year) &&
//     (month === tmpDate.getMonth()) &&
//     (day === tmpDate.getDate()) )
//     return true;
// else
//     return false;
// }
