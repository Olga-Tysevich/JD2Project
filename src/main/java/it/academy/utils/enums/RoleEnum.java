package it.academy.utils.enums;

import static it.academy.utils.Constants.ADMIN_DESCRIPTION;
import static it.academy.utils.Constants.SERVICE_CENTER_DESCRIPTION;

public enum RoleEnum {
    ADMIN(ADMIN_DESCRIPTION),
    SERVICE_CENTER(SERVICE_CENTER_DESCRIPTION);

    private String description;

    RoleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
