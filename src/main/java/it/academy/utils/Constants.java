package it.academy.utils;

public class Constants {
    //components
    public static final String PERMISSION_TYPE_KEY = "permission.type.%s";
    public static final String PERMISSION_CATEGORY_KEY = "permission.category..%s";

    //resources
    public static final String REPAIR_STATUSES_BUNDLE = "src/repair";

    //parameters
    public static final String OBJECT_ID = "id";

    //Account parameters
    public static final String EMAIL = "email";
    public static final String USER_NAME = "userName";
    public static final String USER_SURNAME = "userSurname";

    //DAO
    public static final String LIKE_QUERY_PATTERN = "%% %s %%";

    //DAO names
    public static final String ACCOUNT = "account";

    //QueryManager
    public static final String PUNCTUATION_MARKS_PATTERN = "\\s*\\p{P}\\s*||\\s+";

    private Constants() {
    }
}
