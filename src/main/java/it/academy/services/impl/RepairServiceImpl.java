package it.academy.services.impl;


import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dto.repair.RepairDTO;
import it.academy.entities.repair.Repair;
import it.academy.services.RepairService;
import it.academy.utils.converters.repair.RepairConverter;
import it.academy.utils.dao.TransactionManger;

public class RepairServiceImpl implements RepairService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private RepairDAO repairDAO = new RepairDAOImpl();

    @Override
    public void addRepair(RepairDTO repairDTO) {
        Repair repair = RepairConverter.convertDTOToEntity(repairDTO);
        transactionManger.execute(() -> repairDAO.create(repair));
    }

}
