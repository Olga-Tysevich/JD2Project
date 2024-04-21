package it.academy.utils.constants;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Constants {
    public static final Random RANDOM = new Random();
    public static final int LIST_SIZE = 20;
    public static final int FIRST_PAGE = 1;
    public static final String LIKE_QUERY_PATTERN = "%%%s%%";
    public static final String DEVICE_DESCRIPTION_PATTERN = "%s\n %s %s";
    public static final long DEFAULT_ID = 1L;
    public static final String DEFAULT_VALUE = "";
    //Log
    public static final String CURRENT_CLASS = "Class name: %s";
    public static final String CURRENT_METHOD = "Method name: %s";
    public static final String CURRENT_ACCOUNT_PATTERN = "Current account: %s";
    public static final String FORM_EXTRACTED_PATTERN = "form extracted: %s";
    //ERROR_MESSAGES
    //sql
    public static final String GET_NUMBER_OF_ENTRIES = "SELECT count(s) FROM %s s";
    public static final String GET_NUMBER_OF_ENTRIES_BY_FILTER = "SELECT count(s) FROM %s s WHERE %s LIKE :value";
    public static final String GET_NUMBER_OF_ACTIVE_ENTRIES_BY_FILTER = "SELECT count(s) FROM %s s WHERE %s LIKE :value AND active = true";
    public static final String FIND_BY_ACTIVE_FIELD = "SELECT s FROM %s s WHERE active = :isActive ORDER BY s.id DESC";
    public static final String CHECK_ACCOUNT = "SELECT a FROM Account a WHERE a.id != :id AND a.email = :email";
    public static final String CHECK_SERVICE_CENTER = "SELECT s FROM ServiceCenter s WHERE s.id != :id AND s.serviceName = :name";
    public static final String CHECK_DEVICE_COMPONENT = "SELECT count(s) FROM %s s WHERE s.id != :id AND s.name = :name";
    public static final String FIND_ACCOUNTS_BY_SERVICE_CENTER_ID = "SELECT a FROM Account a WHERE serviceCenter.id = :id ORDER BY a.id DESC";
    public static final String CHECK_MODEL = "SELECT count(m) FROM Model m WHERE m.id != :id AND m.name = :name AND m.brand.id = :brandId AND m.type.id = :typeId";
    public static final String FIND_MODEL_BY_BRAND_ID = "SELECT m FROM Model m WHERE m.brand.id = :brandId";
    public static final String FIND_ACTIVE_MODEL_BY_BRAND_ID = "SELECT m FROM Model m WHERE m.brand.id = :brandId and active = :isActive";
    public static final String FIND_MODEL_DEVICE_TYPE_ID = "typeId";
    public static final String PARAMETER_VALUE = "value";
    //parameters
    //common parameters
    public static final String OBJECT_ID = "id";
    public static final String IS_ACTIVE = "isActive";
    public static final String OBJECT_NAME = "name";
    //common
    public static final String ERROR = "error";
    public static final String CURRENT_ACCOUNT = "currentAccount";
    //account parameters
    public static final String ACCOUNT = "account";
    public static final String ROLE = "role";
    public static final String ACCOUNT_SERVICE_CENTER = "serviceCenter";
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
    public static final String LIST_FOR_PAGE = "table";
    public static final String FILTER = "filter";
    public static final String USER_INPUT = "input";
    public static final String COMMAND = "command";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE = "page";
    //jsp pages
    //error page
    public static final String ERROR_PAGE_PATH = "/pages/error.jsp";
    //login main
    public static final String LOGIN_PAGE_PATH = "/pages/index.jsp";
    public static final String MAIN_PAGE_PATH = "/pages/main.jsp";
    //account
    public static final String SERVICE_CENTERS = "service_centers";
    public static final String NEW_ACCOUNT_PAGE_PATH = "/pages/account/addAccount.jsp";
    public static final String ACCOUNT_PAGE_PATH = "/pages/account/changeAccount.jsp";
    public static final String ACCOUNT_TABLE_PAGE_PATH = "/pages/account/accountTable.jsp";
    //service center
    public static final String SERVICE_CENTER = "service_center";
    public static final String SERVICE_CENTER_PAGE_PATH = "/pages/service_center/changeServiceCenter.jsp";
    public static final String SERVICE_CENTER_TABLE_PAGE_PATH = "/pages/service_center/serviceCenterTable.jsp";
    //brand
    public static final String BRAND = "brand";
    public static final String BRAND_PAGE_PATH = "/pages/brand/changeBrand.jsp";
    public static final String BRAND_TABLE_PAGE_PATH = "/pages/brand/brandTable.jsp";
    //device type
    public static final String DEVICE_TYPE = "device_type";
    public static final String DEVICE_TYPE_PAGE_PATH = "/pages/device_type/changeDeviceType.jsp";
    public static final String DEVICE_TYPE_TABLE_PAGE_PATH = "/pages/device_type/deviceTypesTable.jsp";
    //model
    public static final String MODEL = "model";
    public static final String BRAND_ID = "brandId";
    public static final String TYPE_ID = "deviceTypeId";
    public static final String DEVICE_TYPE_ID = "device_type_id";
    public static final String MODEL_PAGE_PATH = "/pages/model/changeModel.jsp";
    public static final String MODEL_TABLE_PAGE_PATH = "/pages/model/modelTable.jsp";
    //spare parts
    public static final String SPARE_PART = "spare_part";
    public static final String SPARE_PART_ID = "spare_part_id";
    public static final String SPARE_PART_PAGE_PATH = "/pages/spare_part/changeSparePart.jsp";
    public static final String SPARE_PART_TABLE_PAGE_PATH = "/pages/spare_part/sparePartTable.jsp";
    //repair
    public static final String REPAIR_FORM = "repair_form";
    public static final String CHANGE_REPAIR_FORM = "change_repair_form";
    public static final String REPAIR = "repair";
    public static final String REPAIR_STATUS = "status";
    public static final String REPAIR_CATEGORY = "category";
    public static final String SERVICE_CENTER_REPAIR_NUMBER = "repairNumber";
    public static final String END_DATE = "endDate";
    public static final String REPAIR_TYPE_LEVEL = "level";
    public static final String REPAIR_TYPE_CODE = "code";
    public static final String DEFECT_DESCRIPTION = "defectDescription";
    public static final String CURRENT_BRAND_ID = "currentBrandId";
    public static final String MODEL_ID = "modelId";
    public static final String DEVICE_ID = "deviceId";
    public static final String SERIAL_NUMBER = "serialNumber";
    public static final String SALESMAN_NAME = "salesmanName";
    public static final String SALESMAN_PHONE = "salesmanPhone";
    public static final String BUYER_NAME = "buyerName";
    public static final String BUYER_SURNAME = "buyerSurname";
    public static final String BUYER_PHONE = "buyerPhone";
    public static final String DATE_OF_SALE = "dateOfSale";
    public static final String REPAIR_PAGE_PATH = "/pages/repair/repairPage.jsp";
    public static final String ADD_REPAIR_PAGE_PATH = "/pages/repair/addRepairPage.jsp";
    public static final String REPAIR_TABLE_PAGE_PATH = "/pages/repair/repairTable.jsp";
    //spare part
    public static final String ORDER_REPAIR_ID = "repairId";
    public static final String ORDER_DATE = "orderDate";
    public static final String DEPARTURE_DATE = "departureDate";
    public static final String DELIVERY_DATE = "deliveryDate";
    public static final String SPARE_PARTS = "spareParts";
    public static final String DEVICE_TYPES = "typeSet";
    public static final String REPAIR_NUMBER = "repair_number";
    public static final String SPARE_PART_QUANTITY = "quantity";
    public static final String ORDERS = "orders";
    public static final String ORDER_DATA = "order_data";
    public static final String SPARE_PART_ORDER_PAGE_PATH = "/pages/spare_part/sparePartsOrder.jsp";
    public static final String CHANGE_SPARE_PART_ORDER_PAGE_PATH = "/pages/spare_part/changeSparePartOrder.jsp";
    //for pages
    public static final String EMAIL_ALREADY_EXISTS = "Email: %s уже зарегистрирован!";
    public static final String PASSWORDS_NOT_MATCH = "Введенные пароли не совпадают!";
    public static final String USER_NOT_FOUND = "Пользователь не существует!";
    public static final String USER_IS_BLOCKED = "Пользователь заблокирован!";
    public static final String INCORRECT_PASSWORD = "Неверный пароль!";
    public static final String SERVICE_CENTERS_NOT_FOUND = "Сервисные центры еще не добавлены!";
    public static final String SERVICE_CENTER_ALREADY_EXIST = "Сервисный центр уже существует!";
    public static final String SERVICE_CENTER_NOT_FOUND = "Сервисный центр не найден!";
    public static final String SERVICE_NAME_ALREADY_TAKEN = "Сервисное имя занято!";
    public static final String BRAND_ALREADY_EXIST = "Бренд уже добавлен!";
    public static final String DEVICE_TYPE_ALREADY_EXIST = "Данный тип устройства уже добавлен!";
    public static final String MODEL_ALREADY_EXIST = "Модель уже добавлена!";
    public static final String ERROR_MESSAGE = "Что-то пошло не так ...";
    public static final String ACCESS_IS_DENIED = "У вас нет доступа к данной операции, обратитесь к администратору!";
    public static final String BRAND_NOT_FOUND = "Бренд не найден!";
    public static final String BRANDS_NOT_FOUND = "Нет добавленных брендов!";
    public static final String MODELS_NOT_FOUND = "Нет добавленных моделей!";
    public static final String MODEL_NOT_FOUND = "Модель не найдена!";
    public static final String DEVICE_TYPES_NOT_FOUND = "Нет добавленных добавленных типов устройств!";
    public static final String DEVICE_TYPE_NOT_FOUND = "Тип устройства не найден!";
    public static final String DEVICE_TYPE_NOT_SELECTED = "Не выбраны связанные устройства!";
    public static final String SPARE_PART_ALREADY_EXIST = "Запчасть уже добавлена!";
    public static final String OBJECTS_NOT_FOUND_MESSAGE = "Ничего не найдено!";
    //description
    //role description
    public static final String ADMIN_DESCRIPTION = "Администатор";
    public static final String SERVICE_CENTER_DESCRIPTION = "Сервисный центр";
    //repair statuses description
    public static final String REQUEST_DESCRIPTION = "Заявка";
    public static final String CURRENT_DESCRIPTION = "Текущий ремонт";
    public static final String WAITING_SP_DESCRIPTION = "Заказаны запчасти";
    public static final String COMPLETED_DESCRIPTION = "Завершен";
    public static final String DECOMMISSIONED_DESCRIPTION = "Списан";
    public static final String PAID_DESCRIPTION = "Оплачен";
    public static final String DELIVERED_DESCRIPTION = "Выдан";
    public static final String REJECTED_DESCRIPTION = "Отклонен";
    public static final String ALL_DESCRIPTION = "Все ремонты";
    //repair categories description
    public static final String WARRANTY_DESCRIPTION = "Гарантийный";
    public static final String PRE_SALE_DESCRIPTION = "Предпродажный";
    public static final String PAID_CATEGORY_DESCRIPTION = "Платный";
    public static final String REPEATED_DESCRIPTION = "Повторный";


    //info
    public static final String OBJECT_CREATED_PATTERN = "Object created: {}";
    public static final String OBJECT_UPDATED_PATTERN = "object updated: {}";
    public static final String OBJECT_FOUND_PATTERN = "Object found: {}";
    public static final String ERROR_PATTERN = "Error: {}";

    //filters
    //account filters
    public static final String ACCOUNT_USER_NAME = "Имя";
    public static final String ACCOUNT_USER_SURNAME = "Фамилия";
    //repairType filters
    public static final String REPAIR_TYPE_CODE_FILTER = "Код ремонта";
    public static final String REPAIR_TYPE_LEVEL_FILTER = "Уровень ремонта";
    public static final String REPAIR_TYPE_DESCRIPTION_FILTER = "Описание ремонта";
    //SparePartOrder filters
    public static final String ORDER_DATE_DESCRIPTION = "Дата заказа";
    public static final String ORDER_DEPARTURE_DATE_DESCRIPTION = "Дата отправки";
    public static final String ORDER_DELIVERY_DATE_DESCRIPTION = "Дата доставки";
    //SparePart filters
    public static final String SPARE_PART_NAME_DESCRIPTION = "Название запчасти";
    //DeviceType filters
    public static final String DEVICE_TYPE_NAME_DESCRIPTION = "Тип устройства";
    //Brand filters
    public static final String BRAND_NAME_DESCRIPTION = "Название бренда";
    //Model filters
    public static final String MODEL_NAME_FILTER = "Название модели";
    //open commands
    public static final String OPEN_REPAIR_TYPE_TABLE_PAGE = "main?command=show_repair_type_table&&page=%d";
    public static final String OPEN_SPARE_PART_ORDERS_TABLE_PAGE = "main?command=show_spare_part_orders_table&&page=%d";
    public static final String OPEN_REPAIR_PAGE = "/repair?command=show_confirmed_repair&&repair_id=%d";
    public static final String OPEN_START_PAGE = "main?command=open_page&&page=1";
    //jsp
    public static final String REPAIR_TYPE = "type";
    public static final String REPAIR_TYPE_ID = "type_id";
    public static final String REPAIR_TYPE_NAME = "type_name";
    //Paths
    public static final String SHOW_COMMAND = "show_command";
    public static final String REPAIR_TYPE_PAGE_PATH = "/pages/change_pages/changeRepairType.jsp";
    //show_command
    public static final String SHOW_SPARE_PART_ORDERS_TABLE = "show_spare_part_orders_table";
    public static final String REPAIR_TYPES = "repair_types";

}