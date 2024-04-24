package it.academy.dto.spare_part;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SparePartDTO {

    private Long id;

    private String name;

    private Boolean isActive;

}
