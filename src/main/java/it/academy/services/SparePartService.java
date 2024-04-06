package it.academy.services;

import it.academy.dto.ListForPage;
import it.academy.dto.spare_parts.SparePartDTO;

public interface SparePartService {

    void addSparePart(SparePartDTO partDTO);

    void changeSparePart(SparePartDTO partDTO);

    SparePartDTO findSparePart(long id);

    ListForPage<SparePartDTO> findSpareParts(int pageNumber);

//    ListForPage<SparePartDTO> findSparePart(ParametersForSearchDTO parameters);

}
