package it.academy.utils.fiterForSearch;

import it.academy.entities.account.Account;
import it.academy.entities.account.Account_;
import it.academy.entities.device.Device;
import it.academy.entities.device.Device_;
import it.academy.entities.repair.Repair_;
import it.academy.entities.spare_part.SparePart;
import it.academy.entities.spare_part.SparePart_;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;
import java.util.List;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.REPAIR_TYPE_DESCRIPTION_FILTER;
import static it.academy.utils.constants.JSPConstant.*;

@UtilityClass
public class FilterManager {

    public static <T> List<EntityFilter> getFilters(Class<T> objectClass) {
        String className = objectClass.getSimpleName().toLowerCase();
        switch (className) {
            case ACCOUNT:
                return getFiltersForAccount();
            case BRAND:
                return getFiltersForBrand();
            case DEVICE_TYPE:
                return getFiltersForDeviceType();
            case MODEL:
                return getFiltersForModel();
            case REPAIR_TYPE:
                return getFiltersForRepairType();
            case SPARE_PART:
                return getFiltersForSparePart();
            case SERVICE_CENTER_FILTER:
                return getFiltersForServiceCenter();
            case REPAIR:
                return getFiltersForRepair();
            default:
                return null;
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
                return getFiltersForRepair();
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
        return List.of(new EntityFilter(SERVICE_CENTER_NAME, SERVICE_CENTER_NAME_DESCRIPTION),
                new EntityFilter(EMAIL, EMAIL),
                new EntityFilter(SERVICE_CENTER_ACTUAL_ADDRESS, SERVICE_CENTER_ACTUAL_ADDRESS_DESCRIPTION),
                new EntityFilter(SERVICE_CENTER_LEGAL_ADDRESS, SERVICE_CENTER_LEGAL_ADDRESS_DESCRIPTION),
                new EntityFilter(SERVICE_CENTER_PHONE, SERVICE_CENTER_PHONE_DESCRIPTION));
    }


    public static List<EntityFilter> getFiltersForModel() {
        return List.of(new EntityFilter(OBJECT_NAME, MODEL_NAME_FILTER),
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

    public static List<EntityFilter> getFiltersForRepair() {
        return List.of(new EntityFilter(Repair_.REPAIR_NUMBER, REPAIR_NUMBER_DESCRIPTION),
                new EntityFilter(Repair_.DEVICE + POINT + Device_.SERIAL_NUMBER, DEVICE_SERIAL_NUMBER_DESCRIPTION),
                new EntityFilter(OBJECT_NAME, REPAIR_TYPE_DESCRIPTION_FILTER));
    }

    public static List<EntityFilter> getFiltersForSparePart() {
        return List.of(new EntityFilter(SparePart_.MODELS, MODEL_NAME_FILTER),
                new EntityFilter(SparePart_.NAME, SPARE_PART_NAME_DESCRIPTION));
    }

    public List<EntityFilter> getFiltersForSparePartOrder() {
        return List.of(new EntityFilter(ORDER_DATE, ORDER_DATE_DESCRIPTION),
                new EntityFilter(DEPARTURE_DATE, ORDER_DEPARTURE_DATE_DESCRIPTION),
                new EntityFilter(DELIVERY_DATE, ORDER_DELIVERY_DATE_DESCRIPTION));
    }

}
