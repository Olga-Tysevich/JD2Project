// document.getElementById('select_send').addEventListener('change', function() {
//     document.getElementById('repair').submit();
// });

// function addSparePart() {
//     let sparePart = document.getElementById('spare_part_id').getAttribute('value').valueOf();
//     console.log(sparePart)
//
//     let quantity = document.getElementById('quantity_id').getAttribute('value').valueOf();
//     console.log(quantity)
//
//     let table = document.getElementById('order_table');
//     let newRow = table.insertRow();
//     let cell1 = newRow.insertCell(sparePart);
//     let cell2 = newRow.insertCell(1);
//
//     cell1.innerHTML = "Новая ячейка 1";
//     cell2.innerHTML = "Новая ячейка 2";
// }

function addRowToTable(qualifiedName, value) {
    var selectElement = document.querySelector('select[name="id"]');

    var quantity = document.getElementById("quantity_id").value;

    var selectedOption = selectElement.options[selectElement.selectedIndex];

    var orderTable = document.getElementById("order_table");
    var newRow = orderTable.insertRow();
    newRow.setAttribute('class', 'spare_part_input');

    var cell1 = newRow.insertCell(0);
    cell1.setAttribute('name', 'spare_part_name')

    var cell2 = newRow.insertCell(1);

    var sparePart = document.createElement('input');
    var spName = selectedOption.textContent;


    sparePart.setAttribute('type', 'text');
    sparePart.setAttribute('name', 'spare_part_name');
    sparePart.setAttribute('value', spName);
    sparePart.setAttribute('disabled', value);

    var sparePartQuantity = document.createElement('input');
    sparePartQuantity.setAttribute('type', 'number');
    sparePartQuantity.setAttribute('name', 'quantity');
    sparePartQuantity.setAttribute('value', quantity);

    cell1.appendChild(sparePart);
    cell2.appendChild(sparePartQuantity);
}

document.getElementById('add_id').onclick = function() {
    addRowToTable();
};

function send() {

    var orderTable = document.getElementById("addSparePartOrder");
    console.log("table " +orderTable);
    var sparePartInputs = document.getElementsByClassName('spare_part_input');
    console.log("input  " +sparePartInputs);
    console.log("input  " +sparePartInputs.length);
    console.log("input  " +sparePartInputs.item(0));
    var keyValueMap = {};

    // sparePartInputs.forEach(function(element) {
    //     var nameInput = element.querySelector('input[type="text"]');
    //     console.log("tasdsd " + nameInput);
    //     var quantityInput = element.querySelector('input.quantity');
    //     console.log(quantityInput);
    //
    //     var key = nameInput.name;
    //     console.log("12312 " + key);
    //     var value = parseInt(quantityInput.value);
    //     console.log("DED12312 " +value);
    //
    //     keyValueMap[key] = value;
    // });

    for (var i = 0; i < sparePartInputs.length; i++) {
        var element = sparePartInputs[i];

        // Находим вложенные элементы input внутри текущего элемента tr
        var nameInput = element.querySelector('input[type="text"]');
        console.log("nameInput " + nameInput);
        var quantityInput = element.querySelector('input[type="number"]').value;
        console.log("quantityInput" +quantityInput);
        console.log("quantityInput" + quantityInput.value);
        console.log("quantityInput" + quantityInput.name);
        console.log("quantityInput" + quantityInput.class);
        console.log("quantityInput" + quantityInput.type);
        // Получаем значение атрибута name и значение атрибута value
        var key = nameInput.value;
        console.log("12312 " + key);
        var value = parseInt(quantityInput); // Предполагаем, что значение - это число, поэтому используем parseInt
        keyValueMap[key] = value;
        // Добавляем пару ключ-значение в объект
        keyValueMap[key] = value;
    }

    var hiddenField = document.createElement('input');
    hiddenField.type = 'hidden';
    hiddenField.name = 'order_data';
    hiddenField.value = JSON.stringify(keyValueMap);

    orderTable.appendChild(hiddenField);

    orderTable.submit()

}

//
// document.getElementById('submit_id').addEventListener('click', function() {
//     var formData = new FormData();
//     var rows = document.getElementById('data_id');
//     for (var i = 1; i < rows.length; i++) {
//         var row = rows[i];
//         var cells = row.getElementsByTagName('td');
//         formData.append('name[]', cells[0].innerHTML); // добавляем данные из ячейки 1
//         formData.append('quantity[]', cells[1].getElementsByTagName('input')[0].value); // добавляем данные из ячейки 2
//     }
//
//     var xhr = new XMLHttpRequest();
//     xhr.open('POST', '/main', true);
//     xhr.onload = function () {
//         // Обработка ответа от сервера, если требуется
//     };
//     xhr.onerror = function() {
//         // Обработка ошибок
//     };
//     xhr.send(formData);
// });