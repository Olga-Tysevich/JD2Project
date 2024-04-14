package it.academy.servlets.commands.impl.add;

import it.academy.dto.resp.DeviceDTOResp;
import it.academy.dto.resp.RepairDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairExtractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();
    private Extractor extractor = new RepairExtractor();

    @Override
    public String execute(HttpServletRequest req) {

        extractor.extractValues(req);

        long lastBrandId = (long) extractor.getParameter(BRAND_ID);
        long currentBrandId = (long) extractor.getParameter(CURRENT_BRAND_ID);

        extractor.insertAttributes(req);

        if (!(currentBrandId == lastBrandId)) {
            return REPAIR_PAGE_PATH;
        }

        RepairDTO repairDTO = (RepairDTO) extractor.getParameter(REPAIR);
        DeviceDTOResp deviceDTOResp = repairDTO.getDevice();
        deviceDTOResp.setId(null);
        DeviceDTOResp savedDevice = repairService.addDevice(deviceDTOResp);
        repairDTO.setDevice(savedDevice);

        repairService.addRepair(repairDTO);
        return MAIN_PAGE_PATH;
    }

}
