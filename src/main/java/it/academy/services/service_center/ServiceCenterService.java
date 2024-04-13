package it.academy.services.service_center;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.common.AccessDenied;

import java.util.List;

public interface ServiceCenterService {

    void addServiceCenter(ServiceCenterDTO serviceCenterDTO) throws EmailAlreadyRegistered, AccessDenied;

    void updateServiceCenter(ServiceCenterDTO serviceCenterDTO) throws AccessDenied;

    ServiceCenterDTO findServiceCenter(long id);

    List<ServiceCenterDTO> findServiceCenters(AccountDTO accountDTO);

    ListForPage<ServiceCenterDTO> findServiceCenters(AccountDTO accountDTO, int pageNumber);

    ListForPage<ServiceCenterDTO> findServiceCenters(AccountDTO accountDTO, int pageNumber, String filter, String input);

}
