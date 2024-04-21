package it.academy.services.device;

import it.academy.dto.spare_part.ChangeSparePartDTO;
import it.academy.dto.spare_part.SparePartDTO;
import it.academy.dto.spare_part.SparePartForChangeDTO;
import it.academy.dto.ListForPage;

import java.util.List;

public interface SparePartService {

    void createSparePart(ChangeSparePartDTO sparePartDTO);

    void updateSparePart(ChangeSparePartDTO sparePartDTO);

    SparePartForChangeDTO getSparePartForm();

    SparePartForChangeDTO getSparePartForm(long id);

    SparePartForChangeDTO findSparePart(long id);

    ListForPage<SparePartDTO> findSpareParts(int pageNumber);

    ListForPage<SparePartDTO> findSpareParts(int pageNumber, String filter, String input);

    List<SparePartDTO> findSparePartsByModelId(long id);

}
