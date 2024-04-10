package it.academy;

import it.academy.dao.AccountDAO;
import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.impl.AccountDAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.spare_parts_order.impl.SparePartDAOImpl;
import it.academy.dto.AccountDTO;
import it.academy.entities.Account;
import it.academy.services.AdminService;
import it.academy.services.repair.RepairService;
import it.academy.services.ServiceCenterService;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.repair.impl.RepairServiceImpl;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.utils.converters.Converter;
import it.academy.utils.dao.TransactionManger;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Test2 {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AdminService adminService = new AdminServiceImpl();
        SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
        RepairService repairService = new RepairServiceImpl();
        ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
        RepairDAO repairDAO = new RepairDAOImpl();
        DeviceDAO deviceDAO = new DeviceDAOImpl();
        DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
        TransactionManger transactionManger = TransactionManger.getInstance();
        SparePartDAOImpl sparePartDAO = new SparePartDAOImpl();
        AccountDAO accountDAO = new AccountDAOImpl();

//        List<RepairWorkshop> repairWorkshops = RepairWorkshopConverter.convertDTOListToEntityList(repairWorkshopService.findRepairWorkshops());
//
//        List<Account> accounts = IntStream.range(0, 40)
//                .mapToObj(i -> {
//                    Account account = Generator.generateAccount(i % 10 == 0);
//                    if (!RoleEnum.ADMIN.equals(account.getRole())) {
//                        account.setRepairWorkshop(repairWorkshops.get(RANDOM.nextInt(repairWorkshops.size())));
//                    }
//                    return transactionManger.execute(() -> accountDAO.create(account));
//                })
//                .collect(Collectors.toList());
        List<Account> accounts = accountDAO.findAll();
        Account a = accounts.get(0);
        AccountDTO accountDTO = (AccountDTO) Converter.convert(a, Account.class, AccountDTO.class);

        System.out.println(accountDTO);
    }

}
