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
                .build();
    }

    public static Model convertDTOToEntity(ModelDTO req) {
        return Model.builder()
                .id(req.getId())
                .name(req.getName())
                .brand(Brand.builder()
                        .id(req.getBrandId())
                        .name(req.getBrandName())
                        .build())
                .type(DeviceType.builder()
                        .id(req.getDeviceTypeId())
                        .name(req.getDeviceTypeName())
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
