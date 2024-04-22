package it.academy.utils;

import it.academy.dto.ListForPage;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairFormDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.utils.fiterForSearch.EntityFilter;
import lombok.experimental.UtilityClass;

import java.util.List;

import static it.academy.utils.constants.Constants.*;

@UtilityClass
public class Builder {

    public static <T> ListForPage<T> buildListForPage(List<T> list, int pageNumber, int maxPageNumber, List<EntityFilter> filters) {
        return ListForPage.<T>builder()
                .pageNumber(pageNumber)
                .list(list)
                .maxPageNumber(maxPageNumber)
                .filtersForPage(filters)
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
