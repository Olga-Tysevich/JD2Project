package it.academy.utils.converters.spare_part;

import it.academy.dto.spare_part.ChangeSparePartDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.entities.device.Model;
import it.academy.entities.spare_part.SparePart;
import it.academy.utils.SparePartForOrder;
import it.academy.utils.converters.device.ModelConverter;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartConverter {

    public static SparePartForChangeDTO convertToDTO(SparePart sparePart, List<ModelDTO> deviceTypes) {
        List<Model> relatedModels = new ArrayList<>(sparePart.getModels());
        List<ModelDTO> models = ModelConverter.convertToDTOList(relatedModels);

        return SparePartForChangeDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .relatedModels(models)
                .allModels(deviceTypes)
                .isActive(sparePart.getIsActive())
                .build();
    }

    public static SparePartDTO convertToDTO(SparePart sparePart) {
        return SparePartDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .isActive(sparePart.getIsActive())
                .build();
    }

    public static SparePartForChangeDTO convertToChangeDTO(SparePart sparePart) {
        return SparePartForChangeDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .isActive(sparePart.getIsActive())
                .build();
    }


    public static SparePart convertToEntity(ChangeSparePartDTO partDTO) {
        return SparePart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .isActive(partDTO.getIsActive())
                .build();
    }

    public static SparePart convertToEntity(SparePartForChangeDTO partDTO) {
        return SparePart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .isActive(partDTO.getIsActive())
                .build();
    }

    public static List<SparePartDTO> convertToDTOList(List<SparePart> spareParts) {
        return spareParts.stream()
                .map(SparePartConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static SparePartForChangeDTO convertToSparePartDTO(SparePartForOrder sparePart) {
        return SparePartForChangeDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .isActive(true)
                .build();
    }
}
