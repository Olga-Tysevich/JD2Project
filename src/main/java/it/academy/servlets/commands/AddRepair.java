package it.academy.servlets.commands;

import it.academy.dto.req.repair.CreateRepairDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.module.Configuration;
import java.sql.Date;

import static it.academy.utils.Constants.MAIN_PAGE_PATH;
import static it.academy.utils.Constants.OPEN_START_PAGE;

public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CreateRepairDTO repairDTOReq = CreateRepairDTO.builder()
                .modelId(Long.parseLong(req.getParameter("model")))
                .categoryId(Long.parseLong(req.getParameter("category")))
                .status(RepairStatus.REQUEST)
                .serialNumber(req.getParameter("sn"))
                .defectDescription(req.getParameter("defectDescription"))
                .serviceRepairNumber(req.getParameter("serviceNumber"))
                .dateOfSale(Date.valueOf(req.getParameter("saleDate")))
                .salesmanName(req.getParameter("salesman"))
                .salesmanPhone(req.getParameter("salesmanPhone"))
                .buyerName(req.getParameter("buyerName"))
                .buyerSurname(req.getParameter("buyerSurname"))
                .buyerPhone(req.getParameter("buyerPhone"))
                .build();

        repairService.addRepair(repairDTOReq);

        return ConfigurationManager.getProperty(MAIN_PAGE_PATH);
    }

}
