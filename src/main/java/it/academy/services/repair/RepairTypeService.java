package it.academy.services.repair;

import it.academy.dto.TablePage2;
import it.academy.dto.repair.RepairTypeDTO;

import java.util.List;

public interface RepairTypeService {

    void create(RepairTypeDTO repairTypeDTO);

    void update(RepairTypeDTO repairTypeDTO);

    void delete(long id);

    RepairTypeDTO find(long id);

    List<RepairTypeDTO> findAll();

    TablePage2<RepairTypeDTO> findForPage(int pageNumber);

    TablePage2<RepairTypeDTO> findForPageByFilter(int pageNumber, String filter, String input);

}
