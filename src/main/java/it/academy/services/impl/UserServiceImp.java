package it.academy.services.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dto.resp.RespDTO;
import it.academy.services.UserService;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.ExceptionManager;
import java.math.BigInteger;

public class UserServiceImp implements UserService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private ModelDAO modelDAO = new ModelDAOImpl();

    @Override
    public RespDTO<BigInteger> getNumberOfEntries() {
        RespDTO<BigInteger> resp = ExceptionManager.getObjectFindResult(() -> transactionManger.execute(() -> modelDAO.getNumberOfEntries()));
        transactionManger.closeManager();
        return resp;
    }
}
