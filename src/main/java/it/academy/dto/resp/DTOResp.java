package it.academy.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DTOResp {

    private Integer httpStatus;

    private String message;

}
