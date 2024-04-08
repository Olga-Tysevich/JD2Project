document.addEventListener('DOMContentLoaded', function() {
    const orderDepartureDateInputs = document.querySelectorAll('.f-form.departure-date-input');
    const orderDeliveryDateInputs = document.querySelectorAll('.f-form.delivery-date-input');

    function closest(element, selector) {
        while (element && element.nodeType === 1) {
            if (element.matches(selector)) {
                return element;
            }
            element = element.parentNode;
        }
        return null;
    }

    function showChangeOrderDiv(input) {
        const changeOrderDiv = closest(input, '.order-container').querySelector('.f-input.change-order');
        if (changeOrderDiv) {
            changeOrderDiv.style.display = 'block';
        }
    }

    orderDepartureDateInputs.forEach(function(input) {
        input.addEventListener('change', function() {
            showChangeOrderDiv(input);
        });
    });

    orderDeliveryDateInputs.forEach(function(input) {
        input.addEventListener('change', function() {
            showChangeOrderDiv(input);
        });
    });
});

document.addEventListener('DOMContentLoaded', function() {
    let removeButtons = document.querySelectorAll('.choose-button.btn-table.remove');
    console.log(removeButtons)

    removeButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            let form = button.closest('.lr-form');
            let orderId = form.querySelector('input[name="order_id"]').value;
            let repairId = form.querySelector('input[name="repair_id"]').value;
            console.log(orderId)
            console.log(repairId)
            let xhr = new XMLHttpRequest();
            let url = 'repair?command=delete_spare_part_order&&repair_id=' + repairId + '&&order_id=' + orderId;
            xhr.open('GET', url);
            xhr.send();

            xhr.addEventListener('load', function() {
                if (xhr.status === 200) {
                    form.remove();
                }
            });
        });
    });
});