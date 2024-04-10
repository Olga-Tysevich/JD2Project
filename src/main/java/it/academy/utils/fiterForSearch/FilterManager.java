package it.academy.utils.fiterForSearch;

import it.academy.utils.EntityFilter;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static it.academy.utils.Constants.*;
import static it.academy.utils.Constants.REPAIR_TYPE_DESCRIPTION_FILTER;

@UtilityClass
public class FilterManager {

    public static List<EntityFilter> getFiltersForRepairType() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(REPAIR_TYPE_CODE, REPAIR_TYPE_CODE_FILTER));
        filters.add(new EntityFilter(REPAIR_TYPE_LEVEL, REPAIR_TYPE_LEVEL_FILTER));
        filters.add(new EntityFilter(OBJECT_NAME, REPAIR_TYPE_DESCRIPTION_FILTER));
        return filters;
    }


}
