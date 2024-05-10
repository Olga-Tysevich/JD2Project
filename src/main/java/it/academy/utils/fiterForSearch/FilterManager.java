package it.academy.utils.fiterForSearch;

import it.academy.entities.account.Account_;
import it.academy.entities.account.ServiceCenter_;
import it.academy.entities.device.Device_;
import it.academy.entities.device.Model_;
import it.academy.entities.repair.Repair_;
import it.academy.entities.spare_part.SparePartOrder_;
import it.academy.entities.spare_part.SparePart_;
import lombok.experimental.UtilityClass;

import java.util.List;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.JSPConstant.BUYER_SURNAME_FILTER;

@UtilityClass
public class FilterManager {

    public boolean isDeviceFilter(String filter) {
        switch (filter) {
            case Device_.SERIAL_NUMBER:
            case Device_.BUYER:
            case Device_.DATE_OF_SALE:
            case SALESMAN_NAME_FILTER:
            case BUYER_SURNAME_FILTER:
            case Model_.NAME:
            case Model_.BRAND:
            case Model_.TYPE:
                return true;
            default:
                return false;
        }
    }

    public boolean isModelFilter(String filter) {
        switch (filter) {
            case Model_.NAME:
            case Model_.BRAND:
            case Model_.TYPE:
                return true;
            default:
                return false;
        }
    }

    public boolean isServiceCenterFilter(String filter) {
        switch (filter) {
            case ServiceCenter_.ID:
            case ServiceCenter_.SERVICE_NAME:
                return true;
            default:
                return false;
        }
    }

    public boolean isRepairTypeFilter(String filter) {
        return REPAIR_TYPE.equals(filter);
    }

    public boolean isRequisitesFilter(String filter) {
        switch (filter) {
            case EMAIL:
            case SERVICE_CENTER_LEGAL_ADDRESS:
            case SERVICE_CENTER_ACTUAL_ADDRESS:
            case SERVICE_CENTER_PHONE:
                return true;
            default:
                return false;
        }
    }


    public List<String> getFiltersForRepair() {
        return List.of(REPAIR_STATUS,
                REPAIR_CATEGORY,
                ServiceCenter_.SERVICE_NAME,
                Repair_.REPAIR_NUMBER,
                Device_.SERIAL_NUMBER,
                BUYER_SURNAME_FILTER,
                SALESMAN_NAME_FILTER,
                Device_.DATE_OF_SALE,
                Repair_.START_DATE,
                Repair_.END_DATE,
                Model_.BRAND,
                Model_.TYPE,
                Device_.MODEL,
                REPAIR_TYPE);
    }

    public List<String> getFiltersForAccount() {
        return List.of(ServiceCenter_.SERVICE_NAME,
                Account_.EMAIL,
                Account_.USER_NAME,
                Account_.USER_SURNAME);
    }

    public List<String> getFiltersForServiceCenter() {
        return List.of(ServiceCenter_.SERVICE_NAME,
                EMAIL,
                SERVICE_CENTER_ACTUAL_ADDRESS,
                SERVICE_CENTER_LEGAL_ADDRESS,
                SERVICE_CENTER_PHONE);
    }


    public List<String> getFiltersForModel() {
        return List.of(Model_.NAME,
                Model_.BRAND,
                Model_.TYPE);
    }

    public List<String> getFiltersForSparePart() {
        return List.of(SparePart_.NAME, SparePart_.MODELS);
    }

    public List<String> getFiltersForSparePartOrder() {
        return List.of(Repair_.REPAIR_NUMBER,
                ServiceCenter_.SERVICE_NAME,
                SparePartOrder_.ORDER_DATE,
                SparePartOrder_.DEPARTURE_DATE,
                SparePartOrder_.DELIVERY_DATE);
    }

}
