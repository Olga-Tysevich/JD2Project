package it.academy.servlets.commands.impl.change;

import it.academy.dto.device.BrandDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowBrand;
import it.academy.servlets.commands.impl.show.tables.ShowBrandTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ChangeBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);
        BrandDTO forUpdate = Extractor.extract(req, new BrandDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        try {
            brandService.updateBrand(forUpdate);
        } catch (ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return new ShowBrand().execute(req);
        }
        return new ShowBrandTable().execute(req);

    }

}