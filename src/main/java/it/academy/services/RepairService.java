package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.repair.RepairCategoryDTOReq;
import it.academy.dto.req.repair.RepairStatusDTOReq;
import it.academy.dto.req.repair.RepairTypeDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface RepairService {

    RespDTO<RepairStatusDTOReq> addRepairStatus(RepairStatusDTOReq req);

    RespDTO<RepairStatusDTOReq> changeRepairStatus(RepairStatusDTOReq req);

    RespListDTO<RepairStatusDTOReq> findRepairStatus();

    RespListDTO<RepairStatusDTOReq> findRepairStatus(int pageNumber, int listSize);

    RespListDTO<RepairStatusDTOReq> findRepairStatus(ParametersForSearchDTO parameters);

    RespDTO<RepairTypeDTOReq> addRepairType(RepairTypeDTOReq req);

    RespDTO<RepairTypeDTOReq> changeRepairType(RepairTypeDTOReq req);

    RespListDTO<RepairTypeDTOReq> findRepairTypes();

    RespListDTO<RepairTypeDTOReq> findRepairTypes(int pageNumber, int listSize);

    RespListDTO<RepairTypeDTOReq> findRepairTypes(ParametersForSearchDTO parameters);

    RespDTO<RepairCategoryDTOReq> addRepairCategory(RepairCategoryDTOReq req);

    RespDTO<RepairCategoryDTOReq> changeRepairCategory(RepairCategoryDTOReq req);

    RespListDTO<RepairCategoryDTOReq> findRepairCategories();

    RespListDTO<RepairCategoryDTOReq> findRepairCategories(int pageNumber, int listSize);

    RespListDTO<RepairCategoryDTOReq> findRepairCategories(ParametersForSearchDTO parameters);

}
