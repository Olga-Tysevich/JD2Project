package it.academy;

import it.academy.dto.repair_page_N.RepairPageDataDTO;
import it.academy.dto.req.device.ModelDTO;
import it.academy.dto.req.repair.CreateRepairDTO;
import it.academy.dto.req.repair.RepairDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;

import java.sql.Date;
import java.util.List;

import static it.academy.utils.Constants.LIST_SIZE;

public class Test2 {

    public static void main(String[] args) {
        ModelService modelService = new ModelServiceImpl();

        List<ModelDTO> models = modelService.findModelsByBrandId(1L);
        models.forEach(System.out::println);

        RepairService repairService = new RepairServiceImpl();

        RepairPageDataDTO test = repairService.getDataForPage(1L);
        System.out.println();

        CreateRepairDTO repairDTOReq = CreateRepairDTO.builder()
                .modelId(1L)
                .categoryId(1L)
                .status(RepairStatus.REQUEST)
                .serialNumber("SN test")
                .defectDescription("some defects")
                .serviceRepairNumber("22-51")
                .dateOfSale(Date.valueOf("2022-01-11"))
                .salesmanName("salesman")
                .salesmanPhone("+37511")
                .buyerName("buyerName")
                .buyerSurname("buyerSurname")
                .buyerPhone("+375255")
                .build();

//        repairService.addRepair(repairDTOReq);

        RespListDTO<RepairDTO> repairResp = repairService.findRepairs(1, LIST_SIZE);
        System.out.println();
    }

}
