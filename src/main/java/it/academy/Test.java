package it.academy;

import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.repair_workshop.RepairWorkshopDAO;
import it.academy.dao.repair_workshop.impl.RepairWorkshopDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.PermissionDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.req.repair_workshop.RepairWorkshopDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.Role;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.Defect;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.device.components.Model;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.services.*;
import it.academy.services.impl.*;
import it.academy.utils.Generator;
import it.academy.utils.services.converters.accounts.PermissionConverter;
import it.academy.utils.services.converters.accounts.RoleConverter;
import it.academy.utils.services.converters.device.*;
import it.academy.utils.services.converters.repair_workshop.RepairWorkshopConverter;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.academy.utils.Constants.*;


public class Test {
    private static RoleService roleService = new RoleServiceImpl();
    private static CompanyAdminService adminService = new CompanyAdminServiceImpl();
    private static CompanyOwnerService ownerService = new CompanyOwnerServiceImpl();
    private static UserService userService = new UserServiceImp();
    private static DeviceService deviceService = new DeviceServiceImpl();
    private static RepairWorkshopService repairWorkshopService = new RepairWorkshopServiceImpl();
    private static RepairWorkshopDAO repairWorkshopDAO = new RepairWorkshopDAOImpl();
    private static RoleDAO roleDAO = new RoleDAOImpl();
    private static BrandDAO brandDAO = new BrandDAOImpl();
    private static DeviceTypeDAO devicdeviceTypeDAO = new DeviceTypeDAOImpl();
    private static DeviceDAO deviceDAO = new DeviceDAOImpl();

    public static void main(String[] args) {

        List<Permission> permissions = roleService.findPermissions().getList().stream()
                .map(PermissionConverter::convertToEntity)
                .collect(Collectors.toList());

        RespListDTO<PermissionDTOReq> permissions1 = roleService.findPermissions();
        RespListDTO<PermissionDTOReq> permissions2 = roleService.findPermissions(1, 5);
        RespListDTO<PermissionDTOReq> permissions3 = roleService.findPermissions(2, 5);
        RespListDTO<PermissionDTOReq> permissions4 = roleService.findPermissions(
                ParametersForSearchDTO.builder()
                        .pageNumber(3)
                        .listSize(3)
                        .filters(List.of(PERMISSION_TYPE))
                        .userInput("REJECT DELETE")
                        .build());

        RoleDTOReq roleReq = RoleConverter.convertToDTOReq(Generator.generateRole(false));
        RoleDTOReq roleReq2 = RoleConverter.convertToDTOReq(Generator.generateRole(true));

        RespDTO<RoleDTOReq> role1 = roleService.createRole(roleReq);
        RespDTO<RoleDTOReq> role2 = roleService.createRole(roleReq2);
        List<Role> roleList = roleDAO.findAll();

        List<RepairWorkshop> repairWorkshops = IntStream.range(0, 15)
                .mapToObj(i -> {
                    RepairWorkshop repairWorkshop = Generator.generateRepairWorkshop();
                    repairWorkshopService.addRepairWorkshop(RepairWorkshopConverter.convertToDTOReq(repairWorkshop));
                    return repairWorkshop;
                })
                .collect(Collectors.toList());
        List<RepairWorkshop> workshopList = repairWorkshopDAO.findAll();
        RespListDTO<RepairWorkshopDTOReq> repairWorkshops1 = repairWorkshopService.findRepairWorkshop();
        RespListDTO<RepairWorkshopDTOReq> repairWorkshops2 = repairWorkshopService.findRepairWorkshop(1, 5);
        RespListDTO<RepairWorkshopDTOReq> repairWorkshops3 = repairWorkshopService.findRepairWorkshop(
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
                a.setRepairWorkshop(
                        RepairWorkshopConverter.convertToEntity(
                                repairWorkshops1.getList().get(RANDOM.nextInt(repairWorkshops1.getList().size()))
                        )));

        accountDTOReqs.forEach(ownerService::addAdminAccount);
        accountDTOReqs2.forEach(adminService::addServiceAccount);

        List<Brand> brands = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateBrand())
                .collect(Collectors.toList());

        brands.forEach(b -> deviceService.addBrand(BrandConverter.convertToDTOReq(b)));
        List<Brand> brands2 = deviceService.findBrands().getList().stream()
                .map(BrandConverter::convertToEntity)
                .collect(Collectors.toList());

        IntStream.range(0, workshopList.size()).forEach(i -> {
            RepairWorkshop repairWorkshop = workshopList.get(i);
            Brand brand = brands2.get(i);
            repairWorkshop.addBrand(brand);
            repairWorkshopService.changeRepairWorkshop(RepairWorkshopConverter.convertToDTOReq(repairWorkshop));
        });


        ParametersForSearchDTO p = ParametersForSearchDTO.builder()
                .pageNumber(1)
                .listSize(3)
                .filters(List.of("name"))
                .userInput(brands2.get(0).getName())
                .build();

        deviceService.findBrands(p);

        List<DeviceType> deviceTypes = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateDeviceType())
                .collect(Collectors.toList());

        deviceTypes.forEach(dt -> deviceService.addDeviceType(DeviceTypeConverter.convertToDTOReq(dt)));
        List<DeviceType> deviceTypes2 = deviceService.findDeviceType().getList().stream()
                .map(DeviceTypeConverter::convertToEntity)
                .collect(Collectors.toList());

        List<Model> models = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateModel())
                .collect(Collectors.toList());

        IntStream.range(0, models.size())
                .forEach(i -> {
                    Model model = models.get(i);
                    model.setBrand(brands2.get(i));
                    model.setType(deviceTypes2.get(i));
                    deviceService.addModel(ModelConverter.convertToDTOReq(model));
                });
        List<Model> models2 = deviceService.findModels().getList().stream()
                .map(ModelConverter::convertToEntity)
                .collect(Collectors.toList());

        List<Device> devices = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateDevice())
                .collect(Collectors.toList());
        devices.forEach(d -> {
            d.setModel(models2.get(RANDOM.nextInt(models2.size())));
            deviceService.addDevice(DeviceConverter.convertToDTOReq(d));
        });

        List<Defect> defects = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateDefect())
                .collect(Collectors.toList());
        defects.forEach(d -> {
            deviceService.addDefect(DefectConverter.convertToDTOReq(d));

        });

        List<Defect> defects2 = deviceService.findDefect().getList().stream()
                .map(DefectConverter::convertToEntity)
                .collect(Collectors.toList());

        deviceTypes2.forEach(dt -> {
            new HashSet<>(defects2.subList(0, RANDOM.nextInt(defects2.size())))
                    .forEach(dt::addDefect);
            deviceService.changeDeviceType(DeviceTypeConverter.convertToDTOReq(dt));
        });


    }
}
