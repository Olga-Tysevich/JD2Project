package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.LiquidationCertificateDAO;
import it.academy.entities.repair.decommissioning.LiquidationCertificate;

public class LiquidationCertificateDAOImpl extends DAOImpl<LiquidationCertificate, Long>
        implements LiquidationCertificateDAO {

    public LiquidationCertificateDAOImpl() {
        super(LiquidationCertificate.class);
    }

}
