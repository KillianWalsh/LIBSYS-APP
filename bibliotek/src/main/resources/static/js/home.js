function hideLoginDivUser() {
    document.getElementById("iFrame").src = ".\\UserProfil\\UserProfil.html";
    document.getElementById("iFrame").style.display = "block";
    document.getElementById("UserLoginButton").setAttribute("disabled", "");
    document.getElementById("AdminLoginButton").setAttribute("disabled", "");
    document.getElementById("LibrarianLoginButton").setAttribute("disabled", "");
    document.getElementById("LogoutButton").style.display = "block";
    document.getElementById("UserProfilButton").style.display = "inline";

}

function hideLoginDivAdmin() {
    document.getElementById("iFrame").src = ".\\News\\news.html";
    document.getElementById("iFrame").style.display = "block";
    document.getElementById("UserLoginButton").setAttribute("disabled", "");
    document.getElementById("AdminLoginButton").setAttribute("disabled", "");
    document.getElementById("LibrarianLoginButton").setAttribute("disabled", "");
    let list = document.getElementsByClassName("AdminButton");
    for (var i = 0; i < list.length; i++) {
        list.item(i).style.display = "inline";
    }
    document.getElementById("LogoutButton").style.display = "block";
}

function logout() {
    document.getElementById("LogoutButton").style.display = "none";
    document.getElementById("iFrame").src = '.\\Logout\\Logout.html';
    document.getElementById("UserLoginButton").removeAttribute("disabled");
    document.getElementById("AdminLoginButton").removeAttribute("disabled");
    document.getElementById("LibrarianLoginButton").removeAttribute("disabled");
    document.getElementById("UserProfilButton").style.display = "none";
    let list = document.getElementsByClassName("AdminButton");
    for (var i = 0; i < list.length; i++) {
        list.item(i).style.display = "none";
    }
}

function showIFrame() {
    document.getElementById("iFrame").style.display = "block";
    // document.getElementById("LoginElement").style.display = "none";
}

function AdminNavBarButton() {
    document.getElementById("iFrame").style.display = "block";
}

function UserNavBarButton() {
    document.getElementById("iFrame").style.display = "block";
}

function ContactButton() {
    document.getElementById("iFrame").src = ".\\Contact\\contact.html";
}