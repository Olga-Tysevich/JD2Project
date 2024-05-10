package it.academy.utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JSPConstant {

    // page paths
    public static final String ADMIN_MAIN_PAGE_PATH = "/pages/adminMainPage.jsp";
    public static final String USER_MAIN_PAGE_PATH = "/pages/userMainPage.jsp";
    public static final String ERROR_PAGE_PATH = "/pages/error.jsp";
    public static final String LOGIN_PAGE_PATH = "/pages/index.jsp";
    public static final String MAIN_PAGE_PATH = "/pages/index.jsp";

    //account
    public static final String SERVICE_CENTERS = "service_centers";
    public static final String ADD_ACCOUNT_PAGE_PATH = "/pages/account/addAccount.jsp";
    public static final String UPDATE_ACCOUNT_PAGE_PATH = "/pages/account/updateAccount.jsp";
    public static final String ACCOUNT_TABLE_PAGE_PATH = "/pages/account/accountTable.jsp";
    public static final String ACCOUNT_FILTERS_PAGE_PATH = "/pages/forms/filters/accountFilters.jsp";

    //brand
    public static final String ADD_BRAND_PAGE_PATH = "/pages/brand/addBrand.jsp";
    public static final String UPDATE_BRAND_PAGE_PATH = "/pages/brand/updateBrand.jsp";
    public static final String BRAND_TABLE_PAGE_PATH = "/pages/brand/brandTable.jsp";

    //deviceType
    public static final String ADD_DEVICE_TYPE_PAGE_PATH = "/pages/device_type/addDeviceType.jsp";
    public static final String UPDATE_DEVICE_TYPE_PAGE_PATH = "/pages/device_type/updateDeviceType.jsp";
    public static final String DEVICE_TYPE_TABLE_PAGE_PATH = "/pages/device_type/deviceTypeTable.jsp";

    //model
    public static final String MODEL_FORM = "model_form";
    public static final String ADD_MODEL_PAGE_PATH = "/pages/model/addModel.jsp";
    public static final String UPDATE_MODEL_PAGE_PATH = "/pages/model/updateModel.jsp";
    public static final String MODEL_TABLE_PAGE_PATH = "/pages/model/modelTable.jsp";
    public static final String MODEL_FILTERS_PAGE_PATH = "/pages/forms/filters/modelFilters.jsp";

    //Device
    public static final String SALESMAN_NAME_FILTER = "salesman_name";
    public static final String BUYER_SURNAME_FILTER = "buyer_surname";

    //sparePart
    public static final String SPARE_PART = "sparePart";
    public static final String ADD_SPARE_PART_PAGE_PATH = "/pages/spare_part/addSparePart.jsp";
    public static final String UPDATE_SPARE_PART_PAGE_PATH = "/pages/spare_part/updateSparePart.jsp";
    public static final String SPARE_PART_TABLE_PAGE_PATH = "/pages/spare_part/sparePartTable.jsp";
    public static final String SPARE_PART_FILTERS_PAGE_PATH = "/pages/forms/filters/sparePartFilters.jsp";

    //sparePartOrder
    public static final String ORDER = "order";
    public static final String ORDER_DEPARTURE_DATE = "departureDate";
    public static final String ORDER_DELIVERY_DATE = "deliveryDate";
    public static final String ADD_SPARE_PART_ORDER_PAGE_PATH = "/pages/spare_part_order/addSparePartOrder.jsp";
    public static final String UPDATE_SPARE_PART_ORDER_PAGE_PATH = "/pages/spare_part_order/updateSparePartOrder.jsp";
    public static final String SPARE_PART_ORDER_TABLE_PAGE_PATH = "/pages/spare_part_order/sparePartOrdersTable.jsp";

    //repairType
    public static final String REPAIR_TYPE_ID = "repair_type_id";
    public static final String REPAIR_TYPE = "repairType";
    public static final String ADD_REPAIR_TYPE_PAGE_PATH = "/pages/repair_type/addRepairType.jsp";
    public static final String UPDATE_REPAIR_TYPE_PAGE_PATH = "/pages/repair_type/updateRepairType.jsp";
    public static final String REPAIR_TYPE_TABLE_PAGE_PATH = "/pages/repair_type/repairTypeTable.jsp";

    //serviceCenter
    public static final String ADD_SERVICE_CENTER_PAGE_PATH = "/pages/service_center/addServiceCenter.jsp";
    public static final String UPDATE_SERVICE_CENTER_PAGE_PATH = "/pages/service_center/updateServiceCenter.jsp";
    public static final String SERVICE_CENTER_TABLE_PAGE_PATH = "/pages/service_center/serviceCenterTable.jsp";
    public static final String SERVICE_CENTER_FILTERS_PAGE_PATH = "/pages/forms/filters/serviceCenterFilters.jsp";

    //repair
    public static final String ADD_REPAIR_PAGE_PATH = "/pages/repair/addRepairPage.jsp";
    public static final String REPAIR_TABLE_PAGE_PATH = "/pages/repair/repairTable.jsp";
    public static final String ADMIN_REPAIR_PAGE_PATH = "/pages/repair/adminRepairPage.jsp";
    public static final String USER_REPAIR_PAGE_PATH = "/pages/repair/userRepairPage.jsp";
    public static final String REPAIR_FILTERS_PAGE_PATH = "/pages/forms/filters/repairFilters.jsp";

    //common
    public static final String COMPONENT_FILTERS_PAGE_PATH = "/pages/forms/filters/componentFilters.jsp";
    public static final String OPEN_TABLE_PAGE = "main?command=%s&&page=%s&&pageNumber=%d";
    public static final String OPEN_TABLE_PAGE_BY_FILTER = "main?command=%s&&page=%s&&pageNumber=%d";
    public static final String OPEN_NEW_OBJECT_FORM_PAGE = "%s?command=%s";
    public static final String OPEN_FORM = "%s?command=%s&&id=%d";
    public static final String PARAMETER_PATTERN = "&&%s=%s&&";
    public static final String PAGE_NUMBER_REGEX = "pageNumber=[\\d]+";
    public static final String PAGE_NUMBER_PATTERN = "pageNumber=";
    public static final String LAST_PAGE = "last_page";


}
