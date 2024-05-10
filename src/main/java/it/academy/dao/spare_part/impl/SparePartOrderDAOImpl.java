package it.academy.dao.spare_part.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.spare_part.SparePartOrderDAO;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.account.ServiceCenter_;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.Repair_;
import it.academy.entities.spare_part.SparePartOrder;
import it.academy.entities.spare_part.SparePartOrder_;
import it.academy.utils.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static it.academy.utils.constants.Constants.*;

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

    @Override
    protected Predicate[] createPredicate(From<?, ?> root, Map<String, String> searchParam) {
        List<Predicate> predicates = new ArrayList<>();
        searchParam.forEach((key, value) -> {
            if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
                String filter = key.trim();
                String input = value.trim().toLowerCase();
                if (FilterManager.isServiceCenterFilter(filter)) {
                    Join<SparePartOrder, Repair> repairJoin = root.join(SparePartOrder_.REPAIR);
                    Join<Repair, ServiceCenter> serviceCenterJoin = repairJoin.join(Repair_.SERVICE_CENTER);
                    Class<?> fClass = serviceCenterJoin.get(ServiceCenter_.SERVICE_NAME).getJavaType();
                    addSimpleAttributes(predicates, serviceCenterJoin, fClass, ServiceCenter_.SERVICE_NAME, input);
                } else if (Repair_.REPAIR_NUMBER.equals(filter)) {
                    Join<SparePartOrder, Repair> repairJoin = root.join(SparePartOrder_.REPAIR);
                    Class<?> fClass = repairJoin.get(ServiceCenter_.SERVICE_NAME).getJavaType();
                    addSimpleAttributes(predicates, repairJoin, fClass, ServiceCenter_.SERVICE_NAME, input);
                } else {
                    Class<?> fClass = root.get(filter).getJavaType();
                    addSimpleAttributes(predicates, root, fClass, filter, input);
                }
            }
        });

        return predicates.toArray(new Predicate[0]);
    }

}
