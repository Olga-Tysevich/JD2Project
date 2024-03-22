package it.academy.dao.repair.impl;

import it.academy.dao.impl.DAOImpl;
import it.academy.dao.repair.DecommissioningCertificateDAO;
import it.academy.entities.repair.decommissioning.DecommissioningCertificate;

public class DecommissioningCertificateDAOImpl extends DAOImpl<DecommissioningCertificate, Long>
        implements DecommissioningCertificateDAO {

    public DecommissioningCertificateDAOImpl() {
        super(DecommissioningCertificate.class);
    }

}
