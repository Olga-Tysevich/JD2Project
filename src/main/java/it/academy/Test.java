package it.academy;


import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.service_center.ServiceCenterDAO;
import it.academy.dao.service_center.impl.ServiceCenterDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.PermissionDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.req.service_center.ServiceCenterDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.Role;
import it.academy.entities.device.components.Brand;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.services.*;
import it.academy.services.impl.*;
import it.academy.utils.Generator;
import it.academy.utils.services.converters.accounts.PermissionConverter;
import it.academy.utils.services.converters.accounts.RoleConverter;
import it.academy.utils.services.converters.device.BrandConverter;
import it.academy.utils.services.converters.service_center.ServiceCenterConverter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.academy.utils.Constants.*;


public class Test {
    private static CompanyAdminService adminService = new CompanyAdminServiceImpl();
    private static CompanyOwnerService ownerService = new CompanyOwnerServiceImpl();
    private static UserService userService = new UserServiceImp();
    private static DeviceService deviceService = new DeviceServiceImpl();
    private static ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
    private static ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();
    private static RoleDAO roleDAO = new RoleDAOImpl();
    private static BrandDAO brandDAO = new BrandDAOImpl();

    public static void main(String[] args) {



        List<Permission> permissions = userService.findPermissions().getList().stream()
                .map(PermissionConverter::convertToEntity)
                .collect(Collectors.toList());

        RespListDTO<PermissionDTOReq> permissions1 = userService.findPermissions();
        RespListDTO<PermissionDTOReq> permissions2 = userService.findPermissions(1, 5);
        RespListDTO<PermissionDTOReq> permissions3 = userService.findPermissions(2, 5);
        RespListDTO<PermissionDTOReq> permissions4 = userService.findPermissions(
                ParametersForSearchDTO.builder()
                        .pageNumber(3)
                        .listSize(3)
                        .filters(List.of(PERMISSION_TYPE))
                        .userInput("REJECT DELETE")
                        .build());

        RoleDTOReq roleReq = RoleConverter.convertToDTOReq(Generator.generateRole(false));
        RoleDTOReq roleReq2 = RoleConverter.convertToDTOReq(Generator.generateRole(true));

        RespDTO<RoleDTOReq> role1 = adminService.createRole(roleReq);
        RespDTO<RoleDTOReq> role2 = adminService.createRole(roleReq2);
        List<Role> roleList = roleDAO.findAll();

        List<ServiceCenter> centers = IntStream.range(0, 15)
                .mapToObj(i -> {
                    ServiceCenter serviceCenter = Generator.generateServiceCenter();
                    serviceCenterService.addServiceCenter(ServiceCenterConverter.convertToDTOReq(serviceCenter));
                    return serviceCenter;
                })
                .collect(Collectors.toList());
        List<ServiceCenter> centers2 = serviceCenterDAO.findAll();
        RespListDTO<ServiceCenterDTOReq> services1 = serviceCenterService.findServiceCenters();
        RespListDTO<ServiceCenterDTOReq> services2 = serviceCenterService.findServiceCenters(1, 5);
        RespListDTO<ServiceCenterDTOReq> services3 = serviceCenterService.findServiceCenters(
                ParametersForSearchDTO.builder()
                        .pageNumber(1)
                        .listSize(10)
                        .filters(List.of(SERVICE_NAME))
                        .userInput("Кенфорд")
                        .build());

        List<AccountDTOReq> accountDTOReqs = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateAccountDTOReq(false, i % 2 == 0))
                .collect(Collectors.toList());
        accountDTOReqs.forEach(a -> a.setRole(roleList.get(RANDOM.nextInt(roleList.size()))));

        List<AccountDTOReq> accountDTOReqs2 = accountDTOReqs.subList(0, RANDOM.nextInt(accountDTOReqs.size()));

        accountDTOReqs2.forEach(a ->
                a.setServiceCenter(
                        ServiceCenterConverter.convertToEntity(
                                services1.getList().get(RANDOM.nextInt(services1.getList().size()))
                        )));

        accountDTOReqs.forEach(ownerService::addAdminAccount);
        accountDTOReqs2.forEach(adminService::addServiceAccount);

        List<Brand> brands = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateBrand())
                .collect(Collectors.toList());

        brands.forEach(b -> deviceService.addBrand(BrandConverter.convertToDTOReq(b)));
        List<Brand> brands2 = brandDAO.findAll();

        IntStream.range(0, centers2.size()).forEach(i -> {
            ServiceCenter serviceCenter = centers2.get(i);
            Brand brand = brands2.get(i);
            serviceCenter.addBrand(brand);
            serviceCenterService.changeServiceCenter(ServiceCenterConverter.convertToDTOReq(serviceCenter));
        });


        ParametersForSearchDTO p = ParametersForSearchDTO.builder()
                .pageNumber(1)
                .listSize(3)
                .filters(List.of("name"))
                .userInput(brands2.get(0).getName())
                .build();

        deviceService.findBrands(p);

    }
}
