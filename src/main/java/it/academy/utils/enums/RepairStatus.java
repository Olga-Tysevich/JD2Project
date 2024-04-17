package it.academy.utils.enums;

import static it.academy.utils.constants.Constants.*;

public enum RepairStatus {
    REQUEST(REQUEST_DESCRIPTION, false, false),
    CURRENT(CURRENT_DESCRIPTION, false, false),
    WAITING_FOR_SPARE_PARTS(WAITING_SP_DESCRIPTION, false, false),
    COMPLETED(COMPLETED_DESCRIPTION, true, false),
    DECOMMISSIONED(DECOMMISSIONED_DESCRIPTION, true, false),
    PAID(PAID_DESCRIPTION, true, true),
    DELIVERED(DELIVERED_DESCRIPTION, true, true),
    REJECTED(REJECTED_DESCRIPTION, false, false),
    ALL(ALL_DESCRIPTION, false, false);

    private String description;
    private boolean isFinishedStatus;
    private boolean isDeliveredStatus;

    RepairStatus(String description, boolean isFinishedStatus, boolean isDeliveredStatus) {
        this.description = description;
        this.isFinishedStatus = isFinishedStatus;
        this.isDeliveredStatus = isDeliveredStatus;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinishedStatus() {
        return isFinishedStatus;
    }

    public boolean isDeliveredStatus() {
        return isDeliveredStatus;
    }
}
