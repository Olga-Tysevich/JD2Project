package it.academy.services;

import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.SparePartDTO;
import it.academy.dto.resp.ListForPage;

import java.util.List;

public interface SparePartService {

    void createSparePart(ChangeSparePartDTO sparePartDTO);

    void updateSparePart(ChangeSparePartDTO sparePartDTO);

    SparePartDTO findSparePart(long id);

    ListForPage<SparePartDTO> findSpareParts(AccountDTO accountDTO, int pageNumber);

    ListForPage<SparePartDTO> findSpareParts(AccountDTO accountDTO, int pageNumber, String filter, String input);

    List<SparePartDTO> findSparePartsByDeviceTypeId(AccountDTO accountDTO, long id);

}
