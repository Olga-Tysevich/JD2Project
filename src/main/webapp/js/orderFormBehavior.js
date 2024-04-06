function addRowToTable() {
    let selectedSparePart = document.querySelector('select[name="id"]');
    let selectedOption = selectedSparePart.options[selectedSparePart.selectedIndex];
    let quantity = document.getElementById("quantity_id").value;

    let orderTable = document.getElementById("order_table");
    let existingRow = orderTable.getElementsByClassName('spare_part_input')[0]; // предполагаем, что у нас уже есть один ряд для запчастей

    if (!existingRow) {
        existingRow = orderTable.insertRow();
        existingRow.className = 'spare_part_input';

        let cell1 = existingRow.insertCell(0);

        let sparePart = document.createElement('input');
        sparePart.type = 'text';
        sparePart.name = 'spare_part_name';
        sparePart.value = selectedOption.textContent;
        sparePart.disabled = true;

        let id = document.createElement('input');
        id.type = 'hidden';
        id.name = 'spare_part_id';
        id.value = selectedSparePart.value;

        cell1.appendChild(sparePart);
        cell1.appendChild(id);

        let cell2 = existingRow.insertCell(1);
        let sparePartQuantity = document.createElement('input');
        sparePartQuantity.type = 'number';
        sparePartQuantity.name = 'quantity';
        sparePartQuantity.value = quantity;
        cell2.appendChild(sparePartQuantity);
    } else {
        // Если ряд уже существует, обновляем его значения
        let sparePart = existingRow.querySelector('input[type="text"]');
        let id = existingRow.querySelector('input[type="hidden"]');
        let sparePartQuantity = existingRow.querySelector('input[type="number"]');
        sparePart.value = selectedOption.textContent;
        id.value = selectedSparePart.value;
        sparePartQuantity.value = quantity;
    }
}

document.getElementById('add_id').onclick = addRowToTable;

function send() {
    const orderTable = document.getElementById("addSparePartOrder");
    const sparePartInputs = document.querySelectorAll('.spare_part_input');
    const keyValueMap = [];

    for (let i = 0; i < sparePartInputs.length; i++) {
        const element = sparePartInputs[i];
        const idInput = element.querySelector('input[type="hidden"]');
        const nameInput = element.querySelector('input[type="text"]');
        const quantityInput = element.querySelector('input[type="number"]');

        // console.log("idInput:", idInput.value);
        // console.log("nameInput:", nameInput.value);
        // console.log("quantityInput:", quantityInput.value);

        if (idInput && nameInput && quantityInput) {
            const id = idInput.value;
            const name = nameInput.value;
            const quantity = quantityInput.value;

            keyValueMap.push({ id, name, quantity });
        }
    }

    const hiddenField = document.createElement('input');
    hiddenField.type = 'hidden';
    hiddenField.name = 'order_data';
    hiddenField.value = JSON.stringify(keyValueMap);
    orderTable.appendChild(hiddenField);
    orderTable.submit();
}
