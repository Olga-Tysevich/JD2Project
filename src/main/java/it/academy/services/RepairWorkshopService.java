package it.academy.services;

import it.academy.dto.ListForPage;
import it.academy.dto.repair_workshop.RepairWorkshopDTO;

import java.util.List;

public interface RepairWorkshopService {

    void addRepairWorkshop(RepairWorkshopDTO repairWorkshop);

    void updateRepairWorkshop(RepairWorkshopDTO repairWorkshop);

    RepairWorkshopDTO findRepairWorkshop(long id);

    List<RepairWorkshopDTO> findRepairWorkshops();

    ListForPage<RepairWorkshopDTO> findRepairWorkshops(int pageNumber);

    ListForPage<RepairWorkshopDTO> findRepairWorkshops(int pageNumber, String filter, String input);

}
