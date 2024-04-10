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
import it.academy.entities.RoleEnum;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.services.AdminService;
import it.academy.services.RepairService;
import it.academy.services.RepairWorkshopService;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.RepairWorkshopServiceImpl;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.utils.Generator;
import it.academy.utils.converters.Converter;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;
import it.academy.utils.dao.TransactionManger;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.academy.utils.Constants.RANDOM;

public class Test2 {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AdminService adminService = new AdminServiceImpl();
        SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
        RepairService repairService = new RepairServiceImpl();
        RepairWorkshopService repairWorkshopService = new RepairWorkshopServiceImpl();
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
//        Converter.convertToDTO(a, Account.class, AccountDTO.class);


        System.out.println();
    }

}
