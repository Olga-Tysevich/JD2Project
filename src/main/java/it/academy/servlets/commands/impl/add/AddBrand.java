package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.BrandDTO;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.ERROR;
import static it.academy.utils.constants.Constants.OBJECT_NAME;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOR_SAVE_PATTERN;

@Slf4j
public class AddBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        CommandHelper.checkRole(req);
        String brandName = Extractor.extractString(req, OBJECT_NAME, null);

        BrandDTO forCreate = BrandDTO.builder()
                .name(brandName)
                .isActive(true)
                .build();
        log.info(OBJECT_FOR_SAVE_PATTERN, forCreate);

        try {
            brandService.create(forCreate);
        } catch (ObjectAlreadyExist | ValidationException e) {
            req.setAttribute(ERROR, e.getMessage());
        }
        return Extractor.extractLastPage(req);
    }

}
