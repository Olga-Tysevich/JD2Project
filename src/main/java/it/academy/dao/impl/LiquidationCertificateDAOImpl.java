package it.academy.dao.impl;

import it.academy.dao.LiquidationCertificateDAO;
import it.academy.entities.liquidation.LiquidationCertificate;

public class LiquidationCertificateDAOImpl extends DAOImpl<LiquidationCertificate, Long>
        implements LiquidationCertificateDAO {

    public LiquidationCertificateDAOImpl() {
        super(LiquidationCertificate.class);
    }

}
