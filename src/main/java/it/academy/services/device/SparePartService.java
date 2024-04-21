package it.academy.services.device;

import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.resp.SparePartDTO;
import it.academy.dto.resp.SparePartForChangeDTO;
import it.academy.dto.resp.ListForPage;

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
