package it.academy.utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JSPConstant {

    //common
    public static final String LAST_PAGE = "last_page";

    // page paths
    public static final String CHANGE_FORM_PAGE_PATH = "/pages/forms/changeForm.jsp";
    public static final String PAGINATION_PAGE_PATH = "/pages/forms/pagination.jsp";
    public static final String ADMIN_MAIN_PAGE_PATH = "/pages/adminMainPage.jsp";
    public static final String USER_MAIN_PAGE_PATH = "/pages/userMainPage.jsp";
    public static final String ERROR_PAGE_PATH = "/pages/error.jsp";
    public static final String LOGIN_PAGE_PATH = "/pages/index.jsp";
    public static final String MAIN_PAGE_PATH = "/pages/index.jsp";

    //account
    public static final String SERVICE_CENTERS = "service_centers";
    public static final String NEW_ACCOUNT_PAGE_PATH = "/pages/account/addAccount.jsp";
    public static final String ACCOUNT_PAGE_PATH = "/pages/account/changeAccount.jsp";
    public static final String ACCOUNT_TABLE_PAGE_PATH = "/pages/account/accountTable.jsp";

    //brand
    public static final String BRAND_PAGE_PATH = "/pages/brand/changeBrand.jsp";
    public static final String BRAND_TABLE_PAGE_PATH = "/pages/brand/brandTable.jsp";

    //deviceType
    public static final String DEVICE_TYPE_PAGE_PATH = "/pages/device_type/changeDeviceType.jsp";
    public static final String DEVICE_TYPE_TABLE_PAGE_PATH = "/pages/device_type/deviceTypesTable.jsp";

    //model
    public static final String MODEL_PAGE_PATH = "/pages/model/changeModel.jsp";
    public static final String MODEL_TABLE_PAGE_PATH = "/pages/model/modelTable.jsp";

    //sparePart
    public static final String SPARE_PART_PAGE_PATH = "/pages/spare_part/changeSparePart.jsp";
    public static final String SPARE_PART_TABLE_PAGE_PATH = "/pages/spare_part/sparePartTable.jsp";

    //sparePartOrder
    public static final String ADD_SPARE_PART_ORDER_PAGE_PATH = "/pages/spare_part_order/addSparePartOrder.jsp";
    public static final String SPARE_PART_ORDER_PAGE_PATH = "/pages/spare_part_order/changeSparePartOrder.jsp";
    public static final String SPARE_PART_ORDER_TABLE_PAGE_PATH = "/pages/spare_part_order/sparePartOrdersTable.jsp";

    //repairType
    public static final String REPAIR_TYPE_ID = "repair_type_id";
    public static final String REPAIR_TYPE_PAGE_PATH = "/pages/repair/changeRepairType.jsp";
    public static final String REPAIR_TYPE_TABLE_PAGE_PATH = "/pages/repair/repairTypeTable.jsp";

    //serviceCenter
    public static final String SERVICE_CENTER_PAGE_PATH = "/pages/service_center/changeServiceCenter.jsp";
    public static final String SERVICE_CENTER_TABLE_PAGE_PATH = "/pages/service_center/serviceCenterTable.jsp";

    // repair
    public static final String ADD_REPAIR_PAGE_PATH = "/pages/repair/addRepairPage.jsp";
    public static final String REPAIR_TABLE_PAGE_PATH = "/pages/repair/repairTable.jsp";
    public static final String REPAIR_PAGE_PATH = "/pages/repair/repairPage.jsp";
    public static final String USER_REPAIR_PAGE_PATH = "/pages/repair/userRepairPage.jsp";

    public static final String OPEN_TABLE_PAGE = "main?command=%s&&page=%s&&pageNumber=%d";
    public static final String OPEN_TABLE_PAGE_BY_FILTER = "main?command=%s&&page=%s&&pageNumber=%d&&filter=%s&&input=%s";
    public static final String OPEN_NEW_OBJECT_FORM_PAGE = "main?command=%s";
    public static final String OPEN_FORM = "%s?command=%s&&id=%d";


}
