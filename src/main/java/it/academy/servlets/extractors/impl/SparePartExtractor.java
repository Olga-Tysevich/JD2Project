package it.academy.servlets.extractors.impl;

import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.services.AdminService;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.TableManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class SparePartExtractor implements Extractor<SparePartDTO> {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
    private AdminService adminService = new AdminServiceImpl();
    private SparePartDTO sparePart;


    @Override
    public void extractValues(HttpServletRequest req) {
        Long id = req.getParameter(OBJECT_ID) != null ? Long.parseLong(req.getParameter(OBJECT_ID)) : null;
        String sparePartName = req.getParameter(OBJECT_NAME);

        this.sparePart = SparePartDTO.builder()
                .id(id)
                .name(sparePartName)
                .build();
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);
        List<DeviceTypeDTO> deviceTypes = adminService.findDeviceTypes();

        ListForPage<SparePartDTO> spareParts;
        if (input != null && !input.isBlank()) {
            spareParts = sparePartOrderService.findSpareParts(pageNumber, filter, input);
        } else {
            spareParts = sparePartOrderService.findSpareParts(pageNumber);
        }

        TableManager.insertAttributesForTable(req, spareParts, SPARE_PART_TABLE_TYPE_PAGE_PATH);
        req.setAttribute(SHOW_COMMAND, SHOW_SPARE_PART_TABLE);
        req.setAttribute(DEVICE_TYPES, deviceTypes);
    }

    @Override
    public void addParameter(String parameterName, Object parameter) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return null;
    }

    @Override
    public SparePartDTO getResult() {
        return sparePart;
    }
}
