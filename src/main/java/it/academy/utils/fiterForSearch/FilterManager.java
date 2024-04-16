package it.academy.utils.fiterForSearch;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.REPAIR_TYPE_DESCRIPTION_FILTER;

@UtilityClass
public class FilterManager {

    public static List<EntityFilter> getFiltersForAccount() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(EMAIL, EMAIL));
        filters.add(new EntityFilter(USER_NAME, ACCOUNT_USER_NAME));
        filters.add(new EntityFilter(USER_SURNAME, ACCOUNT_USER_SURNAME));
        return filters;
    }

    public static List<EntityFilter> getFiltersForRepairType() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(REPAIR_TYPE_CODE, REPAIR_TYPE_CODE_FILTER));
        filters.add(new EntityFilter(REPAIR_TYPE_LEVEL, REPAIR_TYPE_LEVEL_FILTER));
        filters.add(new EntityFilter(OBJECT_NAME, REPAIR_TYPE_DESCRIPTION_FILTER));
        return filters;
    }

    public static List<EntityFilter> getFiltersForBrand() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
        return filters;
    }

    public static List<EntityFilter> getFiltersForDeviceType() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, REPAIR_TYPE_DESCRIPTION_FILTER));
        return filters;
    }

    public static List<EntityFilter> getFiltersForModel() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, MODEL_NAME_FILTER));
        filters.add(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
        filters.add(new EntityFilter(OBJECT_NAME, DEVICE_TYPE_NAME_DESCRIPTION));
        return filters;
    }

    public static List<EntityFilter> getFiltersForServiceCenter() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
        return filters;
    }

    public static List<EntityFilter> getFiltersForSparePart() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(DEVICE_TYPES, DEVICE_TYPE_NAME_DESCRIPTION));
        filters.add(new EntityFilter(OBJECT_NAME, SPARE_PART_NAME_DESCRIPTION));
        return filters;
    }

    public List<EntityFilter> getFiltersForSparePartOrder() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(ORDER_DATE_PARAMETER, ORDER_DATE_DESCRIPTION));
        filters.add(new EntityFilter(DEPARTURE_DATE_PARAMETER, ORDER_DEPARTURE_DATE_DESCRIPTION));
        filters.add(new EntityFilter(DELIVERY_DATE_PARAMETER, ORDER_DELIVERY_DATE_DESCRIPTION));
        return filters;
    }

}
