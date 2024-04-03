package it.academy.converters.device;

import it.academy.converters.Converter;
import it.academy.dto.req.device.ModelDTOReq;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.device.components.Model;

import java.util.List;
import java.util.stream.Collectors;

public class ModelConverter implements Converter<Model,ModelDTOReq > {

    @Override
    public ModelDTOReq convertToDTOReq(Model model) {
        return ModelDTOReq.builder()
                .id(model.getId())
                .name(model.getName())
                .brandId(model.getBrand().getId())
                .brandName(model.getBrand().getName())
                .deviceTypeId(model.getType().getId())
                .deviceTypeName(model.getType().getName())
                .build();
    }

    @Override
    public Model convertDTOReqToEntity(ModelDTOReq req) {
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

    @Override
    public List<ModelDTOReq> convertListToDTOReq(List<Model> models) {
        return models.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<Model> convertDTOReqListToEntityList(List<ModelDTOReq> models) {
        return models.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
