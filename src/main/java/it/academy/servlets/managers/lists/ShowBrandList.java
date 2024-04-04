package it.academy.servlets.managers.lists;

import it.academy.dto.BrandDTO;
import it.academy.services.BrandService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Constants.BRANDS;
import static it.academy.utils.Constants.BRAND_LIST_PAGE_PATH;

public class ShowBrandList implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<BrandDTO> brandDTOList = brandService.findBrands();
        req.setAttribute(BRANDS, brandDTOList);

        return BRAND_LIST_PAGE_PATH;
    }


}
