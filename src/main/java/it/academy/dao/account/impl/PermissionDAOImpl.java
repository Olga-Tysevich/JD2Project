package it.academy.dao.account.impl;

import it.academy.dao.account.PermissionDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.Role;
import it.academy.utils.dao.ParameterContainer;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class PermissionDAOImpl extends DAOImpl<Permission, Long> implements PermissionDAO {

    public PermissionDAOImpl() {
        super(Permission.class);
    }


    @Override
    public Permission findByCategoryAndType(List<ParameterContainer<?>> parameters) {
        CriteriaQuery<Permission> findByParameters = criteriaBuilder().createQuery(Permission.class);
        Root<Permission> root = findByParameters.from(Permission.class);

        Predicate equalParameters = criteriaBuilder().conjunction();
        parameters.forEach(p ->
                equalParameters.getExpressions()
                        .add(criteriaBuilder()
                                .and(criteriaBuilder()
                                        .equal(root.get(p.getParameterName()), p.getParameterValue())))
        );

        findByParameters.select(root)
                .where(equalParameters);
        return entityManager().createQuery(findByParameters)
                .getResultStream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
