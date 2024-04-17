package it.academy.dao.impl;

import it.academy.dao.SparePartDAO;
import it.academy.entities.DeviceType;
import it.academy.entities.SparePart;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class SparePartDAOImpl extends DAOImpl<SparePart, Long> implements SparePartDAO {

    public SparePartDAOImpl() {
        super(SparePart.class);
    }

    public SparePartDAOImpl(TransactionManger manger) {
        super(manger, SparePart.class);
    }

    @Override
    public List<SparePart> findByDeviceTypeId(long id) {
        CriteriaQuery<SparePart> find = criteriaBuilder().createQuery(SparePart.class);
        Root<SparePart> root = find.from(SparePart.class);

        Join<SparePart, DeviceType> join = root.join(DEVICE_TYPES);
        find.select(root).where(criteriaBuilder().equal(join.get(OBJECT_ID), id));
        return entityManager()
                .createQuery(find)
                .getResultList();
    }
}
