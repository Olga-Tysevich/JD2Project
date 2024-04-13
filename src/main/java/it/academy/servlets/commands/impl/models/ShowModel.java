package it.academy.servlets.commands.impl.models;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.device.req.BrandDTO;
import it.academy.dto.device.req.DeviceTypeDTO;
import it.academy.dto.device.req.ModelDTO;
import it.academy.services.device.BrandService;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static it.academy.utils.Constants.*;

public class ShowModel implements ActionCommand {
    private ModelService modelService = new ModelServiceImpl();
    private BrandService brandService = new BrandServiceImpl();
    private DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();


    @Override
    public String execute(HttpServletRequest req) {

        AccountDTO currentAccountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);

        long modelId = Long.parseLong(req.getParameter(MODEL_ID));
        int pageNumber = Integer.parseInt(req.getParameter(PAGE_NUMBER));
        ModelDTO model = modelService.findModel(modelId);
        List<BrandDTO> brands = brandService.findBrands(currentAccountDTO);
        List<DeviceTypeDTO> deviceTypes = deviceTypeService.findDeviceTypes();

        req.setAttribute(DEVICE_TYPES, deviceTypes);
        req.setAttribute(BRANDS, brands);
        req.setAttribute(MODEL, model);
        req.setAttribute(PAGE_NUMBER, pageNumber);

        return MODEL_PAGE_PATH;
    }

}
