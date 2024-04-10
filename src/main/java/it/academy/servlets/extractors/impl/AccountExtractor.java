package it.academy.servlets.extractors.impl;

import it.academy.dto.AccountDTO;
import it.academy.dto.ListForPage;
import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.entities.RoleEnum;
import it.academy.services.AdminService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.TableManager;
import javax.servlet.http.HttpServletRequest;
import static it.academy.utils.Constants.*;

public class AccountExtractor implements Extractor<AccountDTO> {
    private AdminService adminService = new AdminServiceImpl();
    private AccountDTO account;

    @Override
    public void extractValues(HttpServletRequest req) {
        Long objectId = req.getParameter(OBJECT_ID) != null?
                Long.parseLong(req.getParameter(OBJECT_ID)) : null;
        String email = req.getParameter(ACCOUNT_EMAIL);
        String userName = req.getParameter(USER_NAME);
        String userSurname = req.getParameter(USER_SURNAME);
        RoleEnum role = RoleEnum.valueOf(req.getParameter(ROLE));
        Object test = req.getAttribute(ACCOUNT_REPAIR_WORKSHOP);

        System.out.println("object " + test);



        RepairWorkshopDTO repairWorkshop = (RepairWorkshopDTO) test;
        Boolean isActive = req.getParameter(IS_ACTIVE) != null && Boolean.parseBoolean(req.getParameter(IS_ACTIVE));
        this.account = AccountDTO.builder()
                .id(objectId)
                .email(email)
                .userName(userName)
                .userSurname(userSurname)
                .role(role)
                .isActive(isActive)
                .build();
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<AccountDTO> brands;
        if (input != null && !input.isBlank()) {
            brands = adminService.findAccount(pageNumber, filter, input);
        } else {
            brands = adminService.findAccount(pageNumber);
        }

        TableManager.insertAttributesForTable(req, brands, ACCOUNT_TABLE_PAGE_PATH);
    }

    @Override
    public void addParameter(String parameterName, Object parameter) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return null;
    }

    @Override
    public AccountDTO getResult() {
        return account;
    }
}