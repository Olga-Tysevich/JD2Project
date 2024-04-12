package it.academy.dao.device.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.DAOImpl;
import it.academy.entities.device.components.Model;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.Constants.BRAND;
import static it.academy.utils.Constants.OBJECT_ID;

public class ModelDAOImpl extends DAOImpl<Model, Long> implements ModelDAO {

    public ModelDAOImpl() {
        super(Model.class);
    }

    @Override
    public List<Model> findAllByBrandId(long brandId) {
        CriteriaQuery<Model> findByBrand = criteriaBuilder().createQuery(Model.class);
        Root<Model> root = findByBrand.from(Model.class);

        findByBrand.select(root)
                .where(criteriaBuilder().equal(root.get(BRAND).get(OBJECT_ID), brandId));

        return entityManager()
                .createQuery(findByBrand)
                .getResultList();
    }
}
