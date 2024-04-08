package it.academy.servlets.commands.impl.forms;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        RepairDTO repairDTO = RepairDTO.builder()
                .brandId(DEFAULT_ID)
                .deviceId(DEFAULT_ID)
                .status(RepairStatus.REQUEST)
                .serialNumber(DEFAULT_VALUE)
                .defectDescription(DEFAULT_VALUE)
                .repairWorkshopRepairNumber(DEFAULT_VALUE)
                .salesmanName(DEFAULT_VALUE)
                .salesmanPhone(DEFAULT_VALUE)
                .buyerName(DEFAULT_VALUE)
                .buyerSurname(DEFAULT_VALUE)
                .buyerPhone(DEFAULT_VALUE)
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
