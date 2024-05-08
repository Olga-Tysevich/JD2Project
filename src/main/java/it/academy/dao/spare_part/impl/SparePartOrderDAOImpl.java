package it.academy.dao.spare_part.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.spare_part.SparePartOrderDAO;
import it.academy.entities.repair.Repair;
import it.academy.entities.spare_part.SparePartOrder;
import it.academy.entities.spare_part.SparePartOrder_;
import it.academy.utils.TransactionManger;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.constants.Constants.DELETE_FROM_ORDER_ITEMS;
import static it.academy.utils.constants.Constants.DELETE_FROM_SPARE_PART;

public class SparePartOrderDAOImpl extends DAOImpl<SparePartOrder, Long> implements SparePartOrderDAO {

    public SparePartOrderDAOImpl(TransactionManger manger) {
        super(manger, SparePartOrder.class);
    }

    @Override
    public List<SparePartOrder> findByRepairId(long id) {
        CriteriaQuery<SparePartOrder> find = criteriaBuilder().createQuery(SparePartOrder.class);
        Root<SparePartOrder> root = find.from(SparePartOrder.class);

        Join<SparePartOrder, Repair> join = root.join(SparePartOrder_.REPAIR);
        find.select(root).where(criteriaBuilder().equal(join.get(SparePartOrder_.ID), id));
        return entityManager()
                .createQuery(find)
                .getResultList();
    }

    @Override
    public boolean delete(Long id) {
        entityManager().createNativeQuery(DELETE_FROM_ORDER_ITEMS)
                .setParameter(1, id)
                .executeUpdate();
        return super.delete(id);
    }

}
