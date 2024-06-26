package it.academy.utils.constants;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Constants {
    public static final Random RANDOM = new Random();
    public static final int LIST_SIZE = 20;
    public static final int FIRST_PAGE = 1;
    public static final String LIKE_QUERY_PATTERN = "%%%s%%";
    public static final String MODEL_DESCRIPTION_PATTERN = "%s %s %s";
    public static final long DEFAULT_ID = 1L;
    public static final String SLASH = "/";
    public static final long ID_FOR_CHECK = 0L;

    //entity patterns
    public static final int MIN_FIELD_LENGTH = 2;
    public static final int MAX_FIELD_LENGTH = 20;
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9-.]+@([a-zA-Z-]+\\.)+[a-zA-Z-]{2,4}$";
    public static final String TEXT_PATTERN = "[A-ZА-Я][a-zа-я]{2,19}";

    //sql
    public static final String GET_LIST = "SELECT s FROM %s s ORDER BY s.id DESC";
    public static final String FIND_ACCOUNTS_BY_SERVICE_CENTER_ID = "SELECT a FROM Account a WHERE serviceCenter.id = :id ORDER BY a.id DESC";
    public static final String FIND_ACTIVE_MODEL_BY_BRAND_ID = "SELECT m FROM Model m WHERE m.brand.id = :brandId and active = :isActive";
    public static final String FIND_ACTIVE_REPAIR_TYPES = "SELECT rt FROM RepairType rt WHERE active = true";
    public static final String FIND_MODEL_DEVICE_TYPE_ID = "typeId";
    public static final String CHECK_ACCOUNT = "SELECT count(a) FROM Account a WHERE a.id != :id AND a.email = :email";
    public static final String CHECK_SERVICE_CENTER = "SELECT count(s) FROM ServiceCenter s WHERE s.id != :id AND s.serviceName = :serviceName";
    public static final String CHECK_COMPONENT = "SELECT count(s) FROM %s s WHERE s.id != :id AND s.name = :name";
    public static final String CHECK_MODEL = "SELECT count(m) FROM Model m WHERE m.id != :id AND m.name = :name AND m.brand.id = :brandId AND m.type.id = :typeId";
    public static final String DELETE_FROM_SPARE_PART = "delete from models_spare_parts where spare_part_id = ?";
    public static final String DELETE_FROM_ORDER_ITEMS = "delete from order_items where order_id = ?";
    //parameters
    //common parameters
    public static final String OBJECT_ID = "id";
    public static final String IS_ACTIVE = "isActive";
    public static final String OBJECT_NAME = "name";
    //common
    public static final String ERROR = "error";
    //account parameters
    public static final String ACCOUNT = "account";
    public static final String ROLE = "role";
    public static final String EMAIL = "email";
    public static final String SERVICE_CENTER_ID = "serviceCenterId";
    public static final String USER_NAME = "userName";
    public static final String USER_SURNAME = "userSurname";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_CONFIRM = "confirmPassword";
    //service center
    public static final String SERVICE_CENTER_NAME = "serviceName";
    public static final String SERVICE_CENTER_BANK_NAME = "bankName";
    public static final String SERVICE_CENTER_BANK_ACCOUNT = "bankAccount";
    public static final String SERVICE_CENTER_BANK_CODE = "bankCode";
    public static final String SERVICE_CENTER_BANK_ADDRESS = "bankAddress";
    public static final String SERVICE_CENTER_FULL_NAME = "fullName";
    public static final String SERVICE_CENTER_LEGAL_ADDRESS = "legalAddress";
    public static final String SERVICE_CENTER_ACTUAL_ADDRESS = "actualAddress";
    public static final String SERVICE_CENTER_PHONE = "phone";
    public static final String SERVICE_CENTER_TAXPAYER_NUMBER = "taxpayerNumber";
    public static final String SERVICE_CENTER_REGISTRATION_NUMBER = "registrationNumber";
    //jsp
    public static final String TABLE_PAGE = "table";
    public static final String FILTER_PAGE = "filter_page";
    public static final String USER_INPUT = "input";
    public static final String COMMAND = "command";
    public static final String FORM_PAGE = "form_page";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE = "page";
    //service center
    public static final String SERVICE_CENTER = "serviceCenter";
    //brand
    public static final String BRAND = "brand";
    //device type
    public static final String DEVICE_TYPE = "deviceType";
    //model
    public static final String MODEL = "model";
    public static final String BRAND_ID = "brandId";
    public static final String TYPE_ID = "deviceTypeId";
    //repair type
    public static final String REPAIR_TYPE_CODE = "code";
    public static final String REPAIR_TYPE_LEVEL = "level";
    //repair
    public static final String REPAIR_FORM = "repair_form";
    public static final String REPAIR = "repair";
    public static final String REPAIR_STATUS = "status";
    public static final String REPAIR_CATEGORY = "category";
    public static final String SERVICE_CENTER_REPAIR_NUMBER = "repairNumber";
    public static final String DEFECT_DESCRIPTION = "defectDescription";
    public static final String MODEL_ID = "modelId";
    public static final String DEVICE_ID = "deviceId";
    public static final String SERIAL_NUMBER = "serialNumber";
    public static final String SALESMAN_NAME = "salesmanName";
    public static final String SALESMAN_PHONE = "salesmanPhone";
    public static final String BUYER_NAME = "buyerName";
    public static final String BUYER_SURNAME = "buyerSurname";
    public static final String BUYER_PHONE = "buyerPhone";
    public static final String DATE_OF_SALE = "dateOfSale";
    //spare part
    public static final String SPARE_PART_MODEL_ID = "model_id";
    public static final String SPARE_PARTS = "spareParts";
    public static final String MODELS = "models";
    public static final String REPAIR_NUMBER = "repair_number";
    public static final String ORDER_DATA = "order_data";
    //for pages
    public static final String NAME_IS_EMPTY = "Имя не может быть пустым!";
    public static final String EMAIL_ALREADY_EXISTS = "Email: %s уже зарегистрирован!";
    public static final String PASSWORDS_NOT_MATCH = "Введенные пароли не совпадают!";
    public static final String USER_NOT_FOUND = "Пользователь не существует!";
    public static final String USER_IS_BLOCKED = "Пользователь заблокирован!";
    public static final String INCORRECT_PASSWORD = "Неверный пароль!";
    public static final String SERVICE_CENTERS_NOT_FOUND = "Сервисные центры еще не добавлены!";
    public static final String SERVICE_CENTER_ALREADY_EXIST = "Сервисный центр уже существует!";
    public static final String SERVICE_CENTER_NOT_FOUND = "Сервисный центр не найден!";
    public static final String BRAND_ALREADY_EXIST = "Бренд уже добавлен!";
    public static final String REPAIR_TYPE_ALREADY_EXIST = "Тип ремонта уже добавлен!";
    public static final String DEVICE_TYPE_ALREADY_EXIST = "Тип устройства уже добавлен!";
    public static final String MODEL_ALREADY_EXIST = "Модель уже добавлена!";
    public static final String ERROR_MESSAGE = "Что-то пошло не так ...";
    public static final String ACCESS_IS_DENIED = "У вас нет доступа к данной операции, обратитесь к администратору!";
    public static final String BRANDS_NOT_FOUND = "Нет добавленных брендов!";
    public static final String MODELS_NOT_FOUND = "Нет добавленных моделей!";
    public static final String DEVICE_TYPES_NOT_FOUND = "Нет добавленных добавленных типов устройств!";
    public static final String MODELS_NOT_SELECTED = "Не выбраны связанные модели!";
    public static final String SPARE_PART_ALREADY_EXIST = "Запчасть уже добавлена!";
    public static final String REPAIR_NOT_FOUND = "Ремонт не найден!";
    public static final String ORDER_DELETE_FAILED_MESSAGE = "Заказ уже отправлен!";
    public static final String DELETE_FAILED_MESSAGE = "Удаление невозможно! Есть связанные записи!";
    public static final String EMPTY_ORDER_DATA_MESSAGE = "Нельзя создать пустой заказ!";

     //role description
    public static final String ADMIN_DESCRIPTION = "Администатор";
    public static final String SERVICE_CENTER_DESCRIPTION = "Сервисный центр";
    //repair statuses description
    public static final String REQUEST_DESCRIPTION = "Заявка";
    public static final String CURRENT_DESCRIPTION = "Текущий ремонт";
    public static final String WAITING_SP_DESCRIPTION = "Заказаны запчасти";
    public static final String COMPLETED_DESCRIPTION = "Завершен";
    public static final String PAID_DESCRIPTION = "Оплачен";
    public static final String REJECTED_DESCRIPTION = "Отклонен";
    //repair categories description
    public static final String WARRANTY_DESCRIPTION = "Гарантийный";
    public static final String PRE_SALE_DESCRIPTION = "Предпродажный";
    public static final String PAID_CATEGORY_DESCRIPTION = "Платный";
    public static final String REPEATED_DESCRIPTION = "Повторный";

}
