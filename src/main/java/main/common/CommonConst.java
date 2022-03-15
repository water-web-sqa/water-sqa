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
}
