package it.academy.services;

import it.academy.dto.ListForPage;
import it.academy.dto.service_center.ServiceCenterDTO;

import java.util.List;

public interface ServiceCenterService {

    void addServiceCenter(ServiceCenterDTO serviceCenterDTO);

    void updateServiceCenter(ServiceCenterDTO serviceCenterDTO);

    ServiceCenterDTO findServiceCenter(long id);

    List<ServiceCenterDTO> findServiceCenter();

    ListForPage<ServiceCenterDTO> findServiceCenter(int pageNumber);

    ListForPage<ServiceCenterDTO> findServiceCenter(int pageNumber, String filter, String input);

}
