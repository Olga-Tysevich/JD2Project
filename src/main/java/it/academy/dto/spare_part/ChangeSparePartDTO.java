package it.academy.dto.spare_part;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeSparePartDTO {
    
    private List<Long> modelIdList;

    private Long id;

    private String name;

    private Boolean isActive;

}
