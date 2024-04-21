package it.academy.utils.converters;

import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.resp.ModelDTO;
import it.academy.dto.resp.SparePartDTO;
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

    public static SparePartDTO convertToDTO(SparePart sparePart, List<ModelDTO> deviceTypes) {
        List<Model> relatedModels = new ArrayList<>(sparePart.getModels());
        List<ModelDTO> models = ModelConverter.convertToDTOList(relatedModels);

        return SparePartDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .relatedModels(models)
                .allModels(deviceTypes)
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

    public static SparePart convertToEntity(SparePartDTO partDTO) {
        return SparePart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .isActive(partDTO.getIsActive())
                .build();
    }

    public static List<SparePartDTO> convertToDTOList(List<SparePart> spareParts, List<ModelDTO> models) {
        return spareParts.stream()
                .map(s -> convertToDTO(s, models))
                .collect(Collectors.toList());
    }

    public static SparePartDTO convertToSparePartDTO(SparePartForOrder sparePart) {
        return SparePartDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .isActive(true)
                .build();
    }
}
