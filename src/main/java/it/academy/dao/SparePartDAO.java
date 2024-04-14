package it.academy.dao;

import it.academy.entities.SparePart;

import java.util.List;

public interface SparePartDAO extends DAO<SparePart, Long> {

    List<SparePart> findByDeviceTypeId(long id);
}
