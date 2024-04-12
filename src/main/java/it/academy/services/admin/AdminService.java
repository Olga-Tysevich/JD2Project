package it.academy.services.admin;

import it.academy.dto.account.req.ChangeAccountDTO;
import it.academy.dto.account.req.CreateAccountDTO;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import java.util.List;

public interface AdminService {

    void createAccount(CreateAccountDTO account) throws EnteredPasswordsNotMatch, EmailAlreadyRegistered;

    void updateAccount(ChangeAccountDTO account);


    void addServiceCenter(ServiceCenterDTO serviceCenterDTO);

    void updateServiceCenter(ServiceCenterDTO serviceCenterDTO);

    ServiceCenterDTO findServiceCenters(long id);

    List<ServiceCenterDTO> findServiceCenters();

    ListForPage<ServiceCenterDTO> findServiceCenters(int pageNumber);

    ListForPage<ServiceCenterDTO> findServiceCenters(int pageNumber, String filter, String input);

    AccountDTO findAccount(long id);

    List<AccountDTO> findAccounts();

    ListForPage<AccountDTO> findAccounts(int pageNumber);

    ListForPage<AccountDTO> findAccounts(int pageNumber, String filter, String input);


}
