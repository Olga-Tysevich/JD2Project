package it.academy;


import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.Account;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.PermissionCategory;
import it.academy.entities.account.role.PermissionType;
import it.academy.entities.account.role.Role;
import it.academy.services.CompanyAdminService;
import it.academy.services.CompanyOwnerService;
import it.academy.services.impl.CompanyAdminServiceImpl;
import it.academy.services.impl.CompanyOwnerServiceImpl;
import it.academy.utils.dao.TransactionManger;

import java.util.Set;

public class Test {
    public static void main(String[] args) {
        TransactionManger manger = TransactionManger.getInstance();

        CompanyAdminService adminService = new CompanyAdminServiceImpl();
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

//        adminService.createRole(role);


        CompanyOwnerService ownerService = new CompanyOwnerServiceImpl();

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
        System.out.println(ownerService.findAllBlockedAccounts());
        System.out.println(Account.class.getSimpleName());

//        Account account = ownerService.f


    }
}
