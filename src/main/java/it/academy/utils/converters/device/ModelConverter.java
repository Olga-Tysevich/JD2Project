package it.academy.utils.converters.device;

import it.academy.dto.device.ModelDTO;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.device.components.Model;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ModelConverter {

    public static ModelDTO convertToDTO(Model model) {
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

    public static Model convertDTOToEntity(ModelDTO model) {
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

    public static List<ModelDTO> convertListToDTO(List<Model> models) {
        return models.stream()
                .map(ModelConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Model> convertDTOListToEntityList(List<ModelDTO> models) {
        return models.stream()
                .map(ModelConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }

}
