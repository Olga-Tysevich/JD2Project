package it.academy.servlets.commands.impl.forms;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.utils.Builder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ModelDTO modelDTO = repairService.findModel(DEFAULT_ID);
        RepairDTO repairDTO = RepairDTO.builder()
                .device(Builder.buildEmptyDevice(modelDTO))
                .status(RepairStatus.REQUEST)
                .defectDescription(DEFAULT_VALUE)
                .repairWorkshopRepairNumber(DEFAULT_VALUE)
                .build();
        List<BrandDTO> brandDTOList = repairService.findBrands();
        List<ModelDTO> modelDTOList = repairService.findModelsByBrandId(DEFAULT_ID);
        req.setAttribute(REPAIR, repairDTO);
        req.setAttribute(BRANDS, brandDTOList);
        req.setAttribute(MODELS, modelDTOList);
        req.setAttribute(BRAND_ID, DEFAULT_ID);
        req.setAttribute(CURRENT_BRAND_ID, DEFAULT_ID);

        return REPAIR_PAGE_PATH;

    }

}
