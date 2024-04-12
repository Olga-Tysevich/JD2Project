package it.academy.services.service_center;

import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.service_center.ServiceCenterDTO;
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
