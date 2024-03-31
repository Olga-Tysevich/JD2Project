package it.academy.utils;

import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.PermissionCategory;
import it.academy.entities.account.role.PermissionType;
import it.academy.entities.account.role.Role;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.entities.service_center.components.BankAccount;
import it.academy.entities.service_center.components.Requisites;
import lombok.experimental.UtilityClass;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.academy.utils.Constants.RANDOM;

@UtilityClass
public class Generator {
    private List<String> serviceCenters = Arrays.asList("Дрималай", "Кенфорд", "МастерПин", "Патио", "Электросервис");
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

    public static Permission generatePermission() {
        return Permission.builder()
                .type(PermissionType.values()[RANDOM.nextInt(PermissionType.values().length)])
                .category(PermissionCategory.values()[RANDOM.nextInt(PermissionCategory.values().length)])
                .build();
    }

    public static Role generateRole(boolean isOwner) {
        return Role.builder()
                .name(isOwner ? roles.get(0) : roles.get(RANDOM.nextInt(roles.size() - 1) + 1))
                .permissions(
                        IntStream.range(0, RANDOM.nextInt(5) + 1)
                                .mapToObj(i -> generatePermission())
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public static AccountDTOReq generateAccountDTOReq(boolean isOwner, boolean isValidAccount) {
        String password = generateValidPasswords();
        return AccountDTOReq.builder()
                .email(isOwner ? String.format(emails.get(0), RANDOM.nextInt(100)) :
                        String.format(emails.get(RANDOM.nextInt(emails.size() - 1) + 1), RANDOM.nextInt(100)))
                .isActive(true)
                .password(password)
                .confirmPassword(isValidAccount? password : password + RANDOM.nextInt())
                .userName(names.get(RANDOM.nextInt(names.size())))
                .userSurname(surnames.get(RANDOM.nextInt(surnames.size())))
                .role(generateRole(isOwner))
                .build();
    }

    public static ServiceCenter generateServiceCenter() {
        String name = serviceCenters.get(RANDOM.nextInt(serviceCenters.size())) + RANDOM.nextInt(100);
        return ServiceCenter.builder()
                .serviceName(name)
                .requisites(Requisites.builder()
                        .fullName(name + RANDOM.nextInt(serviceCenters.size()))
                        .actualAddress(addresses.get(RANDOM.nextInt(addresses.size())))
                        .legalAddress(addresses.get(RANDOM.nextInt(addresses.size())))
                        .phone(phones.get(RANDOM.nextInt(phones.size())))
                        .email(String.format(serviceEmail, RANDOM.nextInt(1000)))
                        .taxpayerNumber(String.valueOf(RANDOM.nextInt(100000000) + RANDOM.nextInt(100000000)))
                        .registrationNumber(String.valueOf(RANDOM.nextInt(100000) + RANDOM.nextInt(10000)))
                        .build())
                .bankAccount(BankAccount.builder()
                        .bankName(banks.get(RANDOM.nextInt(banks.size())))
                        .bankAccount(String.format(bankAccount, RANDOM.nextInt(100000000)))
                        .bankAddress(addresses.get(RANDOM.nextInt(addresses.size())))
                        .bankCode(String.format(bankCode, RANDOM.nextInt(100000)))
                        .build())
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
