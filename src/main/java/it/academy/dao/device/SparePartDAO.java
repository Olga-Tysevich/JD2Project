package it.academy.dao.device;

import it.academy.entities.SparePart;

import java.util.List;

public interface SparePartDAO extends DeviceComponentDAO<SparePart, Long> {

    List<SparePart> findByModelId(long id);
}
