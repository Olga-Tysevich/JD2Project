package it.academy.utils;

import it.academy.dto.ListForPage;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.CreateRepairDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.utils.enums.RepairCategory;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.experimental.UtilityClass;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

@UtilityClass
public class Builder {

    public static <T, R> ListForPage<T> buildListForPage(List<T> list, int pageNumber, long numberOfEntries, Class<R> entityClass) {
        return ListForPage.<T>builder()
                .pageNumber(pageNumber)
                .list(list)
                .maxPageNumber((int) Math.ceil(((double) numberOfEntries) / LIST_SIZE))
                .filtersForPage(FilterManager.getFilters(entityClass))
                .build();
    }

    public static <T> ListForPage<T> buildListForPage(List<T> list, int pageNumber, int maxPageNumber, List<EntityFilter> filters) {
        return ListForPage.<T>builder()
                .pageNumber(pageNumber)
                .list(list)
                .maxPageNumber(maxPageNumber)
                .filtersForPage(filters)
                .build();
    }

    public static CreateRepairDTO buildEmptyNewRepair(long serviceCenterId) {
        return CreateRepairDTO.builder()
                .serviceCenterId(serviceCenterId)
                .category(RepairCategory.WARRANTY)
                .selectedBrandId(DEFAULT_ID)
                .modelId(DEFAULT_ID)
                .serialNumber(DEFAULT_VALUE)
                .defectDescription(DEFAULT_VALUE)
                .repairNumber(DEFAULT_VALUE)
                .salesmanName(DEFAULT_VALUE)
                .salesmanPhone(DEFAULT_VALUE)
                .buyerName(DEFAULT_VALUE)
                .buyerSurname(DEFAULT_VALUE)
                .buyerPhone(DEFAULT_VALUE)
                .build();

    }

    public static CreateAccountDTO buildEmptyAccount() {
        return CreateAccountDTO.builder()
                .email(DEFAULT_VALUE)
                .userName(DEFAULT_VALUE)
                .userSurname(DEFAULT_VALUE)
                .password(DEFAULT_VALUE)
                .confirmPassword(DEFAULT_VALUE)
                .build();
    }

    public static ServiceCenterDTO buildEmptyServiceCenter() {
        return ServiceCenterDTO.builder()
                .serviceName(DEFAULT_VALUE)
                .bankAccount(DEFAULT_VALUE)
                .bankCode(DEFAULT_VALUE)
                .bankName(DEFAULT_VALUE)
                .bankAddress(DEFAULT_VALUE)
                .fullName(DEFAULT_VALUE)
                .actualAddress(DEFAULT_VALUE)
                .legalAddress(DEFAULT_VALUE)
                .phone(DEFAULT_VALUE)
                .email(DEFAULT_VALUE)
                .taxpayerNumber(DEFAULT_VALUE)
                .registrationNumber(DEFAULT_VALUE)
                .isActive(true)
                .build();
    }

    public static ModelDTO buildEmptyModel() {
        return ModelDTO.builder()
                .isActive(true)
                .name(DEFAULT_VALUE)
                .build();
    }

    public static SparePartForChangeDTO buildEmptySparePart() {
        return SparePartForChangeDTO.builder()
                .isActive(true)
                .name(DEFAULT_VALUE)
                .build();
    }

}
