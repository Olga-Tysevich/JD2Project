package it.academy.utils.enums;

import static it.academy.utils.constants.Constants.*;

public enum RepairCategory {
    WARRANTY(WARRANTY_DESCRIPTION),
    PRE_SALE(PRE_SALE_DESCRIPTION),
    PAID(PAID_CATEGORY_DESCRIPTION),
    REPEATED(REPEATED_DESCRIPTION);

    private String description;

    RepairCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

