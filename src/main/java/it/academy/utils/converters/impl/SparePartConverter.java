package it.academy.utils.converters.impl;

import it.academy.dto.device.ModelDTO;
import it.academy.dto.spare_part.ChangeSparePartDTO;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.entities.device.Model;
import it.academy.entities.spare_part.SparePart;
import it.academy.utils.converters.EntityConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SparePartConverter implements EntityConverter<SparePartDTO, SparePart> {
    private final ModelConverter modelConverter = new ModelConverter();

    public SparePartForChangeDTO convertToDTO(SparePart sparePart, List<ModelDTO> deviceTypes) {
        List<Model> relatedModels = new ArrayList<>(sparePart.getModels());
        List<ModelDTO> models = modelConverter.convertToDTOList(relatedModels);

        return SparePartForChangeDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .relatedModels(models)
                .allModels(deviceTypes)
                .isActive(sparePart.getIsActive())
                .build();
    }

    public SparePartDTO convertToDTO(SparePart sparePart) {
        return SparePartDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .isActive(sparePart.getIsActive())
                .build();
    }

    public SparePartForChangeDTO convertToChangeDTO(SparePart sparePart) {
        return SparePartForChangeDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .isActive(sparePart.getIsActive())
                .build();
    }

    @Override
    public SparePart convertToEntity(SparePartDTO dto) {
        return SparePart.builder()
                .id(dto.getId())
                .name(dto.getName())
                .isActive(dto.getIsActive())
                .build();
    }

    public SparePart convertToEntity(ChangeSparePartDTO partDTO) {
        return SparePart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .isActive(partDTO.getIsActive())
                .build();
    }

    public SparePart convertToEntity(SparePartForChangeDTO partDTO) {
        return SparePart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .isActive(partDTO.getIsActive())
                .build();
    }

    public List<SparePartDTO> convertToDTOList(List<SparePart> spareParts) {
        return spareParts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
