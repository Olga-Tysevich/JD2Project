package it.academy.utils;

import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.PermissionCategory;
import it.academy.entities.account.role.PermissionType;
import it.academy.entities.account.role.Role;
import lombok.experimental.UtilityClass;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.academy.utils.Constants.RANDOM;

@UtilityClass
public class Generator {
    private List<String> roles = Arrays.asList("Owner", "Admin", "Manager", "Technician", "Support Specialist", "Administrator", "Engineer");
    private List<String> emails = Arrays.asList("owner@mail.ru", "admin@mail.ru", "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "example.com");
    private List<String> names = Arrays.asList("Александр", "Иван", "Екатерина", "Ольга", "Дмитрий", "Михаил", "Татьяна", "Светлана", "Николай", "Мария");
    private List<String> surnames = Arrays.asList(
            "Иванов", "Петров", "Сидоров", "Смирнов", "Кузнецов", "Васильев",
            "Попов", "Соколов", "Михайлов", "Федоров", "Морозов", "Волков");

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
                        IntStream.range(0, RANDOM.nextInt(5))
                                .mapToObj(i -> generatePermission())
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public static AccountDTOReq generateAccountDTOReq(boolean isOwner, boolean isValidAccount) {
        String password = generateValidPasswords();
        return AccountDTOReq.builder()
                .email(isOwner ? emails.get(0) : emails.get(RANDOM.nextInt(emails.size() - 1) + 1))
                .isActive(true)
                .password(password)
                .confirmPassword(isValidAccount? password : password + RANDOM.nextInt())
                .userName(names.get(RANDOM.nextInt(names.size())))
                .userSurname(surnames.get(RANDOM.nextInt(surnames.size())))
                .role(generateRole(isOwner))
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
