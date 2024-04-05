package it.academy.servlets.commands.lists;

import it.academy.dto.device.BrandDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Constants.BRANDS;
import static it.academy.utils.Constants.BRAND_LIST_PAGE_PATH;

public class ShowBrandList implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<BrandDTO> brandDTOList = repairService.findBrands();
        req.setAttribute(BRANDS, brandDTOList);

        return BRAND_LIST_PAGE_PATH;
    }


}
