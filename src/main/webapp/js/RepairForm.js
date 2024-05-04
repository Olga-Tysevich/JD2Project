function getModelsForForm() {
    let commandField = document.getElementById('command_id');
    commandField.value = "FIND_MODELS_FOR_REPAIR";
    document.getElementById('form_for_submit').submit();
}

function showRepairTypes() {
    let repairTypesBlock = document.getElementById('repairTypesBlockId');
    console.log(repairTypesBlock)
    repairTypesBlock.style.display = "flex";
}

function hideRepairTypes() {
    let repairTypesBlock = document.getElementById('repairTypesBlockId');
    repairTypesBlock.style.display = "none";
}


