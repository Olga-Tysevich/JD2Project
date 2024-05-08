package it.academy.servlets.commands.impl.get.tables;

import it.academy.dto.TablePage2;
import it.academy.dto.TablePageReq;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.CommandHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import static it.academy.utils.constants.LoggerConstants.CURRENT_TABLE;

@Slf4j
public class ShowRepairTypeTable extends ShowTable {
    private RepairTypeService repairTypeService = new RepairTypeServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        TablePageReq reqData = Extractor.extractDataForTable(req);
        String filter = Objects.toString(reqData.getFilter(), StringUtils.EMPTY);

        TablePage2<RepairTypeDTO> repairTypes = StringUtils.EMPTY.equals(filter) ?
                repairTypeService.findForPage(reqData.getPageNumber())
                : repairTypeService.findForPageByFilter(reqData.getPageNumber(), reqData.getFilter(), reqData.getInput());

        CommandHelper.insertTableData(req, reqData, repairTypes);
        log.info(CURRENT_TABLE, repairTypes);

        return Extractor.extractMainPagePath(req);
    }

}