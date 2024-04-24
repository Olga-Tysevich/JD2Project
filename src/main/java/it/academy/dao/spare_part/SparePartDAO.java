package it.academy.dao.spare_part;

import it.academy.dao.DAO;
import it.academy.entities.spare_part.SparePart;

import java.util.List;

public interface SparePartDAO extends DAO<SparePart, Long> {

    List<SparePart> findByModelId(long id);
}
