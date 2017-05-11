package com.sweetpro.dao;

import com.sweetpro.entities.CommondAttr;
import com.sweetpro.entities.ShopEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielchang on 2017/5/11.
 */
public class ShopDAO {

    DbUtils dbUtils = new DbUtils();


    public int insertShop(ShopEntity shop){
        Connection conn = dbUtils.getConn();
        int i = 0;
        String sql = "insert into "+ CommondAttr.TBNAME_SHOP+" ("+
                CommondAttr.SHOP_ID+")" +
                " values(?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,shop.getShopID());
            i = preparedStatement.executeUpdate();
            dbUtils.releaseResource(preparedStatement,conn);
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
        Connection conn = dbUtils.getConn();
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
            dbUtils.releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


    public List<ShopEntity> queryShop(){
        Connection conn = dbUtils.getConn();
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
            dbUtils.releaseResource(preparedStatement,conn);
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
}
