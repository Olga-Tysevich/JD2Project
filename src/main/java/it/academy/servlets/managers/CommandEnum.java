package it.academy.servlets.managers;

import it.academy.servlets.commands.*;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public enum CommandEnum {
    OPEN_PAGE(new ShowPageCommand(MAIN_PAGE_PATH)),
    SHOW_REPAIR_TABLE(new ShowRepairTable()),
    SHOW_REPAIR(new ShowRepair()),
    ADD_REPAIR(new AddRepair());
//    OPEN_LOGIN_FORM(new ShowPageCommand(Constants.LOGIN_PAGE_PATH)),
//    OPEN_REGISTRATION_FORM(new ShowPageCommand(Constants.REGISTRATION_PAGE_PATH)),
//    REGISTRATION(new RegistrationCommand()),
//    LOGIN(new LoginCommand()),
//    SHOW_STUDENTS(new ChangePageCommand()),
//    SAVE_STUDENT(new SaveOrUpdateCommand()),
//    CHANGE_STUDENT(new ChangeStudentCommand()),
//    DELETE_STUDENT(new DeleteStudentCommand()),
//    NEXT_PAGE(new ChangePageCommand()),
//    PREV_PAGE(new ChangePageCommand()),
//    FIND_STUDENTS(new FindStudentCommand());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
