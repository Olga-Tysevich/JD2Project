package it.academy.dao.liqidation.impl;

import it.academy.dao.DAOImpl;
import it.academy.dao.liqidation.LiquidationCertificateDAO;
import it.academy.entities.liquidation.LiquidationCertificate;

public class LiquidationCertificateDAOImpl extends DAOImpl<LiquidationCertificate, Long>
        implements LiquidationCertificateDAO {

    public LiquidationCertificateDAOImpl() {
        super(LiquidationCertificate.class);
    }

}
