package it.academy.services.account;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.TablePage;

import java.util.List;

public interface ServiceCenterService{

    void createServiceCenter(ServiceCenterDTO serviceCenterDTO);

    void updateServiceCenter(ServiceCenterDTO serviceCenterDTO);

    void deleteServiceCenter(long id);

    ServiceCenterDTO findServiceCenter(long id);

    List<ServiceCenterDTO> findServiceCenters();

    TablePage<ServiceCenterDTO> findServiceCenters(int pageNumber, String filter, String input);

}
