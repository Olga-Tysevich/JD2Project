package it.academy.dao.device;

import it.academy.dao.DAO;

public interface DeviceComponentDAO<T, R> extends DAO<T, R> {

    boolean checkIfComponentExist(R id, String name);

}
