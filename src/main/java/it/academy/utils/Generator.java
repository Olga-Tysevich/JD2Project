package it.academy.utils;


import it.academy.entities.account.Account;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.*;
import it.academy.entities.repair.components.RepairType;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.entities.liquidation.LiquidationCertificate;
import it.academy.entities.spare_parts_order.SparePartsOrder;
import it.academy.entities.service_center.BankAccount;
import it.academy.entities.service_center.Requisites;
import lombok.experimental.UtilityClass;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static it.academy.utils.Constants.RANDOM;

@UtilityClass
public class Generator {
    private List<String> repairWorkshops = Arrays.asList("Дрималай", "Кенфорд", "МастерПин", "Патио", "Электросервис");
    private List<String> emails = Arrays.asList("owner%s@mail.ru", "admin%s@mail.ru", "user%s@gmail.com", "user%s@yahoo.com", "user%s@outlook.com");
    private List<String> names = Arrays.asList("Александр", "Иван", "Екатерина", "Ольга", "Дмитрий", "Михаил", "Татьяна", "Светлана", "Николай", "Мария");
    private List<String> surnames = Arrays.asList("Иванович", "Петрович", "Сидорович", "Васильевич", "Попович", "Соколович", "Михайлович");
    private List<String> roles = Arrays.asList("Owner", "Admin", "Service owner", "Service manager", "Service engineer");
    private List<String> addresses = Arrays.asList("Улица Ленина, дом 10, квартира 5", "Проспект Победы, дом 25", "Шоссе Южное, дом 7, офис 3",
            "Переулок Садовый, дом 3, квартира 12", "Улица Центральная, дом 15");
    private List<String> phones = Arrays.asList("+375 29 123-45-58", "+375 29 154-45-58", "+375 29 758-55-56",
            "+375 25 654-25-53", "+375 33 444-45-38");
    private String serviceEmail = "email%d@mail.ru";
    private String bankAccount = "BA%d";
    private String bankCode = "BK%d";
    private List<String> banks = Arrays.asList("Созвездие Банк", "Яшма Капитал", "Арктика Финанс", "Золотой Бриллиант Банк", "Вершина Успеха");
    private List<String> salesmen = Arrays.asList("Центральный", "Алми", "Пятый элемент", "Электросила", "Скала");
    private List<String> brands = Arrays.asList("Ritmix", "Texet", "LG", "Brayer", "First");
    private List<String> deviceTypes = Arrays.asList("Наушники", "Мобильный телефон", "Акустика", "Ноутбук", "Диктофон");
    private List<String> models = Arrays.asList("S-FIT", "IPC-240B-Tuya", "Q3", "RDM-169", "DK2001");
    private String serialNumber = "SN%d";
    private List<String> defects = Arrays.asList("Неисправен трансформатор", "Неисправность не обнаружена", "Неисправна плата",
            "Не гарантийный случай", "Неисправен динамик");
    private List<String> repairCategories = Arrays.asList("Гарантийный", "Предпродажный", "Платный");
    private List<String> repairTypes = Arrays.asList("Замена динамика", "Без ремонта", "Замена платы", "Проверка качества", "Замена мотора");
    private List<String> spareParts = Arrays.asList("Динамик", "Плата", "Трансформатор", "Предохранитель", "Мотор");
    private List<String> dates = Arrays.asList("2024-04-01", "2024-04-15", "2024-04-24", "2024-04-12", "2024-04-07");

    public static Account generateAccount(boolean isOwner) {
        String password = generateValidPasswords();
        RoleEnum role = isOwner? RoleEnum.ADMIN : RoleEnum.SERVICE_CENTER;
        return Account.builder()
                .email(isOwner ? String.format(emails.get(0), RANDOM.nextInt(100)) :
                        String.format(emails.get(RANDOM.nextInt(emails.size() - 1) + 1), RANDOM.nextInt(100)))
                .isActive(true)
                .password(password)
                .userName(names.get(RANDOM.nextInt(names.size())))
                .userSurname(surnames.get(RANDOM.nextInt(surnames.size())))
                .role(role)
                .build();
    }


    public static ServiceCenter generateServiceCenter() {
        String name = repairWorkshops.get(RANDOM.nextInt(repairWorkshops.size())) + RANDOM.nextInt(100);
        return ServiceCenter.builder()
                .serviceName(name)
                .requisites(Requisites.builder()
                        .fullName(name + RANDOM.nextInt(repairWorkshops.size()))
                        .actualAddress(addresses.get(RANDOM.nextInt(addresses.size())))
                        .legalAddress(addresses.get(RANDOM.nextInt(addresses.size())))
                        .phone(phones.get(RANDOM.nextInt(phones.size())))
                        .email(String.format(serviceEmail, RANDOM.nextInt(1000)))
                        .taxpayerNumber(RANDOM.nextInt(100000000) + RANDOM.nextInt(100000000))
                        .registrationNumber(RANDOM.nextInt(100000) + RANDOM.nextInt(10000))
                        .build())
                .bankAccount(BankAccount.builder()
                        .bankName(banks.get(RANDOM.nextInt(banks.size())))
                        .bankAccount(String.format(bankAccount, RANDOM.nextInt(100000000)))
                        .bankAddress(addresses.get(RANDOM.nextInt(addresses.size())))
                        .bankCode(String.format(bankCode, RANDOM.nextInt(100000)))
                        .build())
                .isActive(RANDOM.nextInt(100) % 10 == 0)
                .build();

    }

    public static Brand generateBrand() {
        return Brand.builder()
                .name(brands.get(RANDOM.nextInt(brands.size())) + RANDOM.nextInt(10000))
                .isActive(true)
                .build();
    }

    public static DeviceType generateDeviceType() {
        return DeviceType.builder()
                .name(deviceTypes.get(RANDOM.nextInt(deviceTypes.size())) + RANDOM.nextInt(10000))
                .isActive(true)
                .build();
    }

    public static Model generateModel() {
        return Model.builder()
                .name(models.get(RANDOM.nextInt(deviceTypes.size())) + RANDOM.nextInt(10000))
                .build();
    }

    public static Device generateDevice() {
        return Device.builder()
                .serialNumber(String.format(serialNumber, RANDOM.nextInt(100000000)))
                .dateOfSale(Date.valueOf(dates.get(RANDOM.nextInt(dates.size()))))
                .buyer(Buyer.builder()
                        .name(names.get(RANDOM.nextInt(names.size())))
                        .surname(surnames.get(RANDOM.nextInt(surnames.size())))
                        .phone(phones.get(RANDOM.nextInt(phones.size())))
                        .build())
                .salesman(Salesman.builder()
                .name(salesmen.get(RANDOM.nextInt(salesmen.size())))
                .phone(phones.get(RANDOM.nextInt(phones.size())))
                .build())
                .build();
    }

    public static RepairType generateType() {
        return RepairType.builder()
                .name(repairTypes.get(RANDOM.nextInt(repairTypes.size())) + RANDOM.nextInt(10000))
                .code("C" + RANDOM.nextInt(100))
                .level("L" + RANDOM.nextInt(100))
                .build();
    }

    public static SparePart generateSparePart() {
        return SparePart.builder()
                .name(spareParts.get(RANDOM.nextInt(spareParts.size()))+ RANDOM.nextInt(10000))
                .build();
    }

    public static SparePartsOrder generateOrder() {
        Date date = Date.valueOf(dates.get(RANDOM.nextInt(dates.size())));
        return SparePartsOrder.builder()
                .orderDate(date)
                .departureDate(date)
                .deliveryDate(date)
                .build();
    }

    public static LiquidationCertificate generateCertificate() {
        return LiquidationCertificate.builder()
                .number("LCNo." + RANDOM.nextInt(10000))
                .build();
    }

    private static String generateValidPasswords() {
        String pattern = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}";

        String password = generateRandomPassword();
        while (!password.matches(pattern)) {
            password = generateRandomPassword();
        }
        return password;
    }

    private static String generateRandomPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*";
        String allChars = upper + lower + digits + special;

        StringBuilder password = new StringBuilder();
        for (int j = 0; j < 8; j++) {
            int index = RANDOM.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }
        return password.toString();
    }
}
