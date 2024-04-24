package it.academy.utils.fiterForSearch;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.Constants.REPAIR_TYPE_DESCRIPTION_FILTER;

@UtilityClass
public class FilterManager {

    public static<T> List<EntityFilter> getFilters(Class<T> objectClass) {
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
            case SERVICE_CENTER:
                return getFiltersForServiceCenter();
            case REPAIR:
                return getFiltersForRepair();
            default: return null;
        }
    }

    public static List<EntityFilter> getFiltersForAccount() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(EMAIL, EMAIL));
        filters.add(new EntityFilter(USER_NAME, ACCOUNT_USER_NAME));
        filters.add(new EntityFilter(USER_SURNAME, ACCOUNT_USER_SURNAME));
        return filters;
    }

    public static List<EntityFilter> getFiltersForBrand() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
        return filters;
    }

    public static List<EntityFilter> getFiltersForDeviceType() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, DEVICE_TYPE_NAME_DESCRIPTION));
        return filters;
    }

    public static List<EntityFilter> getFiltersForModel() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, MODEL_NAME_FILTER));
        filters.add(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
        filters.add(new EntityFilter(OBJECT_NAME, DEVICE_TYPE_NAME_DESCRIPTION));
        return filters;
    }

    public static List<EntityFilter> getFiltersForRepairType() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(REPAIR_TYPE_CODE, REPAIR_TYPE_CODE_FILTER));
        filters.add(new EntityFilter(REPAIR_TYPE_LEVEL, REPAIR_TYPE_LEVEL_FILTER));
        filters.add(new EntityFilter(OBJECT_NAME, REPAIR_TYPE_DESCRIPTION_FILTER));
        return filters;
    }


    public static List<EntityFilter> getFiltersForServiceCenter() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
        return filters;
    }


    public static List<EntityFilter> getFiltersForRepair() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(REPAIR_TYPE_CODE, REPAIR_TYPE_CODE_FILTER));
        filters.add(new EntityFilter(REPAIR_TYPE_LEVEL, REPAIR_TYPE_LEVEL_FILTER));
        filters.add(new EntityFilter(OBJECT_NAME, REPAIR_TYPE_DESCRIPTION_FILTER));
        return filters;
    }

    public static List<EntityFilter> getFiltersForSparePart() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(MODELS, DEVICE_TYPE_NAME_DESCRIPTION));
        filters.add(new EntityFilter(OBJECT_NAME, SPARE_PART_NAME_DESCRIPTION));
        return filters;
    }

    public List<EntityFilter> getFiltersForSparePartOrder() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(ORDER_DATE, ORDER_DATE_DESCRIPTION));
        filters.add(new EntityFilter(DEPARTURE_DATE, ORDER_DEPARTURE_DATE_DESCRIPTION));
        filters.add(new EntityFilter(DELIVERY_DATE, ORDER_DELIVERY_DATE_DESCRIPTION));
        return filters;
    }

}
