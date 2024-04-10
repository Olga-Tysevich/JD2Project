package it.academy.servlets.extractors.impl;

import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.TableManager;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class DeviceTypeExtractor implements Extractor<DeviceTypeDTO> {
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
    private DeviceTypeDTO deviceType;

    @Override
    public void extractValues(HttpServletRequest req) {
        Long deviceTypeId = req.getParameter(DEVICE_TYPE_ID) != null?
                Long.parseLong(req.getParameter(DEVICE_TYPE_ID)) : null;
        String deviceTypeName = req.getParameter(DEVICE_TYPE_NAME);
        Boolean isActive = req.getParameter(IS_ACTIVE) != null && Boolean.parseBoolean(req.getParameter(IS_ACTIVE));
        this.deviceType = DeviceTypeDTO.builder()
                .id(deviceTypeId)
                .name(deviceTypeName)
                .isActive(isActive)
                .build();
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<DeviceTypeDTO> repairTypes;
        if (input != null && !input.isBlank()) {
            repairTypes = deviceTypeService.findDeviceTypes(pageNumber, filter, input);
        } else {
            repairTypes = deviceTypeService.findDeviceTypes(pageNumber);
        }

        TableManager.insertAttributesForTable(req, repairTypes, DEVICE_TYPE_TABLE_PAGE_PATH);
    }

    @Override
    public void addParameter(String parameterName, Object parameter) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return null;
    }

    @Override
    public DeviceTypeDTO getResult() {
        return deviceType;
    }
}
