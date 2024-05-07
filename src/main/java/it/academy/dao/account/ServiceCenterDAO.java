package it.academy.dao.account;

import it.academy.dao.DAO;
import it.academy.entities.account.ServiceCenter;

import java.util.List;

public interface ServiceCenterDAO extends DAO<ServiceCenter, Long> {

    boolean checkIfServiceCenterExist(long id, String name);

}
