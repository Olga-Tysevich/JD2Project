package it.academy.servlets.extractors.impl;

import it.academy.dto.ListForPage;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.AdminService;
import it.academy.services.RepairWorkshopService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.RepairWorkshopImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.EntityFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.REPAIR_TYPE_NAME;

public class RepairTypeExtractor implements Extractor<RepairTypeDTO> {
    private static int count;
    private AdminService adminService = new AdminServiceImpl();
    private RepairWorkshopService repairWorkshopService = new RepairWorkshopImpl();
    private Map<String, Object> reqParameters = new HashMap<>();
    private RepairTypeDTO repairType;


    @Override
    public void extractValues(HttpServletRequest req) {
        Long repairTypeId = req.getParameter(REPAIR_TYPE_ID) != null?
                Long.parseLong(req.getParameter(REPAIR_TYPE_ID)) : null;
        String repairTypeCode = req.getParameter(REPAIR_TYPE_CODE);
        String repairTypeLevel = req.getParameter(REPAIR_TYPE_LEVEL);
        String repairTypeName = req.getParameter(REPAIR_TYPE_NAME);

        RepairTypeDTO repairType = RepairTypeDTO.builder()
                .id(repairTypeId)
                .code(repairTypeCode)
                .level(repairTypeLevel)
                .name(repairTypeName)
                .build();

        reqParameters.put(REPAIR_TYPE, repairType);
        this.repairType = repairType;
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);
        System.out.println("Counter " + count);
        count++;
        System.out.println("filter " + filter);
        System.out.println("parameter " + input);

        ListForPage<RepairTypeDTO> repairTypes;
        if (input != null && !input.isBlank()) {
            System.out.println("in filter search");
            repairTypes = adminService.findRepairTypes(pageNumber, filter, input);
        } else {
            System.out.println("in common search");
            repairTypes = adminService.findRepairTypes(pageNumber);
        }
        System.out.println("max page " + repairTypes.getMaxPageNumber());
        System.out.println("page " + repairTypes.getPageNumber());
        System.out.println("filters " + repairTypes.getFiltersForPage());
        System.out.println("list " + repairTypes.getList());

        List<EntityFilter> filters = repairTypes.getFiltersForPage();

        req.setAttribute(PAGE, REPAIR_TABLE_TYPE_PAGE_PATH);
        req.setAttribute(FILTERS, filters);
        req.setAttribute(LIST_FOR_PAGE, repairTypes);
        req.setAttribute(PAGE_NUMBER, pageNumber);
        req.setAttribute(MAX_PAGE, repairTypes.getMaxPageNumber());
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
