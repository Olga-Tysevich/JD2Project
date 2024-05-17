function addRowToTable() {
    let selectedSparePart = document.querySelector('select[name="id"]');
    let selectedOption = selectedSparePart.options[selectedSparePart.selectedIndex];
    let quantity = document.getElementById("quantity_id").value;

    let orderTable = document.getElementById("order_table");
    let existingRow = Array.from(orderTable.getElementsByClassName('spare_part_input')).find(row => {
        let input = row.querySelector('input[type="text"]');
        console.log(input.value)
        console.log(input.value === selectedOption.textContent)
        return input && input.value === selectedOption.textContent;
    });

    console.log(existingRow)
    if (!existingRow) {
        let newRow = orderTable.insertRow();
        newRow.className = 'spare_part_input';

        let cell1 = newRow.insertCell(0);
        let sparePart = document.createElement('input');
        sparePart.type = 'text';
        sparePart.name = 'spare_part_name';
        sparePart.value = selectedOption.textContent;
        sparePart.disabled = true;

        let id = document.createElement('input');
        id.type = 'hidden';
        id.name = 'spare_part_id';
        id.value = selectedSparePart.value;
        id.disabled = true;

        cell1.appendChild(sparePart);
        cell1.appendChild(id);

        let cell2 = newRow.insertCell(1);
        let sparePartQuantity = document.createElement('input');
        sparePartQuantity.type = 'number';
        sparePartQuantity.name = 'quantity';
        sparePartQuantity.value = quantity;
        sparePartQuantity.disabled = true;
        cell2.appendChild(sparePartQuantity);

        let cell3 = newRow.insertCell(2);
        let deleteButton = document.createElement('button');
        deleteButton.type = 'button';
        deleteButton.id = selectedSparePart.value;
        deleteButton.textContent = '-';

        deleteButton.addEventListener('click', function () {
            const row = this.closest('tr');
            console.log(row);
            row.remove();
        });

        cell3.appendChild(deleteButton);

    } else {
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
    const orderTable = document.getElementById("sparePartOrder_id");
    console.log('order_table' + orderTable);
    const sparePartInputs = document.querySelectorAll('.spare_part_input');
    const keyValueMap = [];

    for (let i = 0; i < sparePartInputs.length; i++) {
        const element = sparePartInputs[i];
        const idInput = element.querySelector('input[type="hidden"]');
        const quantityInput = element.querySelector('input[type="number"]');

        if (idInput && quantityInput) {
            const id = idInput.value;
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
