package it.academy.utils.services.converters.device;

import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.req.device.ModelDTOReq;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.device.components.Model;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ModelConverter {

    public static ModelDTOReq convertToDTOReq(Model model) {
        return ModelDTOReq.builder()
                .id(model.getId())
                .name(model.getName())
                .brandId(model.getBrand().getId())
                .brandName(model.getBrand().getName())
                .deviceTypeId(model.getType().getId())
                .deviceTypeName(model.getType().getName())
                .build();
    }

    public static List<ModelDTOReq> convertListToDTOReq(List<Model> models) {
        return models.stream()
                .map(ModelConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static Model convertToEntity(ModelDTOReq req) {
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
}
