package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.account.ServiceCenter_;
import it.academy.entities.device.*;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.RepairType;
import it.academy.entities.repair.RepairType_;
import it.academy.entities.repair.Repair_;
import it.academy.utils.TransactionManger;
import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class RepairDAOImpl extends DAOImpl<Repair, Long> implements RepairDAO {

    public RepairDAOImpl(TransactionManger manger) {
        super(manger, Repair.class);
    }

//    @Override
//    public List<Repair> findRepairsByStatus(RepairStatus status, int page, int listSize) {
//        return entityManager().createQuery(FIND_REPAIRS_BY_STATUS, Repair.class)
//                .setParameter(REPAIR_STATUS, status)
//                .setFirstResult((page - 1) * listSize)
//                .setMaxResults(listSize)
//                .getResultList();
//    }
//
//    @Override
//    public List<Repair> findRepairsByStatusAndServiceId(long serviceCenterId, RepairStatus status, int page, int listSize) {
//        return entityManager().createQuery(FIND_REPAIRS_BY_STATUS_AND_SERVICE_ID, Repair.class)
//                .setParameter(REPAIR_STATUS, status)
//                .setParameter(OBJECT_ID, serviceCenterId)
//                .setFirstResult((page - 1) * listSize)
//                .setMaxResults(listSize)
//                .getResultList();
//    }
//
//    @Override
//    public long getNumberOfEntriesByStatusAndServiceId(long serviceCenterId, RepairStatus status) {
//        return entityManager().createQuery(GET_NUMBER_OF_REPAIRS_BY_STATUS_AND_SERVICE_ID, Long.class)
//                .setParameter(REPAIR_STATUS, status)
//                .setParameter(OBJECT_ID, serviceCenterId)
//                .getSingleResult();
//    }
//
//    @Override
//    public long getNumberOfEntriesByStatus(RepairStatus status) {
//        return entityManager().createQuery(GET_NUMBER_OF_REPAIRS_BY_STATUS, Long.class)
//                .setParameter(REPAIR_STATUS, status)
//                .getSingleResult();
//    }
//
//    @Override
//    public List<Repair> findRepairsByServiceId(long serviceCenterId, int page, int listSize) {
//        return entityManager().createQuery(FIND_REPAIRS_BY_SERVICE_ID, Repair.class)
//                .setParameter(OBJECT_ID, serviceCenterId)
//                .setFirstResult((page - 1) * listSize)
//                .setMaxResults(listSize)
//                .getResultList();
//    }
//
//    @Override
//    public long getNumberOfEntriesByServiceId(long serviceCenterId) {
//        return entityManager().createQuery(GET_NUMBER_OF_REPAIRS_BY_SERVICE_ID, Long.class)
//                .setParameter(OBJECT_ID, serviceCenterId)
//                .getSingleResult();
//    }
//
//    @Override
//    public List<Repair> findRepairsByFilterAndServiceId(long serviceCenterId, int page, int listSize, String filter, String input) {
//        CriteriaQuery<Repair> find = criteriaBuilder().createQuery(Repair.class);
//        Root<Repair> repairRoot = find.from(Repair.class);
//        Join<Repair, ServiceCenter> join = repairRoot.join(Repair_.SERVICE_CENTER);
//
//        Predicate findByAnyMatch = createLikePredicate(repairRoot, filter, input);
//
//        find.select(repairRoot)
//                .where(criteriaBuilder().equal(join.get(ServiceCenter_.ID), serviceCenterId), findByAnyMatch);
//
//        return entityManager().createQuery(find)
//                .setFirstResult((page - 1) * listSize)
//                .setMaxResults(listSize)
//                .getResultList();
//    }
//
//    @Override
//    public long getNumberOfEntriesByFilterAndServiceId(long serviceCenterId, String filter, String input) {
//        CriteriaQuery<Long> find = criteriaBuilder().createQuery(Long.class);
//        Root<Repair> repairRoot = find.from(Repair.class);
//        Join<Repair, ServiceCenter> join = repairRoot.join(Repair_.SERVICE_CENTER);
//
//        Predicate findByAnyMatch = createLikePredicate(repairRoot, filter, input);
//
//        find.select(criteriaBuilder().count(repairRoot))
//                .where(criteriaBuilder().equal(join.get(ServiceCenter_.ID), serviceCenterId), findByAnyMatch);
//
//        return entityManager().createQuery(find)
//                .getSingleResult();
//    }

    @Override
    public long getCountBySearch(Integer page, Integer listSize, Map<String, String> searchParam) {
        CriteriaQuery<Long> find = criteriaBuilder().createQuery(Long.class);
        Root<Repair> root = find.from(Repair.class);
        Predicate[] predicates = cratePredicate(root, searchParam);
        find.select(criteriaBuilder().count(root));
        if (predicates.length != 0) {
            find.where(criteriaBuilder().and(predicates));
        }
        return entityManager().createQuery(find)
                .getSingleResult();
    }

    @Override
    public List<Repair> findAllByPageAndSearch(Integer page, Integer listSize, Map<String, String> searchParam) {
        CriteriaQuery<Repair> find = criteriaBuilder().createQuery(Repair.class);
        Root<Repair> root = find.from(Repair.class);
        Predicate[] predicates = cratePredicate(root, searchParam);
        if (predicates.length != 0) {
            find.select(root).where(criteriaBuilder().and(predicates));
        }
        find.orderBy(criteriaBuilder().desc(root.get(Repair_.ID)));

        return entityManager().createQuery(find)
                .setFirstResult((page - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    private Predicate[] cratePredicate(From<?, ?> root, Map<String, String> searchParam) {
        List<Predicate> predicates = new ArrayList<>();
        searchParam.forEach((key, value) -> {
            if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
                String filter = key.trim();
                String input = value.trim().toLowerCase();
                if (FilterManager.isDeviceFilter(filter)) {
                    addDevicePredicate(root, filter, input, predicates);
                } else if (FilterManager.isServiceCenterFilter(filter)) {
                    addServiceCenterPredicate(root, filter, input, predicates);
                } else if (FilterManager.isRepairTypeFilter(filter)) {
                    addRepairType(root, filter, input, predicates);
                } else if (REPAIR_CATEGORY.equals(filter)) {
                    addRepairCategory(root, filter, input, predicates);
                } else if (REPAIR_STATUS.equals(filter)) {
                    addRepairStatus(root, filter, input, predicates);
                } else {
                    Class<?> fClass = root.get(filter).getJavaType();
                    addSimpleAttributes(predicates, root, fClass, filter, input);
                }
            }
        });

        return predicates.toArray(new Predicate[0]);
    }

    private void addDevicePredicate(From<?, ?> root, String filter, String input, List<Predicate> predicates) {
        Join<Repair, Device> deviceJoin = root.join(Repair_.DEVICE);
        if (!FilterManager.isModelFilter(filter)) {
            Class<?> fClass = deviceJoin.get(filter).getJavaType();
            addSimpleAttributes(predicates, deviceJoin, fClass, filter, input);
            return;
        }
        addModelPredicate(root, filter, input, predicates);
    }

    private void addModelPredicate(From<?, ?> root, String filter, String input, List<Predicate> predicates) {
        Join<Repair, Device> deviceJoin = root.join(Repair_.DEVICE);
        Join<Device, Model> modelJoin = deviceJoin.join(Device_.MODEL);

        if (Model_.BRAND.equals(filter)) {
            Join<Model, Brand> brandJoin = modelJoin.join(Model_.BRAND);
            Class<?> fClass = brandJoin.get(Brand_.NAME).getJavaType();
            addSimpleAttributes(predicates, brandJoin, fClass, Brand_.NAME, input);
            return;
        }

        if (Model_.TYPE.equals(filter)) {
            Join<Model, DeviceType> typeJoin = modelJoin.join(Model_.TYPE);
            Class<?> fClass = typeJoin.get(DeviceType_.NAME).getJavaType();
            addSimpleAttributes(predicates, typeJoin, fClass, DeviceType_.NAME, input);
            return;
        }

        Class<?> fClass = modelJoin.get(filter).getJavaType();
        addSimpleAttributes(predicates, modelJoin, fClass, filter, input);
    }

    private void addServiceCenterPredicate(From<?, ?> root, String filter, String input, List<Predicate> predicates) {
        Join<Repair, ServiceCenter> serviceCenterJoin = root.join(Repair_.SERVICE_CENTER);
        if (ServiceCenter_.ID.equals(filter)) {
            Class<?> fClass = serviceCenterJoin.get(ServiceCenter_.ID).getJavaType();
            addSimpleAttributes(predicates, serviceCenterJoin, fClass, ServiceCenter_.ID, input);
            return;
        }
        Class<?> fClass = serviceCenterJoin.get(ServiceCenter_.SERVICE_NAME).getJavaType();
        addSimpleAttributes(predicates, serviceCenterJoin, fClass, ServiceCenter_.SERVICE_NAME, input);
    }

    private void addRepairType(From<?, ?> root, String filter, String input, List<Predicate> predicates) {
        Join<Repair, RepairType> repairTypeJoin = root.join(Repair_.REPAIR_TYPE);
        Class<?> fClass = repairTypeJoin.get(RepairType_.NAME).getJavaType();
        addSimpleAttributes(predicates, root, fClass, filter, input);
    }

    private void addRepairStatus(From<?, ?> root, String filter, String input, List<Predicate> predicates) {
        try {
            RepairStatus status = RepairStatus.valueOf(input.toUpperCase());
            predicates.add(criteriaBuilder().equal(root.get(filter), status));
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
        }
    }

    private void addRepairCategory(From<?, ?> root, String filter, String input, List<Predicate> predicates) {
        try {
            RepairCategory status = RepairCategory.valueOf(input.toUpperCase());
            predicates.add(criteriaBuilder().equal(root.get(filter), status));
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
        }
    }

}
