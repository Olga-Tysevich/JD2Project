package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeAccountDTO;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowAccountTable;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

@Slf4j
public class ChangeAccount implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            String result = FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> adminService.updateAccount((ChangeAccountDTO) a)),
                    (id) -> adminService.findAccount((Long) id),
                    ChangeAccountDTO.class,
                    ACCOUNT,
                    ACCOUNT_PAGE_PATH,
                    () -> new ShowAccountTable().execute(req));
            log.info(String.format(CURRENT_PAGE, result));
            return result;
        } catch (Exception e) {
            req.setAttribute(ERROR, ERROR_MESSAGE);
            return ERROR_PAGE_PATH;
        }
    }

}