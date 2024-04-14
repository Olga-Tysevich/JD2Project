package it.academy.services.spare_part;

import it.academy.dto.req.SparePartDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.SparePartForTableDTO;
import it.academy.dto.resp.ListForPage;

import java.util.List;

public interface SparePartService {

    void addSparePart(SparePartDTO sparePartDTO);

    void changeSparePart(SparePartDTO sparePartDTO);

    SparePartDTO findSparePart(long id);

    ListForPage<SparePartForTableDTO> findSpareParts(AccountDTO accountDTO, int pageNumber);

    List<SparePartForTableDTO> findSparePartsByDeviceTypeId(AccountDTO accountDTO, long id);

    ListForPage<SparePartForTableDTO> findSpareParts(AccountDTO accountDTO, int pageNumber, String filter, String input);


}
