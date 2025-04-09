package com.vnnet.newscommon.config;



import java.util.Arrays;
import java.util.List;

public class  Constants {

    public static final int PROTOCOL_VERSION = 1;

    public static final String MASTER_VERSION = "1.0.0"; // master data version
    public static final int MASTER_EXPIRED_TIME = 1000 * 60 * 60 * 24 * 5; // master data expired timeout (in milliseconds)

    public static final int PERMISSION_CANCEL = 1;
    public static final int PERMISSION_EDIT = 2;
    public static final int PERMISSION_DELETE = 3;
    public static final int PERMISSION_CONFIRM = 4;


    public static final int ROLE_SYS_ADMIN = 1;
    public static final int ROLE_SYS_STAFF = 2;

    public static final String ADMIN = "admin";

    public static final String ORDER_DRAFT = "draft";
    public static final String ORDER_SENT = "sent";
    public static final String ORDER_SALE = "sale";
    public static final String ORDER_CANCEL = "cancel";

    public static final String ODOO_DB = "kinhteso";
    public static final String ODOO_URL = "https://kinhteso.mobifone5.vn";
    public static final String ODOO_ADMIN = "ngoc47711@gmail.com";
    public static final String ODOO_PASS = "1";
    public static final String PROXY_HOST = "10.151.151.50";
    public static final int PROXY_PORT = 9999;

    public static final String PRODUCT_IMAGE_PATH = "/opt/MiniAppCongAn/image/";
    public static final String NEWS_IMAGE_PATH = "/opt/MiniAppCongAn/image/news/";
    public static final String SUPPLY_IMAGE_PATH = "/opt/MiniAppCongAn/image/supply/";
    public static final String SUPPLY_CERTIFICATE_PATH = "/opt/MiniAppCongAn/certificate/";
    public static final String VIOLATION_IMG = "/opt/MiniAppCongAn/image/violation/";

    public static final String BROADCASTING_RECORD_PATH = "/opt/MiniAppCongAn/record/";

    public static final String PAGE_NUM = "1";
    public static final String PAGE_SIZE = "12";

    public static final String ECOMMERCE = "https://shopee.vn/";

    public static final List<String> TRAVELS = List.of("url1", "url2", "url3", "url4", "url5", "url6", "url7", "url8");

    public static final List<String> PLANTINGAREAS = List.of("url1", "url2", "url3", "url4", "url5", "url6", "url7", "url8");


    public static final String ALGORITHM = "HmacSHA256";
    public static final String ZALO_APP_SECRET_KEY = "U8M5AL73sW5hVe12ix4A";
    public static final String API_URL = "https://graph.zalo.me/v2.0/me?fields=id,name,birthday,picture,phone";
    public static final String API_PHONE = "https://graph.zalo.me/v2.0/me/info";

    public static final String DEFAULT_PASSWORD = "123456";

    public static final String PUBLIC_TYPE = "PUBLIC";
    public static final String PRIVATE_TYPE = "PRIVATE";

    public static final String REPLAY_TYPE = "REPLAY";
    public static final String LIVE_TYPE = "LIVE";

    public static final List<String> PROPERTIES_TO_COPY = Arrays.asList("dNgayTao", "cNguoiTao");

}