package it.academy.dao.spare_part.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.spare_part.SparePartDAO;
import it.academy.entities.device.Model;
import it.academy.entities.spare_part.SparePart;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class SparePartDAOImpl extends DAOImpl<SparePart, Long> implements SparePartDAO {

    public SparePartDAOImpl(TransactionManger manger) {
        super(manger, SparePart.class);
    }

    @Override
    public List<SparePart> findByModelId(long id) {
        CriteriaQuery<SparePart> find = criteriaBuilder().createQuery(SparePart.class);
        Root<SparePart> root = find.from(SparePart.class);

        Join<SparePart, Model> join = root.join(MODELS);
        find.select(root).where(criteriaBuilder().equal(join.get(OBJECT_ID), id));
        return entityManager()
                .createQuery(find)
                .getResultList();
    }
}
