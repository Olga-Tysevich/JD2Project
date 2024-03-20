package it.academy.entities.account.role;

import java.util.ResourceBundle;

import static it.academy.utils.Constants.*;

public enum PermissionCategory {
    ROLE,
    ADMIN_ACCOUNT,
    SERVICE_ACCOUNT,
    ALL_REPAIRS,
    SERVICE_REPAIRS,
    REPAIR,
    DEFECT,
    SPARE_PART,
    DECOMMISSIONING,
    DECOMMISSIONING_CAUSE,
    REPAIR_STATUS,
    SALESMAN,
    BUYER,
    BRAND,
    REPAIR_CATEGORY,
    MODEL,
    REPAIR_TYPE,
    SPARE_PART_ORDER;

    private String description;

    private String getDescription() {
        ResourceBundle bundle = ResourceBundle.getBundle(REPAIR_STATUSES_BUNDLE);
        return bundle.getString(String.format(PERMISSION_CATEGORY_KEY, this.name().toLowerCase()));
    }
}
