package com.sweetpro.dao;

import com.sweetpro.entities.CommondAttr;
import com.sweetpro.entities.DishEntity;
import com.sweetpro.entities.ShopEntity;
import com.sweetpro.entities.UsersEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbUtils {

    private Connection getConn(){
        String driver = "com.mysql.jdbc.driver";
        String url = "jdbc:mysql://localhost:3306/test_for_sweet";
        String userName = "root";
        String passWord = "root";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,userName,passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public int insertUsers(UsersEntity user){
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into "+ CommondAttr.TBNAME_USERS+" ("+
                CommondAttr.USER_NAME+"," +
                CommondAttr.USER_PASS+","+
                CommondAttr.USER_MOBILE+","+
                CommondAttr.USER_SHOP_ID +
                ") values(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassWord());
            preparedStatement.setString(3,user.getMobile());
            preparedStatement.setString(4,user.getShopID());
            i = preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //如果shop_id 不为空，则插入shop表中
        if (user.getShopID() != null){
            ShopEntity shop = new ShopEntity();
            shop.setShopID(user.getShopID());
            insertShop(shop);
        }

        return i;
    }

    public int insertDish(DishEntity dish){
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into "+ CommondAttr.TBNAME_DISH+" ("+
                CommondAttr.DISH_SHOP_ID+"," +
                CommondAttr.DISH_NAME+","+
                CommondAttr.DISH_DESC+","+
                CommondAttr.DISH_CATEGORY+"," +
                CommondAttr.DISH_PIC+"," +
                CommondAttr.DISH_PRE_PRICE+"," +
                CommondAttr.DISH_CUT_PRICE+"," +
                ") values(?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,dish.getDishShopID());
            preparedStatement.setString(2,dish.getDishName());
            preparedStatement.setString(3,dish.getDishDescribe());
            preparedStatement.setString(4,dish.getDishCategory());
            preparedStatement.setString(5,dish.getDishPic());
            preparedStatement.setString(6,dish.getDishPrePrice());
            preparedStatement.setString(7,dish.getDishCutPrice());
            i = preparedStatement.executeUpdate();
            releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    private int insertShop(ShopEntity shop){
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into "+CommondAttr.TBNAME_SHOP+" ("+
                CommondAttr.SHOP_ID+")" +
                " values(?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,shop.getShopID());
            i = preparedStatement.executeUpdate();
            releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 更新店铺表
     * @param shop
     * @return
     */
    public int updateShop(ShopEntity shop){
        Connection conn = getConn();
        int i = 0;
        String sql = "update "+CommondAttr.TBNAME_SHOP+" set "+
                CommondAttr.SHOP_NAME+"='"+ shop.getShopName()+"',"+
                CommondAttr.SHOP_ADDR+"='"+shop.getShopAddr()+"'," +
                CommondAttr.SHOP_POINT+"='"+shop.getShopPoint()+"',"+
                CommondAttr.SHOP_PIC+"='" + shop.getShopPic()+"'" +
                " where "+
                CommondAttr.SHOP_ID+"='"+shop.getShopID()+"'";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            i = preparedStatement.executeUpdate();
            releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     * 更新菜品表
     * @param dish
     * @return
     */
    public int updateDish(DishEntity dish){
        Connection conn = getConn();
        int i = 0;
        String sql = "update "+CommondAttr.TBNAME_DISH+" set "+
                CommondAttr.DISH_NAME+"='" + dish.getDishName()+"',"+
                CommondAttr.DISH_DESC+"='"+dish.getDishDescribe()+"'," +
                CommondAttr.DISH_CATEGORY +"='"+dish.getDishCategory()+"',"+
                CommondAttr.DISH_CUT_PRICE +"='"+dish.getDishCutPrice()+"'" +
                " where "+
                CommondAttr.DISH_ID +"='"+dish.getDishID()+"'";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            i = preparedStatement.executeUpdate();
            releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    public List<ShopEntity> queryShop(){
        Connection conn = getConn();
        ResultSet result = null;
        List<ShopEntity> myShop = null;
        String sql = "select * from "+CommondAttr.TBNAME_SHOP;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            if (result != null){
                myShop = convertToShopList(result);
            }
            releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myShop;
    }

    private List<ShopEntity> convertToShopList(ResultSet result) {
        List<ShopEntity> myShop = new ArrayList<>();
        try {
            while(result.next()){
                ShopEntity shopEntity = new ShopEntity();
                shopEntity.setShopName(result.getString(CommondAttr.SHOP_NAME));
                shopEntity.setShopAddr(result.getString(CommondAttr.SHOP_ADDR));
                shopEntity.setShopPic(result.getString(CommondAttr.SHOP_PIC));
                shopEntity.setShopPoint(result.getString(CommondAttr.SHOP_POINT));
                myShop.add(shopEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myShop;
    }

    public List<DishEntity> queryDish(DishEntity dish){
        Connection conn = getConn();
        ResultSet result = null;
        List<DishEntity> myDish = null;
        String sql = "select * from "+CommondAttr.TBNAME_DISH+" where "+
                CommondAttr.DISH_SHOP_ID+"='"+dish.getDishShopID()+"'";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            if (result != null){
                myDish = convertToDishList(result);
            }
            releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myDish;
    }

    private List<DishEntity> convertToDishList(ResultSet result) {
        List<DishEntity> myDish = new ArrayList<>();
        try {
            while(result.next()){
                DishEntity dishEntity = new DishEntity();
                dishEntity.setDishName(result.getString(CommondAttr.DISH_NAME));
                dishEntity.setDishDescribe(result.getString(CommondAttr.DISH_DESC));
                dishEntity.setDishCategory(result.getString(CommondAttr.DISH_CATEGORY));
//                dishEntity.setDishPic(result.getString(CommondAttr.DISH_PIC));
                dishEntity.setDishPrePrice(result.getString(CommondAttr.DISH_PRE_PRICE));
                dishEntity.setDishCutPrice(result.getString(CommondAttr.DISH_CUT_PRICE));
                myDish.add(dishEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myDish;

    }


    /**
     * 返回值规定：两位十进制数字（0，1）
     * 第一位表示登录是否成功：成功为 1 ，失败为 0
     * 第二位表示登录者身份：商家 1， 普通用户  0（管理员 2 未参与）
     * @param user
     * @return
     */
    public String userLogin(UsersEntity user){
        Connection conn = getConn();
        StringBuilder sb = new StringBuilder();
        String select_sql = "select * from "+CommondAttr.TBNAME_USERS +" " +
                "where "+ CommondAttr.USER_NAME+"="+user.getUserName();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(select_sql);
            ResultSet result = preparedStatement.executeQuery();
            if (result != null){
                if (result.isFirst()){
                    if (user.getPassWord().equals(result.getString(CommondAttr.USER_PASS))){
                        sb.append("1");
                    } else {
                        sb.append("0");
                    }
                    if (result.getString(CommondAttr.USER_SHOP_ID)==null){
                        sb.append("0");
                    } else {
                        sb.append("1");
                    }
                }
            } else {
                sb.append("00");
            }
            releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
    

    private void releaseResource(PreparedStatement preparedStatement, Connection conn) throws SQLException {
        preparedStatement.close();
        conn.close();
    }





}
