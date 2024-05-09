package it.academy.dao.device.impl;

import it.academy.dao.device.DeviceDAO;
import it.academy.dao.impl.ComponentDAOImpl;
import it.academy.entities.device.Device;
import it.academy.entities.device.Model;
import it.academy.entities.repair.RepairType;
import it.academy.utils.TransactionManger;
import it.academy.utils.enums.RepairStatus;
import it.academy.utils.enums.RoleEnum;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static it.academy.utils.constants.Constants.REGEX;

public class DeviceDAOImpl extends ComponentDAOImpl<Device, Long> implements DeviceDAO {

    public DeviceDAOImpl(TransactionManger manger) {
        super(manger, Device.class);
    }

    @Override
    protected <S extends Enum<S>> Predicate createSearchPredicate2(CriteriaBuilder cb, Root<?> root, String searchQuery) {
        String[] searchTerms = searchQuery.split(REGEX);
        List<Predicate> searchPredicates = new ArrayList<>();

        for (String term : searchTerms) {
            List<Predicate> predicates = new ArrayList<>();
            Set<? extends SingularAttribute<?, ?>> attributes = root.getModel().getDeclaredSingularAttributes();

            for (SingularAttribute attribute : attributes) {
                Class<?> attributeType = attribute.getJavaType();

                if (attributeType.equals(Model.class)) {
                    CriteriaQuery<Model> criteria = cb.createQuery(Model.class);
                    Root<Model> modelRoot = criteria.from(Model.class);
                    predicates.add(super.createSearchPredicate2(cb, modelRoot, searchQuery));
                } else {
                    addSimpleAttributes(predicates, attribute, root, attributeType, term);
                }
            }

            searchPredicates.add(cb.or(predicates.toArray(new Predicate[0])));
        }

        return cb.and(searchPredicates.toArray(new Predicate[0]));
    }
}
