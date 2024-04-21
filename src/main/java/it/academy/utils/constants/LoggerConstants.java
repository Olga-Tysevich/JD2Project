package it.academy.utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LoggerConstants {
    public static final int MIN_FIELD_LENGTH = 2;
    public static final int MAX_FIELD_LENGTH = 20;

    //entity patterns
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9-.]+@([a-zA-Z-]+\\.)+[a-zA-Z-]{2,4}$";
    public static final String TEXT_PATTERN = "[A-ZА-Я][a-zа-я]{2,19}";

    //logger
    public static final String EXTRACT_ERROR_PATTERN = "Extract error: {}, object {}, field {}";
    public static final String ERROR_PATTERN = "Transaction error class: {}, message {}, object {}";
    public static final String UNKNOWN_COMMAND = "Unknown command: {}";
    public static final String VALIDATION_ERROR = "Validation is failed! Object {}";
    public static final String EMAIL_MUST_BE_NOT_NULL = "Email must not be null!";
    public static final String IS_ACTIVE_MUST_BE_NOT_NULL = "IsActive must not be null!";
    public static final String INVALID_NAME_SIZE_ERROR = "Name must be between  and  characters long.";
    public static final String INVALID_EMAIL_ERROR = "Invalid email!";
    public static final String INVALID_NAME_ERROR = "Invalid name!";
    public static final String INVALID_SURNAME_ERROR = "Invalid surname!";
    public static final String INVALID_PASSWORD_ERROR = "Password must be not null!";
    public static final String INVALID_ROLE_ERROR = "Role is empty!";
    public static final String OBJECT_ALREADY_EXIST = "Object already exist! Object: {}";
    public static final String OBJECT_NOT_FOUND_PATTERN = "Object not found id: {}, class: {}";
    public static final String OBJECTS_NOT_FOUND_PATTERN = "Objects not found, class: {}";
    public static final String INVALID_ROLE = "Invalid role! {}";
    public static final String WRONG_PASSWORD_ERROR = "Wrong password. Current: {}; Except: {}";
    public static final String USER_IS_BLOCKED_ERROR = "User is blocked {}";
    public static final String UNSUPPORTED_CLASS = "Unsupported field class! Field class {}";

    //info
    public static final String CURRENT_COMMAND = "Current command: {}";
    public static final String CURRENT_TABLE = "Current table: {}";
    public static final String OBJECT_FOR_SAVE_PATTERN = "Object for save: {}";
    public static final String OBJECT_CREATED_PATTERN = "Object created: {}";
    public static final String OBJECT_FOR_UPDATE_PATTERN = "Object for update: {}";
    public static final String OBJECT_UPDATED_PATTERN = "object updated: {}";
    public static final String OBJECT_FOUND_PATTERN = "Object found: {}";
    public static final String OBJECTS_FOUND_PATTERN = "Objects found list size: {}, class: {}";
    public static final String CURRENT_ACCOUNT_PATTERN = "Current account: {}";
    public static final String CURRENT_PAGE_PATTERN = "Current page: {}";
    public static final String OBJECT_EXTRACTED_PATTERN = "Object extracted : {}";


}
