package it.academy.servlets.managers;

import it.academy.servlets.commands.*;
import it.academy.servlets.commands.impl.LoginCommand;
import it.academy.servlets.commands.impl.add.*;
import it.academy.servlets.commands.impl.change.*;
import it.academy.servlets.commands.impl.delete.DeleteSparePart;
import it.academy.servlets.commands.impl.delete.DeleteSparePartOrder;
import it.academy.servlets.commands.impl.forms.*;
import it.academy.servlets.commands.impl.lists.ShowBrandList;
import it.academy.servlets.commands.impl.lists.ShowModelList;
import it.academy.servlets.commands.impl.ShowPageCommand;
import it.academy.servlets.commands.impl.lists.ShowRepairTypeList;
import it.academy.servlets.commands.impl.tables.*;

import static it.academy.utils.Constants.*;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    SHOW_ACCOUNT(new ShowAccount()),
    ADD_ACCOUNT(new AddAccount()),
    CHANGE_ACCOUNT(new ChangeAccount()),
    SHOW_ACCOUNT_TABLE(new ShowAccountTable()),
    OPEN_PAGE(new ShowPageCommand(MAIN_PAGE_PATH)),
    SHOW_BRAND_LIST(new ShowBrandList()),
    SHOW_MODEL_LIST(new ShowModelList()),
    SHOW_REPAIR(new ShowRepair()),
    ADD_REPAIR(new AddRepair()),
    SHOW_CONFIRMED_REPAIR(new ShowConfirmedRepair()),
    CHANGE_REPAIR(new ChangeRepair()),
    SHOW_REPAIR_TABLE(new ShowRepairTable()),
    SHOW_ORDER_SPARE_PART(new ShowOrderSparePart()),
    ADD_SPARE_PART_ORDER(new AddSparePartOrder()),
    CHANGE_SPARE_PART_ORDER(new ChangeSparePartOrder()),
    DELETE_SPARE_PART_ORDER(new DeleteSparePartOrder()),
    SHOW_REPAIR_TYPE_LIST(new ShowRepairTypeList()),
    COMPLETE_REPAIR(new CompleteRepair()),
    SHOW_REPAIR_TYPE_TABLE(new ShowRepairTypeTable()),
    ADD_REPAIR_TYPE(new AddRepairType()),
    CHANGE_REPAIR_TYPE(new ChangeRepairType()),
    SHOW_REPAIR_TYPE(new ShowRepairType()),
    SHOW_SPARE_PART_ORDERS_TABLE(new ShowSparePartOrdersTable()),
    SHOW_SPARE_PART_TABLE(new ShowSparePartTable()),
    ADD_SPARE_PART(new AddSparePart()),
    SHOW_SPARE_PART_FORM(new ShowSparePartForm()),
    CHANGE_SPARE_PART(new ChangeSparePart()),
    DELETE_SPARE_PART(new DeleteSparePart()),
    SHOW_DEVICE_TYPE_TABLE(new ShowDeviceTypeTable()),
    ADD_DEVICE_TYPE(new AddDeviceType()),
    SHOW_DEVICE_TYPE(new ShowDeviceType()),
    CHANGE_DEVICE_TYPE(new ChangeDeviceType()),
    SHOW_BRAND_TABLE(new ShowBrandTable()),
    ADD_BRAND(new AddBrand()),
    SHOW_BRAND(new ShowBrand()),
    CHANGE_BRAND(new ChangeBrand()),
    SHOW_MODEL_TABLE(new ShowModelTable()),
    ADD_MODEL(new AddModel()),
    CHANGE_MODEL(new ChangeModel()),
    SHOW_MODEL(new ShowModel()),
    SHOW_REPAIR_WORKSHOP_TABLE(new ShowRepairWorkshopTable()),
    SHOW_REPAIR_WORKSHOP(new ShowRepairWorkshop()),
    ADD_REPAIR_WORKSHOP(new AddRepairWorkshop()),
    CHANGE_REPAIR_WORKSHOP(new ChangeRepairWorkshop());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
