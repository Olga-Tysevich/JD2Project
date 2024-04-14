package it.academy.servlets.commands.impl.spare_part;

import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.resp.SparePartDTO;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.spare_part.SparePartService;
import it.academy.services.spare_part.impl.SparePartServiceImpl;
import it.academy.servlets.extractors.ExtractorImpl;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ChangeSparePart extends AddSparePart {
    private SparePartService sparePartService = new SparePartServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        try {
            ChangeSparePartDTO sparePartDTO = ExtractorImpl.extract(req, new ChangeSparePartDTO());
            List<Long> deviceTypesId = getDeviceTypeId(req);
            sparePartDTO.setDeviceTypeIdList(deviceTypesId);
            System.out.println("change spare part " + sparePartDTO);
            sparePartService.updateSparePart(sparePartDTO);
        } catch (IllegalArgumentException | AccessDenied e) {
            System.out.println("error " + e.getMessage());
            req.setAttribute(ERROR, e.getMessage());
            long sparePartId = req.getParameter(SPARE_PART_ID) != null?
                    Long.parseLong(req.getParameter(SPARE_PART_ID)) : DEFAULT_ID;

            req.setAttribute(SPARE_PART, sparePartService.findSparePart(sparePartId));
            req.setAttribute(PAGE, SPARE_PART_PAGE_PATH);
            req.setAttribute(PAGE_NUMBER, pageNumber);

            return SPARE_PART_PAGE_PATH;
        }

        req.setAttribute(PAGE, req.getParameter(PAGE));
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return new ShowSparePartTable().execute(req);
    }

}
