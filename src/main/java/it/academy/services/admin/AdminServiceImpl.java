package it.academy.services.admin;

import it.academy.dao.ServiceCenterDAO;
import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.AccountDAOImpl;
import it.academy.dao.impl.ServiceCenterDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.account.req.ChangeAccountDTO;
import it.academy.dto.account.req.CreateAccountDTO;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.utils.Builder;
import it.academy.utils.EntityFilter;
import it.academy.utils.converters.account.AccountConverter;
import it.academy.utils.converters.service_center.ServiceCenterConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.EMAIL;
import static it.academy.utils.Constants.LIST_SIZE;

public class AdminServiceImpl implements AdminService {
    private TransactionManger transactionManger = new TransactionManger();
    private AccountDAO accountDAO = new AccountDAOImpl();
    private ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();

    @Override
    public void createAccount(CreateAccountDTO createAccountDTO) throws EnteredPasswordsNotMatch, EmailAlreadyRegistered {
        Account account = AccountConverter.convertToEntity(createAccountDTO);
        System.out.println("add adto " + createAccountDTO);
        System.out.println("add a " + account);

        if (!createAccountDTO.getPassword().equals(createAccountDTO.getConfirmPassword())) {
            throw new EnteredPasswordsNotMatch();
        }

        if (accountDAO.findByUniqueParameter(EMAIL, account.getEmail()) != null) {
            throw new EmailAlreadyRegistered(account.getEmail());
        }

        Supplier<Account> create = () -> {

            if (RoleEnum.SERVICE_CENTER.equals(account.getRole())) {
                ServiceCenter serviceCenter = serviceCenterDAO.find(createAccountDTO.getServiceCenterId());
                System.out.println("add servicee center " + serviceCenter);
                account.setServiceCenter(serviceCenter);
            }

            Account result = accountDAO.create(account);

            System.out.println("after save " + result);

            return result;
        };

        transactionManger.execute(create);
    }

    @Override
    public void updateAccount(ChangeAccountDTO account) {
        Account result = AccountConverter.convertToEntity(account);

        System.out.println("update  account" + result);
        transactionManger.execute(() -> {
            if (account.getPassword() == null || account.getPassword().isBlank()) {
                Account temp = accountDAO.find(account.getId());
                System.out.println("update temp a " + temp);
                ServiceCenter serviceCenter = serviceCenterDAO.find(account.getServiceCenterId());
                System.out.println("update service center " + serviceCenter);
                result.setServiceCenter(serviceCenter);
                result.setPassword(temp.getPassword());

                System.out.println("update after set passw " + temp);
            }
            Account t = accountDAO.update(result);
            System.out.println("update after " + t);
            return t;
        });
    }

    @Override
    public AccountDTO findAccount(long id) {
        Account result = transactionManger.execute(() -> accountDAO.find(id));
        return AccountConverter.convertToDTO(result);
    }

    @Override
    public List<AccountDTO> findAccounts() {
        List<Account> repairs = transactionManger.execute(() -> accountDAO.findAll());
        return AccountConverter.convertToDTOList(repairs);
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(int pageNumber) {
        List<EntityFilter> filters = FilterManager.getFiltersForAccount();

        Supplier<ListForPage<AccountDTO>> find = () -> {
            List<Account> accounts = accountDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) accountDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<AccountDTO> list = AccountConverter.convertToDTOList(accounts);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = FilterManager.getFiltersForAccount();

        Supplier<ListForPage<AccountDTO>> find = () -> {
            List<Account> accounts = accountDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) accountDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<AccountDTO> list = AccountConverter.convertToDTOList(accounts);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public void addServiceCenter(ServiceCenterDTO serviceCenterDTO) {
        ServiceCenter result = ServiceCenterConverter.convertDTOToEntity(serviceCenterDTO);
        transactionManger.execute(() -> serviceCenterDAO.create(result));
    }

    @Override
    public void updateServiceCenter(ServiceCenterDTO serviceCenterDTO) {
        ServiceCenter result = ServiceCenterConverter.convertDTOToEntity(serviceCenterDTO);
        transactionManger.execute(() -> serviceCenterDAO.update(result));
    }

    @Override
    public ServiceCenterDTO findServiceCenters(long id) {
        ServiceCenter result = transactionManger.execute(() -> serviceCenterDAO.find(id));
        return ServiceCenterConverter.convertToDTO(result);
    }

    @Override
    public List<ServiceCenterDTO> findServiceCenters() {
        List<ServiceCenter> repairs = transactionManger.execute(() -> serviceCenterDAO.findAll());
        return ServiceCenterConverter.convertListToDTO(repairs);
    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenters(int pageNumber) {
        List<EntityFilter> filters = FilterManager.getFiltersForRepairWorkshop();

        Supplier<ListForPage<ServiceCenterDTO>> find = () -> {
            List<ServiceCenter> repairs = serviceCenterDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) serviceCenterDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<ServiceCenterDTO> list = ServiceCenterConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenters(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = FilterManager.getFiltersForRepairWorkshop();

        Supplier<ListForPage<ServiceCenterDTO>> find = () -> {
            List<ServiceCenter> repairs = serviceCenterDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) serviceCenterDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<ServiceCenterDTO> list = ServiceCenterConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }


}
