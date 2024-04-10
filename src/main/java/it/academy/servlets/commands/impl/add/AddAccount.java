package it.academy.servlets.commands.impl.add;

import it.academy.dto.AccountDTO;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.AccountExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class AddAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();
    private Extractor<AccountDTO> extractor = new AccountExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        AccountDTO accountDTO  = extractor.getResult();
        accountDTO.setIsActive(true);
        adminService.addAccount(accountDTO);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }

}