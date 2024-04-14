package it.academy.servlets.commands.impl.forms;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Builder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

//        CreateModelDTO createModelDTO = repairService.findModel(DEFAULT_ID);
        RepairDTO repairDTO = RepairDTO.builder()
//                .device(Builder.buildEmptyDevice(createModelDTO))
                .status(RepairStatus.REQUEST)
                .defectDescription(DEFAULT_VALUE)
                .serviceCenterRepairNumber(DEFAULT_VALUE)
                .build();
        List<BrandDTO> brandDTOList = repairService.findBrands();
//        List<CreateModelDTO> createModelDTOList = repairService.findModelsByBrandId(DEFAULT_ID);
        req.setAttribute(REPAIR, repairDTO);
        req.setAttribute(BRANDS, brandDTOList);
//        req.setAttribute(MODELS, createModelDTOList);
        req.setAttribute(BRAND_ID, DEFAULT_ID);
        req.setAttribute(CURRENT_BRAND_ID, DEFAULT_ID);

        return REPAIR_PAGE_PATH;

    }

}
