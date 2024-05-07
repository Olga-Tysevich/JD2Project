package it.academy.services.account;

import it.academy.dto.TablePage2;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.TablePage;

import java.util.List;

public interface ServiceCenterService{

    void create(ServiceCenterDTO serviceCenterDTO);

    void update(ServiceCenterDTO serviceCenterDTO);

    void delete(long id);

    ServiceCenterDTO find(long id);

    List<ServiceCenterDTO> findAll();

    TablePage2<ServiceCenterDTO> findForPage(int pageNumber);

    TablePage2<ServiceCenterDTO> findForPageByFilter(int pageNumber, String filter, String input);

}
