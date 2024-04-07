package it.academy.servlets.commands.impl.add;

import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.services.RepairService;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.RepairExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class AddRepair implements ActionCommand {
    private RepairService repairService = new RepairServiceImpl();
    private Extractor extractor = new RepairExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        long lastBrandId = (long) extractor.getParameter(BRAND_ID);
        long currentBrandId = (long) extractor.getParameter(CURRENT_BRAND_ID);

        extractor.insertAttributes(req);

        if (!(currentBrandId == lastBrandId)) {
            return REPAIR_PAGE_PATH;
        }

        RepairDTO repairDTO = (RepairDTO) extractor.getParameter(REPAIR);
        ModelDTO modelDTO = repairService.findModel(repairDTO.getModelId());

        DeviceDTO deviceDTO = DeviceDTO.builder()
                .model(modelDTO)
                .serialNumber(repairDTO.getSerialNumber())
                .dateOfSale(repairDTO.getDateOfSale())
                .salesmanName(repairDTO.getSalesmanName())
                .salesmanPhone(repairDTO.getSalesmanPhone())
                .buyerName(repairDTO.getBuyerName())
                .buyerSurname(repairDTO.getBuyerSurname())
                .buyerPhone(repairDTO.getBuyerPhone())
                .build();

        repairService.addDevice(deviceDTO);
        repairService.addRepair(repairDTO);
        return MAIN_PAGE_PATH;
    }

}
