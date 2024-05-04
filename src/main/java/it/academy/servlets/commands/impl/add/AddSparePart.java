package it.academy.servlets.commands.impl.add;

import it.academy.dto.spare_part.ChangeSparePartDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.tables.GetSpareParts;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static it.academy.servlets.commands.factory.CommandEnum.ADD_SPARE_PART;
import static it.academy.servlets.commands.factory.CommandEnum.GET_SPARE_PARTS;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOR_SAVE_PATTERN;

@Slf4j
public class AddSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("in add spare pert");
        CommandHelper.checkRole(req);
        ChangeSparePartDTO forCreate = Extractor.extract(req, new ChangeSparePartDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);

        try {
            List<Long> modelsId = getModelsId(req);
            forCreate.setModelIdList(modelsId);
            log.info(OBJECT_FOR_SAVE_PATTERN, forCreate);
            sparePartService.create(forCreate);
        } catch (IllegalArgumentException | ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return CommandHelper.insertFormData(req,
                    SPARE_PART_TABLE_PAGE_PATH,
                    SPARE_PART_PAGE_PATH,
                    ADD_SPARE_PART,
                    GET_SPARE_PARTS);
        }

        return new GetSpareParts().execute(req, resp);
    }

    protected List<Long> getModelsId(HttpServletRequest req) {
        String[] modelsIdArray = req.getParameterValues(SPARE_PART_MODEL_ID);
        if (modelsIdArray == null) {
            return new ArrayList<>();
        }

        List<String> modelsId = List.of(modelsIdArray);
        return modelsId.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

    }
}
