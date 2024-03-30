package it.academy.dao.account;

import it.academy.dao.DAO;
import it.academy.entities.account.role.Permission;
import it.academy.utils.dao.ParameterContainer;

import java.util.List;

public interface PermissionDAO extends DAO<Permission, Long> {

    Permission findByCategoryAndType(List<ParameterContainer<?>> parameters);
}
