package it.academy.servlets.commands;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.repair.CurrentRepairDTO;
import it.academy.dto.req.repair.RepairDTO;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.impl.AdminServiceImpl;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowRepairTable implements ActionCommand {
    private RepairStatus status;
    private String pagePath;
    private AdminService adminService = new AdminServiceImpl();
    private RepairService repairService = new RepairServiceImpl();

    public ShowRepairTable(RepairStatus status, String pagePath) {
        this.status = status;
        this.pagePath = pagePath;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTOReq account = AccountDTOReq.builder()
                .email("user40@yahoo.com")
                .build();

        RespDTO<AccountDTO> response = adminService.findAdminAccountByEmail(account);
        AccountDTO accountDTO = response.getParameter();

        req.getSession().setAttribute(USER, accountDTO);

        int pageNumber = req.getParameter(PAGE_NUMBER) != null? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        List<RepairDTO> repairResp = repairService.findRepairsByStatus(status, pageNumber, LIST_SIZE);


//        resp.setStatus(repairResp.getHttpStatus());
//        req.setAttribute(TABLE_PAGE_PATH, ConfigurationManager.getProperty(REPAIR_TABLE_PATH));
        req.setAttribute(TABLE_PAGE_PATH, ConfigurationManager.getProperty(pagePath));
        req.setAttribute(TABLE_FOR_PAGE, repairResp);
        req.setAttribute(PAGE_NUMBER, pageNumber);

//        return START_PAGE_PATH;
        return ConfigurationManager.getProperty(MAIN_PAGE_PATH);
    }

}