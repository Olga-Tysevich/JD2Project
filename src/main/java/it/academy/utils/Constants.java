package it.academy.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Constants {
    public static final Random RANDOM = new Random();

    //common parameters
    public static final String OBJECT_ID = "id";
    public static final String IS_ACTIVE = "isActive";
    public static final String OBJECT_NAME = "name";

    //parameters
    //common
    public static final String IS_DELETED = "isDeleted";
    public static final String ERROR = "error";
    public static final String CURRENT_ACCOUNT = "currentAccount";
    //account parameters
    public static final String ACCOUNT = "account";
    public static final String ACCOUNT_EMAIL = "email";
    public static final String ROLE = "role";
    public static final String ACCOUNT_SERVICE_CENTER = "serviceCenter";
    public static final String EMAIL = "email";
    public static final String SERVICE_CENTER_ID = "serviceCenterId";
    public static final String USER_NAME = "userName";
    public static final String USER_SURNAME = "userSurname";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_CONFIRM = "confirmPassword";
    //service center
    public static final String SERVICE_CENTER_REQUISITES = "requisites";
    public static final String SERVICE_CENTER_EMAIL = "email";
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
    //brand
    public static final String BRAND_NAME = "name";

    //jsp
    public static final String LIST_FOR_PAGE = "table";
    public static final String FILTER = "filter";
    public static final String USER_INPUT = "input";
    public static final String COMMAND = "command";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final int LIST_SIZE = 20;
    public static final int FIRST_PAGE = 1;
    public static final String MAX_PAGE = "maxPage";
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



    //serviceCenter
    public static final String REPAIR_WORKSHOP_NAME = "serviceName";
    //repair
    public static final String SERVICE_CENTER_REPAIR_NUMBER = "serviceCenterNumber";
    public static final String REPAIR_CATEGORY = "category";
    public static final String DEFECT_DESCRIPTION = "defectDescription";
    public static final String REPAIR_STATUS = "status";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String REPAIR_TYPE_LEVEL = "level";
    public static final String REPAIR_TYPE_CODE = "code";
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
    //model
    public static final String SERIAL_NUMBER = "serialNumber";
    public static final String SALESMAN_NAME = "salesmanName";
    public static final String SALESMAN_PHONE = "salesmanPhone";
    public static final String BUYER_NAME = "buyerName";
    public static final String BUYER_SURNAME = "buyerSurname";
    public static final String BUYER_PHONE = "buyerPhone";
    //Device
    public static final String DATE_OF_SALE = "dateOfSale";
    //Device types
    public static final String SPARE_PARTS = "spareParts";
    //SparePart
    public static final String DEVICE_TYPES = "typeSet";
    //SparePartOrder
    public static final String ORDER_DATE_PARAMETER = "orderDate";
    public static final String DEPARTURE_DATE_PARAMETER = "departureDate";
    public static final String DELIVERY_DATE_PARAMETER = "deliveryDate";


    //jsp
    public static final String SHOW_COMMAND = "show_command";
    //RepairWorkshop parameters
    //Model parameters
    public static final String MODEL_ID = "model_id";
    public static final String MODEL_NAME = "model_name";
    //Brand parameters
    public static final String BRAND_ID = "brand_id";
    public static final String CURRENT_BRAND_ID = "current_brand_id";
    //Repair parameters
    public static final String REPAIR_ID = "repair_id";
    public static final String REPAIR_TYPE = "type";
    public static final String REPAIR_TYPE_ID = "type_id";
    public static final String REPAIR_TYPE_NAME = "type_name";
    public static final String REPAIR_MODEL_NAME = "model_name";
    //SparePartOrder parameters
    public static final String SPARE_PART_ORDER = "sp_order";
    //Device parameters
    public static final String DEVICE_ID = "device_id";
    //DeviceType parameters
    public static final String DEVICE_TYPE = "device_type_id";
    public static final String DEVICE_TYPE_ID = "device_type_id";
    public static final String DEVICE_TYPE_NAME = "device_type_name";
    //Spare part order
    public static final String ORDER_ID = "order_id";
    public static final String REPAIR_NUMBER = "repair_number";
    public static final String SPARE_PART_QUANTITY = "quantity";
    public static final String SPARE_PART_NAME = "spare_part_name";
    public static final String SPARE_PART_ID = "spare_part_id";
    public static final String ORDERS = "orders";
    public static final String ORDER_DATA = "order_data";
    public static final String ORDER_DATE = "order_date";
    public static final String DEPARTURE_DATE = "departure_date";
    public static final String DELIVERY_DATE = "delivery_date";

    //SparePart parameters
    public static final String CURRENT_SPARE_PART = "current_spare_part";
    public static final String CURRENT_SPARE_PART_ID = "current_spare_part_id";

    //DAO
    public static final String LIKE_QUERY_PATTERN = "%%%s%%";

    //QueryManager
    public static final String PUNCTUATION_MARKS_PATTERN = "[\\s\\p{P}]+";

    //Paths

    public static final String REPAIR_PAGE_PATH = "/pages/add/repairPage.jsp";
    public static final String REPAIR_TYPE_PAGE_PATH = "/pages/change_pages/changeRepairType.jsp";
    public static final String CHANGE_REPAIR_PAGE_PATH = "/pages/change_pages/changeRepairPage.jsp";
    public static final String CHANGE_SPARE_PART_ORDER_PAGE_PATH = "/pages/change_pages/changePartsOrder.jsp";
    public static final String SPARE_PART_PAGE_PATH = "/pages/change_pages/changeSparePart.jsp";
    public static final String DEVICE_TYPE_PAGE_PATH = "/pages/change_pages/changeDeviceType.jsp";
    public static final String MODEL_PAGE_PATH = "/pages/change_pages/changeModel.jsp";

    public static final String BRAND_LIST_PAGE_PATH = "/pages/forms/brandList.jsp";
    public static final String MODEL_LIST_PAGE_PATH = "/pages/forms/modelList.jsp";
    public static final String REPAIR_TYPE_LIST_PAGE_PATH = "/pages/forms/repairType.jsp";
    public static final String SPARE_PART_ORDER_PAGE_PATH = "/pages/forms/sparePartsOrder.jsp";


    public static final String REPAIR_TABLE_PAGE_PATH = "/pages/tables/repairTable.jsp";
    public static final String REPAIR_TABLE_TYPE_PAGE_PATH = "/pages/tables/repairTypesTable.jsp";
    public static final String SPARE_PART_ORDERS_TABLE_PAGE_PATH = "/pages/tables/sparePartOrdersTable.jsp";
    public static final String SPARE_PART_TABLE_PAGE_PATH = "/pages/tables/sparePartTable.jsp";
    public static final String DEVICE_TYPE_TABLE_PAGE_PATH = "/pages/tables/deviceTypesTable.jsp";
    public static final String MODEL_TABLE_PAGE_PATH = "/pages/tables/modelTable.jsp";

    //jsp

    //show_command
    public static final String SHOW_START_PAGE = "open_page";
    public static final String SHOW_REPAIR_TYPE_TABLE = "show_repair_type_table";
    public static final String SHOW_SPARE_PART_ORDERS_TABLE = "show_spare_part_orders_table";
    public static final String SHOW_SPARE_PART_TABLE = "show_spare_part_table";
    public static final String SHOW_DEVICE_TYPE_TABLE = "show_device_type_table";
    public static final String SHOW_BRAND = "show_brand";
    public static final String ADD_BRAND = "add_brand";
    public static final String SHOW_MODEL_TABLE = "show_model_table";
    public static final String SHOW_MODEL = "show_model";
    public static final String ADD_MODEL = "add_model";
    public static final String ADD_SERVICE_CENTER = "add_service_center";
    public static final String CHANGE_SERVICE_CENTER = "change_service_center";


    public static final String BRANDS = "brands";
    public static final String MODELS = "models";
    public static final String MODEL = "model";
    public static final String REPAIR = "repair";
    public static final String REPAIR_TYPES = "repair_types";
    public static final String DEVICE_DESCRIPTION_PATTERN = "%s\n %s %s";

    public static final long DEFAULT_ID = 1L;
    public static final String DEFAULT_VALUE = "";

    //open commands
    public static final String OPEN_REPAIR_TABLE_PAGE = "main?command=show_repair_table&&status=ALL&&page=%d";
    public static final String OPEN_REPAIR_TYPE_TABLE_PAGE = "main?command=show_repair_type_table&&page=%d";
    public static final String OPEN_SPARE_PART_ORDERS_TABLE_PAGE = "main?command=show_spare_part_orders_table&&page=%d";
    public static final String OPEN_SPARE_PART_TABLE_PAGE = "main?command=show_spare_part_table&&page=%d";
    public static final String OPEN_REPAIR_PAGE = "/repair?command=show_confirmed_repair&&repair_id=%d";
    public static final String OPEN_DEVICE_TYPE_TABLE_PAGE = "main?command=show_device_type_table&&page=%d";
    public static final String OPEN_MODEL_TABLE_PAGE = "main?command=show_model_table&&page=%d";


    public static final String OPEN_START_PAGE = "main?command=open_page&&page=1";

    //filters
    public static final String FILTERS = "filters";
    public static final String IS_BLOCKED = "Заблокированные";
    //account filters
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

    //ERROR_MESSAGES
    public static final String ERROR_PATTERN = "text: %s, object: %s";
    public static final String UNSUPPORTED_CLASS = "Unsupported field class!";
    //for pages
    public static final String EMAIL_ALREADY_EXISTS = "Email: %s уже зарегистрирован!";
    public static final String PASSWORDS_NOT_MATCH = "Введенные пароли не совпадают!";
    public static final String FIELD_NOT_FILLED = "Необходимые поля не заполнены!";
    public static final String USER_NOT_FOUND = "Пользователь не существует!";
    public static final String USER_IS_BLOCKED = "Пользователь заблокирован!";
    public static final String INCORRECT_PASSWORD = "Неверный пароль!";
    public static final String SERVICE_CENTERS_NOT_FOUND = "Сервисные центры еще не добавлены!";
    public static final String SERVICE_CENTERS_ALREADY_EXIST = "Сервисный центр уже существует!";
    public static final String SERVICE_NAME_ALREADY_TAKEN = "Сервисное имя занято!";
    public static final String BRAND_ALREADY_EXIST = "Бренд уже добавлен!";
    public static final String ERROR_MESSAGE = "Что-то пошло не так ...";
    public static final String ACCESS_IS_DENIED = "У вас нет доступа к данной операции, обратитесь к администратору!";

    //sql
    public static final String FIND_BY_ACTIVE_FIELD = "SELECT s FROM %s s WHERE active = :isActive";
    public static final String FIND_ACCOUNTS_BY_SERVICE_CENTER_ID = "SELECT a FROM Account a WHERE serviceCenter.id = :id";
    public static final String IS_ACTIVE_PARAMETER = "isActive";

}
