package it.academy.dao.spare_part.impl;

import it.academy.dao.spare_part.SparePartDAO;
import it.academy.dao.impl.ComponentDAOImpl;
import it.academy.entities.device.Model;
import it.academy.entities.device.Model_;
import it.academy.entities.spare_part.SparePart;
import it.academy.entities.spare_part.SparePart_;
import it.academy.utils.TransactionManger;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static it.academy.utils.constants.Constants.*;

public class SparePartDAOImpl extends ComponentDAOImpl<SparePart, Long> implements SparePartDAO {

    public SparePartDAOImpl(TransactionManger manger) {
        super(manger, SparePart.class);
    }

    @Override
    public List<SparePart> findByModelId(long id) {
        CriteriaQuery<SparePart> find = criteriaBuilder().createQuery(SparePart.class);
        Root<SparePart> root = find.from(SparePart.class);

        Join<SparePart, Model> join = root.join(SparePart_.MODELS);
        find.select(root).where(criteriaBuilder().equal(join.get(Model_.ID), id),
                criteriaBuilder().equal(join.get(SparePart_.IS_ACTIVE), true));
        return entityManager()
                .createQuery(find)
                .getResultList();
    }

    @Override
    public boolean delete(Long id) {
        entityManager().createNativeQuery(DELETE_FROM_SPARE_PART)
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
                if (SparePart_.MODELS.equals(filter)) {
                    Join<SparePart, Model> modelJoin = root.join(SparePart_.MODELS);
                    Class<?> fClass = modelJoin.get(Model_.NAME).getJavaType();
                    addSimpleAttributes(predicates, modelJoin, fClass, Model_.NAME, input);
                } else {
                    Class<?> fClass = root.get(filter).getJavaType();
                    addSimpleAttributes(predicates, root, fClass, filter, input);
                }
            }
        });

        return predicates.toArray(new Predicate[0]);
    }
}
