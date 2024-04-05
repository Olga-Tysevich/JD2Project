package it.academy.servlets.extractors.impl;

import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
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

    @Override
    public void extractValues(HttpServletRequest req) {
        //TODO добавить извлечение сервисного уентра
//        long repairWorkshopId = Long.parseLong(req.getParameter(OBJECT_ID));
//        RepairWorkshopDTO repairWorkshop = repairWorkshopService.findRepairWorkshop(repairWorkshopId);


        long modelId = Long.parseLong(req.getParameter(MODEL_ID));
        ModelDTO modelDTO = repairService.findModel(modelId);
        String serialNumber = req.getParameter(SERIAL_NUMBER);
        Date dateOfSale = Date.valueOf(req.getParameter(DATE_OF_SALE));
        String salesmanName = req.getParameter(SALESMAN_NAME);
        String salesmanPhone = req.getParameter(SALESMAN_PHONE);
        String buyerName = req.getParameter(BUYER_NAME);
        String buyerSurname = req.getParameter(BUYER_SURNAME);
        String buyerPhone = req.getParameter(BUYER_PHONE);

        DeviceDTO deviceDTO = DeviceDTO.builder()
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
        String defectDescription = req.getParameter(DEFECT_DESCRIPTION);

        RepairDTO repairDTO = RepairDTO.builder()
                .device(deviceDTO)
                .category(category)
                .status(RepairStatus.REQUEST)
                .defectDescription(defectDescription)
                .repairWorkshopRepairNumber(repairWorkshopRepairNumber)
                .isDeleted(false)
                .build();

        repairService.addRepair(repairDTO);
    }

}
