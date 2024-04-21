package it.academy.services.account;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.ListForPage;
import it.academy.exceptions.account.EmailAlreadyRegistered;

import java.util.List;

public interface ServiceCenterService {

    void addServiceCenter(ServiceCenterDTO serviceCenterDTO) throws EmailAlreadyRegistered;

    void updateServiceCenter(ServiceCenterDTO serviceCenterDTO);

    ServiceCenterDTO findServiceCenter(long id);

    List<ServiceCenterDTO> findServiceCenters();

    ListForPage<ServiceCenterDTO> findServiceCenters(int pageNumber);

    ListForPage<ServiceCenterDTO> findServiceCenters(int pageNumber, String filter, String input);

}
