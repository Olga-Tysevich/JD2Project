package it.academy.servlets.commands.factory;

import it.academy.servlets.commands.*;
import it.academy.servlets.commands.impl.add.AddBrand;
import it.academy.servlets.commands.impl.show.forms.ShowSparePartForm;
import it.academy.servlets.commands.impl.update.UpdateRepair;
import it.academy.servlets.commands.impl.update.UpdateBrand;
import it.academy.servlets.commands.impl.add.AddDeviceType;
import it.academy.servlets.commands.impl.update.UpdateDeviceType;
import it.academy.servlets.commands.impl.delete.*;
import it.academy.servlets.commands.impl.show.forms.formComponents.FindModelsForRepair;
import it.academy.servlets.commands.impl.show.forms.ShowModelForm;
import it.academy.servlets.commands.impl.login.LogOutCommand;
import it.academy.servlets.commands.impl.login.LoginCommand;
import it.academy.servlets.commands.impl.add.*;
import it.academy.servlets.commands.impl.update.*;
import it.academy.servlets.commands.impl.update.UpdateModel;
import it.academy.servlets.commands.impl.show.ShowMainPageCommand;
import it.academy.servlets.commands.impl.show.tables.*;
import it.academy.servlets.commands.impl.add.AddServiceCenter;
import it.academy.servlets.commands.impl.show.forms.ShowUpdateServiceCenter;
import it.academy.servlets.commands.impl.show.tables.ShowServiceCenterTable;
import it.academy.servlets.commands.impl.add.AddSparePart;
import it.academy.servlets.commands.impl.update.UpdateSparePart;
import it.academy.servlets.commands.impl.show.forms.*;

import static it.academy.utils.constants.JSPConstant.*;

public enum CommandEnum {
    SHOW_MAIN_PAGE(new ShowMainPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogOutCommand()),
    SHOW_ADD_ACCOUNT(new ShowAddAccount()),
    SHOW_UPDATE_ACCOUNT(new ShowUpdateAccount()),
    DELETE_ACCOUNT(new DeleteAccount()),
    ADD_ACCOUNT(new AddAccount()),
    UPDATE_ACCOUNT(new UpdateAccount()),
    SHOW_ACCOUNT_TABLE(new ShowAccountTable()),
    SHOW_SERVICE_CENTER_TABLE(new ShowServiceCenterTable()),
    SHOW_ADD_SERVICE_CENTER(new ShowForm(ADD_SERVICE_CENTER_PAGE_PATH)),
    SHOW_UPDATE_SERVICE_CENTER(new ShowUpdateServiceCenter()),
    ADD_SERVICE_CENTER(new AddServiceCenter()),
    DELETE_SERVICE_CENTER(new DeleteServiceCenter()),
    UPDATE_SERVICE_CENTER(new UpdateServiceCenter()),
    SHOW_BRAND_TABLE(new ShowBrandTable()),
    ADD_BRAND(new AddBrand()),
    DELETE_BRAND(new DeleteBrand()),
    SHOW_ADD_BRAND(new ShowForm(ADD_BRAND_PAGE_PATH)),
    SHOW_UPDATE_BRAND(new ShowUpdateBrand()),
    UPDATE_BRAND(new UpdateBrand()),
    SHOW_DEVICE_TYPE_TABLE(new ShowDeviceTypeTable()),
    ADD_DEVICE_TYPE(new AddDeviceType()),
    DELETE_DEVICE_TYPE(new DeleteDeviceType()),
    SHOW_ADD_DEVICE_TYPE(new ShowForm(ADD_DEVICE_TYPE_PAGE_PATH)),
    SHOW_UPDATE_DEVICE_TYPE(new ShowUpdateDeviceType()),
    UPDATE_DEVICE_TYPE(new UpdateDeviceType()),
    SHOW_MODEL_TABLE(new ShowModelTable()),
    ADD_MODEL(new AddModel()),
    DELETE_MODEL(new DeleteModel()),
    SHOW_UPDATE_MODEL(new ShowUpdateModel()),
    SHOW_ADD_MODEL(new ShowModelForm(ADD_MODEL_PAGE_PATH)),
    UPDATE_MODEL(new UpdateModel()),
    SHOW_SPARE_PART_TABLE(new ShowSparePartTable()),
    ADD_SPARE_PART(new AddSparePart()),
    DELETE_SPARE_PART(new DeleteSparePart()),
    SHOW_ADD_SPARE_PART(new ShowSparePartForm(ADD_SPARE_PART_PAGE_PATH)),
    SHOW_UPDATE_SPARE_PART(new ShowUpdateSparePart()),
    SHOW_SPARE_PART(new ShowUpdateSparePart()),
    UPDATE_SPARE_PART(new UpdateSparePart()),
    GET_NEW_SPARE_PART_ORDER(new GetNewSparePartOrder()),
    SHOW_SPARE_PART_ORDER(new ShowUpdateSparePartOrder()),
    ADD_SPARE_PART_ORDER(new AddSparePartOrder()),
    DELETE_SPARE_PART_ORDER(new DeleteSparePartOrder()),
    UPDATE_SPARE_PART_ORDER(new UpdateSparePartOrder()),
    SHOW_SPARE_PART_ORDER_TABLE(new ShowSparePartOrderTable()),
    SHOW_REPAIR_TYPE_TABLE(new ShowRepairTypeTable()),
    ADD_REPAIR_TYPE(new AddRepairType()),
    DELETE_REPAIR_TYPE(new DeleteRepairType()),
    SHOW_ADD_REPAIR_TYPE(new ShowForm(ADD_REPAIR_TYPE_PAGE_PATH)),
    SHOW_UPDATE_REPAIR_TYPE(new ShowUpdateRepairType()),
    UPDATE_REPAIR_TYPE(new UpdateRepairType()),
    GET_NEW_REPAIR(new GetNewRepair()),
    SHOW_UPDATE_REPAIR(new ShowUpdateRepair()),
    FIND_MODELS_FOR_REPAIR(new FindModelsForRepair()),
    ADD_REPAIR(new AddRepair()),
    UPDATE_REPAIR(new UpdateRepair()),
    SHOW_REPAIR_TABLE(new ShowRepairTable()),
    COMPLETE_REPAIR(new CompleteRepair());


    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
