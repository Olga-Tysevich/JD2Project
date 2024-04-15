package it.academy.servlets.commands.impl.change;

import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;

public class ChangeRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        try {



//            return FormExtractor.extract(req,
//                    (a) -> ThrowingConsumerWrapper.apply(() -> repairService.updateRepair((RepairDTO) a)),
//                    (id) -> repairService.findRepair((Long) id),
//                    RepairDTO.class,
//                    REPAIR,
//                    REPAIR_PAGE_PATH,
//                    () -> new ShowRepairTable().execute(req));
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }

    }

}
