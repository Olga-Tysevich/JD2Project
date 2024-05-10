package it.academy.utils.fiterForSearch;

import it.academy.entities.account.Account_;
import it.academy.entities.account.ServiceCenter_;
import it.academy.entities.device.Device_;
import it.academy.entities.device.Model;
import it.academy.entities.device.Model_;
import it.academy.entities.repair.RepairType;
import it.academy.entities.repair.RepairType_;
import it.academy.entities.repair.Repair_;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;
import java.util.List;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.REPAIR_TYPE_DESCRIPTION_FILTER;
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



    public static List<String> getFiltersForRepair() {
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

    public static List<String> getFiltersForPage(@NotNull String pagePath) {
        switch (pagePath) {
            case ACCOUNT_TABLE_PAGE_PATH:
                return getFiltersForAccount();
            case BRAND_TABLE_PAGE_PATH:
                return getFiltersForBrand();
            case DEVICE_TYPE_TABLE_PAGE_PATH:
                return getFiltersForDeviceType();
            case MODEL_TABLE_PAGE_PATH:
                return getFiltersForModel();
            case REPAIR_TYPE_TABLE_PAGE_PATH:
                return getFiltersForRepairType();

            default:
                return null;
        }
    }

    public static List<String> getFiltersForAccount() {
        return List.of(Account_.EMAIL,
                Account_.USER_NAME,
                Account_.USER_SURNAME,
                Account_.SERVICE_CENTER);
    }

    public static List<String> getFiltersForServiceCenter() {
        return List.of(ServiceCenter_.SERVICE_NAME,
                EMAIL,
                SERVICE_CENTER_ACTUAL_ADDRESS,
                SERVICE_CENTER_LEGAL_ADDRESS,
                SERVICE_CENTER_PHONE);
    }


    public static List<String> getFiltersForModel() {
        return List.of(Model_.NAME,
                Model_.BRAND,
                Model_.TYPE);
    }


    public static List<String> getFiltersForBrand() {
        return List.of(OBJECT_NAME);
    }

    public static List<String> getFiltersForDeviceType() {
        return List.of(OBJECT_NAME);
    }


    public static List<String> getFiltersForRepairType() {
        return List.of(RepairType_.CODE,
                RepairType_.NAME,
                RepairType_.LEVEL);
    }


    public static List<EntityFilter> getFiltersForSparePart() {
        return List.of();
    }

    public List<EntityFilter> getFiltersForSparePartOrder() {
        return List.of(new EntityFilter(ORDER_DATE, ORDER_DATE_DESCRIPTION),
                new EntityFilter(DEPARTURE_DATE, ORDER_DEPARTURE_DATE_DESCRIPTION),
                new EntityFilter(DELIVERY_DATE, ORDER_DELIVERY_DATE_DESCRIPTION));
    }

}
