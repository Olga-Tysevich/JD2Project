package it.academy;

import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.entities.repair.components.RepairType;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.services.AdminService;
import it.academy.services.RepairService;
import it.academy.services.RepairWorkshopService;
import it.academy.services.SparePartService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.RepairWorkshopImpl;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.dao.TransactionManger;

import java.sql.Date;
import java.util.List;

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

        AdminService adminService = new AdminServiceImpl();
        SparePartService sparePartService = new SparePartServiceImpl();
        RepairService repairService = new RepairServiceImpl();
        RepairWorkshopService repairWorkshopService = new RepairWorkshopImpl();
        RepairDAO repairDAO = new RepairDAOImpl();
        DeviceDAO deviceDAO = new DeviceDAOImpl();
        DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
        TransactionManger transactionManger = TransactionManger.getInstance();

        ListForPage<RepairTypeDTO> f = adminService.findRepairTypes(1, REPAIR_TYPE_CODE, "C1");


//        List<SparePartOrderDTO> orders = sparePartService.findSparePartOrdersByRepairId(135L);
//
//
//        SparePartDTO s = sparePartService.findSparePart(2L);
//
//        List<SparePartDTO> sparePartDTOS = sparePartService.findSparePartsByDeviceTypeId(1L);
//        System.out.println();

//        SparePartDTO sparePart = sparePartService.findSparePart(1L);
//
//        List<DeviceTypeDTO> all = DeviceTypeConverter.convertListToDTO(transactionManger.execute(deviceTypeDAO::findAll));
//
//        sparePart.setRelatedDeviceTypes(all);
//        sparePartService.changeSparePart(sparePart);

//        SparePartDTO sparePart = SparePartDTO.builder()
//                .name("new sp")
//                .relatedDeviceTypes(all)
//                .build();
//        sparePartService.addSparePart(sparePart);
//
//
//        List<DeviceType> types = transactionManger.execute(() -> deviceTypeDAO.findBySparePartId(1L));

//        RepairDTO repairDTO = repairService.findRepair(1L);
//        DeviceDTO deviceDTO = DeviceConverter.convertToDTO(transactionManger.execute(() -> deviceDAO.find(2L)));
//        ModelDTO modelDTO = repairService.findModel(1L);
//        deviceDTO.setModel(modelDTO);
//
//        repairService.addDevice(deviceDTO);
//
//        SparePart sparePart = SparePart.builder()
//                .name("test")
//                .build();
//        SparePart sparePart2 = SparePart.builder()
//                .name("test2")
//                .build();
//
//        DeviceType dt = DeviceType.builder()
//                .name("device type")
//                .isActive(true)
//                .spareParts(Set.of(sparePart, sparePart2))
//                .build();



//        ListForPage<RepairDTO> r = repairService.findRepairs(1);
//        ListForPage<RepairDTO> r2 = repairService.findRepairsByStatus(RepairStatus.REQUEST, 1);
//        RepairDTO r = repairService.findRepair(1);
//        r.setDeleted(true);
//        repairService.changeRepair(r);

//        for (int i = 0; i < 100; i++) {
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
//                    .status(RepairStatus.values()[RANDOM.nextInt(RepairStatus.values().length)])
//                    .defectDescription(DEFECT_DESCRIPTION + i)
//                    .repairWorkshopRepairNumber(REPAIR_WORKSHOP_REPAIR_NUMBER + i)
//                    .isDeleted(false)
//                    .build();
//
//            repairService.addRepair(repairDTO);
//        }
//
//
//        System.out.println();
    }

}
