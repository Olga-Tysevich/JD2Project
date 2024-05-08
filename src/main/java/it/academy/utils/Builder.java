package it.academy.utils;

import it.academy.dto.account.AccountDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RoleEnum;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import static it.academy.utils.constants.Constants.*;

@UtilityClass
public class Builder {

    public static RepairDTO buildEmptyRepair(long serviceCenterId) {
        return RepairDTO.builder()
                .serviceCenterId(serviceCenterId)
                .category(RepairCategory.WARRANTY)
                .brandId(DEFAULT_ID)
                .modelId(DEFAULT_ID)
                .serialNumber(StringUtils.EMPTY)
                .defectDescription(StringUtils.EMPTY)
                .repairNumber(StringUtils.EMPTY)
                .salesmanName(StringUtils.EMPTY)
                .salesmanPhone(StringUtils.EMPTY)
                .buyerName(StringUtils.EMPTY)
                .buyerSurname(StringUtils.EMPTY)
                .buyerPhone(StringUtils.EMPTY)
                .build();

    }

    public static AccountDTO buildEmptyAccount() {
        return AccountDTO.builder()
                .role(RoleEnum.SERVICE_CENTER)
                .email(StringUtils.EMPTY)
                .userName(StringUtils.EMPTY)
                .userSurname(StringUtils.EMPTY)
                .password(StringUtils.EMPTY)
                .passwordConfirm(StringUtils.EMPTY)
                .build();
    }

}
