package it.academy.utils.converters;

import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.resp.ModelDTO;
import it.academy.dto.resp.SparePartDTO;
import it.academy.dto.resp.SparePartForChangeDTO;
import it.academy.entities.Model;
import it.academy.entities.SparePart;
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
