package it.academy.servlets.commands.impl.forms;

import it.academy.dto.AccountDTO;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.entities.RoleEnum;
import it.academy.services.AdminService;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Long accountId = req.getParameter(OBJECT_ID) != null? Long.parseLong(req.getParameter(OBJECT_ID)) : null;
        System.out.println(accountId);
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        AccountDTO account;
        if (accountId != null) {
            account = adminService.findAccount(accountId);
            req.setAttribute(COMMAND, CHANGE_ACCOUNT);
        } else {
            account = AccountDTO.builder()
                    .email(DEFAULT_VALUE)
                    .userName(DEFAULT_VALUE)
                    .userSurname(DEFAULT_VALUE)
                    .role(RoleEnum.SERVICE_CENTER)
                    .isActive(true)
                    .build();
            req.setAttribute(COMMAND, ADD_ACCOUNT);

        }
        List<ServiceCenterDTO> serviceCenters = serviceCenterService.findServiceCenter();

        req.setAttribute(ACCOUNT, account);
        req.setAttribute(PAGE_NUMBER, pageNumber);
        req.setAttribute(SERVICE_CENTERS, serviceCenters);

        return ACCOUNT_PAGE_PATH;
    }

}
