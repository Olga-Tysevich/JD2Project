package it.academy.services;

import it.academy.dto.spare_parts.SparePartDTO;

import java.util.List;

public interface SparePartService {

    void addSparePart(SparePartDTO sparePartDTO, List<Long> deviceTypesId);

    void updateSparePart(SparePartDTO sparePartDTO, List<Long> deviceTypesId);

    void deleteSparePart(long id);

}
