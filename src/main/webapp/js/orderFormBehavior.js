function addRowToTable(qualifiedName, value) {
    let selectedSparePart = document.querySelector('select[name="id"]');
    let sparePartId = document.querySelector('select[name="id"]').value;
    let quantity = document.getElementById("quantity_id").value;

    let selectedOption = selectedSparePart.options[selectedSparePart.selectedIndex];

    let orderTable = document.getElementById("order_table");
    let newRow = orderTable.insertRow();
    newRow.setAttribute('class', 'spare_part_input');

    let cell1 = newRow.insertCell(0);
    cell1.setAttribute('name', 'spare_part_name')

    let cell2 = newRow.insertCell(1);

    let sparePart = document.createElement('input');
    let spName = selectedOption.textContent;
    sparePart.setAttribute('type', 'text');
    sparePart.setAttribute('name', 'spare_part_name');
    sparePart.setAttribute('value', spName);
    sparePart.setAttribute('disabled', value);

    let sparePartQuantity = document.createElement('input');
    sparePartQuantity.setAttribute('type', 'number');
    sparePartQuantity.setAttribute('name', 'quantity');
    sparePartQuantity.setAttribute('value', quantity);

    let id = document.createElement('input');
    id.setAttribute('type', 'hidden');
    id.setAttribute('name', 'spare_part_id');
    id.setAttribute('value', sparePartId);


    cell1.appendChild(sparePart);
    cell1.appendChild(id);
    cell2.appendChild(sparePartQuantity);
}

document.getElementById('add_id').onclick = function() {
    addRowToTable();
};

function send() {

    let orderTable = document.getElementById("addSparePartOrder");
    console.log("table " +orderTable);
    let sparePartInputs = document.getElementsByClassName('spare_part_input');
    console.log("input  " +sparePartInputs);
    console.log("input  " +sparePartInputs.length);
    console.log("input  " +sparePartInputs.item(0));
    let keyValueMap = [];

    for (let i = 0; i < sparePartInputs.length; i++) {
        let element = sparePartInputs[i];
        let map = {};
        let id = element.querySelector('input[type="hidden"]').value;
        let nameInput = element.querySelector('input[type="text"]').value;
        let quantityInput = element.querySelector('input[type="number"]').value;
        map['id'] = id;
        map['name'] = nameInput;
        map['quantity'] = quantityInput;
        keyValueMap.push(map);
    }

    let hiddenField = document.createElement('input');
    hiddenField.type = 'hidden';
    hiddenField.name = 'order_data';
    hiddenField.value = JSON.stringify(keyValueMap);
    orderTable.appendChild(hiddenField);
    orderTable.submit()
}
