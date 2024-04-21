package it.academy.servlets.commands.impl.add;

import it.academy.dto.spare_part.ChangeSparePartDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.SparePartService;
import it.academy.services.device.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.forms.ShowNewSparePart;
import it.academy.servlets.commands.impl.show.tables.ShowSparePartTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOR_SAVE_PATTERN;

@Slf4j
public class AddSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);
        ChangeSparePartDTO forCreate = Extractor.extract(req, new ChangeSparePartDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forCreate);

        try {
            List<Long> modelsId = getModelsId(req);
            forCreate.setModelIdList(modelsId);
            log.info(OBJECT_FOR_SAVE_PATTERN, forCreate);
            sparePartService.createSparePart(forCreate);
        } catch (IllegalArgumentException | ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return new ShowNewSparePart().execute(req);
        }

        return new ShowSparePartTable().execute(req);
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
