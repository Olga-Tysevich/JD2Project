package it.academy.utils.services;

import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import lombok.experimental.UtilityClass;
import java.util.List;

@UtilityClass
public class ResponseManager {

    public static<T> RespDTO<T> getDTOResp(int httpStatus, String message) {
        return RespDTO.<T>builder()
                .httpStatus(httpStatus)
                .message(message)
//                .parameter(object)
                .build();
    }

    public static <T> RespListDTO<T> getDTORespList(int httpStatus, String message, List<T> resultList) {
            return RespListDTO.<T>builder()
                    .httpStatus(httpStatus)
                    .message(message)
                    .list(resultList)
                    .build();
    }
}
