package it.academy.dao.account.impl;

import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.account.ServiceCenter_;
import it.academy.utils.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static it.academy.utils.constants.Constants.*;

public class ServiceCenterDAOImpl extends DAOImpl<ServiceCenter, Long> implements ServiceCenterDAO {

    public ServiceCenterDAOImpl(TransactionManger manger) {
        super(manger, ServiceCenter.class);
    }

    @Override
    public boolean checkIfServiceCenterExist(long id, String name) {
        TypedQuery<Long> find = entityManager().createQuery(CHECK_SERVICE_CENTER, Long.class);
        find.setParameter(ServiceCenter_.ID, id);
        find.setParameter(ServiceCenter_.SERVICE_NAME, name);

        return find.getSingleResult() != 0;
    }

    @Override
    protected Predicate[] createPredicate(From<?, ?> root, Map<String, String> searchParam) {
        List<Predicate> predicates = new ArrayList<>();
        searchParam.forEach((key, value) -> {
            if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
                String filter = key.trim();
                String input = value.trim().toLowerCase();
                if (FilterManager.isRequisitesFilter(filter)) {
                    Path<String> field = root.get(ServiceCenter_.REQUISITES).get(filter);
                    predicates.add(criteriaBuilder().like(criteriaBuilder().lower(field), String.format(LIKE_QUERY_PATTERN, input)));
                } else {
                    Class<?> fClass = root.get(filter).getJavaType();
                    addSimpleAttributes(predicates, root, fClass, filter, input);
                }
            }
        });

        return predicates.toArray(new Predicate[0]);
    }
}
