package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.TablePage;
import it.academy.dto.TablePageReq;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import static it.academy.utils.constants.Constants.OBJECT_NAME;
import static it.academy.utils.constants.JSPConstant.COMPONENT_FILTERS_PAGE_PATH;

public class ShowRepairTypeTable implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Map<String, String> userInput = Extractor.extractParameterMap(req, List.of(OBJECT_NAME));
        TablePageReq reqData = Extractor.extractDataForTable(req);
        reqData.setUserInput(userInput);
        reqData.setFilterPage(COMPONENT_FILTERS_PAGE_PATH);
        TablePage<RepairTypeDTO> brands = repairTypeService.findForPage(reqData.getPageNumber(), userInput);
        CommandHelper.insertTableData(req, reqData, brands);
        return Extractor.extractMainPagePath(req);
    }

}