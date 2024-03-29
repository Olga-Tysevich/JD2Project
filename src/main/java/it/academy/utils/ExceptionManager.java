package it.academy.utils;

import it.academy.dto.resp.DTOResp;
import it.academy.dto.resp.DTORespList;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class ExceptionManager {

    public static DTOResp getResp(Exception e) {
        return DTOResp.builder()
                .message(e.getMessage())
                .build();
    }

    public static<T> DTORespList<T> getRespList(Exception e) {
        return DTORespList.<T>builder()
                .message(e.getMessage())
                .list(new ArrayList<>())
                .build();
    }
}
