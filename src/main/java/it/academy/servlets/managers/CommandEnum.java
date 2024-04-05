package it.academy.servlets.managers;

import it.academy.servlets.commands.*;
import it.academy.servlets.commands.add.AddRepair;
import it.academy.servlets.commands.change.ChangeRepair;
import it.academy.servlets.commands.forms.ShowConfirmedRepair;
import it.academy.servlets.commands.forms.ShowRepair;
import it.academy.servlets.commands.lists.ShowBrandList;
import it.academy.servlets.commands.lists.ShowModelList;
import it.academy.servlets.commands.tables.ShowRepairTable;

import static it.academy.utils.Constants.*;

public enum CommandEnum {
    OPEN_PAGE(new ShowPageCommand(MAIN_PAGE_PATH)),
    SHOW_BRAND_LIST(new ShowBrandList()),
    SHOW_MODEL_LIST(new ShowModelList()),
    SHOW_REPAIR(new ShowRepair()),
    ADD_REPAIR(new AddRepair()),
    CHANGE_REPAIR(new ChangeRepair()),
    SHOW_CONFIRMED_REPAIR(new ShowConfirmedRepair()),
    SHOW_REPAIR_TABLE(new ShowRepairTable());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
