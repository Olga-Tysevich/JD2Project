package it.academy.servlets.managers;

import it.academy.servlets.commands.*;
import it.academy.servlets.commands.impl.add.AddRepair;
import it.academy.servlets.commands.impl.add.AddRepairType;
import it.academy.servlets.commands.impl.add.AddSparePart;
import it.academy.servlets.commands.impl.change.*;
import it.academy.servlets.commands.impl.add.AddSparePartOrder;
import it.academy.servlets.commands.impl.delete.DeleteSparePartOrder;
import it.academy.servlets.commands.impl.forms.*;
import it.academy.servlets.commands.impl.lists.ShowBrandList;
import it.academy.servlets.commands.impl.lists.ShowModelList;
import it.academy.servlets.commands.impl.ShowPageCommand;
import it.academy.servlets.commands.impl.lists.ShowRepairTypeList;
import it.academy.servlets.commands.impl.tables.ShowRepairTable;
import it.academy.servlets.commands.impl.tables.ShowRepairTypeTable;
import it.academy.servlets.commands.impl.tables.ShowSparePartOrdersTable;
import it.academy.servlets.commands.impl.tables.ShowSparePartTable;

import static it.academy.utils.Constants.*;

public enum CommandEnum {
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
    CHANGE_SPARE_PART(new ChangeSparePart());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
