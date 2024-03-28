package it.academy;


import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
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

        RoleDTOReq role = RoleDTOReq.builder()
                .name("admin")
                .permissions(Set.of(permission))
                .build();

        adminService.createRole(role);


        CompanyOwnerService service = new CompanyOwnerServiceImpl();

        AccountDTOReq req = AccountDTOReq.builder()
                .email("admin@mail.ru")
                .isActive(true)
                .password("123")
                .confirmPassword("123")
                .userName("Olga")
                .userSurname("Tysevich")
                .role(new Role())
                .build();

    }
}
