package it.academy.dao.device.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.Constants.OBJECT_ID;
import static it.academy.utils.Constants.SPARE_PARTS;

public class DeviceTypeDAOImpl extends DAOImpl<DeviceType, Long> implements DeviceTypeDAO {

    public DeviceTypeDAOImpl() {
        super(new TransactionManger(), DeviceType.class);
    }

    @Override
    public List<DeviceType> findBySparePartId(long id) {
        CriteriaQuery<DeviceType> find = criteriaBuilder().createQuery(DeviceType.class);
        Root<DeviceType> root = find.from(DeviceType.class);

        Join<DeviceType, SparePart> join = root.join(SPARE_PARTS);
        find.select(root).where(criteriaBuilder().equal(join.get(OBJECT_ID), id));
        return entityManager()
                .createQuery(find)
                .getResultList();
    }
}
