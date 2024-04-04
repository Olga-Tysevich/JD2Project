package it.academy.servlets.managers;

import it.academy.servlets.commands.*;
import it.academy.servlets.managers.lists.ShowBrandList;
import it.academy.servlets.managers.lists.ShowModelList;

import static it.academy.utils.Constants.*;

public enum CommandEnum {
    OPEN_PAGE(new ShowPageCommand(MAIN_PAGE_PATH)),
    SHOW_BRAND_LIST(new ShowBrandList()),
    SHOW_MODEL_LIST(new ShowModelList());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
