package it.academy.servlets.commands.impl.show.tables;

import it.academy.dto.ListForPage;
import it.academy.dto.TableReq;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TYPE_TABLE;
import static it.academy.utils.constants.Constants.LIST_FOR_PAGE;
import static it.academy.utils.constants.Constants.MAIN_PAGE_PATH;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowRepairTypeTable implements ActionCommand {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        ListForPage<RepairTypeDTO> repairTypes;
        TableReq dataFromPage = Extractor.extract(req, new TableReq());
        boolean findByFilters = dataFromPage.getFilter() != null && dataFromPage.getInput() != null;

        if (findByFilters) {
            repairTypes = repairTypeService.findRepairTypes(
                    dataFromPage.getPageNumber(),
                    dataFromPage.getFilter(),
                    dataFromPage.getInput());
        } else {
            repairTypes = repairTypeService.findRepairTypes(dataFromPage.getPageNumber());
        }

        repairTypes.setPage(dataFromPage.getPage());
        repairTypes.setCommand(SHOW_REPAIR_TYPE_TABLE.name());
        log.info(CURRENT_TABLE, repairTypes);
        req.setAttribute(LIST_FOR_PAGE, repairTypes);
        return MAIN_PAGE_PATH;
    }

}