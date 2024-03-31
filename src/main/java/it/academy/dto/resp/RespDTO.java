package it.academy.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespDTO<T> {

    private Integer httpStatus;

    private String message;

    private T parameter;

}
