package it.academy;

import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.RepairService;
import it.academy.services.RepairWorkshopService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.RepairWorkshopImpl;
import it.academy.utils.dao.TransactionManger;

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
        RepairDAO repairDAO = new RepairDAOImpl();
        TransactionManger transactionManger = TransactionManger.getInstance();

        ListForPage<RepairDTO> r = repairService.findRepairs(1);

        System.out.println();

//        for (int i = 0; i < 30; i++) {
//            long modelId = 1L;
//            ModelDTO modelDTO = repairService.findModel(modelId);
//            Date dateOfSale = Date.valueOf("2022-11-01");
//
//            DeviceDTO deviceDTO = DeviceDTO.builder()
//                    .model(modelDTO)
//                    .serialNumber(SERIAL_NUMBER + i)
//                    .dateOfSale(dateOfSale)
//                    .salesmanName(SALESMAN_NAME + i)
//                    .salesmanPhone(SALESMAN_PHONE + i)
//                    .buyerName(BUYER_NAME + i)
//                    .buyerSurname(BUYER_SURNAME + i)
//                    .buyerPhone(BUYER_PHONE + i)
//                    .build();
//
//            deviceDTO = repairService.addDevice(deviceDTO);
//
//            RepairDTO repairDTO = RepairDTO.builder()
//                    .device(deviceDTO)
//                    .category(RepairCategory.WARRANTY)
//                    .status(RepairStatus.REQUEST)
//                    .defectDescription(DEFECT_DESCRIPTION + i)
//                    .repairWorkshopRepairNumber(REPAIR_WORKSHOP_REPAIR_NUMBER + i)
//                    .isDeleted(false)
//                    .build();
//
//            repairService.addRepair(repairDTO);
//        }


        System.out.println();
    }

}
