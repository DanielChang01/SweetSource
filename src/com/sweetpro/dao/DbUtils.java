package com.sweetpro.dao;

import com.sweetpro.entities.CommondAttr;
import com.sweetpro.entities.DishEntity;
import com.sweetpro.entities.ShopEntity;
import com.sweetpro.entities.UsersEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by danielchang on 2017/5/5.
 */
public class DbUtils {

    private static Connection getConn(){
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

    private static int insertUsers(UsersEntity user){
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

        return i;
    }

    private static int insertDish(DishEntity dish){
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
            preparedStatement.setString(1,dish.getShopID());
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

    private static int insertShop(ShopEntity shop){
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into "+CommondAttr.TBNAME_SHOP+" ("+
                CommondAttr.SHOP_ID+","+
                CommondAttr.SHOP_NAME+","+
                CommondAttr.SHOP_ADDR+","+
                CommondAttr.SHOP_PIC+","+
                CommondAttr.SHOP_POINT+")" +
                " values(?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,shop.getShopID());
            preparedStatement.setString(2,shop.getShopName());
            preparedStatement.setString(3,shop.getShopAddr());
            preparedStatement.setString(4,shop.getShopPic());
            preparedStatement.setString(5,shop.getShopPoint());
            i = preparedStatement.executeUpdate();
            releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    private static int updateShop(ShopEntity shop){
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

    private static int updateDish(DishEntity dish){
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

    private static int queryShop(){
        return 0;
    }

    private static int queryDish(){
        return 0;
    }

    private static int queryUsers(){
        return 0;
    }









    private static void releaseResource(PreparedStatement preparedStatement, Connection conn) throws SQLException {
        preparedStatement.close();
        conn.close();
    }





}
