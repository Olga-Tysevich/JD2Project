package it.academy.entities.account.role;

import java.util.ResourceBundle;

import static it.academy.utils.Constants.PERMISSION_TYPE_KEY;
import static it.academy.utils.Constants.REPAIR_STATUSES_BUNDLE;

public enum PermissionType {
    CREATE,
    UPDATE,
    FIND,
    DELETE,
    CONFIRM,
    REJECT;

    private String description;

    private String getDescription() {
        ResourceBundle bundle = ResourceBundle.getBundle(REPAIR_STATUSES_BUNDLE);
        return bundle.getString(String.format(PERMISSION_TYPE_KEY, this.name().toLowerCase()));
    }
}
