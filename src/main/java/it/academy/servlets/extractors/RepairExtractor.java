package it.academy.servlets.extractors;

import it.academy.dto.repair.RepairDTO;
import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.BUYER_SURNAME_FILTER;
import static it.academy.utils.constants.JSPConstant.SALESMAN_NAME_FILTER;

@UtilityClass
public class RepairExtractor {

    public RepairDTO extract(HttpServletRequest request) {

        long serviceCenterId = Extractor.extractLongVal(request, SERVICE_CENTER_ID, DEFAULT_ID);
        RepairStatus repairStatus = Extractor.extractRepairStatus(request);
        RepairCategory repairCategory = Extractor.extractRepairCategory(request);
        long brandId = Extractor.extractLongVal(request, SELECTED_BRAND_ID, DEFAULT_ID);
        long modelID = Extractor.extractLongVal(request, MODEL_ID, DEFAULT_ID);
        String serialNumber = Extractor.extractString(request, SERIAL_NUMBER, DEFAULT_VALUE);
        String defectDescription = Extractor.extractString(request, DEFECT_DESCRIPTION, DEFAULT_VALUE);
        String repairNumber = Extractor.extractString(request, SERVICE_CENTER_REPAIR_NUMBER, DEFAULT_VALUE);
        Date dateOfSale = Extractor.extractDate(request, DATE_OF_SALE);
        String salesmanName = Extractor.extractString(request, SALESMAN_NAME_FILTER, DEFAULT_VALUE);
        String salesmanPhone = Extractor.extractString(request, SALESMAN_PHONE, DEFAULT_VALUE);
        String buyerName = Extractor.extractString(request, BUYER_NAME, DEFAULT_VALUE);
        String buyerSurname = Extractor.extractString(request, BUYER_SURNAME_FILTER, DEFAULT_VALUE);
        String buyerPhone = Extractor.extractString(request, BUYER_PHONE, DEFAULT_VALUE);
        return null;




    }
}
