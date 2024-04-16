package it.academy.servlets.commands.impl.change;

import it.academy.dto.req.BrandDTO;
import it.academy.services.BrandService;
import it.academy.services.impl.BrandServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.show.tables.ShowBrandTable;
import it.academy.servlets.extractors.FormExtractor;
import it.academy.utils.interfaces.wrappers.ThrowingConsumerWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

@Slf4j
public class ChangeBrand implements ActionCommand {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        try {
            String result = FormExtractor.extract(req,
                    (a) -> ThrowingConsumerWrapper.apply(() -> brandService.updateBrand((BrandDTO) a)),
                    (id) -> brandService.findBrand((Long) id),
                    BrandDTO.class,
                    BRAND,
                    BRAND_PAGE_PATH,
                    () -> new ShowBrandTable().execute(req));
            log.info(String.format(CURRENT_PAGE, result));
            return result;
        } catch (Exception e) {
            return ERROR_PAGE_PATH;
        }

    }

}