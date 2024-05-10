package it.academy.dao.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.Account;
import it.academy.entities.account.Account_;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.account.ServiceCenter_;
import it.academy.utils.TransactionManger;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static it.academy.utils.constants.Constants.*;

public class AccountDAOImpl extends DAOImpl<Account, Long> implements AccountDAO {

    public AccountDAOImpl(TransactionManger manger) {
        super(manger, Account.class);
    }

    @Override
    public boolean checkIfEmailExist(long id, String email) {
        TypedQuery<Long> find = entityManager().createQuery(CHECK_ACCOUNT, Long.class);
        find.setParameter(OBJECT_ID, id);
        find.setParameter(EMAIL, email);
        return find.getSingleResult() != 0;
    }

    @Override
    public List<Account> findServiceCenterAccounts(long serviceCenterId) {
        TypedQuery<Account> find = entityManager().createQuery(FIND_ACCOUNTS_BY_SERVICE_CENTER_ID, Account.class);
        find.setParameter(ServiceCenter_.ID, serviceCenterId);

        return find.getResultList();
    }

    @Override
    protected Predicate[] createPredicate(From<?, ?> root, Map<String, String> searchParam) {
        List<Predicate> predicates = new ArrayList<>();
        searchParam.forEach((key, value) -> {
            if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
                String filter = key.trim();
                String input = value.trim().toLowerCase();
                if (ServiceCenter_.SERVICE_NAME.equals(filter)) {
                    Join<Account, ServiceCenter> serviceCenterJoin = root.join(Account_.SERVICE_CENTER);
                    Class<?> fClass = serviceCenterJoin.get(filter).getJavaType();
                    addSimpleAttributes(predicates, serviceCenterJoin, fClass, filter, input);
                } else {
                    Class<?> fClass = root.get(filter).getJavaType();
                    addSimpleAttributes(predicates, root, fClass, filter, input);
                }
            }
        });

        return predicates.toArray(new Predicate[0]);
    }


}
