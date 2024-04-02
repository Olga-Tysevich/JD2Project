package it.academy.utils.services.converters.repair;

import it.academy.dto.req.repair.RepairStatusDTOReq;
import it.academy.entities.repair.components.RepairStatus;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairStatusConverter {

    public static RepairStatusDTOReq convertToDTOReq(RepairStatus status) {
        return RepairStatusDTOReq.builder()
                .id(status.getId())
                .name(status.getName())
                .build();
    }

    public static List<RepairStatusDTOReq> convertListToDTOReq(List<RepairStatus> repairStatuses) {
        return repairStatuses.stream()
                .map(RepairStatusConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static RepairStatus convertToEntity(RepairStatusDTOReq req) {
        return RepairStatus.builder()
                .id(req.getId())
                .name(req.getName())
                .build();
    }
}
