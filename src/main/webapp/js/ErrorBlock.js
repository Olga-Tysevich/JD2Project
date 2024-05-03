const errorField = document.getElementById("error");
document.addEventListener("DOMContentLoaded", setVisibility);

function setVisibility() {
    console.log("field : " + errorField);
    console.log("error field text: " + errorField.textContent);
    console.log("error field text equals : " + errorField.textContent.valueOf() === "");
    errorField.style.display = errorField.textContent.valueOf() === "" ? "none" : "block";
}


