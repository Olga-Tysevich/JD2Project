package it.academy.dao.impl;

import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.entities.repair.Repair;
import it.academy.utils.TransactionManger;

import java.util.List;
import java.util.Map;

import static it.academy.utils.constants.Constants.LIST_SIZE;
import static it.academy.utils.constants.Constants.SERIAL_NUMBER;

public class Test {
    public static void main(String[] args) {
       RepairDAO dao = new RepairDAOImpl(new TransactionManger());

        List<Repair> list = dao.findAllByPageAndFilter(1, LIST_SIZE, Map.of(SERIAL_NUMBER, "RPR050122"));
    }
}
