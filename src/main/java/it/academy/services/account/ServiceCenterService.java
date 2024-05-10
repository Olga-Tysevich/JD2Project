package it.academy.services.account;

import it.academy.dto.TablePage;
import it.academy.dto.account.ServiceCenterDTO;
import java.util.List;
import java.util.Map;

public interface ServiceCenterService{

    void create(ServiceCenterDTO serviceCenterDTO);

    void update(ServiceCenterDTO serviceCenterDTO);

    void delete(long id);

    ServiceCenterDTO find(long id);

    List<ServiceCenterDTO> findAll();

    TablePage<ServiceCenterDTO> findForPage(int pageNumber, Map<String, String> userInput);

}
