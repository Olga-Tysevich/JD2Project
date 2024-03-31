package it.academy.dto.req.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionDTOReq {

    private Long id;

    private String type;

    private String category;

}
