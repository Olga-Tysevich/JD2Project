package it.academy.utils.converters.impl;

import it.academy.dto.device.ChangeModelDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.entities.device.Brand;
import it.academy.entities.device.DeviceType;
import it.academy.entities.device.Model;
import it.academy.utils.converters.EntityConverter;

import java.util.List;
import java.util.stream.Collectors;

public class ModelConverter implements EntityConverter<ModelDTO, Model> {

    public ModelDTO convertToDTO(Model model) {
        return ModelDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .brandId(model.getBrand().getId())
                .brandName(model.getBrand().getName())
                .deviceTypeId(model.getType().getId())
                .deviceTypeName(model.getType().getName())
                .isActive(model.getIsActive())
                .build();
    }

    public Model convertToEntity(ModelDTO model) {
        return Model.builder()
                .id(model.getId())
                .name(model.getName())
                .isActive(model.getIsActive())
                .brand(Brand.builder()
                        .id(model.getBrandId())
                        .name(model.getBrandName())
                        .build())
                .type(DeviceType.builder()
                        .id(model.getDeviceTypeId())
                        .name(model.getDeviceTypeName())
                        .build())
                .build();
    }

    public Model convertToEntity(ChangeModelDTO model) {
        return Model.builder()
                .id(model.getId())
                .name(model.getName())
                .isActive(model.getIsActive())
                .build();
    }

    public List<ModelDTO> convertToDTOList(List<Model> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}