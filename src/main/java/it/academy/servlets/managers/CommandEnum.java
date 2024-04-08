package it.academy.servlets.managers;

import it.academy.servlets.commands.*;
import it.academy.servlets.commands.impl.add.AddRepair;
import it.academy.servlets.commands.impl.change.ChangeRepair;
import it.academy.servlets.commands.impl.add.AddSparePartOrder;
import it.academy.servlets.commands.impl.change.ChangeSparePartOrder;
import it.academy.servlets.commands.impl.forms.ShowConfirmedRepair;
import it.academy.servlets.commands.impl.forms.ShowOrderSparePart;
import it.academy.servlets.commands.impl.forms.ShowRepair;
import it.academy.servlets.commands.impl.lists.ShowBrandList;
import it.academy.servlets.commands.impl.lists.ShowModelList;
import it.academy.servlets.commands.impl.ShowPageCommand;
import it.academy.servlets.commands.impl.tables.ShowRepairTable;

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
    CHANGE_SPARE_PART_ORDER(new ChangeSparePartOrder());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
