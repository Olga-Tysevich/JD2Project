package it.academy.servlets.commands;

import it.academy.dto.repair_page_N.RepairPageDataDTO;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.impl.AdminServiceImpl;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.utils.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepair implements ActionCommand{
    private AdminService adminService = new AdminServiceImpl();
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTOReq account = AccountDTOReq.builder()
                .email("user40@yahoo.com")
                .build();

        RespDTO<AccountDTO> response = adminService.findAdminAccountByEmail(account);
        AccountDTO accountDTO = response.getParameter();

        req.getSession().setAttribute(USER, accountDTO);

        long brandId = req.getParameter(BRAND_ID) != null? Long.parseLong(req.getParameter(BRAND_ID)) : BRAND_DEFAULT_ID;
        RepairPageDataDTO pageData = repairService.getDataForPage(brandId);
        req.setAttribute(REPAIR_PAGE_DATA, pageData);

        return ConfigurationManager.getProperty(REPAIR_PAGE_PATH);
    }

}
