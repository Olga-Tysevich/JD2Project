package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ChangeModelDTO;
import it.academy.dto.device.ModelForChangeDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.tables.GetModels;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.servlets.commands.factory.CommandEnum.ADD_MODEL;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.MODEL_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOR_SAVE_PATTERN;

@Slf4j
public class AddModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<BrandDTO> test = (List<BrandDTO>) req.getAttribute("BRANDS");

        CommandHelper.checkRole(req);
        String name = Extractor.extractString(req, OBJECT_NAME, null);
        Long brandId = Extractor.extractLongVal(req, BRAND_ID, null);
        Long deviceTypeId = Extractor.extractLongVal(req, TYPE_ID, null);

        ChangeModelDTO forCreate = ChangeModelDTO.builder()
                .name(name)
                .brandId(brandId)
                .deviceTypeId(deviceTypeId)
                .isActive(true)
                .build();
        log.info(OBJECT_FOR_SAVE_PATTERN, forCreate);

       ModelForChangeDTO modelForm = modelService.create(forCreate);
       req.setAttribute(MODEL, modelForm);
       String errorMessage = modelForm.getErrorMessage();

       if (!StringUtils.isBlank(errorMessage)) {
           req.setAttribute(ERROR, errorMessage);
           return CommandHelper.insertFormData(req, MODEL_PAGE_PATH, ADD_MODEL);
       }

        return Extractor.extractLastPage(req);
    }

}
