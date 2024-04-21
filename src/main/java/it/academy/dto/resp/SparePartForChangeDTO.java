package it.academy.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SparePartForChangeDTO {

    private Long id;

    private String name;

    private Boolean isActive;

    private List<ModelDTO> relatedModels;

    private List<ModelDTO> allModels;

}
