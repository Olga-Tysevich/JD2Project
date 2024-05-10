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
import static it.academy.utils.constants.JSPConstant.COMPONENT_FILTERS_PAGE_PATH;

public class ShowRepairTypeTable implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        TablePageReq reqData = Extractor.extractDataForTable(req, COMPONENT_FILTERS_PAGE_PATH);
        TablePage<RepairTypeDTO> repairTypes = repairTypeService.findForPage(reqData.getPageNumber(), reqData.getUserInput());
        CommandHelper.insertTableData(req, reqData, repairTypes);
        return Extractor.extractMainPagePath(req);
    }

}