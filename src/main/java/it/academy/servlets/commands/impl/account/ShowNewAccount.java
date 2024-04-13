package it.academy.servlets.commands.impl.account;

import it.academy.dto.account.req.CreateAccountDTO;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.services.service_center.ServiceCenterService;
import it.academy.services.service_center.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowNewAccount implements ActionCommand {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

        CreateAccountDTO createAccountDTO = CreateAccountDTO.builder()
                .email(DEFAULT_VALUE)
                .userName(DEFAULT_VALUE)
                .userSurname(DEFAULT_VALUE)
                .password(DEFAULT_VALUE)
                .confirmPassword(DEFAULT_VALUE)
                .build();

        List<ServiceCenterDTO> serviceCenterList = serviceCenterService.findServiceCenters(currentAccount);
        if (!serviceCenterList.isEmpty()) {
            req.setAttribute(ACCOUNT, createAccountDTO);
            req.setAttribute(SERVICE_CENTERS, serviceCenterList);
            return NEW_ACCOUNT_PAGE_PATH;
        }
        req.setAttribute(ERROR, SERVICE_CENTERS_NOT_FOUND);
        System.out.println(SERVICE_CENTERS_NOT_FOUND);

        return MAIN_PAGE_PATH;
    }

}
