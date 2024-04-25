package it.academy.dao.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.impl.ComponentDAOImpl;
import it.academy.entities.device.Brand;
import it.academy.entities.device.Model;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.criteria.*;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class BrandDAOImpl extends ComponentDAOImpl<Brand, Long> implements BrandDAO {

    public BrandDAOImpl(TransactionManger manger) {
        super(manger, Brand.class);
    }

    @Override
    public List<Brand> findBrandsWithModels() {
        CriteriaQuery<Brand> query = criteriaBuilder().createQuery(Brand.class);
        Root<Brand> brandRoot = query.from(Brand.class);
        Root<Model> modelRoot = query.from(Model.class);
        Join<Model, Brand> modelJoin = modelRoot.join(BRAND, JoinType.INNER);

        query.select(modelJoin)
                .where(criteriaBuilder().equal(modelJoin.get(OBJECT_ID), brandRoot.get(OBJECT_ID)),
                        criteriaBuilder().equal(brandRoot.get(IS_ACTIVE), true));

       return entityManager()
               .createQuery(query)
               .getResultList();
    }
}
