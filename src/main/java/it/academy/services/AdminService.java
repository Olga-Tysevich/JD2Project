package it.academy.services;

import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.spare_parts.SparePartDTO;

import java.util.List;

public interface AdminService {

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber);

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input);

    RepairTypeDTO findRepairType(long id);

    void addRepairType(RepairTypeDTO repairType);

    void updateRepairType(RepairTypeDTO repairType);

    List<DeviceTypeDTO> findDeviceTypes();

    void addSparePart(SparePartDTO sparePartDTO, List<Long> deviceTypesId);

}
