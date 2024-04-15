package it.academy.servlets.commands.factory;

import it.academy.servlets.commands.*;
import it.academy.servlets.commands.impl.add.AddBrand;
import it.academy.servlets.commands.impl.change.ChangeRepair;
import it.academy.servlets.commands.impl.change.ChangeBrand;
import it.academy.servlets.commands.impl.add.AddDeviceType;
import it.academy.servlets.commands.impl.change.ChangeDeviceType;
import it.academy.servlets.commands.impl.login.LoginCommand;
import it.academy.servlets.commands.impl.add.*;
import it.academy.servlets.commands.impl.change.*;
import it.academy.servlets.commands.impl.delete.DeleteSparePartOrder;
import it.academy.servlets.commands.impl.lists.ShowRepairTypeList;
import it.academy.servlets.commands.impl.change.ChangeModel;
import it.academy.servlets.commands.impl.show.tables.*;
import it.academy.servlets.commands.impl.add.AddServiceCenter;
import it.academy.servlets.commands.impl.change.ChangeServiceCenter;
import it.academy.servlets.commands.impl.show.forms.ShowServiceCenter;
import it.academy.servlets.commands.impl.show.tables.ShowServiceCenterTable;
import it.academy.servlets.commands.impl.add.AddSparePart;
import it.academy.servlets.commands.impl.change.ChangeSparePart;
import it.academy.servlets.commands.impl.show.forms.*;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    SHOW_NEW_ACCOUNT(new ShowNewAccount()),
    SHOW_ACCOUNT(new ShowAccount()),
    ADD_ACCOUNT(new AddAccount()),
    CHANGE_ACCOUNT(new ChangeAccount()),
    SHOW_ACCOUNT_TABLE(new ShowAccountTable()),
    SHOW_SERVICE_CENTER_TABLE(new ShowServiceCenterTable()),
    SHOW_SERVICE_CENTER(new ShowServiceCenter()),
    ADD_SERVICE_CENTER(new AddServiceCenter()),
    SHOW_BRAND_TABLE(new ShowBrandTable()),
    ADD_BRAND(new AddBrand()),
    SHOW_BRAND(new ShowBrand()),
    CHANGE_BRAND(new ChangeBrand()),
    SHOW_DEVICE_TYPE_TABLE(new ShowDeviceTypeTable()),
    ADD_DEVICE_TYPE(new AddDeviceType()),
    SHOW_DEVICE_TYPE(new ShowDeviceType()),
    CHANGE_DEVICE_TYPE(new ChangeDeviceType()),
    SHOW_MODEL_TABLE(new ShowModelTable()),
    ADD_MODEL(new AddModel()),
    SHOW_MODEL(new ShowModel()),
    CHANGE_MODEL(new ChangeModel()),
    SHOW_SPARE_PART_TABLE(new ShowSparePartTable()),
    ADD_SPARE_PART(new AddSparePart()),
    SHOW_SPARE_PART_FORM(new ShowSparePart()),
    CHANGE_SPARE_PART(new ChangeSparePart()),
    SHOW_REPAIR(new ShowRepair()),
    CHANGE_REPAIR(new ChangeRepair()),
    SHOW_REPAIR_TABLE(new ShowRepairTable()),

//    OPEN_PAGE(new ShowPageCommand(MAIN_PAGE_PATH)),
    SHOW_CONFIRMED_REPAIR(new ShowConfirmedRepair()),
//    SHOW_ORDER_SPARE_PART(new ShowOrderSparePart()),
//    ADD_SPARE_PART_ORDER(new AddSparePartOrder()),
    CHANGE_SPARE_PART_ORDER(new ChangeSparePartOrder()),
    DELETE_SPARE_PART_ORDER(new DeleteSparePartOrder()),
    SHOW_REPAIR_TYPE_LIST(new ShowRepairTypeList()),
    COMPLETE_REPAIR(new CompleteRepair()),
    SHOW_REPAIR_TYPE_TABLE(new ShowRepairTypeTable()),
    ADD_REPAIR_TYPE(new AddRepairType()),
    CHANGE_REPAIR_TYPE(new ChangeRepairType()),
    SHOW_REPAIR_TYPE(new ShowRepairType()),
    SHOW_SPARE_PART_ORDERS_TABLE(new ShowSparePartOrdersTable()),

//    DELETE_SPARE_PART(new DeleteSparePart()),
//    SHOW_MODEL(new ShowModel()),

    CHANGE_SERVICE_CENTER(new ChangeServiceCenter());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}