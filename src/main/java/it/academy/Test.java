package it.academy;


import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.ServiceAccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.ServiceAccountDAOImpl;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceAccount;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.PermissionCategory;
import it.academy.entities.account.role.PermissionType;
import it.academy.entities.account.role.Role;
import it.academy.services.CompanyAdminService;
import it.academy.services.CompanyOwnerService;
import it.academy.services.impl.CompanyAdminServiceImpl;
import it.academy.services.impl.CompanyOwnerServiceImpl;
import it.academy.utils.Generator;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static it.academy.utils.Constants.LIKE_QUERY_PATTERN;

public class Test {
    public static void main(String[] args) {
        TransactionManger manger = TransactionManger.getInstance();
        CompanyAdminService adminService = new CompanyAdminServiceImpl();
        CompanyOwnerService ownerService = new CompanyOwnerServiceImpl();

        Permission permission = Permission.builder()
                .type(PermissionType.CREATE)
                .category(PermissionCategory.ROLE)
                .build();
        permission.setId(1L);


        Role role = Role.builder()
                .name("admin")
                .permissions(Set.of(permission))
                .build();
        role.setId(1L);

//        for (int i = 0; i < 20; i++) {
//            AccountDTOReq accountDTOReq = Generator.generateAccountDTOReq(false, i % 10 == 0);
//            accountDTOReq.setRole(role);
//            ownerService.addAdminAccount(accountDTOReq);
//        }

        ServiceAccountDAO serviceAccountDAO = new ServiceAccountDAOImpl();
//        List<ServiceAccount> a = serviceAccountDAO.findAll();
        AccountDAOImpl<Account> accountAccountDAO = new AccountDAOImpl<>(Account.class);


        RespListDTO<AccountDTO> accounts1 = adminService.findAccounts();
        List<Account>  accounts2 = accountAccountDAO.findForPageByAnyMatch(1, 2, List.of(new ParameterContainer<>("userName", "Мария")));
        List<Account>  accounts4 = accountAccountDAO.findBlockedAccounts();


        RoleDTOReq roleReq = RoleDTOReq.builder()
                .name("admin")
                .permissions(Set.of(permission))
                .build();
        role.setId(1L);

        adminService.createRole(roleReq);


        AccountDTOReq req = AccountDTOReq.builder()
                .email("admin@mail.ru")
                .isActive(true)
                .password("123")
                .confirmPassword("123")
                .userName("Olga")
                .userSurname("Tysevich")
                .role(role)
                .build();

//        service.addAdminAccount(req);
        System.out.println(ownerService.findBlockedAccounts());
        System.out.println(Account.class.getSimpleName());


//        Account account = ownerService.f


    }
}
