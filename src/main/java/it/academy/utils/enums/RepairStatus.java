package it.academy.utils.enums;

import static it.academy.utils.constants.Constants.*;

public enum RepairStatus {
    REQUEST(REQUEST_DESCRIPTION, false),
    CURRENT(CURRENT_DESCRIPTION, false),
    WAITING_FOR_SPARE_PARTS(WAITING_SP_DESCRIPTION, false),
    COMPLETED(COMPLETED_DESCRIPTION, true),
    PAID(PAID_DESCRIPTION, true),
    REJECTED(REJECTED_DESCRIPTION, false),
    ALL(ALL_REPAIRS, false);

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
