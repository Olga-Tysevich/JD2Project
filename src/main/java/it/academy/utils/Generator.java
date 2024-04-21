package it.academy.utils;

import it.academy.dto.account.CreateAccountDTO;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.account.embeddable.BankAccount;
import it.academy.entities.account.embeddable.Requisites;
import it.academy.entities.device.Brand;
import it.academy.entities.device.DeviceType;
import it.academy.entities.spare_part.SparePart;
import it.academy.utils.enums.RoleEnum;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

import static it.academy.utils.constants.Constants.RANDOM;

@UtilityClass
public class Generator {
    public static final int BOUND = 10000;
    public static final int MAX_DIGIT = 8;
    private List<String> repairWorkshops = Arrays.asList("Дрималай", "Кенфорд", "МастерПин", "Патио", "Электросервис");
    private List<String> emails = Arrays.asList("account%s@mail.ru", "admin%s@mail.ru", "user%s@gmail.com", "user%s@yahoo.com", "user%s@outlook.com");
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

    public static CreateAccountDTO generateAccount() {
        String password = generateValidPasswords();
        return CreateAccountDTO.builder()
                .email(String.format(emails.get(RANDOM.nextInt(emails.size() - 1) + 1), RANDOM.nextInt(BOUND)))
                .password(password)
                .confirmPassword(password)
                .userName(names.get(RANDOM.nextInt(names.size())))
                .userSurname(surnames.get(RANDOM.nextInt(surnames.size())))
                .role(RoleEnum.SERVICE_CENTER)
                .build();
    }


    public static ServiceCenter generateServiceCenter() {
        String name = repairWorkshops.get(RANDOM.nextInt(repairWorkshops.size())) + RANDOM.nextInt(BOUND);
        return ServiceCenter.builder()
                .serviceName(name)
                .requisites(Requisites.builder()
                        .fullName(name + RANDOM.nextInt(repairWorkshops.size()))
                        .actualAddress(addresses.get(RANDOM.nextInt(addresses.size())))
                        .legalAddress(addresses.get(RANDOM.nextInt(addresses.size())))
                        .phone(phones.get(RANDOM.nextInt(phones.size())))
                        .email(String.format(serviceEmail, RANDOM.nextInt(BOUND)))
                        .taxpayerNumber(RANDOM.nextInt(BOUND) + RANDOM.nextInt(BOUND))
                        .registrationNumber(RANDOM.nextInt(BOUND) + RANDOM.nextInt(BOUND))
                        .build())
                .bankAccount(BankAccount.builder()
                        .bankName(banks.get(RANDOM.nextInt(banks.size())))
                        .bankAccount(String.format(bankAccount, RANDOM.nextInt(BOUND)))
                        .bankAddress(addresses.get(RANDOM.nextInt(addresses.size())))
                        .bankCode(String.format(bankCode, RANDOM.nextInt(BOUND)))
                        .build())
                .isActive(RANDOM.nextInt(BOUND) % 2 == 0)
                .build();

    }

    public static Brand generateBrand() {
        return Brand.builder()
                .name(brands.get(RANDOM.nextInt(brands.size())) + RANDOM.nextInt(BOUND))
                .isActive(true)
                .build();
    }

    public static DeviceType generateDeviceType() {
        return DeviceType.builder()
                .name(deviceTypes.get(RANDOM.nextInt(deviceTypes.size())) + RANDOM.nextInt(BOUND))
                .isActive(true)
                .build();
    }

    public static String generateModelName() {
        return models.get(RANDOM.nextInt(deviceTypes.size())) + RANDOM.nextInt(BOUND);
    }


    public static SparePart generateSparePart() {
        return SparePart.builder()
                .name(spareParts.get(RANDOM.nextInt(spareParts.size())) + RANDOM.nextInt(BOUND))
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
        for (int j = 0; j < MAX_DIGIT; j++) {
            int index = RANDOM.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }
        return password.toString();
    }
}
