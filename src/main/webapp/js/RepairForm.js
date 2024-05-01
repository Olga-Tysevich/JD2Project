function getModelsForForm() {
    let commandField = document.getElementById('command_id');
    commandField.value = "FIND_MODELS_FOR_REPAIR";
    document.getElementById('form_for_submit').submit();
}


