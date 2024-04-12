package it.academy.servlets.commands.impl.account;

import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.account.req.CreateAccountDTO;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Extractor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class AddAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
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
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return MAIN_PAGE_PATH;
    }

}