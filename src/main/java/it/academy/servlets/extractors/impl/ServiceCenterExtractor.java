package it.academy.servlets.extractors.impl;

import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.servlets.extractors.Extractor;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.*;

public class ServiceCenterExtractor implements Extractor<ServiceCenterDTO> {
    private ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
    private ServiceCenterDTO repairType;


    @Override
    public void extractValues(HttpServletRequest req) {
        Long repairWorkshopId = req.getParameter(SERVICE_CENTER_ID) != null?
                Long.parseLong(req.getParameter(SERVICE_CENTER_ID)) : null;
        String name = req.getParameter(SERVICE_CENTER_NAME);
        String bankAccount = req.getParameter(SERVICE_CENTER_BANK_ACCOUNT);
        String bankCode = req.getParameter(SERVICE_CENTER_BANK_CODE);
        String bankName = req.getParameter(SERVICE_CENTER_BANK_NAME);
        String bankAddress = req.getParameter(SERVICE_CENTER_BANK_ADDRESS);
        String fullName = req.getParameter(SERVICE_CENTER_FULL_NAME);
        String actualAddress = req.getParameter(SERVICE_CENTER_ACTUAL_ADDRESS);
        String legalAddress = req.getParameter(SERVICE_CENTER_LEGAL_ADDRESS);
        String phone = req.getParameter(SERVICE_CENTER_PHONE);
        String email = req.getParameter(SERVICE_CENTER_EMAIL);
        String taxpayerNumber = req.getParameter(SERVICE_CENTER_TAXPAYER_NUMBER);
        String registrationNumber = req.getParameter(SERVICE_CENTER_REGISTRATION_NUMBER);
        Boolean isActive = req.getParameter(IS_ACTIVE) != null;

        this.repairType = ServiceCenterDTO.builder()
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
//                .taxpayerNumber(taxpayerNumber)
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

        ListForPage<ServiceCenterDTO> repairTypes;
        if (input != null && !input.isBlank()) {
            repairTypes = serviceCenterService.findServiceCenter(pageNumber, filter, input);
        } else {
            repairTypes = serviceCenterService.findServiceCenter(pageNumber);
        }

//        PageManager.insertAttributesForTable(req, repairTypes, SERVICE_CENTER_TABLE_PAGE_PATH);
    }

    @Override
    public void addParameter(String parameterName, Object parameter) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return null;
    }

    @Override
    public ServiceCenterDTO getResult() {
        return repairType;
    }

}