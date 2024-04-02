package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.LiquidationCertificateDTOReq;
import it.academy.entities.repair.decommissioning.LiquidationCertificate;

import java.util.List;
import java.util.stream.Collectors;

public class LiquidationCertificateConverter {

    public static LiquidationCertificateDTOReq convertToDTOReq(LiquidationCertificate certificate) {
        return LiquidationCertificateDTOReq.builder()
                .id(certificate.getId())
                .number(certificate.getNumber())
                .repair(RepairConverter.convertToDTOReq(certificate.getRepair()))
                .cause(LiquidationCauseConverter.convertToDTOReq(certificate.getCause()))
                .build();
    }

    public static List<LiquidationCertificateDTOReq> convertListToDTOReq(List<LiquidationCertificate> repairCategories) {
        return repairCategories.stream()
                .map(LiquidationCertificateConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static LiquidationCertificate convertToEntity(LiquidationCertificateDTOReq req) {
        return LiquidationCertificate.builder()
                .id(req.getId())
                .number(req.getNumber())
                .repair(RepairConverter.convertToEntity(req.getRepair()))
                .cause(LiquidationCauseConverter.convertToEntity(req.getCause()))
                .build();
    }
}
