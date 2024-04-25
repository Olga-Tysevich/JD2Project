package it.academy.dao.account.impl;

import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.ServiceCenter;
import it.academy.utils.dao.TransactionManger;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import static it.academy.utils.constants.Constants.*;

public class ServiceCenterDAOImpl extends DAOImpl<ServiceCenter, Long> implements ServiceCenterDAO {

    public ServiceCenterDAOImpl(TransactionManger manger) {
        super(manger, ServiceCenter.class);
    }

    @Override
    public boolean checkIfServiceCenterExist(long id, String name) {
        TypedQuery<Long> find = entityManager().createQuery(CHECK_SERVICE_CENTER, Long.class);
        find.setParameter(OBJECT_ID, id);
        find.setParameter(OBJECT_NAME, name);

        return find.getSingleResult() != 0;
    }

    @Override
    public List<ServiceCenter> findByRequisites(String filter, String input, int pageNumber, int listSize) {
        CriteriaQuery<ServiceCenter> find = criteriaBuilder().createQuery(ServiceCenter.class);
        Root<ServiceCenter> root = find.from(ServiceCenter.class);

        find.select(root)
                .where(criteriaBuilder()
                        .like(root.get(SERVICE_CENTER_REQUISITES)
                                .get(filter), String.format(LIKE_QUERY_PATTERN, input)));

        return entityManager().createQuery(find)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public long getNumberOfEntriesByRequisites(String filter, String input) {
        String query = String.format(GET_NUMBER_OF_SERVICE_CENTERS_BY_REQUISITES, filter);
        TypedQuery<Long> count = entityManager().createQuery(query, Long.class);
        count.setParameter(PARAMETER_VALUE, String.format(LIKE_QUERY_PATTERN, input));
        return count.getSingleResult();
    }
}
