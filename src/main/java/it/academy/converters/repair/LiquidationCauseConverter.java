package it.academy.converters.repair;

import it.academy.converters.Converter;
import it.academy.dto.req.repair.LiquidationCauseDTOReq;
import it.academy.entities.repair.decommissioning.LiquidationCause;

import java.util.List;
import java.util.stream.Collectors;

public class LiquidationCauseConverter implements Converter<LiquidationCause, LiquidationCauseDTOReq> {

    @Override
    public LiquidationCauseDTOReq convertToDTOReq(LiquidationCause cause) {
        return LiquidationCauseDTOReq.builder()
                .id(cause.getId())
                .name(cause.getName())
                .build();
    }

    @Override
    public LiquidationCause convertDTOReqToEntity(LiquidationCauseDTOReq req) {
        return LiquidationCause.builder()
                .id(req.getId())
                .name(req.getName())
                .build();
    }

    @Override
    public List<LiquidationCauseDTOReq> convertListToDTOReq(List<LiquidationCause> repairCategories) {
        return repairCategories.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<LiquidationCause> convertDTOReqListToEntityList(List<LiquidationCauseDTOReq> repairCategories) {
        return repairCategories.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
