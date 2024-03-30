package it.academy.dao.account.impl;

import it.academy.dao.account.RoleDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.role.Role;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoleDAOImpl extends DAOImpl<Role, Long> implements RoleDAO {

    public RoleDAOImpl() {
        super(Role.class);
    }

}
