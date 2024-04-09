package it.academy;

import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dao.repair.RepairDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.spare_parts_order.SparePartDAO;
import it.academy.dao.spare_parts_order.impl.SparePartDAOImpl;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.services.AdminService;
import it.academy.services.RepairService;
import it.academy.services.RepairWorkshopService;
import it.academy.services.SparePartOrderService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.RepairServiceImpl;
import it.academy.services.impl.RepairWorkshopImpl;
import it.academy.services.impl.SparePartOrderServiceImpl;
import it.academy.utils.Generator;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test2 {

    public static void main(String[] args) {
        AdminService adminService = new AdminServiceImpl();
        SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
        RepairService repairService = new RepairServiceImpl();
        RepairWorkshopService repairWorkshopService = new RepairWorkshopImpl();
        RepairDAO repairDAO = new RepairDAOImpl();
        DeviceDAO deviceDAO = new DeviceDAOImpl();
        DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
        TransactionManger transactionManger = TransactionManger.getInstance();
        SparePartDAOImpl sparePartDAO = new SparePartDAOImpl();

        List<DeviceType> t = deviceTypeDAO.findAll();

        List<SparePart> spareParts = sparePartDAO.findAll();

        System.out.println();
    }

}
