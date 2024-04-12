package it.academy.dao.liquidation.impl;

import it.academy.dao.liquidation.LiquidationCertificateDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.liquidation.LiquidationCertificate;
import it.academy.utils.dao.TransactionManger;

public class LiquidationCertificateDAOImpl extends DAOImpl<LiquidationCertificate, Long>
        implements LiquidationCertificateDAO {

    public LiquidationCertificateDAOImpl() {
        super(new TransactionManger(), LiquidationCertificate.class);
    }

}
