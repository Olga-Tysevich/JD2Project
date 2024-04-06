package it.academy.servlets.extractors.impl;

import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.RepairService;
import it.academy.services.RepairWorkshopService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.RepairWorkshopImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

import static it.academy.utils.Constants.*;

public class RepairExtractor implements Extractor {
    private RepairService repairService = new RepairServiceImpl();
    private RepairWorkshopService repairWorkshopService = new RepairWorkshopImpl();
    private RepairDTO repairDTO;

    @Override
    public void extractValues(HttpServletRequest req) {
        //TODO добавить извлечение сервисного уентра
//        long repairWorkshopId = Long.parseLong(req.getParameter(OBJECT_ID));
//        RepairWorkshopDTO repairWorkshop = repairWorkshopService.findRepairWorkshop(repairWorkshopId);

        long modelId = Long.parseLong(req.getParameter(MODEL_ID));
        ModelDTO modelDTO = repairService.findModel(modelId);
        System.out.println("model from extractor " + modelDTO);
        String serialNumber = req.getParameter(SERIAL_NUMBER);
        Date dateOfSale = Date.valueOf(req.getParameter(DATE_OF_SALE));
        String salesmanName = req.getParameter(SALESMAN_NAME);
        String salesmanPhone = req.getParameter(SALESMAN_PHONE);
        String buyerName = req.getParameter(BUYER_NAME);
        String buyerSurname = req.getParameter(BUYER_SURNAME);
        String buyerPhone = req.getParameter(BUYER_PHONE);
        Long deviceId = req.getParameter(DEVICE_ID) == null ? null : Long.parseLong(req.getParameter(DEVICE_ID));

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

        deviceDTO = repairService.addDevice(deviceDTO);

        String repairWorkshopRepairNumber = req.getParameter(REPAIR_WORKSHOP_REPAIR_NUMBER);
        RepairCategory category = RepairCategory.valueOf(req.getParameter(REPAIR_CATEGORY));
        RepairStatus status = RepairStatus.valueOf(req.getParameter(REPAIR_STATUS));
        String defectDescription = req.getParameter(DEFECT_DESCRIPTION);

        Long repairTypeId = req.getParameter(REPAIR_TYPE_ID) == null ? null : Long.parseLong(req.getParameter(REPAIR_TYPE_ID));
        RepairTypeDTO repairType = repairTypeId == null ? null : repairService.findRepairType(repairTypeId);
        Date startDate = req.getParameter(START_DATE) == null ? null : Date.valueOf(req.getParameter(START_DATE));
        Date endDate = req.getParameter(END_DATE) == null ? null : Date.valueOf(req.getParameter(END_DATE));
        Date deliveryDate = req.getParameter(DELIVERY_DATE) == null ? null : Date.valueOf(req.getParameter(DELIVERY_DATE));
        boolean isDeleted = req.getParameter(IS_DELETED) != null;
        System.out.println(req.getParameter(IS_DELETED));
        System.out.println(isDeleted);

        Long repairId = req.getParameter(REPAIR_ID) == null ? null : Long.parseLong(req.getParameter(REPAIR_ID));

        this.repairDTO = RepairDTO.builder()
                .id(repairId)
                .device(deviceDTO)
                .category(category)
                .status(status)
                .defectDescription(defectDescription)
                .startDate(startDate)
                .repairWorkshopRepairNumber(repairWorkshopRepairNumber)
                .endDate(endDate)
                .deliveryDate(deliveryDate)
                .type(repairType)
                .isDeleted(isDeleted)
                .build();
    }

    public RepairDTO getRepairDTO() {
        return repairDTO;
    }
}
