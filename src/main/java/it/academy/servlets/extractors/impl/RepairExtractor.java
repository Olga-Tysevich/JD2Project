package it.academy.servlets.extractors.impl;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.repair.RepairService;
import it.academy.services.ServiceCenterService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.academy.utils.Constants.*;

public class RepairExtractor implements Extractor<RepairDTO> {
    private RepairService repairService = new RepairServiceImpl();
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
    private Map<String, Object> reqParameters = new HashMap<>();

    @Override
    public void extractValues(HttpServletRequest req) {
        //TODO добавить извлечение сервисного уентра
//        long repairWorkshopId = Long.parseLong(req.getParameter(OBJECT_ID));
//        RepairWorkshopDTO repairWorkshop = repairWorkshopService.findRepairWorkshop(repairWorkshopId);

        long lastBrandId = Long.parseLong(req.getParameter(BRAND_ID));
        long currentBrandId = Long.parseLong(req.getParameter(CURRENT_BRAND_ID));
        long modelId = Long.parseLong(req.getParameter(MODEL_ID));
        String serialNumber = req.getParameter(SERIAL_NUMBER);
        Date dateOfSale = !req.getParameter(DATE_OF_SALE).isEmpty() ? Date.valueOf(req.getParameter(DATE_OF_SALE)) : null;
        String salesmanName = req.getParameter(SALESMAN_NAME);
        String salesmanPhone = req.getParameter(SALESMAN_PHONE);
        String buyerName = req.getParameter(BUYER_NAME);
        String buyerSurname = req.getParameter(BUYER_SURNAME);
        String buyerPhone = req.getParameter(BUYER_PHONE);
        long deviceId = Long.parseLong(req.getParameter(DEVICE_ID));
        String repairWorkshopRepairNumber = req.getParameter(REPAIR_WORKSHOP_REPAIR_NUMBER);
        RepairCategory category = RepairCategory.valueOf(req.getParameter(REPAIR_CATEGORY));
        RepairStatus status = RepairStatus.valueOf(req.getParameter(REPAIR_STATUS));
        String defectDescription = req.getParameter(DEFECT_DESCRIPTION);
        boolean isDeleted = req.getParameter(IS_DELETED) != null;
        Date startDate = req.getParameter(START_DATE) != null? Date.valueOf(req.getParameter(START_DATE)) : null;
        Long repairId = req.getParameter(REPAIR_ID) != null ? Long.parseLong(req.getParameter(REPAIR_ID)) : null;

        ModelDTO modelDTO = repairService.findModel(modelId);
        DeviceDTO deviceDTO = DeviceDTO.builder()
                .id(deviceId)
                .model(modelDTO)
                .serialNumber(serialNumber)
                .dateOfSale(dateOfSale)
                .salesmanName(salesmanName)
                .salesmanPhone(salesmanPhone)
                .buyerName(buyerName)
                .buyerSurname(buyerSurname)
                .buyerPhone(buyerPhone)
                .build();


        RepairDTO repairDTO = RepairDTO.builder()
                .id(repairId)
                .device(deviceDTO)
                .category(category)
                .status(status)
                .defectDescription(defectDescription)
                .repairWorkshopRepairNumber(repairWorkshopRepairNumber)
                .startDate(startDate)
                .isDeleted(isDeleted)
                .build();

        if (status.isFinishedStatus()) {
            long repairTypeId = Long.parseLong(req.getParameter(REPAIR_TYPE_ID));
            Date endDate = Date.valueOf(req.getParameter(END_DATE));
            RepairTypeDTO repairTypeDTO = repairService.findRepairType(repairTypeId);

            repairDTO.setEndDate(endDate);
            repairDTO.setRepairType(repairTypeDTO);
        }

        if (status.isDeliveredStatus()) {
            Date deliveryDate = Date.valueOf(req.getParameter(DELIVERY_DATE));
            repairDTO.setDeliveryDate(deliveryDate);
        }

        reqParameters.put(REPAIR_ID, repairId);
        reqParameters.put(BRAND_ID, lastBrandId);
        reqParameters.put(CURRENT_BRAND_ID, currentBrandId);
        reqParameters.put(REPAIR, repairDTO);
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
        RepairDTO repairDTO = (RepairDTO) reqParameters.get(REPAIR);
        long lastBrandId = (long) reqParameters.get(BRAND_ID);
        long currentBrandId = (long) reqParameters.get(CURRENT_BRAND_ID);
        List<ModelDTO> modelDTOList = repairService.findModelsByBrandId(currentBrandId);
        List<BrandDTO> brandDTOList = repairService.findBrands();
        repairDTO.getDevice().getModel().setBrandId(currentBrandId);

        req.setAttribute(REPAIR, repairDTO);
        req.setAttribute(BRANDS, brandDTOList);
        req.setAttribute(MODELS, modelDTOList);
        req.setAttribute(BRAND_ID, lastBrandId);
        req.setAttribute(CURRENT_BRAND_ID, currentBrandId);
    }

    @Override
    public void addParameter(String parameterName, Object parameter) {
        reqParameters.put(parameterName, parameter);
    }

    @Override
    public Object getParameter(String parameterName) {
        return reqParameters.get(parameterName);
    }

    @Override
    public RepairDTO getResult() {
        return null;
    }


}
