package main.common;

import java.io.File;

public class URLConst {

    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String RESET = "/reset-data";

    public static final String USER_HOME = "/user";

    public class Water {
        public static final String WATER_HOME = "/water";

        public static final String WATER_HOME_1 = "/water1";

        public static final String WATER_HOME_2 = "/water2";

        public static final String WATER_SEARCH_HOUSEHOLD = WATER_HOME + "/search";

        public static final String GET_WATER_SUPPLIER = WATER_HOME + "/getWaterSupplier";

        public static final String UPDATE_HOUSE_HOLD = WATER_HOME + "/updateHouseHold";

        public static final String UPDATE_WATER_MONEY = WATER_HOME + "/updateWaterMoney";

        public static final String GET_REQUEST_RESIGTER_WATER = WATER_HOME_2 + "/registerWater";
    }

    public class User {
        public static final String URL_USER = "/payoo";

        public static final String USER_SEARCH_HOUSEHOLD = URL_USER + "/search";
    }

}
