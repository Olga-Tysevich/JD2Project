package it.academy.dao.account.impl;

import it.academy.dao.account.RoleDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.role.Role;

public class RoleDAOImpl extends DAOImpl<Role, Long> implements RoleDAO {

    public RoleDAOImpl() {
        super(Role.class);
    }

}
