package it.academy.servlets.commands.impl.tables;

import it.academy.dto.device.req.DeviceTypeDTO;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.DeviceTypeExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowDeviceTypeTable implements ActionCommand {
    private Extractor<DeviceTypeDTO> extractor = new DeviceTypeExtractor();

    @Override
    public String execute(HttpServletRequest req) {

        extractor.insertAttributes(req);
        req.setAttribute(COMMAND, SHOW_DEVICE_TYPE_TABLE);

        return MAIN_PAGE_PATH;
    }

}
