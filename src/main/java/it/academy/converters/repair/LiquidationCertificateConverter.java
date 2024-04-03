package it.academy.converters.repair;

import it.academy.converters.Converter;
import it.academy.dto.req.repair.LiquidationCertificateDTOReq;
import it.academy.entities.repair.decommissioning.LiquidationCertificate;

import java.util.List;
import java.util.stream.Collectors;

public class LiquidationCertificateConverter implements Converter<LiquidationCertificate, LiquidationCertificateDTOReq> {
    private RepairConverter repairConverter = new RepairConverter();
    private LiquidationCauseConverter liquidationCauseConverter = new LiquidationCauseConverter();

    @Override
    public LiquidationCertificateDTOReq convertToDTOReq(LiquidationCertificate certificate) {
        return LiquidationCertificateDTOReq.builder()
                .id(certificate.getId())
                .number(certificate.getNumber())
                .repair(repairConverter.convertToDTOReq(certificate.getRepair()))
                .cause(liquidationCauseConverter.convertToDTOReq(certificate.getCause()))
                .build();
    }

    @Override
    public LiquidationCertificate convertDTOReqToEntity(LiquidationCertificateDTOReq req) {
        return LiquidationCertificate.builder()
                .id(req.getId())
                .number(req.getNumber())
                .repair(repairConverter.convertDTOReqToEntity(req.getRepair()))
                .cause(liquidationCauseConverter.convertDTOReqToEntity(req.getCause()))
                .build();
    }

    @Override
    public List<LiquidationCertificateDTOReq> convertListToDTOReq(List<LiquidationCertificate> repairCategories) {
        return repairCategories.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<LiquidationCertificate> convertDTOReqListToEntityList(List<LiquidationCertificateDTOReq> repairCategories) {
        return repairCategories.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
