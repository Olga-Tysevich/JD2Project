package it.academy.servlets.commands.impl.change;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static it.academy.utils.Constants.*;

public class ChangeRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();
    private Extractor extractor = new RepairExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Long lastBrandId = req.getParameter(BRAND_ID) == null? null : Long.parseLong(req.getParameter(BRAND_ID));
        Long currentBrandId = req.getParameter(CURRENT_BRAND_ID) == null? null : Long.parseLong(req.getParameter(CURRENT_BRAND_ID));
        extractor.extractValues(req);
        RepairDTO repairDTO = ((RepairExtractor) extractor).getRepairDTO();
        System.out.println("model before update" + repairDTO);

        if (currentBrandId != null && !currentBrandId.equals(lastBrandId)) {
            List<ModelDTO> modelDTOList = repairService.findModelsByBrandId(currentBrandId);
            List<BrandDTO> brandDTOList = repairService.findBrands();
            repairDTO.getDevice().getModel().setBrandId(currentBrandId);
            req.setAttribute(BRANDS, brandDTOList);
            req.setAttribute(MODELS, modelDTOList);
            req.setAttribute(REPAIR, repairDTO);
            return CHANGE_REPAIR_PAGE_PATH;
        }

        System.out.println("model after update" + repairDTO);

        repairService.changeRepair(repairDTO);

        return MAIN_PAGE_PATH;
    }

}
