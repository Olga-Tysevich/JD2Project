package it.academy.utils.services;

import it.academy.dto.resp.DTOResp;
import it.academy.dto.resp.DTORespList;
import lombok.experimental.UtilityClass;
import java.util.List;

@UtilityClass
public class ResponseManager {

    public static<T> DTOResp getDTOResp(int httpStatus, String message) {
        return DTOResp.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }

    public static <T> DTORespList<T> getDTORespList(int httpStatus, String message, List<T> resultList) {
            return DTORespList.<T>builder()
                    .httpStatus(httpStatus)
                    .message(message)
                    .list(resultList)
                    .build();
    }
}
