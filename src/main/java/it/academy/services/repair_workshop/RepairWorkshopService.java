package it.academy.services.repair_workshop;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.repair_workshop.RepairWorkshopDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface RepairWorkshopService {

    RespDTO<RepairWorkshopDTOReq> addRepairWorkshop(RepairWorkshopDTOReq req);

    RespDTO<RepairWorkshopDTOReq> changeRepairWorkshop(RepairWorkshopDTOReq req);

    RespListDTO<RepairWorkshopDTOReq> findRepairWorkshop();

    RespListDTO<RepairWorkshopDTOReq> findRepairWorkshop(int pageNumber, int listSize);

    RespListDTO<RepairWorkshopDTOReq> findRepairWorkshop(ParametersForSearchDTO parameters);

}
