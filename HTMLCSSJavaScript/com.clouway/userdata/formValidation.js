window.onload = init;

function init() {
    document.getElementById("send").onclick = validateForm;

    document.getElementById("reset").onclick = clearDisplay;
}
function clearDisplay() {

}

function validateForm() {
    var listTextField = document.getElementsByClassName("textField");


   for(var i = 0; i < listTextField.length; i++) {
       var elem = listTextField.item(i);

       if(elem.name == "confirmPassword") {
            confirmPassword();
           continue;
       }

       if(elem.value.length == 0) {
           alert("There are empty fields!");
       } else {
           isValid(elem);
       }
   }
}

function isValid(elem) {
    var regex = elem.getAttribute("patt");

    var errorElement = document.getElementById(elem.name + "Message");

    var val = elem.value;

    var valid = val.search(regex);
    showMessage(valid, errorElement);

}

function showMessage(valid, errorElement) {
    if(valid == -1) {
        errorElement.className = "red";
        errorElement.innerHTML = "wrong";
    } else {
        errorElement.className = "green";
        errorElement.innerHTML = "" +
            "valid";

    }
}

function confirmPassword() {
    var pass1 = document.getElementById("password");
    var pass2 = document.getElementById("confirmPassword");
    var elementError = document.getElementById("confirmPasswordMessage");

    if(pass1.value == pass2.value){
        document.getElementById("confirmPasswordMessage").className = "green";
        elementError.innerHTML = "valid";
    } else {
        document.getElementById("confirmPasswordMessage").className = "red";
        elementError.innerHTML = "wrong";
    }
}

function onClickTextField(elem, message) {
     document.getElementById(message).className = "message";
    document.getElementById(message).innerHTML = elem.getAttribute("data-desc");
}