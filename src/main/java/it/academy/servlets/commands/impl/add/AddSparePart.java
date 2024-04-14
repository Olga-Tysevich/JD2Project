package it.academy.servlets.commands.impl.add;

import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.SparePartService;
import it.academy.services.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowSparePartTable;
import it.academy.servlets.extractors.impl.ExtractorImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.*;

public class AddSparePart implements ActionCommand {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        try {
            ChangeSparePartDTO sparePartDTO = ExtractorImpl.extract(req, new ChangeSparePartDTO());
            List<Long> deviceTypesId = getDeviceTypeId(req);
            sparePartDTO.setDeviceTypeIdList(deviceTypesId);
            sparePartService.createSparePart(sparePartDTO);

        } catch (IllegalArgumentException | AccessDenied e) {
            System.out.println("error " + e.getMessage());
            req.setAttribute(ERROR, e.getMessage());
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowSparePartTable().execute(req);
    }

    protected List<Long> getDeviceTypeId(HttpServletRequest req) {
        String[] deviceTypesId = req.getParameterValues(DEVICE_TYPE_ID);
        if (deviceTypesId == null) {
            return new ArrayList<>();
        }

        List<String> deviceTypeId = List.of(deviceTypesId);
        deviceTypeId.forEach(System.out::println);
        return deviceTypeId.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

    }
}
