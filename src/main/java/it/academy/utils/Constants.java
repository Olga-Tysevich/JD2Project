package it.academy.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Constants {
    public static final Random RANDOM = new Random();

    //components
    public static final String PERMISSION_TYPE_KEY = "permission.type.%s";
    public static final String PERMISSION_CATEGORY_KEY = "permission.category..%s";

    //resources
    public static final String REPAIR_STATUSES_BUNDLE = "src/repair";
    public static final String MESSAGES = "src/messages";
    //message keys
    public static final String SAVED_SUCCESSFULLY = "message.save.successfully";
    public static final String UPDATED_SUCCESSFULLY = "message.update.successfully";
    public static final String OBJECT_FOUND = "message.find.successfully";

    //parameters
    public static final String OBJECT_ID = "id";

    //Permission parameters
    public static final String PERMISSION_TYPE = "type";
    public static final String PERMISSION_CATEGORY = "category";

    //Role parameters
    public static final String ROLE_NAME = "name";

    //Account parameters
    public static final String EMAIL = "email";
    public static final String USER_NAME = "userName";
    public static final String USER_SURNAME = "userSurname";
    public static final String IS_ACTIVE_ACCOUNT = "isActive";

    //ServiceAccount parameters
    public static final String SERVICE_CENTER = "serviceCenter";


    //DAO
    public static final String LIKE_QUERY_PATTERN = "%%%s%%";

    //QueryManager
    public static final String PUNCTUATION_MARKS_PATTERN = "\\s*\\p{P}\\s*||\\s+";

}
