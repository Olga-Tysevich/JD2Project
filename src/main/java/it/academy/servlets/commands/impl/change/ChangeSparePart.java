package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.device.SparePartService;
import it.academy.services.device.impl.SparePartServiceImpl;
import it.academy.servlets.commands.impl.add.AddSparePart;
import it.academy.servlets.commands.impl.show.forms.ShowNewSparePart;
import it.academy.servlets.commands.impl.show.forms.ShowSparePart;
import it.academy.servlets.commands.impl.show.tables.ShowSparePartTable;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOR_SAVE_PATTERN;

@Slf4j
public class ChangeSparePart extends AddSparePart {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        CommandHelper.checkRole(req);
        ChangeSparePartDTO forUpdate = Extractor.extract(req, new ChangeSparePartDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        try {
            List<Long> modelsId = getModelsId(req);
            forUpdate.setModelIdList(modelsId);
            log.info(OBJECT_FOR_SAVE_PATTERN, forUpdate);
            sparePartService.updateSparePart(forUpdate);
        } catch (IllegalArgumentException | ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return new ShowSparePart().execute(req);
        }

        return new ShowSparePartTable().execute(req);
    }
}
