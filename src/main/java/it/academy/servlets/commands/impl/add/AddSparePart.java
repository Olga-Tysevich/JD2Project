package it.academy.servlets.commands.impl.add;

import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.Extractor;
import it.academy.servlets.extractors.impl.SparePartExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.DEVICE_TYPE_ID;
import static it.academy.utils.Constants.MAIN_PAGE_PATH;

public class AddSparePart implements ActionCommand {
    private AdminService adminService = new AdminServiceImpl();
    private Extractor<SparePartDTO> extractor = new SparePartExtractor();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        extractor.extractValues(req);

        List<String> deviceTypeId = List.of(req.getParameterValues(DEVICE_TYPE_ID));
        if (deviceTypeId.isEmpty()) {
            return null;
        }
        List<Long> idList = deviceTypeId.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        SparePartDTO sparePartDTO = extractor.getResult();
        adminService.addSparePart(sparePartDTO, idList);

        extractor.insertAttributes(req);

        return MAIN_PAGE_PATH;
    }
}
