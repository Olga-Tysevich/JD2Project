const saveButton = document.getElementById('save_btn_id');
const myForm = document.getElementById('form_for_submit');

saveButton.addEventListener('click', function () {
    // Отменяем стандартное поведение формы
    myForm.preventDefault();

    // Получаем данные из формы
    const formData = new FormData(myForm);

    // Отправляем данные на сервер
    fetch('/models', {
        method: 'POST',
        body: formData,
    })
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {
            // Обрабатываем ошибку
            console.error(error);
        });
});

