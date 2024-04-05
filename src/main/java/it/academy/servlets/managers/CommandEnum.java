package it.academy.servlets.managers;

import it.academy.servlets.commands.*;
import it.academy.servlets.managers.add.AddRepair;
import it.academy.servlets.managers.forms.ShowRepair;
import it.academy.servlets.managers.lists.ShowBrandList;
import it.academy.servlets.managers.lists.ShowModelList;
import it.academy.servlets.managers.tables.ShowRepairTable;

import static it.academy.utils.Constants.*;

public enum CommandEnum {
    OPEN_PAGE(new ShowPageCommand(MAIN_PAGE_PATH)),
    SHOW_BRAND_LIST(new ShowBrandList()),
    SHOW_MODEL_LIST(new ShowModelList()),
    SHOW_REPAIR(new ShowRepair()),
    ADD_REPAIR(new AddRepair()),
    SHOW_REQUEST_REPAIR_TABLE(new ShowRepairTable());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
