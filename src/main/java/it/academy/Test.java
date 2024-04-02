package it.academy;

import it.academy.dao.account.RoleDAO;
import it.academy.dao.account.impl.RoleDAOImpl;
import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DefectDAO;
import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DefectDAOImpl;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.repair.RepairCategoryDAO;
import it.academy.dao.repair.RepairTypeDAO;
import it.academy.dao.repair.impl.RepairCategoryImpl;
import it.academy.dao.repair.impl.RepairTypeDAOImpl;
import it.academy.dao.repair_workshop.RepairWorkshopDAO;
import it.academy.dao.repair_workshop.impl.RepairWorkshopDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.PermissionDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.req.repair.SparePartsOrderDTOReq;
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
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.entities.repair.components.RepairType;
import it.academy.entities.device.components.SparePart;
import it.academy.entities.repair.decommissioning.LiquidationCause;
import it.academy.entities.repair.decommissioning.LiquidationCertificate;
import it.academy.entities.repair.spare_parts_order.SparePartsOrder;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.services.*;
import it.academy.services.impl.*;
import it.academy.utils.Generator;
import it.academy.utils.converters.accounts.PermissionConverter;
import it.academy.utils.converters.accounts.RoleConverter;
import it.academy.utils.converters.device.*;
import it.academy.utils.converters.repair.*;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.academy.utils.Constants.*;


public class Test {
    private static RoleService roleService = new RoleServiceImpl();
    private static CompanyAdminService adminService = new CompanyAdminServiceImpl();
    private static CompanyOwnerService ownerService = new CompanyOwnerServiceImpl();
    private static UserService userService = new UserServiceImp();
    private static DeviceService deviceService = new DeviceServiceImpl();
    private static RepairService repairService = new RepairServiceImpl();
    private static SparePartService sparePartService = new SparePartServiceImpl();
    private static SparePartsOrderService sparePartOrderService = new SparePartsOrderServiceImpl();
    private static RepairWorkshopService repairWorkshopService = new RepairWorkshopServiceImpl();
    private static LiquidationCertificateService liquidationCertificateService = new LiquidationCertificateServiceImpl();
    private static RepairWorkshopDAO repairWorkshopDAO = new RepairWorkshopDAOImpl();
    private static DefectDAO defectDAO = new DefectDAOImpl();
    private static RoleDAO roleDAO = new RoleDAOImpl();
    private static BrandDAO brandDAO = new BrandDAOImpl();
    public static RepairCategoryDAO repairCategoryDAO = new RepairCategoryImpl();
    public static RepairTypeDAO repairTypeDAO = new RepairTypeDAOImpl();
    private static DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
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

        List<AccountDTOReq> accountDTOReqs2 = accountDTOReqs.subList(0, accountDTOReqs.size() / 2);
        List<AccountDTOReq> accountDTOReqs3 = accountDTOReqs.subList(accountDTOReqs.size() / 2 + 1, accountDTOReqs.size() - 1);

        accountDTOReqs3.forEach(a ->
                a.setRepairWorkshop(
                        RepairWorkshopConverter.convertToEntity(
                                repairWorkshops1.getList().get(RANDOM.nextInt(repairWorkshops1.getList().size()))
                        )));

        accountDTOReqs2.forEach(ownerService::addAdminAccount);
        accountDTOReqs3.forEach(adminService::addServiceAccount);

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
        defects.forEach(d -> deviceService.addDefect(DefectConverter.convertToDTOReq(d)));

        List<Defect> defects2 = deviceService.findDefect().getList().stream()
                .map(DefectConverter::convertToEntity)
                .collect(Collectors.toList());

        deviceTypes2.forEach(dt -> {
            new HashSet<>(defects2.subList(0, RANDOM.nextInt(defects2.size())))
                    .forEach(dt::addDefect);
            deviceService.changeDeviceType(DeviceTypeConverter.convertToDTOReq(dt));
        });

        List<RepairType> types = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateType())
                .collect(Collectors.toList());
        types.forEach(t -> repairService.addRepairType(RepairTypeConverter.convertToDTOReq(t)));

        List<RepairCategory> categories = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateCategory())
                .collect(Collectors.toList());
        categories.forEach(t -> repairService.addRepairCategory(RepairCategoryConverter.convertToDTOReq(t)));


        List<SparePart> spareParts = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateSparePart())
                .collect(Collectors.toList());
        spareParts.forEach(sp -> sparePartService.addSparePart(SparePartConverter.convertToDTOReq(sp)));
        List<SparePart> spareParts2 = sparePartService.findSparePart().getList().stream()
                .map(SparePartConverter::convertToEntity)
                .collect(Collectors.toList());

        deviceTypes2.forEach(dt -> {
            List<SparePart> spareParts3 = spareParts2.subList(0, RANDOM.nextInt(spareParts2.size()));
            spareParts3.forEach(dt::addSpareParts);
            deviceService.changeDeviceType(DeviceTypeConverter.convertToDTOReq(dt));
        });

        List<RepairWorkshop> workshops = repairWorkshops1.getList().stream()
                .map(RepairWorkshopConverter::convertToEntity)
                .collect(Collectors.toList());

        List<RepairCategory> categoryList = repairService.findRepairCategories().getList().stream()
                .map(RepairCategoryConverter::convertToEntity)
                .collect(Collectors.toList());
        List<RepairType> typeList = repairService.findRepairTypes().getList().stream()
                .map(RepairTypeConverter::convertToEntity)
                .collect(Collectors.toList());
        List<Defect> defectList = deviceService.findDefect().getList().stream()
                .map(DefectConverter::convertToEntity)
                .collect(Collectors.toList());
        List<Device> deviceList = deviceService.findDevices().getList().stream()
                .map(DeviceConverter::convertToEntity)
                .collect(Collectors.toList());

        List<Repair> repairs = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateRepair(i % 3 == 0))
                .collect(Collectors.toList());
        repairs.forEach(r -> {
            r.setRepairWorkshop(workshops.get(RANDOM.nextInt(workshops.size())));
            r.setStatus(RepairStatus.values()[RANDOM.nextInt(RepairStatus.values().length)]);
            r.setCategory(categoryList.get(RANDOM.nextInt(categoryList.size())));
            r.setType(typeList.get(RANDOM.nextInt(typeList.size())));
            r.setIdentifiedDefect(defectList.get(RANDOM.nextInt(defectList.size())));
            r.setDevice(deviceList.get(RANDOM.nextInt(deviceList.size())));
            repairService.addRepair(RepairConverter.convertToDTOReq(r));
        });

        List<Repair> repairList = repairService.findRepairs().getList().stream()
                .map(RepairConverter::convertToEntity)
                .collect(Collectors.toList());

        List<SparePartsOrder> orders = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateOrder())
                .collect(Collectors.toList());
        orders.forEach(o -> {
            o.setRepair(repairList.get(RANDOM.nextInt(repairList.size())));
            Set<SparePart> sparePartSet = new HashSet<>(spareParts2.subList(0, RANDOM.nextInt(10)));
            sparePartSet.forEach(sp -> o.addSparePart(sp, RANDOM.nextInt(10)));
            SparePartsOrderDTOReq t = SparePartsOrderConverter.convertToDTOReq(o);
            sparePartOrderService.addSparePartsOrder(SparePartsOrderConverter.convertToDTOReq(o));
        });

        List<LiquidationCause> causes = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateCause())
                .collect(Collectors.toList());
        causes.forEach(c -> liquidationCertificateService.addLiquidationCause(LiquidationCauseConverter.convertToDTOReq(c)));

        List<LiquidationCause> causes2 = liquidationCertificateService.findLiquidationCause().getList().stream()
                .map(LiquidationCauseConverter::convertToEntity)
                .collect(Collectors.toList());

        List<LiquidationCertificate> certificates = IntStream.range(0, 20)
                .mapToObj(i -> Generator.generateCertificate())
                .collect(Collectors.toList());
        certificates.forEach(c -> {
            c.setRepair(repairList.get(RANDOM.nextInt(repairList.size())));
            c.setCause(causes2.get(RANDOM.nextInt(causes2.size())));
            liquidationCertificateService.addLiquidationCertificate(LiquidationCertificateConverter.convertToDTOReq(c));
        });


    }
}
