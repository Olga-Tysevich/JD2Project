package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.LiquidationCauseDTOReq;
import it.academy.entities.repair.decommissioning.LiquidationCause;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class LiquidationCauseConverter {

    public static LiquidationCauseDTOReq convertToDTOReq(LiquidationCause cause) {
        return LiquidationCauseDTOReq.builder()
                .id(cause.getId())
                .name(cause.getName())
                .build();
    }

    public static List<LiquidationCauseDTOReq> convertListToDTOReq(List<LiquidationCause> repairCategories) {
        return repairCategories.stream()
                .map(LiquidationCauseConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static LiquidationCause convertToEntity(LiquidationCauseDTOReq req) {
        return LiquidationCause.builder()
                .id(req.getId())
                .name(req.getName())
                .build();
    }
}
