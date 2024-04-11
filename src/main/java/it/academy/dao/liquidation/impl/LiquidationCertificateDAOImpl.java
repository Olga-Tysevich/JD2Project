package it.academy.dao.liquidation.impl;

import it.academy.dao.liquidation.LiquidationCertificateDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.liquidation.LiquidationCertificate;

public class LiquidationCertificateDAOImpl extends DAOImpl<LiquidationCertificate, Long>
        implements LiquidationCertificateDAO {

    public LiquidationCertificateDAOImpl() {
        super(LiquidationCertificate.class);
    }

}
