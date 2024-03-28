package it.academy.utils.dao;


import it.academy.dao.DAO;

import java.util.List;

public class DAOManager {
    private static DAOManager instance;
    private List<DAO> daoList = List.of();

    private DAOManager() {
    }

}
