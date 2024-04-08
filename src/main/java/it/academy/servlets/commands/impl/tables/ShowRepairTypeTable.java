package it.academy.servlets.commands.impl.tables;

import it.academy.dto.ListForPage;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepairTypeTable implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        ListForPage<RepairTypeDTO> repairs = adminService.findRepairTypes(pageNumber);

        req.setAttribute(PAGE, REPAIR_TABLE_TYPE_PAGE_PATH);
        req.setAttribute(LIST_FOR_PAGE, repairs);
        req.setAttribute(PAGE_NUMBER, pageNumber);
        req.setAttribute(MAX_PAGE, repairs.getMaxPageNumber());

        return MAIN_PAGE_PATH;
    }

}
