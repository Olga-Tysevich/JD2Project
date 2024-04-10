package it.academy.services;

import it.academy.dto.AccountDTO;
import it.academy.dto.ListForPage;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.repair_workshop.RepairWorkshopDTO;

import java.util.List;

public interface AdminService {

    void addAccount(AccountDTO account);

    void updateAccount(AccountDTO account);

    AccountDTO findAccount(long id);

    List<AccountDTO> findAccount();

    ListForPage<AccountDTO> findAccount(int pageNumber);

    ListForPage<AccountDTO> findAccount(int pageNumber, String filter, String input);



    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber);

    ListForPage<RepairTypeDTO> findRepairTypes(int pageNumber, String filter, String input);

    RepairTypeDTO findRepairType(long id);

    void addRepairType(RepairTypeDTO repairType);

    void updateRepairType(RepairTypeDTO repairType);

    List<DeviceTypeDTO> findDeviceTypes();


}
