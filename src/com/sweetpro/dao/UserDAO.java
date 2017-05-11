package com.sweetpro.dao;

import com.sweetpro.entities.CommondAttr;
import com.sweetpro.entities.ShopEntity;
import com.sweetpro.entities.UsersEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by danielchang on 2017/5/11.
 */
public class UserDAO {

    DbUtils dbUtils = new DbUtils();
    ShopDAO shopDAO = new ShopDAO();

    public int insertUsers(UsersEntity user){
        Connection conn = dbUtils.getConn();
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
            shopDAO.insertShop(shop);
        }

        return i;
    }

    /**
     * 返回值规定：两位十进制数字（0，1）
     * 第一位表示登录是否成功：成功为 1 ，失败为 0
     * 第二位表示登录者身份：商家 1， 普通用户  0（管理员 2 未参与）
     * @param user
     * @return
     */
    public String userLogin(UsersEntity user){
        Connection conn = dbUtils.getConn();
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
            dbUtils.releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
