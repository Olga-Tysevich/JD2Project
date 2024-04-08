package it.academy.servlets.extractors.impl;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.RepairService;
import it.academy.services.RepairWorkshopService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.RepairWorkshopImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.REPAIR_TYPE_NAME;

public class RepairTypeExtractor implements Extractor {
    private RepairService repairService = new RepairServiceImpl();
    private RepairWorkshopService repairWorkshopService = new RepairWorkshopImpl();
    private Map<String, Object> reqParameters = new HashMap<>();


    @Override
    public void extractValues(HttpServletRequest req) {
        long repairTypeId = Long.parseLong(req.getParameter(REPAIR_TYPE_ID));
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
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {

    }

    @Override
    public void addParameter(String parameterName, Object parameter) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return null;
    }
}
