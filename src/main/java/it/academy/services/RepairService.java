package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.repair.RepairCategoryDTOReq;
import it.academy.dto.req.repair.RepairDTOReq;
import it.academy.dto.req.repair.RepairTypeDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface RepairService {

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

    RespDTO<RepairDTOReq> addRepair(RepairDTOReq req);

    RespDTO<RepairDTOReq> changeRepair(RepairDTOReq req);

    RespListDTO<RepairDTOReq> findRepairs();

    RespListDTO<RepairDTOReq> findRepairs(int pageNumber, int listSize);

    RespListDTO<RepairDTOReq> findRepairs(ParametersForSearchDTO parameters);

}
