package it.academy.servlets.commands.impl.get.tables;

import it.academy.dto.TablePageReq;
import it.academy.dto.TablePage;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowModelTable extends ShowTable {
    private ModelService modelService = new ModelServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

        TablePageReq dataForPage = Extractor.extractDataForTable(req);
        TablePage<ModelDTO> models = modelService.findModels(
                dataForPage.getPageNumber(),
                dataForPage.getFilter(),
                dataForPage.getInput());
        setTableData(req, dataForPage, models);
        log.info(CURRENT_TABLE, models);
        return RoleEnum.ADMIN.equals(accountDTO.getRole()) ? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;
    }
}