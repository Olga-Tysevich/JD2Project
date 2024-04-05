package it.academy.entities.repair.components;

import static it.academy.utils.Constants.*;

public enum RepairStatus {
    REQUEST(REQUEST_DESCRIPTION, false),
    CURRENT(CURRENT_DESCRIPTION, false),
    WAITING_FOR_SPARE_PARTS(WAITING_SP_DESCRIPTION, false),
    COMPLETED(COMPLETED_DESCRIPTION, true),
    DECOMMISSIONED(DECOMMISSIONED_DESCRIPTION, true),
    PAID(PAID_DESCRIPTION, true),
    DELIVERED(DELIVERED_DESCRIPTION, true),
    REJECTED(REJECTED_DESCRIPTION, false),
    ALL(ALL_DESCRIPTION, false);

    private String description;
    private boolean isFinishedStatus;

    RepairStatus(String description, boolean isFinishedStatus) {
        this.description = description;
        this.isFinishedStatus = isFinishedStatus;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinishedStatus() {
        return isFinishedStatus;
    }
}
