package it.academy.servlets.commands.impl.get.forms;

import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Builder;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class ShowNewAccount implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CreateAccountDTO createAccountDTO = Builder.buildEmptyAccount();
        List<ServiceCenterDTO> serviceCenterList = serviceCenterService.findServiceCenters();
//        AccountFormDTO accountFormDTO = new AccountFormDTO(serviceCenterList, createAccountDTO, DEFAULT_VALUE);

//        req.setAttribute(ACCOUNT, accountFormDTO);
//        return CommandHelper.insertFormData(req, NEW_ACCOUNT_PAGE_PATH, ADD_ACCOUNT);
        return null;

    }

}
