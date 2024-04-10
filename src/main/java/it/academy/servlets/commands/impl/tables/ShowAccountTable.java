package it.academy.servlets.commands.impl.tables;

import it.academy.dto.AccountDTO;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.AccountExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowAccountTable implements ActionCommand {
    private Extractor<AccountDTO> extractor = new AccountExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.insertAttributes(req);
        req.setAttribute(SHOW_COMMAND, SHOW_ACCOUNT_TABLE);

        return MAIN_PAGE_PATH;
    }

}