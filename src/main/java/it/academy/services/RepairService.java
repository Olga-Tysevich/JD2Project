package it.academy.services;


import it.academy.dto.ListForPage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.device.components.Brand;
import it.academy.entities.repair.components.RepairStatus;

import java.util.List;

public interface RepairService {

    void addRepair(RepairDTO repairDTO);

    void changeRepair(RepairDTO repairDTO);

    List<BrandDTO> findBrands();

    DeviceDTO addDevice(DeviceDTO deviceDTO);

    ModelDTO findModel(long id);

    BrandDTO findBrand(long id);

    RepairTypeDTO findRepairType(long id);

    List<ModelDTO> findModelsByBrandId(long brandId);

    RepairDTO findRepair(long id);

    ListForPage<RepairDTO> findRepairs(int pageNumber);

    ListForPage<RepairDTO> findRepairsByStatus(RepairStatus status, int pageNumber);

}
