const errorField = document.getElementById("error");
document.addEventListener("DOMContentLoaded", setVisibility);

function setVisibility() {
    errorField.style.display = errorField.textContent.valueOf() === "" ? "none" : "block";
}
