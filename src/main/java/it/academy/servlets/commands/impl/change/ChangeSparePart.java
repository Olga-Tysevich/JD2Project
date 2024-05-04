package it.academy.servlets.commands.impl.change;

import it.academy.dto.spare_part.ChangeSparePartDTO;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.impl.add.AddSparePart;
import it.academy.servlets.commands.impl.get.tables.GetSpareParts;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.servlets.commands.factory.CommandEnum.CHANGE_SPARE_PART;
import static it.academy.servlets.commands.factory.CommandEnum.GET_SPARE_PARTS;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.SPARE_PART_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOR_SAVE_PATTERN;

@Slf4j
public class ChangeSparePart extends AddSparePart {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("in change spare part");
        CommandHelper.checkRole(req);
        ChangeSparePartDTO forUpdate = Extractor.extract(req, new ChangeSparePartDTO());
        log.info(OBJECT_EXTRACTED_PATTERN, forUpdate);

        try {
            List<Long> modelsId = getModelsId(req);
            forUpdate.setModelIdList(modelsId);
            log.info(OBJECT_FOR_SAVE_PATTERN, forUpdate);
            sparePartService.update(forUpdate);
        } catch (IllegalArgumentException | ObjectAlreadyExist e) {
            req.setAttribute(ERROR, e.getMessage());
            return CommandHelper.insertFormData(req,
                    SPARE_PART_TABLE_PAGE_PATH,
                    SPARE_PART_PAGE_PATH,
                    CHANGE_SPARE_PART,
                    GET_SPARE_PARTS);
        }

        return new GetSpareParts().execute(req, resp);
    }
}
