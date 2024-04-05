package it.academy.entities.repair.components;

import static it.academy.utils.Constants.*;

public enum RepairStatus {
    REQUEST(REQUEST_DESCRIPTION),
    CURRENT(CURRENT_DESCRIPTION),
    WAITING_FOR_SPARE_PARTS(WAITING_SP_DESCRIPTION),
    COMPLETED(COMPLETED_DESCRIPTION),
    DECOMMISSIONED(DECOMMISSIONED_DESCRIPTION),
    PAID(PAID_DESCRIPTION),
    DELIVERED(DELIVERED_DESCRIPTION),
    REJECTED(REJECTED_DESCRIPTION);

    private String description;

    RepairStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
