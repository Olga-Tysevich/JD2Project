package it.academy.utils;

import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.utils.fiterForSearch.EntityFilter;
import lombok.experimental.UtilityClass;
import java.util.List;

import static it.academy.utils.Constants.*;

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

//    public static DeviceDTOResp buildEmptyDevice(CreateModelDTO model) {
//        return DeviceDTOResp.builder()
//                .id(DEFAULT_ID)
//                .model(model)
//                .serialNumber(DEFAULT_VALUE)
//                .buyerName(DEFAULT_VALUE)
//                .buyerSurname(DEFAULT_VALUE)
//                .buyerPhone(DEFAULT_VALUE)
//                .salesmanName(DEFAULT_VALUE)
//                .salesmanPhone(DEFAULT_VALUE)
//                .build();
//    }

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

}
