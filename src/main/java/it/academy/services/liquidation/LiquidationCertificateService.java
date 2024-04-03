package it.academy.services.liquidation;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.repair.LiquidationCauseDTOReq;
import it.academy.dto.req.repair.LiquidationCertificateDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface LiquidationCertificateService {

    RespDTO<LiquidationCauseDTOReq> addLiquidationCause(LiquidationCauseDTOReq req);

    RespDTO<LiquidationCauseDTOReq> changeLiquidationCause(LiquidationCauseDTOReq req);

    RespListDTO<LiquidationCauseDTOReq> findLiquidationCause();

    RespListDTO<LiquidationCauseDTOReq> findLiquidationCause(int pageNumber, int listSize);

    RespListDTO<LiquidationCauseDTOReq> findLiquidationCause(ParametersForSearchDTO parameters);

    RespDTO<LiquidationCertificateDTOReq> addLiquidationCertificate(LiquidationCertificateDTOReq req);

    RespDTO<LiquidationCertificateDTOReq> changeLiquidationCertificate(LiquidationCertificateDTOReq req);

    RespListDTO<LiquidationCertificateDTOReq> findLiquidationCertificate();

    RespListDTO<LiquidationCertificateDTOReq> findLiquidationCertificate(int pageNumber, int listSize);

    RespListDTO<LiquidationCertificateDTOReq> findLiquidationCertificate(ParametersForSearchDTO parameters);

}
