package it.academy;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.service_center.ServiceCenterDAO;
import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.ModelDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.device.impl.ModelDAOImpl;
import it.academy.dao.account.AccountDAOImpl;
import it.academy.dao.service_center.ServiceCenterDAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.spare_parts_order.impl.SparePartDAOImpl;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.services.admin.AdminService;
import it.academy.services.ServiceCenterService;
import it.academy.services.admin.AdminServiceImpl;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.services.repair.RepairService;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.services.spare_part.SparePartOrderService;
import it.academy.services.spare_part.impl.SparePartOrderServiceImpl;
import it.academy.servlets.factory.CommandEnum;
import it.academy.utils.converters.service_center.ServiceCenterConverter;
import it.academy.utils.dao.TransactionManger;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static it.academy.servlets.factory.CommandEnum.SHOW_ACCOUNT_TABLE;

public class Test2 {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AdminService adminService = new AdminServiceImpl();
        SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
        RepairService repairService = new RepairServiceImpl();
        ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
        RepairDAO repairDAO = new RepairDAOImpl();
        DeviceDAO deviceDAO = new DeviceDAOImpl();
        DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
        TransactionManger transactionManger = new TransactionManger();
        SparePartDAOImpl sparePartDAO = new SparePartDAOImpl();
        AccountDAO accountDAO = new AccountDAOImpl();
        ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();
        ModelDAO modelDAO = new ModelDAOImpl();
        BrandDAO brandDAO = new BrandDAOImpl();

        List<ServiceCenter> repairWorkshops = ServiceCenterConverter.convertDTOListToEntityList(serviceCenterService.findServiceCenter());

        CommandEnum c = CommandEnum.valueOf(SHOW_ACCOUNT_TABLE.name());

//        List<Account> accounts = IntStream.range(0, 40)
//                .mapToObj(i -> {
//                    Account account = Generator.generateAccount(i % 10 == 0);
//                    if (!RoleEnum.ADMIN.equals(account.getRole())) {
//                        account.setServiceCenter(repairWorkshops.get(RANDOM.nextInt(repairWorkshops.size())));
//                    }
//                    return transactionManger.execute(() -> accountDAO.create(account));
//                })
//                .collect(Collectors.toList());
//        List<Account> accounts3 = accountDAO.findAll();

//        List<ServiceCenter> serviceCenters = IntStream.range(0, 40)
//                .mapToObj(i -> {
//                    ServiceCenter serviceCenter = Generator.generateServiceCenter();
//                    return transactionManger.execute(() -> serviceCenterDAO.create(serviceCenter));
//                })
//                .collect(Collectors.toList());

//        List<Brand> brands = IntStream.range(0, 40)
//                .mapToObj(i -> {
//                    Brand brand = Generator.generateBrand();
//                    return transactionManger.execute(() -> brandDAO.create(brand));
//                })
//                .collect(Collectors.toList());

//        List<DeviceType> deviceType = IntStream.range(0, 40)
//                .mapToObj(i -> {
//                    DeviceType type = Generator.generateDeviceType();
//                    return transactionManger.execute(() -> deviceTypeDAO.create(type));
//                })
//                .collect(Collectors.toList());

//        List<Model> models = IntStream.range(0, 40)
//                .mapToObj(i -> {
//                    Model model = Generator.generateModel();
//                    return transactionManger.execute(() -> modelDAO.create(model));
//                })
//                .collect(Collectors.toList());


        System.out.println();
    }

}
