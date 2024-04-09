package it.academy.servlets.extractors.impl;

import it.academy.dto.ListForPage;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.TableManager;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.REPAIR_TYPE_NAME;

public class RepairTypeExtractor implements Extractor<RepairTypeDTO> {
    private AdminService adminService = new AdminServiceImpl();
    private RepairTypeDTO repairType;


    @Override
    public void extractValues(HttpServletRequest req) {
        Long repairTypeId = req.getParameter(REPAIR_TYPE_ID) != null?
                Long.parseLong(req.getParameter(REPAIR_TYPE_ID)) : null;
        String repairTypeCode = req.getParameter(REPAIR_TYPE_CODE);
        String repairTypeLevel = req.getParameter(REPAIR_TYPE_LEVEL);
        String repairTypeName = req.getParameter(REPAIR_TYPE_NAME);

        this.repairType = RepairTypeDTO.builder()
                .id(repairTypeId)
                .code(repairTypeCode)
                .level(repairTypeLevel)
                .name(repairTypeName)
                .build();
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<RepairTypeDTO> repairTypes;
        if (input != null && !input.isBlank()) {
            repairTypes = adminService.findRepairTypes(pageNumber, filter, input);
        } else {
            repairTypes = adminService.findRepairTypes(pageNumber);
        }

        TableManager.insertAttributesForTable(req, repairTypes, REPAIR_TABLE_TYPE_PAGE_PATH);
    }

    @Override
    public void addParameter(String parameterName, Object parameter) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return null;
    }

    @Override
    public RepairTypeDTO getResult() {
        return repairType;
    }

}