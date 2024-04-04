package it.academy.servlets.commands;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.device.BrandDTOReq;
import it.academy.dto.req.device.DefectDTOReq;
import it.academy.dto.req.device.ModelDTOReq;
import it.academy.dto.req.repair.RepairCategoryDTOReq;
import it.academy.dto.req.repair.RepairDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.impl.AdminServiceImpl;
import it.academy.services.admin.impl.DefectsServiceImpl;
import it.academy.services.device.BrandService;
import it.academy.services.device.DefectService;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Constants.*;

public class ShowRepair implements ActionCommand{
    private AdminService adminService = new AdminServiceImpl();
    private RepairService repairService = new RepairServiceImpl();
    private BrandService brandService = new BrandServiceImpl();
    private ModelService modelService = new ModelServiceImpl();
    private DefectService defectService = new DefectsServiceImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        AccountDTOReq account = AccountDTOReq.builder()
                .email("user40@yahoo.com")
                .build();

        RespDTO<AccountDTO> response = adminService.findAdminAccountByEmail(account);
        AccountDTO accountDTO = response.getParameter();

        req.getSession().setAttribute(USER, accountDTO);

        int pageNumber = req.getParameter(PAGE_NUMBER) != null? Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        RespListDTO<RepairDTOReq> repairResp = repairService.findRepairs(pageNumber, LIST_SIZE);
        RespListDTO<RepairCategoryDTOReq> repairCategory = repairService.findRepairCategories();
        RespListDTO<BrandDTOReq> brands = brandService.findBrands();
        RespListDTO<ModelDTOReq> models = modelService.findModels();
        RespListDTO<DefectDTOReq> defects = defectService.findDefect();

        resp.setStatus(repairResp.getHttpStatus());
        req.setAttribute(REPAIR_CATEGORIES, repairCategory.getList());
        req.setAttribute(BRANDS, brands.getList());
        req.setAttribute(MODELS, models.getList());
        req.setAttribute(DEFECTS, defects.getList());
        req.setAttribute(PAGE_NUMBER, pageNumber);

//        return START_PAGE_PATH;
        return ConfigurationManager.getProperty(REPAIR_PAGE_PATH);

    }

}
