package it.academy.services.repair;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.repair_page_N.RepairPageDataDTO;
import it.academy.dto.req.repair.*;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.repair.components.RepairStatus;

import java.util.List;

public interface RepairService {

    RepairPageDataDTO getDataForPage(long currentBrand);

    RepairDTO addRepair(CreateRepairDTO req);

    List<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber, int listSize);


    RespDTO<RepairTypeDTOReq> addRepairType(RepairTypeDTOReq req);

    RespDTO<RepairTypeDTOReq> changeRepairType(RepairTypeDTOReq req);

    RespListDTO<RepairTypeDTOReq> findRepairTypes();

    RespListDTO<RepairTypeDTOReq> findRepairTypes(int pageNumber, int listSize);

    RespListDTO<RepairTypeDTOReq> findRepairTypes(ParametersForSearchDTO parameters);

    RespDTO<RepairCategoryDTO> addRepairCategory(RepairCategoryDTO req);

    RespDTO<RepairCategoryDTO> changeRepairCategory(RepairCategoryDTO req);

    List<RepairCategoryDTO> findRepairCategories();

    RespListDTO<RepairCategoryDTO> findRepairCategories(int pageNumber, int listSize);

    RespListDTO<RepairCategoryDTO> findRepairCategories(ParametersForSearchDTO parameters);

    RespDTO<RepairDTO> changeRepair(RepairDTO req);

    RespListDTO<RepairDTO> findRepairs();

    RespListDTO<RepairDTO> findRepairs(int pageNumber, int listSize);

    RespListDTO<RepairDTO> findRepairs(ParametersForSearchDTO parameters);

}
