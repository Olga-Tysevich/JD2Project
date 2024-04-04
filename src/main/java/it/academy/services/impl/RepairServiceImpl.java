package it.academy.services.impl;


import it.academy.services.RepairService;
import it.academy.utils.dao.TransactionManger;

public class RepairServiceImpl implements RepairService {
    private TransactionManger transactionManger = TransactionManger.getInstance();

}
