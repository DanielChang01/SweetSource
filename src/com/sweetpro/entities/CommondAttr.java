package com.sweetpro.entities;


public class CommondAttr {

    /**
     * 接收及返回字符的首字命令规定
     * 命令长度：
     *   第一字段    1     2    3    4
     *   命令含义   插入  更新  查询  删除
     *   第二字段    1     2    3
     *   命令含义   Users shop  dish
     *
     * eg.如接收字段为 12 则含义为 shop执行插入操作
     * 用户：USER
     * 商家：SHOP
     */

    public static final int USER_INSERT = 11;//对Users表进行插入
    public static final int USER_UPDATE = 21;//对Users表进行更新
    public static final int USER_SELECT = 31;//对Users表进行查找
    public static final int USER_DELETE = 41;//对Users表字段进行删除

    public static final int SHOP_INSERT = 12;
    public static final int SHOP_UPDATE = 22;
    public static final int SHOP_SELECT = 32;
    public static final int SHOP_DELETE = 42;

    public static final int DISH_INSERT = 13;
    public static final int DISH_UPDATE = 23;
    public static final int DISH_SELECT = 33;
    public static final int DISH_DELETE = 43;

    /**
     * 对返回数据的规定
     * 1，登录
     * 2，普通操作
     * 3，店铺
     * 4，菜品
     * 返回数据格式为：以上数字 + 内容
     */
    public static final String SIGN_LOGIN = "1;";
    public static final String SIGN_COMMON = "2;";
    public static final String SIGN_SHOP = "3;";
    public static final String SIGN_DISH = "4;";


    public static final String TBNAME_USERS = "users";
    public static final String TBNAME_SHOP = "shop";
    public static final String TBNAME_DISH = "dish";


    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PASS = "user_pass";
    public static final String USER_MOBILE = "user_mobile";
    public static final String USER_SHOP_ID = "user_shop_id";

    public static final String DISH_ID = "dish_id";
    public static final String DISH_SHOP_ID = "shop_id";
    public static final String DISH_NAME = "dish_name";
    public static final String DISH_DESC = "dish_desc";
    public static final String DISH_CATEGORY = "dish_category";
    public static final String DISH_PIC = "dish_pic";
    public static final String DISH_PRE_PRICE = "dish_pre_price";
    public static final String DISH_CUT_PRICE = "dish_cut_price";

    public static final String SHOP_ID = "shop_id";
    public static final String SHOP_NAME = "shop_name";
    public static final String SHOP_ADDR = "shop_addr";
    public static final String SHOP_PIC = "shop_pic";
    public static final String SHOP_POINT = "shop_point";



}
