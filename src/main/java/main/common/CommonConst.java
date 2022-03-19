package main.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CommonConst {
	public static String SERVER_VERSION = "water_customer_2022";
	public static final String BLANK = "";
	public static final String SPACE = " ";



	public static class COMMON_RESPONSE {
		public static int OK = 200;
		public static int EXCEPTION = 500;
		public static int NON_AUTH = 203;
		public static int NON_ACTIVE = 204;
		public static int NOT_VALID = 422;
		public static int PERMISSION_DENIED = 403;

	}


	public static class DELETE_FLG {
		public static String DELETE = "1";
		public static String NON_DELETE = "0";
		public static Integer DELETE_INT = 1;
		public static Integer NON_DELETE_INT = 0;
	}

	public static class COMMON_PAGE {
		public static String WATER = "1";
		public static String WATER1 = "2";
		public static String WATER2 = "3";

	}

	public static class COMMON_USER_PAGE {
		public static String WATER = "1";
		public static String WATER1 = "2";
		public static String WATER2 = "3";

	}



	public static class DATE_FORMAT {
		/**
		 * Time format
		 */
		public static String YYYYMMDD_HHMMSS = "yyyyMMdd_hhmmss_SSS";

		public static final String YYMM = "yyMM";
		public static final String YYYYMM = "yyyyMM";
		public static final String YYYYMMDD = "yyyyMMdd";
		public static final String YYYY_MM_DD = "yyyy/MM/dd";
		public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
		public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
		/**
		 * yyyy/MM/dd
		 */
		public static final SimpleDateFormat formatterYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
		/**
		 * yyyyMMdd
		 */
		public static final SimpleDateFormat formatterYYYYMMDD2 = new SimpleDateFormat("yyyyMMdd");

		/**
		 * yyyy/MM/dd HH:mm
		 */
		public static final SimpleDateFormat formatterYYYYMMDDHHMM = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		/**
		 * yyyyMMddHHmmssSSS
		 */
		public static final SimpleDateFormat formatterYYYYMMDDHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		/**
		 * yyyy/MM/dd HH:mm:ss
		 */
		public static final SimpleDateFormat formatterYYYYMMDDHHMMss = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		/**
		 * yyyyMMddHHmmss
		 */
		public static final SimpleDateFormat formatterYYYYMMDDHHMMss2 = new SimpleDateFormat("yyyyMMddHHmmss");
		/**
		 * yyyyMMddHHmmss
		 */
		public static final SimpleDateFormat formatterYYYYMMDDHHMM2 = new SimpleDateFormat("yyyyMMddHHmm");
		/**
		 * HHmm
		 */
		public static final SimpleDateFormat formatterHHmm = new SimpleDateFormat("HHmm");
		/**
		 * HH:mm
		 */
		public static final SimpleDateFormat formatterHHmm2 = new SimpleDateFormat("HH:mm");
		/**
		 * MM/dd
		 */
		public static final SimpleDateFormat formatterMMdd = new SimpleDateFormat("MM/dd");

		/**
		 * yy.MM.dd
		 */
		public static final SimpleDateFormat formatteryyMMdd = new SimpleDateFormat("yy.MM.dd");

		/**
		 * yyyy-MM
		 */
		public static final SimpleDateFormat formatterYYYY_MM = new SimpleDateFormat("yyyy-MM");
		public static final SimpleDateFormat formatterYYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

		/**
		 * yyyy-MM
		 *
		 */
		public static final SimpleDateFormat formatterYYYYMM = new SimpleDateFormat("yyyyMM");
	}

	public static class HOUSE_HOLD{
		public static String HOUSE_HOLD = "0";
		public static String POOR_HOUSE_HOLD = "1";
	}

	public static class HOUSE_HOLD_VALID{
		public static Integer HOUSE_HOLD_VALID_BAC_1 = 5973;
		public static Integer HOUSE_HOLD_VALID_BAC_2 = 7052;
		public static Integer HOUSE_HOLD_VALID_BAC_3 = 8669;
		public static Integer HOUSE_HOLD_VALID_BAC_4 = 15929;
		public static Integer THUE_VAT = 5/100;
		public static Integer THUE_MOI_TRUONG = 10/100;
	}

	public static class POOR_HOUSE_HOLD_VALID{
		public static Integer POOR_HOUSE_HOLD_VALID_BAC_1 = 3600;
		public static Integer POOR_HOUSE_HOLD_VALID_BAC_2 = 4500;
		public static Integer POOR_HOUSE_HOLD_VALID_BAC_3 = 5600;
		public static Integer POOR_HOUSE_HOLD_VALID_BAC_4 = 6700;
		public static Integer THUE_VAT = 5/100;
		public static Integer THUE_MOI_TRUONG = 10/100;
	}

	public static int getBacHouseHold(int numberWater){
		if(numberWater < 10) return 1;
		if(numberWater >= 10 && numberWater < 20) return 2;
		if(numberWater >= 20 && numberWater < 30) return 3;
		return 4;
	}
}
