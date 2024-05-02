package it.academy.servlets.commands.factory;

import it.academy.servlets.commands.*;
import it.academy.servlets.commands.impl.add.AddBrand;
import it.academy.servlets.commands.impl.change.ChangeRepair;
import it.academy.servlets.commands.impl.change.ChangeBrand;
import it.academy.servlets.commands.impl.add.AddDeviceType;
import it.academy.servlets.commands.impl.change.ChangeDeviceType;
import it.academy.servlets.commands.impl.delete.*;
import it.academy.servlets.commands.impl.login.LogOutCommand;
import it.academy.servlets.commands.impl.login.LoginCommand;
import it.academy.servlets.commands.impl.add.*;
import it.academy.servlets.commands.impl.change.*;
import it.academy.servlets.commands.impl.change.ChangeModel;
import it.academy.servlets.commands.impl.get.ShowMainPageCommand;
import it.academy.servlets.commands.impl.get.tables.*;
import it.academy.servlets.commands.impl.add.AddServiceCenter;
import it.academy.servlets.commands.impl.get.forms.GetServiceCenter;
import it.academy.servlets.commands.impl.get.tables.ShowServiceCenterTable;
import it.academy.servlets.commands.impl.add.AddSparePart;
import it.academy.servlets.commands.impl.change.ChangeSparePart;
import it.academy.servlets.commands.impl.get.forms.*;

public enum CommandEnum {
    SHOW_MAIN_PAGE(new ShowMainPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    GET_NEW_ACCOUNT(new GetAccount()),
    GET_ACCOUNT(new GetAccount()),
    DELETE_ACCOUNT(new DeleteObject()),
    ADD_ACCOUNT(new AddAccount()),
    CHANGE_ACCOUNT(new ChangeAccount()),
    SHOW_ACCOUNT_TABLE(new ShowAccountTable()),
    SHOW_SERVICE_CENTER_TABLE(new ShowServiceCenterTable()),
    GET_SERVICE_CENTER(new GetServiceCenter()),
    ADD_SERVICE_CENTER(new AddServiceCenter()),
    DELETE_SERVICE_CENTER(new DeleteObject()),
    CHANGE_SERVICE_CENTER(new ChangeServiceCenter()),
    SHOW_BRAND_TABLE(new ShowBrandTable()),
    ADD_BRAND(new AddBrand()),
    DELETE_BRAND(new DeleteObject()),
    SHOW_BRAND(new ShowBrand()),
    CHANGE_BRAND(new ChangeBrand()),
    SHOW_DEVICE_TYPE_TABLE(new GetDeviceTypeTable()),
    ADD_DEVICE_TYPE(new AddDeviceType()),
    DELETE_DEVICE_TYPE(new DeleteObject()),
    SHOW_DEVICE_TYPE(new ShowDeviceType()),
    CHANGE_DEVICE_TYPE(new ChangeDeviceType()),
    GET_MODELS(new GetModels()),
    ADD_MODEL(new AddModel()),
    DELETE_MODEL(new DeleteObject()),
    SHOW_MODEL(new ShowModel()),
    SHOW_NEW_MODEL(new ShowModel()),
    CHANGE_MODEL(new ChangeModel()),
    SHOW_SPARE_PART_TABLE(new ShowSparePartTable()),
    SHOW_SPARE_PART_ORDER_TABLE(new ShowSparePartTable()),
    ADD_SPARE_PART(new AddSparePart()),
    DELETE_SPARE_PART(new DeleteObject()),
    SHOW_NEW_SPARE_PART(new ShowSparePart()),
    SHOW_SPARE_PART(new ShowSparePart()),
    CHANGE_SPARE_PART(new ChangeSparePart()),
    SHOW_SPARE_PART_ORDER(new ShowSparePartOrder()),
    ADD_SPARE_PART_ORDER(new AddSparePartOrder()),
    DELETE_SPARE_PART_ORDER(new DeleteObject()),
    CHANGE_SPARE_PART_ORDER(new ChangeSparePartOrder()),
    GET_REPAIR_TYPE_TABLE(new GetRepairTypeTable()),
    ADD_REPAIR_TYPE(new AddRepairType()),
    DELETE_REPAIR_TYPE(new DeleteObject()),
    SHOW_REPAIR_TYPE(new ShowRepairType()),
    CHANGE_REPAIR_TYPE(new ChangeRepairType()),
    GET_NEW_REPAIR(new GetNewRepair()),
    GET_REPAIR(new GetRepair()),
    FIND_MODELS_FOR_REPAIR(new FindModelsForRepair()),
    ADD_REPAIR(new AddRepair()),
    CHANGE_REPAIR(new ChangeRepair()),
    GET_REPAIRS(new GetRepairs()),
    GET_REPAIRS_BY_FILTER(new GetRepairsByFilter()),
    GET_REPAIRS_BY_STATUS(new GetRepairsByStatus());


    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
