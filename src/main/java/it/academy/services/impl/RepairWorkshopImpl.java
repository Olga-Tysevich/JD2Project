package it.academy.services.impl;

import it.academy.dao.RepairWorkshopDAO;
import it.academy.dao.impl.RepairWorkshopDAOImpl;
import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.services.RepairWorkshopService;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;
import it.academy.utils.dao.TransactionManger;

public class RepairWorkshopImpl implements RepairWorkshopService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private RepairWorkshopDAO repairWorkshopDAO = new RepairWorkshopDAOImpl();

    @Override
    public RepairWorkshopDTO findRepairWorkshop(long id) {
        RepairWorkshop repairWorkshop = transactionManger.execute(() -> repairWorkshopDAO.find(id));
        RepairWorkshopDTO repairWorkshopDTO = RepairWorkshopConverter.convertToDTO(repairWorkshop);
        transactionManger.closeManager();
        return repairWorkshopDTO;
    }

}
