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
        Long objectId = req.getParameter(OBJECT_ID) != null ?
                Long.parseLong(req.getParameter(OBJECT_ID)) : null;
        String email = req.getParameter(ACCOUNT_EMAIL);
        String userName = req.getParameter(USER_NAME);
        String userSurname = req.getParameter(USER_SURNAME);
        RoleEnum role = req.getParameter(ROLE) != null? RoleEnum.valueOf(req.getParameter(ROLE)) : RoleEnum.SERVICE_CENTER;
        Long repairWorkshopId = req.getParameter(REPAIR_WORKSHOP_ID) != null ?
                Long.parseLong(req.getParameter(REPAIR_WORKSHOP_ID)) : null;

        if (RoleEnum.ADMIN.equals(role)) {
            repairWorkshopId = null;
        }

        Boolean isActive = req.getParameter(IS_ACTIVE) != null && Boolean.parseBoolean(req.getParameter(IS_ACTIVE));
        this.account = AccountDTO.builder()
                .id(objectId)
                .email(email)
                .userName(userName)
                .userSurname(userSurname)
                .role(role)
                .repairWorkshop(RepairWorkshopDTO.builder()
                        .id(repairWorkshopId)
                        .build())
                .isActive(isActive)
                .build();
        System.out.println(this.account);
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<AccountDTO> accounts;
        if (input != null && !input.isBlank()) {
            accounts = adminService.findAccount(pageNumber, filter, input);
        } else {
            accounts = adminService.findAccount(pageNumber);
        }

        TableManager.insertAttributesForTable(req, accounts, ACCOUNT_TABLE_PAGE_PATH);
        req.setAttribute(MAX_PAGE, accounts.getMaxPageNumber());
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