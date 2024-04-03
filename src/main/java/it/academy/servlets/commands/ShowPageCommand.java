package it.academy.servlets.commands;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.impl.AdminServiceImpl;
import it.academy.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.USER;

public class ShowPageCommand implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();
    private String pageKey;

    public ShowPageCommand(String pageKey) {
        this.pageKey = pageKey;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTOReq account = AccountDTOReq.builder()
                .email("user40@yahoo.com")
                .build();

        RespDTO<AccountDTO> response = adminService.findAdminAccountByEmail(account);
        AccountDTO accountDTO = response.getParameter();

        req.getSession().setAttribute(USER, accountDTO);
        resp.setStatus(response.getHttpStatus());
        return ConfigurationManager.getProperty(pageKey);
    }

}
