package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.LiquidationCertificateDTOReq;
import it.academy.entities.repair.decommissioning.LiquidationCertificate;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class LiquidationCertificateConverter {

    public static LiquidationCertificateDTOReq convertToDTOReq(LiquidationCertificate certificate) {
        return LiquidationCertificateDTOReq.builder()
                .id(certificate.getId())
                .number(certificate.getNumber())
                .repair(RepairConverter.convertToDTOReq(certificate.getRepair()))
                .cause(LiquidationCauseConverter.convertToDTOReq(certificate.getCause()))
                .build();
    }

    public static LiquidationCertificate convertDTOReqToEntity(LiquidationCertificateDTOReq req) {
        return LiquidationCertificate.builder()
                .id(req.getId())
                .number(req.getNumber())
                .repair(RepairConverter.convertDTOReqToEntity(req.getRepair()))
                .cause(LiquidationCauseConverter.convertDTOReqToEntity(req.getCause()))
                .build();
    }

    public static List<LiquidationCertificateDTOReq> convertListToDTOReq(List<LiquidationCertificate> repairCategories) {
        return repairCategories.stream()
                .map(LiquidationCertificateConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<LiquidationCertificate> convertDTOReqListToEntityList(List<LiquidationCertificateDTOReq> repairCategories) {
        return repairCategories.stream()
                .map(LiquidationCertificateConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
