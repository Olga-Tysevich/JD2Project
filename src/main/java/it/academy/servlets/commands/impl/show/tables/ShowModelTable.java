package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TableReq;
import it.academy.dto.ListForPage;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.servlets.commands.factory.CommandEnum.SHOW_MODEL_TABLE;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;
import static it.academy.utils.constants.JSPConstant.MODEL_TABLE_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;
import static it.academy.utils.constants.LoggerConstants.OBJECT_EXTRACTED_PATTERN;

@Slf4j
public class ShowModelTable implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

        ListForPage<ModelDTO> models;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        log.info(OBJECT_EXTRACTED_PATTERN, dataFromPage);
        models = modelService.findModels(
                dataFromPage.getPageNumber(),
                dataFromPage.getFilter(),
                dataFromPage.getInput());
        models.setPage(MODEL_TABLE_PAGE_PATH);
        models.setCommand(SHOW_MODEL_TABLE.name());
        req.setAttribute(LIST_FOR_PAGE, models);
        req.getSession().setAttribute(FILTER, dataFromPage.getFilter());
        req.getSession().setAttribute(USER_INPUT, dataFromPage.getInput());
        return RoleEnum.ADMIN.equals(accountDTO.getRole()) ? ADMIN_MAIN_PAGE_PATH : MAIN_PAGE_PATH;
    }
}