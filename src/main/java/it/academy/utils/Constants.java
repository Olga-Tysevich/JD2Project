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
    //repair workshop
    public static final String REPAIR_WORKSHOP_NAME = "serviceName";
    //repair
    public static final String REPAIR_WORKSHOP = "RepairWorkshop";
    public static final String REPAIR_WORKSHOP_REPAIR_NUMBER = "repairWorkshopRepairNumber";
    public static final String REPAIR_CATEGORY = "category";
    public static final String DEFECT_DESCRIPTION = "defectDescription";
    public static final String REPAIR_STATUS = "status";
    //repair statuses description
    public static final String REQUEST_DESCRIPTION = "Заявка";
    public static final String CURRENT_DESCRIPTION = "Текущий ремонт";
    public static final String WAITING_SP_DESCRIPTION = "Заказаны запчасти";
    public static final String COMPLETED_DESCRIPTION = "Завершен";
    public static final String DECOMMISSIONED_DESCRIPTION = "Завершен";
    public static final String PAID_DESCRIPTION = "Оплачен";
    public static final String DELIVERED_DESCRIPTION = "Выдан";
    public static final String REJECTED_DESCRIPTION = "Отклонен";

    //model
    public static final String MODEL_ID = "model_id";
    public static final String SERIAL_NUMBER = "serialNumber";
    public static final String DATE_OF_SALE = "dateOfSale";
    public static final String SALESMAN_NAME = "salesmanName";
    public static final String SALESMAN_PHONE = "salesmanPhone";
    public static final String BUYER_NAME = "buyerName";
    public static final String BUYER_SURNAME = "buyerSurname";
    public static final String BUYER_PHONE = "buyerPhone";

    //Permission parameters
    public static final String PERMISSION_TYPE = "type";
    public static final String PERMISSION_CATEGORY = "category";

    //Role parameters
    public static final String ROLE_NAME = "name";

    //Account parameters
    public static final String USER = "user";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "userName";
    public static final String USER_SURNAME = "userSurname";
    public static final String IS_ACTIVE_ACCOUNT = "isActive";

    //ServiceAccount parameters
    public static final String SERVICE_CENTER = "serviceCenter";

    //Service center parameters
    public static final String SERVICE_NAME = "serviceName";


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
    public static final String BRAND_LIST_PAGE_PATH = "/pages/lists/brandList.jsp";
    public static final String MODEL_LIST_PAGE_PATH = "/pages/lists/modelList.jsp";
    public static final String REPAIR_PAGE_PATH = "/pages/add/repairPage.jsp";
    public static final String REPAIR_TABLE_PAGE_PATH = "/pages/tables/repairTable.jsp";

    //jsp
    public static final String PAGE_NUMBER = "pageNumber";
    public static final int LIST_SIZE = 12;
    public static final int FIRST_PAGE = 1;
    public static final String MAX_PAGE = "maxPage";
    public static final String PAGE = "page";
    public static final String LIST_FOR_PAGE = "table";



    public static final String BRANDS = "brands";
    public static final String BRAND = "brand";
    public static final String MODELS = "models";
    public static final String MODEL = "model";
    public static final String DEVICE_DESCRIPTION_PATTERN = "%s\n %s %s";

    public static final String REPAIR_PAGE_DATA = "repair page data";
    public static final long DEFAULT_ID = 1L;
    public static final String BRAND_ID = "brand_id";

    //open commands
    public static final String OPEN_REPAIR_TABLE_PAGE = "main?command=show_request_repair_table&&page=%d";
    public static final String OPEN_REPAIR_PAGE = "main?command=show_repair&&page=%d&&brand_id=1";


    public static final String OPEN_START_PAGE = "main?command=open_page&&page=1";
    public static final String OPEN_BRAND_LIST_PAGE = "brands?command=show_brand_list";
    public static final String OPEN_MODEL_LIST_PAGE = "brands?command=show_model_list&&id=%s";




}
