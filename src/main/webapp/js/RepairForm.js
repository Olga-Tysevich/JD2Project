document.getElementById('select_send').addEventListener('change', function() {
    let command = document.getElementById('command_id');
    console.log('command ' + command);
    command.value = "FIND_MODELS_FOR_REPAIR";
    console.log('command val ' + command.value);
    document.getElementById('form_for_submit').submit();
});
