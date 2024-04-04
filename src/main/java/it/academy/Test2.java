package it.academy;

import it.academy.entities.repair.components.RepairStatus;
import it.academy.utils.dao.HibernateUtil;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import java.sql.Date;

import static it.academy.utils.Constants.LIST_SIZE;

public class Test2 {

    public static void main(String[] args) {
//        ModelService modelService = new ModelServiceImpl();
//
//        List<ModelDTO> models = modelService.findModelsByBrandId(1L);
//        models.forEach(System.out::println);
//
//        RepairService repairService = new RepairServiceImpl();
//
//        RepairPageDataDTO test = repairService.getDataForPage(1L);
//        System.out.println();
//
//        CreateRepairDTO repairDTOReq = CreateRepairDTO.builder()
//                .modelId(1L)
//                .categoryId(1L)
//                .status(RepairStatus.REQUEST)
//                .serialNumber("SN test")
//                .defectDescription("some defects")
//                .serviceRepairNumber("22-51")
//                .dateOfSale(Date.valueOf("2022-01-11"))
//                .salesmanName("salesman")
//                .salesmanPhone("+37511")
//                .buyerName("buyerName")
//                .buyerSurname("buyerSurname")
//                .buyerPhone("+375255")
//                .build();
//
////        repairService.addRepair(repairDTOReq);
//
//        List<RepairDTO> repairResp = repairService.findRepairsByStatus(RepairStatus.REQUEST, 1, LIST_SIZE);
//        List<RepairDTO> repairResp2 = repairService.findRepairsByStatus(RepairStatus.CURRENT, 1, LIST_SIZE);
//        System.out.println();
        EntityManager manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
    }

}
