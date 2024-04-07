package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.services.RepairService;
import it.academy.services.SparePartService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.impl.forms.ShowOrderSparePart;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.SparePartOrderExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static it.academy.utils.Constants.*;

public class AddSparePartOrder extends ShowOrderSparePart {
    private SparePartService sparePartService = new SparePartServiceImpl();
    private RepairService repairService = new RepairServiceImpl();
    private Extractor extractor = new SparePartOrderExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        SparePartOrderDTO sparePartOrderDTO = (SparePartOrderDTO) extractor.getParameter(SPARE_PART_ORDER);
        sparePartService.addSparePartOrder(sparePartOrderDTO);

        long repairId = (long) extractor.getParameter(REPAIR_ID);
        RepairDTO repairDTO = repairService.findRepair(repairId);
        List<ModelDTO> modelDTOList = repairService.findModelsByBrandId(repairDTO.getBrandId());
        List<BrandDTO> brandDTOList = repairService.findBrands();

        req.setAttribute(REPAIR, repairDTO);
        req.setAttribute(BRANDS, brandDTOList);
        req.setAttribute(MODELS, modelDTOList);
        req.setAttribute(BRAND_ID, repairDTO.getBrandId());
        req.setAttribute(CURRENT_BRAND_ID, repairDTO.getBrandId());

        return CHANGE_REPAIR_PAGE_PATH;
    }

}
