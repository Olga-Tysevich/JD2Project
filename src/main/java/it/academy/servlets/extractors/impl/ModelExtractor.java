package it.academy.servlets.extractors.impl;

import it.academy.dto.ListForPage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.services.BrandService;
import it.academy.services.DeviceTypeService;
import it.academy.services.ModelService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.services.impl.DeviceTypeServiceImpl;
import it.academy.services.impl.ModelServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.TableManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ModelExtractor implements Extractor<ModelDTO> {
    private ModelService modelService = new ModelServiceImpl();
    private BrandService brandService = new BrandServiceImpl();
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
    private ModelDTO model;

    @Override
    public void extractValues(HttpServletRequest req) {
        Long modelId = req.getParameter(MODEL_ID) != null?
                Long.parseLong(req.getParameter(MODEL_ID)) : null;
        String modelName = req.getParameter(MODEL_NAME);
        Long brandId = Long.parseLong(req.getParameter(BRAND_ID));
        Long deviceTypeId = Long.parseLong(req.getParameter(BRAND_ID));
        Boolean isActive = req.getParameter(IS_ACTIVE) != null && Boolean.parseBoolean(req.getParameter(IS_ACTIVE));
        this.model = ModelDTO.builder()
                .id(modelId)
                .name(modelName)
                .brandId(brandId)
                .deviceTypeId(deviceTypeId)
                .isActive(isActive)
                .build();
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<ModelDTO> models;
        List<BrandDTO> brands = brandService.findBrand();
        List<DeviceTypeDTO> deviceTypes = deviceTypeService.findDeviceTypes();
        if (input != null && !input.isBlank()) {
            models = modelService.findModels(pageNumber, filter, input);
        } else {
            models = modelService.findModels(pageNumber);
        }

        req.setAttribute(DEVICE_TYPES, deviceTypes);
        req.setAttribute(BRANDS, brands);
        TableManager.insertAttributesForTable(req, models, MODEL_TABLE_PAGE_PATH);
    }

    @Override
    public void addParameter(String parameterName, Object parameter) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return null;
    }

    @Override
    public ModelDTO getResult() {
        return model;
    }
}
