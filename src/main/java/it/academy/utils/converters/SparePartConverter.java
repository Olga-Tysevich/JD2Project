package it.academy.utils.converters;

import it.academy.dto.spare_part.SparePartDTO;
import it.academy.entities.device.Model;
import it.academy.entities.spare_part.SparePart;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartConverter {

    public SparePartDTO convertToDTO(SparePart sparePart, List<Long> modelIdList) {
        return SparePartDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .isActive(sparePart.getIsActive())
                .modelIdList(modelIdList)
                .build();
    }

    public SparePart convertToEntity(SparePartDTO dto) {
        return SparePart.builder()
                .id(dto.getId())
                .name(dto.getName())
                .isActive(Objects.requireNonNullElse(dto.getIsActive(), true))
                .build();
    }

    public List<SparePartDTO> convertToDTOList(List<SparePart> spareParts) {
        return spareParts.stream()
                .map(sp -> SparePartConverter.convertToDTO(sp, sp.getModels().stream()
                        .map(Model::getId)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

}
