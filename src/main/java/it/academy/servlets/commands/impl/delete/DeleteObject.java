package it.academy.servlets.commands.impl.delete;

import it.academy.services.account.AccountService;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.services.device.BrandService;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.CommandEnum;
import it.academy.servlets.commands.impl.get.tables.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.ADMIN_MAIN_PAGE_PATH;

public class DeleteObject implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long id = Long.parseLong(req.getParameter(OBJECT_ID));
        CommandEnum command = CommandEnum.valueOf(req.getParameter(COMMAND));

        try {
            switch (command) {
                case DELETE_ACCOUNT: return deleteAccount(id, req, resp);
                case DELETE_BRAND: return deleteBrand(id, req, resp);
                case DELETE_DEVICE_TYPE: return deleteDeviceType(id, req, resp);
                case DELETE_MODEL: return deleteModel(id, req, resp);
                case DELETE_REPAIR_TYPE: return deleteRepairType(id, req, resp);
                case DELETE_SERVICE_CENTER: return deleteServiceCenter(id, req, resp);
                case DELETE_SPARE_PART: return deleteSparePart(id, req, resp);
                case DELETE_SPARE_PART_ORDER: return deleteSparePartOrder(id, req, resp);
            }

        } catch (Exception e) {
            req.setAttribute(ERROR, DELETE_FAILED_MESSAGE);
        }
        return ADMIN_MAIN_PAGE_PATH;
    }

    private String deleteAccount(long id, HttpServletRequest req, HttpServletResponse resp) {
        AccountService accountService = new AccountServiceImpl();
        accountService.deleteAccount(id);
        return new ShowAccountTable().execute(req, resp);
    }

    private String deleteBrand(long id, HttpServletRequest req, HttpServletResponse resp) {
        BrandService brandService = new BrandServiceImpl();
        brandService.deleteBrand(id);
        return new ShowBrandTable().execute(req, resp);
    }

    private String deleteDeviceType(long id, HttpServletRequest req, HttpServletResponse resp) {
        DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
        deviceTypeService.deleteDeviceType(id);
        return new GetDeviceTypeTable().execute(req, resp);
    }

    private String deleteModel(long id, HttpServletRequest req, HttpServletResponse resp) {
        ModelService modelService = new ModelServiceImpl();
        modelService.delete(id);
        return new GetModels().execute(req, resp);
    }

    private String deleteRepairType(long id, HttpServletRequest req, HttpServletResponse resp) {
        RepairTypeService repairTypeService = new RepairTypeServiceImpl();
        repairTypeService.delete(id);
        return new GetRepairTypeTable().execute(req, resp);
    }

    private String deleteServiceCenter(long id, HttpServletRequest req, HttpServletResponse resp) {
        ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
        serviceCenterService.deleteServiceCenter(id);
        return new ShowServiceCenterTable().execute(req, resp);
    }

    private String deleteSparePart(long id, HttpServletRequest req, HttpServletResponse resp) {
        SparePartService sparePartService = new SparePartServiceImpl();
        sparePartService.delete(id);
        return new GetSpareParts().execute(req, resp);
    }

    private String deleteSparePartOrder(long id, HttpServletRequest req, HttpServletResponse resp) {
        SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
        sparePartOrderService.delete(id);
        return new GetSparePartOrders().execute(req, resp);
    }
}
