const searchInput = document.getElementById("search_input");
const searchBlock = document.getElementById("search_container");

searchInput.onclick = function (event) {
    event.stopPropagation(); // Остановить событие распространения на родительские элементы
    searchBlock.style.display = "block";
    searchInput.style.display = "none";
};

document.addEventListener('click', function (event) {
    let fInputElements = document.querySelectorAll('.search');

    let isClickInsideSearch = false;
    fInputElements.forEach((div) => {
        if (event.target === div || div.contains(event.target)) {
            isClickInsideSearch = true;
        }
    });

    if (!isClickInsideSearch) {
        searchBlock.style.display = 'none';
        searchInput.style.display = "block";
    }
});