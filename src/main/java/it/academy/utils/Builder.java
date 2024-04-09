package it.academy.utils;

import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
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

    public static DeviceDTO buildEmptyDevice(ModelDTO model) {
        return DeviceDTO.builder()
                .id(DEFAULT_ID)
                .model(model)
                .serialNumber(DEFAULT_VALUE)
                .buyerName(DEFAULT_VALUE)
                .buyerSurname(DEFAULT_VALUE)
                .buyerPhone(DEFAULT_VALUE)
                .salesmanName(DEFAULT_VALUE)
                .salesmanPhone(DEFAULT_VALUE)
                .build();
    }

}
