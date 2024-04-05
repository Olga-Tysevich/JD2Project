package it.academy;

import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.RepairService;
import it.academy.services.RepairWorkshopService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.RepairWorkshopImpl;

import java.sql.Date;

import static it.academy.utils.Constants.*;

public class Test2 {

    public static void main(String[] args) {
//        EntityManager manager = HibernateUtil.getEntityManager();
//        manager.getTransaction().begin();
//        BrandService brandService = new BrandServiceImpl();
//        List<BrandDTO> brandDTOList = brandService.findBrands();
//        System.out.println();
//        ModelService modelService = new ModelServiceImpl();
//        List<ModelDTO> models = modelService.findModelsByBrandId(1L);
//        ModelDTO model = modelService.findModel(2L);
         RepairService repairService = new RepairServiceImpl();
         RepairWorkshopService repairWorkshopService = new RepairWorkshopImpl();

            long modelId = 1L;
            ModelDTO modelDTO = repairService.findModel(modelId);
            Date dateOfSale = Date.valueOf("2022-11-01");

            DeviceDTO deviceDTO = DeviceDTO.builder()
                    .model(modelDTO)
                    .serialNumber(SERIAL_NUMBER)
                    .dateOfSale(dateOfSale)
                    .salesmanName(SALESMAN_NAME)
                    .salesmanPhone(SALESMAN_PHONE)
                    .buyerName(BUYER_NAME)
                    .buyerSurname(BUYER_SURNAME)
                    .buyerPhone(BUYER_PHONE)
                    .build();

            deviceDTO = repairService.addDevice(deviceDTO);

            RepairDTO repairDTO = RepairDTO.builder()
                    .device(deviceDTO)
                    .category(RepairCategory.WARRANTY)
                    .status(RepairStatus.REQUEST)
                    .defectDescription(DEFECT_DESCRIPTION)
                    .repairWorkshopRepairNumber(REPAIR_WORKSHOP_REPAIR_NUMBER)
                    .isDeleted(false)
                    .build();

            repairService.addRepair(repairDTO);


        System.out.println();
    }

}
