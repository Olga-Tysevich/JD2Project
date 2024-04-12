package it.academy.servlets.commands.impl.account;

import it.academy.dto.ListForPage;
import it.academy.dto.account.req.CreateAccountDTO;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.impl.test.Extractor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class AddAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CreateAccountDTO accountDTO = Extractor.extract(req, new CreateAccountDTO());
        ListForPage<ServiceCenterDTO> listFoPage = new ListForPage<>();
        try {
            adminService.createAccount(accountDTO);
        } catch (EnteredPasswordsNotMatch | EmailAlreadyRegistered e) {
            List<ServiceCenterDTO> serviceCenters = adminService.findServiceCenters();
            listFoPage.setList(serviceCenters);
            System.out.println("add account list " + serviceCenters);
            System.out.println("error " + e.getMessage());

            req.setAttribute(SERVICE_CENTERS, serviceCenters);
            req.setAttribute(ERROR, e.getMessage());
            return NEW_ACCOUNT_PAGE_PATH;
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, Integer.parseInt(req.getParameter(PAGE_NUMBER)));

        return MAIN_PAGE_PATH;
    }

}