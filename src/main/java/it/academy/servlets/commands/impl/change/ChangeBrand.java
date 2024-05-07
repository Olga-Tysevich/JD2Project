package it.academy.servlets.commands.impl.change;

import it.academy.dto.device.BrandDTO;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.tables.GetBrands;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_BRAND;
import static it.academy.servlets.commands.factory.CommandEnum.GET_BRANDS;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.BRAND_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.BRAND_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOR_UPDATE_PATTERN;

@Slf4j
public class ChangeBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        long brandId = Extractor.extractLongVal(req, OBJECT_ID, null);
        String brandName = Extractor.extractString(req, OBJECT_NAME, null);
        Boolean isActive = Boolean.valueOf(req.getParameter(IS_ACTIVE));

        BrandDTO forUpdate = BrandDTO.builder()
                .id(brandId)
                .name(brandName)
                .isActive(isActive)
                .build();
        log.info(OBJECT_FOR_UPDATE_PATTERN, forUpdate);

        try {
            brandService.update(forUpdate);
        } catch (ObjectAlreadyExist | ValidationException e) {
            req.setAttribute(ERROR, e.getMessage());
        }

        return Extractor.extractLastPage(req);
    }

}