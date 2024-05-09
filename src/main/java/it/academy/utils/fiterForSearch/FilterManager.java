package it.academy.utils.fiterForSearch;

import it.academy.entities.account.Account_;
import it.academy.entities.account.ServiceCenter_;
import it.academy.entities.account.embeddable.Requisites_;
import it.academy.entities.device.Device_;
import it.academy.entities.device.Model_;
import it.academy.entities.spare_part.SparePart_;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;
import java.util.List;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.REPAIR_TYPE_DESCRIPTION_FILTER;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.JSPConstant.BUYER_SURNAME;

@UtilityClass
public class FilterManager {

    public boolean isDeviceFilter(String filter) {
        switch (filter) {
            case Device_.SERIAL_NUMBER:
            case Device_.BUYER:
            case Device_.DATE_OF_SALE:
            case SALESMAN_NAME:
            case BUYER_SURNAME:
            case Device_.MODEL:
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
            case SERVICE_CENTER_ID:
            case ServiceCenter_.SERVICE_NAME:
                return true;
            default:
                return false;
        }
    }


    public static List<EntityFilter> getFiltersForPage(@NotNull String pagePath) {
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
            case SPARE_PART_TABLE_PAGE_PATH:
                return getFiltersForSparePart();
            case SERVICE_CENTER_TABLE_PAGE_PATH:
                return getFiltersForServiceCenter();
            case REPAIR_TABLE_PAGE_PATH:
            default:
                return null;
        }
    }

    public static List<EntityFilter> getFiltersForAccount() {
        return List.of(new EntityFilter(Account_.EMAIL, EMAIL),
                new EntityFilter(Account_.USER_NAME, ACCOUNT_USER_NAME),
                new EntityFilter(Account_.USER_SURNAME, ACCOUNT_USER_SURNAME),
                new EntityFilter(Account_.SERVICE_CENTER, ACCOUNT_SERVICE_CENTER_DESCRIPTION));
    }

    public static List<EntityFilter> getFiltersForServiceCenter() {
        return List.of(new EntityFilter(ServiceCenter_.SERVICE_NAME, SERVICE_CENTER_NAME_DESCRIPTION),
                new EntityFilter(Requisites_.EMAIL, EMAIL),
                new EntityFilter(SERVICE_CENTER_ACTUAL_ADDRESS_FILTER, SERVICE_CENTER_ACTUAL_ADDRESS_DESCRIPTION),
                new EntityFilter(SERVICE_CENTER_LEGAL_ADDRESS_FILTER, SERVICE_CENTER_LEGAL_ADDRESS_DESCRIPTION),
                new EntityFilter(Requisites_.PHONE, SERVICE_CENTER_PHONE_DESCRIPTION));
    }


    public static List<EntityFilter> getFiltersForModel() {
        return List.of(new EntityFilter(OBJECT_NAME, MODEL_NAME_DESCRIPTION),
                new EntityFilter(BRAND, BRAND_NAME_DESCRIPTION),
                new EntityFilter(DEVICE_TYPE_FILTER, DEVICE_TYPE_NAME_DESCRIPTION));
    }


    public static List<EntityFilter> getFiltersForBrand() {
        return List.of(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
    }

    public static List<EntityFilter> getFiltersForDeviceType() {
        return List.of(new EntityFilter(OBJECT_NAME, DEVICE_TYPE_NAME_DESCRIPTION));
    }


    public static List<EntityFilter> getFiltersForRepairType() {
        return List.of(new EntityFilter(REPAIR_TYPE_CODE, REPAIR_TYPE_CODE_FILTER),
                new EntityFilter(REPAIR_TYPE_LEVEL, REPAIR_TYPE_LEVEL_FILTER),
                new EntityFilter(OBJECT_NAME, REPAIR_TYPE_DESCRIPTION_FILTER));
    }

    public static List<String> getFiltersForRepair() {
        return List.of(Device_.SERIAL_NUMBER,
                BUYER_SURNAME,
                SALESMAN_NAME,
                Device_.DATE_OF_SALE,
                Device_.MODEL,
                ServiceCenter_.SERVICE_NAME,
                SERVICE_CENTER_ID);
    }

    public static List<EntityFilter> getFiltersForSparePart() {
        return List.of(new EntityFilter(SparePart_.MODELS, MODEL_NAME_DESCRIPTION),
                new EntityFilter(SparePart_.NAME, SPARE_PART_NAME_DESCRIPTION));
    }

    public List<EntityFilter> getFiltersForSparePartOrder() {
        return List.of(new EntityFilter(ORDER_DATE, ORDER_DATE_DESCRIPTION),
                new EntityFilter(DEPARTURE_DATE, ORDER_DEPARTURE_DATE_DESCRIPTION),
                new EntityFilter(DELIVERY_DATE, ORDER_DELIVERY_DATE_DESCRIPTION));
    }

}
