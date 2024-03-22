package it.academy.dao.account.impl;

import it.academy.dao.account.PermissionDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.account.role.Permission;

public class PermissionDAOImpl extends DAOImpl<Permission, Long> implements PermissionDAO {

    public PermissionDAOImpl() {
        super(Permission.class);
    }

}
