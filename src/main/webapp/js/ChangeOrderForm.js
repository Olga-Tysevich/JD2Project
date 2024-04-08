document.addEventListener('DOMContentLoaded', function() {
    const orderDepartureDateInput = document.getElementById('orderDepartureDate');
    const orderDeliveryDateInput = document.getElementById('orderDeliveryDate');
    const changeOrderDiv = document.querySelector('.f-input.change-order');

    function showChangeOrderDiv() {
        changeOrderDiv.style.display = 'block';
    }

    orderDepartureDateInput.addEventListener('change', function() {
        showChangeOrderDiv();
    });

    orderDeliveryDateInput.addEventListener('change', function() {
        showChangeOrderDiv();
    });
});