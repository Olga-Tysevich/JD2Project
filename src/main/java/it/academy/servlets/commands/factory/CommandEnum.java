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
import it.academy.servlets.commands.impl.get.tables.GetServiceCenters;
import it.academy.servlets.commands.impl.add.AddSparePart;
import it.academy.servlets.commands.impl.change.ChangeSparePart;
import it.academy.servlets.commands.impl.get.forms.*;

public enum CommandEnum {
    SHOW_MAIN_PAGE(new ShowMainPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    GET_NEW_ACCOUNT(new GetNewAccount()),
    GET_ACCOUNT(new GetAccount()),
    DELETE_ACCOUNT(new DeleteAccount()),
    ADD_ACCOUNT(new AddAccount()),
    CHANGE_ACCOUNT(new ChangeAccount()),
    GET_ACCOUNT_TABLE(new GetAccountTable()),
    GET_SERVICE_CENTERS(new GetServiceCenters()),
    GET_NEW_SERVICE_CENTER(new GetNewServiceCenter()),
    GET_SERVICE_CENTER(new GetServiceCenter()),
    ADD_SERVICE_CENTER(new AddServiceCenter()),
    DELETE_SERVICE_CENTER(new DeleteServiceCenter()),
    CHANGE_SERVICE_CENTER(new ChangeServiceCenter()),
    GET_BRANDS(new GetBrands()),
    ADD_BRAND(new AddBrand()),
    DELETE_BRAND(new DeleteObject()),
    GET_BRAND(new GetBrand()),
    CHANGE_BRAND(new ChangeBrand()),
    SHOW_DEVICE_TYPE_TABLE(new GetDeviceTypeTable()),
    ADD_DEVICE_TYPE(new AddDeviceType()),
    DELETE_DEVICE_TYPE(new DeleteObject()),
    SHOW_DEVICE_TYPE(new ShowDeviceType()),
    CHANGE_DEVICE_TYPE(new ChangeDeviceType()),
    GET_MODELS(new GetModels()),
    ADD_MODEL(new AddModel()),
    DELETE_MODEL(new DeleteObject()),
    GET_MODEL(new GetModel()),
    GET_NEW_MODEL(new GetNewModel()),
    CHANGE_MODEL(new ChangeModel()),
    GET_SPARE_PARTS(new GetSpareParts()),
    SHOW_SPARE_PART_ORDER_TABLE(new GetSpareParts()),
    ADD_SPARE_PART(new AddSparePart()),
    DELETE_SPARE_PART(new DeleteObject()),
    SHOW_NEW_SPARE_PART(new ShowSparePart()),
    SHOW_SPARE_PART(new ShowSparePart()),
    CHANGE_SPARE_PART(new ChangeSparePart()),
    GET_NEW_SPARE_PART_ORDER(new GetNewSparePartOrder()),
    GET_SPARE_PART_ORDER(new GetSparePartOrder()),
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
    GET_REPAIRS_BY_STATUS(new GetRepairsByStatus()),
    COMPLETE_REPAIR(new CompleteRepair());


    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
