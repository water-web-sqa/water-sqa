package main.common;

import javax.servlet.jsp.PageContext;
import java.io.File;
import javax.servlet.jsp.PageContext;

public class URLConst {

    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String RESET = "/reset-data";

    public static final String USER_HOME = "/user";

    public class Water {
        public static final String WATER_HOME = "/water";

        public static final String WATER_HOME_1 = "/water1";

//        public static final String WATER_HOME_2 = "/water2";

        public static final String WATER_SEARCH_HOUSEHOLD = WATER_HOME + "/search";

        public static final String GET_WATER_SUPPLIER = WATER_HOME + "/getWaterSupplier";

        public static final String UPDATE_HOUSE_HOLD = WATER_HOME + "/updateHouseHold";

        public static final String UPDATE_WATER_MONEY = WATER_HOME + "/updateWaterMoney";

        public static final String GET_REQUEST_RESIGTER_WATER = WATER_HOME_1 + "/registerWater";

        public static final String SET_STATUS_REQUEST = GET_REQUEST_RESIGTER_WATER + "/updateStatus";

        public static final String UPDATE_CUSTOMER_RESIGTER = GET_REQUEST_RESIGTER_WATER + "/updateStatus";
    }

    public class User {
        public static final String URL_USER = "/payoo";

        public static final String USER_REGISTER_USE_WATER = URL_USER + "/register";

        public static final String USER_SEARCH_HOUSEHOLD = URL_USER + "/search";

        public static final String USER_SEARCH_BILL = URL_USER + "/searchBill";

        public static final String USER_PAY_MENT = URL_USER + "/payment";

        public static final String VPN_RESPONSE_PAY_MENT = "http://localhost:8080/Water_war" + URL_USER + "/responsePayment";

        public static final String VPN_RESPONSE_PAY_MENT_SERVER = URL_USER + "/responsePayment";

        public static final String WATER_HOME_USER = URL_USER + "/customer";

        public static final String WATER_HOME_USER_1 = URL_USER + "/customer/resgisterWater";

        public static final String WATER_HOME_USER_2 = URL_USER + "/customer/payment";

        public static final String GET_HOUSE_HOLD = URL_USER + "/getHouseHolde";

        public static final String GET_LIST_MEMBER_WATER = URL_USER + "/getMemberWater";

        public static final String ADD_CUSTOMER_REGISTER = USER_REGISTER_USE_WATER+ "/addCustomerRegister";

    }

}
