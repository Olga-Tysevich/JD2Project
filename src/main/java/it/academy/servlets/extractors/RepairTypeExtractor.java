package it.academy.servlets.extractors;

import it.academy.dto.resp.ListForPage;
import it.academy.dto.resp.RepairTypeDTO;
import it.academy.services.RepairTypeService;
import it.academy.services.impl.RepairTypeServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class RepairTypeExtractor {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();
    private RepairTypeDTO repairType;


    public void extractValues(HttpServletRequest req) {
        Long repairTypeId = req.getParameter(REPAIR_TYPE_ID) != null ?
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


    public void insertAttributes(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<RepairTypeDTO> repairTypes;
        if (input != null && !input.isBlank()) {
            repairTypes = repairTypeService.findRepairTypes(pageNumber, filter, input);
        } else {
            repairTypes = repairTypeService.findRepairTypes(pageNumber);
        }

//        PageManager.insertAttributesForTable(req, repairTypes, REPAIR_TABLE_TYPE_PAGE_PATH);
    }


}
