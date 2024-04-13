package it.academy.dao.device.impl;

import it.academy.dao.DAOImpl;
import it.academy.dao.device.BrandDAO;
import it.academy.entities.device.components.Brand;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.Constants.*;

public class BrandDAOImpl extends DAOImpl<Brand, Long> implements BrandDAO {

    public BrandDAOImpl() {
        super(Brand.class);
    }

    @Override
    public List<Brand> findActiveBrands(boolean isActive) {
        String query = String.format(FIND_BY_ACTIVE_FIELD, Brand.class.getSimpleName());
        TypedQuery<Brand> find = entityManager().createQuery(query, Brand.class);
        find.setParameter(IS_ACTIVE_PARAMETER, 1);

        return find.getResultList();
    }

    @Override
    public List<Brand> findActiveBrandsForPage(boolean isActive, int pageNumber, int listSize) {
        String query = String.format(FIND_BY_ACTIVE_FIELD, Brand.class.getSimpleName());
        TypedQuery<Brand> find = entityManager().createQuery(query, Brand.class);
        find.setParameter(IS_ACTIVE_PARAMETER, isActive);

        return find.setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public List<Brand> findActiveBrandsForPage(boolean isActive, int pageNumber, int listSize, String filter, String value) {
        CriteriaQuery<Brand> findByParameters = criteriaBuilder().createQuery(Brand.class);
        Root<Brand> root = findByParameters.from(Brand.class);

        Predicate predicate = createLikePredicate(root, filter, value);

        findByParameters.select(root)
                .where(predicate, criteriaBuilder().equal(root.get(IS_ACTIVE), isActive))
                .orderBy(criteriaBuilder().desc(root.get(OBJECT_ID)));
        return entityManager()
                .createQuery(findByParameters)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }
}
