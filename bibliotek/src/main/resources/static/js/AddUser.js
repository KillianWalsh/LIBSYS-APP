async function saveUser() {
    if (isFilled()) {
        if ($("#PasswordText").val().localeCompare($("#PasswordVerifyText").val()) == 0) {
            $("#PasswordVerifyText").removeClass('error');
            var data = {
                "firstname": $("#NameText").val(),
                "lastname": $("#SurNameText").val(),
                "email": $("#EmailText").val(),
                "socialNumber": $("#PersonNummerText").val(),
                //maybe encrypt the password here
                "passWord": $("#PasswordVerifyText").val()
            };

            data = JSON.stringify(data);
            const url = 'http://localhost:8080/borrower/save/' + data;

            clearText();

            $.post(url, data, function () {
                alert("User added to database!")
            }, 'json')
            //add error handling here

        } else {
            $("#PasswordVerifyText").addClass('error');
        }
    } else {
        alert("Please fill in all fields!")
    }
}

function clearText() {
    $("#NameText").val("");
    $("#SurNameText").val("");
    $("#EmailText").val("");
    $("#PersonNummerText").val("");
    $("#PasswordText").val("");
    $("#PasswordVerifyText").val("");
}

function isFilled() {
    if ($("#NameText").val() != "" && $("#NameText").val() != "" && $("#EmailText").val() != "" && $("#PersonNummerText").val() != "" && $("#PasswordText").val() != "" && $("#PasswordVerifyText").val() != "") {
        return true;
    } else {
        return false;
    }

}

//if needed to encode the url: encodeURIComponent(URL);