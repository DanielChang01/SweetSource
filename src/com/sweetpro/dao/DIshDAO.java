package com.sweetpro.dao;

import com.sweetpro.entities.CommondAttr;
import com.sweetpro.entities.DishEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielchang on 2017/5/11.
 */
public class DIshDAO {

    DbUtils dbUtils = new DbUtils();


    public int insertDish(DishEntity dish){
        Connection conn = dbUtils.getConn();
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
            dbUtils.releaseResource(preparedStatement,conn);
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
        Connection conn = dbUtils.getConn();
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
            dbUtils.releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }





    public List<DishEntity> queryDish(DishEntity dish){
        Connection conn = dbUtils.getConn();
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
            dbUtils.releaseResource(preparedStatement,conn);
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
}
