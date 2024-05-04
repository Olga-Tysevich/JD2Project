package it.academy.utils;

import it.academy.dto.TablePage;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.CreateRepairDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static it.academy.utils.constants.Constants.*;

@UtilityClass
public class Builder {

    public static <T, R> TablePage<T> buildListForPage(List<T> list, int pageNumber, long numberOfEntries) {
        return TablePage.<T>builder()
                .pageNumber(pageNumber)
                .list(list)
                .maxPageNumber((int) Math.ceil(((double) numberOfEntries) / LIST_SIZE))
//                .lastFilter(StringUtils.defaultIfBlank(filter, StringUtils.EMPTY))
//                .lastInput(StringUtils.defaultIfBlank(input, StringUtils.EMPTY))
                .build();
    }

    public static <T> TablePage<T> buildListForPage(List<T> list, int pageNumber, int maxPageNumber, List<EntityFilter> filters) {
        return TablePage.<T>builder()
                .pageNumber(pageNumber)
                .list(list)
                .maxPageNumber(maxPageNumber)
                .filtersForPage(filters)
                .build();
    }

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

    public static CreateAccountDTO buildEmptyAccount() {
        return CreateAccountDTO.builder()
                .role(RoleEnum.SERVICE_CENTER)
                .email(StringUtils.EMPTY)
                .userName(StringUtils.EMPTY)
                .userSurname(StringUtils.EMPTY)
                .password(StringUtils.EMPTY)
                .confirmPassword(StringUtils.EMPTY)
                .build();
    }

    public static ServiceCenterDTO buildEmptyServiceCenter() {
        return ServiceCenterDTO.builder()
                .serviceName(StringUtils.EMPTY)
                .bankAccount(StringUtils.EMPTY)
                .bankCode(StringUtils.EMPTY)
                .bankName(StringUtils.EMPTY)
                .bankAddress(StringUtils.EMPTY)
                .fullName(StringUtils.EMPTY)
                .actualAddress(StringUtils.EMPTY)
                .legalAddress(StringUtils.EMPTY)
                .phone(StringUtils.EMPTY)
                .email(StringUtils.EMPTY)
                .taxpayerNumber(StringUtils.EMPTY)
                .registrationNumber(StringUtils.EMPTY)
                .isActive(true)
                .build();
    }

    public static ModelDTO buildEmptyModel() {
        return ModelDTO.builder()
                .isActive(true)
                .name(StringUtils.EMPTY)
                .build();
    }

    public static SparePartForChangeDTO buildEmptySparePart() {
        return SparePartForChangeDTO.builder()
                .isActive(true)
                .name(StringUtils.EMPTY)
                .build();
    }

}
