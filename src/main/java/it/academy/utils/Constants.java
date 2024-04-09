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
    public static final String PAGES = "src/pages";
    //message keys
    public static final String SAVED_SUCCESSFULLY = "message.save.successfully";
    public static final String UPDATED_SUCCESSFULLY = "message.update.successfully";
    public static final String OBJECT_FOUND = "message.find.successfully";

    //parameters
    public static final String OBJECT_ID = "id";
    public static final String IS_DELETED = "isDeleted";
    public static final String OBJECT_NAME = "name";
    //Account parameters
    public static final String USER = "user";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "userName";
    public static final String USER_SURNAME = "userSurname";
    public static final String IS_ACTIVE_ACCOUNT = "isActive";
    //repair workshop
    public static final String REPAIR_WORKSHOP_NAME = "serviceName";
    //repair
    public static final String REPAIR_WORKSHOP = "RepairWorkshop";
    public static final String REPAIR_WORKSHOP_REPAIR_NUMBER = "repairWorkshopRepairNumber";
    public static final String REPAIR_CATEGORY = "category";
    public static final String DEFECT_DESCRIPTION = "defectDescription";
    public static final String REPAIR_STATUS = "status";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String REPAIR_TYPE_LEVEL = "level";
    public static final String REPAIR_TYPE_CODE = "code";
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
    //Model parameters
    public static final String MODEL_ID = "model_id";
    //Brand parameters
    public static final String BRAND_ID = "brand_id";
    public static final String CURRENT_BRAND_ID = "current_brand_id";
    public static final String BRAND_NAME = "brand_name";
    //Repair parameters
    public static final String REPAIR_ID = "repair_id";
    public static final String REPAIR_WORKSHOP_ID = "repair_workshop_id";
    public static final String REPAIR_TYPE = "type";
    public static final String REPAIR_TYPE_ID = "type_id";
    public static final String REPAIR_TYPE_NAME = "type_name";
    public static final String REPAIR_MODEL_NAME = "model_name";
    //SparePartOrder parameters
    public static final String SPARE_PART_ORDER = "sp_order";
    //Device parameters
    public static final String DEVICE_ID = "device_id";
    //DeviceType parameters
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
    //RepairType parameters

    //SparePart parameters
    public static final String CURRENT_SPARE_PART = "current_spare_part";
    public static final String CURRENT_SPARE_PART_ID = "current_spare_part_id";

    //DAO
    public static final String LIKE_QUERY_PATTERN = "%%%s%%";

    //QueryManager
    public static final String PUNCTUATION_MARKS_PATTERN = "[\\s\\p{P}]+";

    //Commands
    public static final String COMMAND = "command";

    //Paths
    public static final String ERROR_PAGE_PATH = "path.page.error";
    //    public static final String MAIN_PAGE_PATH = "path.page.main";
//    public static final String REPAIR_PAGE_PATH = "path.page.repair";
    public static final String REPAIR_TABLE_PATH = "path.page.repair.table";
    public static final String WAITING_SPARE_PARTS_TABLE_PATH = "path.page.repair.waiting";

    public static final String MAIN_PAGE_PATH = "/pages/main.jsp";
    public static final String REPAIR_PAGE_PATH = "/pages/add/repairPage.jsp";
    public static final String REPAIR_TYPE_PAGE_PATH = "/pages/change_pages/changeRepairType.jsp";
    public static final String CHANGE_REPAIR_PAGE_PATH = "/pages/change_pages/changeRepairPage.jsp";
    public static final String CHANGE_SPARE_PART_ORDER_PAGE_PATH = "/pages/change_pages/changePartsOrder.jsp";
    public static final String SPARE_PART_PAGE_PATH = "/pages/change_pages/changeSparePart.jsp";

    public static final String BRAND_LIST_PAGE_PATH = "/pages/lists/brandList.jsp";
    public static final String MODEL_LIST_PAGE_PATH = "/pages/lists/modelList.jsp";
    public static final String REPAIR_TYPE_LIST_PAGE_PATH = "/pages/lists/repairTypeList.jsp";
    public static final String SPARE_PART_ORDER_PAGE_PATH = "/pages/lists/sparePartsOrder.jsp";

    public static final String REPAIR_TABLE_PAGE_PATH = "/pages/tables/repairTable.jsp";
    public static final String REPAIR_TABLE_TYPE_PAGE_PATH = "/pages/tables/repairTypesTable.jsp";
    public static final String SPARE_PART_ORDERS_TABLE_TYPE_PAGE_PATH = "/pages/tables/sparePartOrdersTable.jsp";
    public static final String SPARE_PART_TABLE_TYPE_PAGE_PATH = "/pages/tables/sparePartTable.jsp";

    //jsp
    public static final String PAGE_NUMBER = "pageNumber";
    public static final int LIST_SIZE = 20;
    public static final int FIRST_PAGE = 1;
    public static final String MAX_PAGE = "maxPage";
    public static final String PAGE = "page";
    public static final String SHOW_COMMAND = "show_command";
    public static final String LIST_FOR_PAGE = "table";
    public static final String FILTER = "filter";
    public static final String USER_INPUT = "user_input";

    //show_command
    public static final String SHOW_REPAIR_TYPE_TABLE = "show_repair_type_table";
    public static final String SHOW_SPARE_PART_ORDERS_TABLE = "show_spare_part_orders_table";
    public static final String SHOW_SPARE_PART_TABLE = "show_spare_part_table";


    public static final String BRANDS = "brands";
    public static final String BRAND = "brand";
    public static final String MODELS = "models";
    public static final String MODEL = "model";
    public static final String REPAIR = "repair";
    public static final String REPAIR_TYPES = "repair_types";
    public static final String DEVICE_DESCRIPTION_PATTERN = "%s\n %s %s";

    public static final String REPAIR_PAGE_DATA = "repair page data";
    public static final long DEFAULT_ID = 1L;
    public static final String DEFAULT_VALUE = "";

    //open commands
    public static final String OPEN_REPAIR_TABLE_PAGE = "main?command=show_repair_table&&status=ALL&&page=%d";
    public static final String OPEN_REPAIR_TYPE_TABLE_PAGE = "main?command=show_repair_type_table&&page=%d";
    public static final String OPEN_SPARE_PART_ORDERS_TABLE_PAGE = "main?command=show_spare_part_orders_table&&page=%d";
    public static final String OPEN_SPARE_PART_TABLE_PAGE = "main?command=show_spare_part_table&&page=%d";
    public static final String OPEN_REPAIR_PAGE = "/repair?command=show_confirmed_repair&&repair_id=%d";
//    public static final String OPEN_REPAIR_PAGE = "repair?command=delete_spare_part_order&&repair_id=repairId&&order_id=orderId";
    public static final String CHANGE_ORDER_PAGE = "main?command=change_spare_part_order&&order_id=%d&&departure_date=%s&&delivery_date=%s";


    public static final String OPEN_START_PAGE = "main?command=open_page&&page=1";
    public static final String OPEN_MODEL_LIST_PAGE = "brands?command=show_model_list&&id=%s";

    //filters
    public static final String FILTERS = "filters";
    //repairType filters
    public static final String REPAIR_TYPE_CODE_FILTER = "Код ремонта";
    public static final String REPAIR_TYPE_LEVEL_FILTER = "Уровень ремонта";
    public static final String REPAIR_TYPE_DESCRIPTION_FILTER = "Описание ремонта";
    //SparePartOrder
    public static final String ORDER_DATE_DESCRIPTION = "Дата заказа";
    public static final String ORDER_DEPARTURE_DATE_DESCRIPTION = "Дата отправки";
    public static final String ORDER_DELIVERY_DATE_DESCRIPTION = "Дата доставки";
    //SparePart
    public static final String SPARE_PART_NAME_DESCRIPTION = "Название запчасти";
    //DeviceTypeDescription
    public static final String DEVICE_TYPE_NAME_DESCRIPTION = "Тип устройства";

}
