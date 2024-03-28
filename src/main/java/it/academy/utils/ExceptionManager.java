package it.academy.utils;

import it.academy.dto.resp.DTOResp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionManager {

    public static DTOResp getResp(Exception e) {
        return DTOResp.builder()
                .message(e.getMessage())
                .build();
    }
}
