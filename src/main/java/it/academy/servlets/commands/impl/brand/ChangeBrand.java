package it.academy.servlets.commands.impl.brand;

import it.academy.dto.device.req.BrandDTO;
import it.academy.services.device.BrandService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ChangeBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            return FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> brandService.updateBrand((BrandDTO) a)),
                    (id) -> brandService.findBrand((Long) id),
                    BrandDTO.class,
                    BRAND,
                    BRAND_PAGE_PATH,
                    () -> new ShowBrandTable().execute(req));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR_PAGE_PATH;
        }

    }

}