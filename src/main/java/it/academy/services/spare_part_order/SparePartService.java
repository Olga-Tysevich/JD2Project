package it.academy.services.spare_part_order;

import it.academy.dto.spare_part.ChangeSparePartDTO;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.dto.TablePage;

import java.util.List;

public interface SparePartService {

    void createSparePart(ChangeSparePartDTO sparePartDTO);

    void updateSparePart(ChangeSparePartDTO sparePartDTO);

    void deleteSparePart(long id);

    SparePartForChangeDTO getSparePartForm();

    SparePartForChangeDTO getSparePartForm(long id);

    SparePartForChangeDTO findSparePart(long id);

    TablePage<SparePartDTO> findSpareParts(int pageNumber, String filter, String input);

    List<SparePartDTO> findSparePartsByRepairId(long id);

}
