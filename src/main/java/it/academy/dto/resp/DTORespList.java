package it.academy.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DTORespList<T> {

    private Integer httpStatus;

    private String message;

    private List<T> list;
}
