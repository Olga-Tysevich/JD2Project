package it.academy.servlets.extractors.impl;

import it.academy.dto.ListForPage;
import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.extractors.Extractor;
import it.academy.utils.TableManager;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class RepairWorkshopExtractor implements Extractor<RepairWorkshopDTO> {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
    private RepairWorkshopDTO repairType;


    @Override
    public void extractValues(HttpServletRequest req) {
        Long repairWorkshopId = req.getParameter(REPAIR_WORKSHOP_ID) != null?
                Long.parseLong(req.getParameter(REPAIR_WORKSHOP_ID)) : null;
        String name = req.getParameter(REPAIR_WORKSHOP_SERVICE_NAME);
        String bankAccount = req.getParameter(REPAIR_WORKSHOP_BANK_ACCOUNT);
        String bankCode = req.getParameter(REPAIR_WORKSHOP_BANK_CODE);
        String bankName = req.getParameter(REPAIR_WORKSHOP_BANK_NAME);
        String bankAddress = req.getParameter(REPAIR_WORKSHOP_BANK_ADDRESS);
        String fullName = req.getParameter(REPAIR_WORKSHOP_FULL_NAME);
        String actualAddress = req.getParameter(REPAIR_WORKSHOP_ACTUAL_ADDRESS);
        String legalAddress = req.getParameter(REPAIR_WORKSHOP_LEGAL_ADDRESS);
        String phone = req.getParameter(REPAIR_WORKSHOP_PHONE);
        String email = req.getParameter(REPAIR_WORKSHOP_EMAIL);
        String taxpayerNumber = req.getParameter(REPAIR_WORKSHOP_TAXPAYER_NUMBER);
        String registrationNumber = req.getParameter(REPAIR_WORKSHOP_REGISTRATION_NUMBER);
        Boolean isActive = req.getParameter(IS_ACTIVE) != null;

        this.repairType = RepairWorkshopDTO.builder()
                .id(repairWorkshopId)
                .serviceName(name)
                .bankAccount(bankAccount)
                .bankCode(bankCode)
                .bankName(bankName)
                .bankAddress(bankAddress)
                .fullName(fullName)
                .actualAddress(actualAddress)
                .legalAddress(legalAddress)
                .phone(phone)
                .email(email)
                .taxpayerNumber(taxpayerNumber)
                .registrationNumber(registrationNumber)
                .isActive(isActive)
                .build();
    }

    @Override
    public void insertAttributes(HttpServletRequest req) {
        int pageNumber = req.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(req.getParameter(PAGE_NUMBER)) : FIRST_PAGE;

        String filter = req.getParameter(FILTER);
        String input = req.getParameter(USER_INPUT);

        ListForPage<RepairWorkshopDTO> repairTypes;
        if (input != null && !input.isBlank()) {
            repairTypes = serviceCenterService.findRepairWorkshops(pageNumber, filter, input);
        } else {
            repairTypes = serviceCenterService.findRepairWorkshops(pageNumber);
        }

        TableManager.insertAttributesForTable(req, repairTypes, REPAIR_WORKSHOP_TABLE_PAGE_PATH);
    }

    @Override
    public void addParameter(String parameterName, Object parameter) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return null;
    }

    @Override
    public RepairWorkshopDTO getResult() {
        return repairType;
    }

}