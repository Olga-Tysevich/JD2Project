package it.academy.servlets.commands.impl.add;

import it.academy.dto.req.CreateAccountDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.req.ServiceCenterDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowAccountTable;
import it.academy.servlets.extractors.impl.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class AddAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();
    private ServiceCenterService service = new ServiceCenterServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO currentAccount = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

        try {
            String result = FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> adminService.createAccount((CreateAccountDTO) a)),
                    (i) -> adminService.findAccount(0L),
                    CreateAccountDTO.class,
                    ACCOUNT,
                    NEW_ACCOUNT_PAGE_PATH,
                    () -> new ShowAccountTable().execute(req));
            System.out.println("result " + result);
            if (NEW_ACCOUNT_PAGE_PATH.equals(result)) {
                ListForPage<ServiceCenterDTO> listFoPage = new ListForPage<>();
                List<ServiceCenterDTO> serviceCenters = service.findServiceCenters(currentAccount);
                listFoPage.setList(serviceCenters);
                req.setAttribute(SERVICE_CENTERS, serviceCenters);
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }
    }

}