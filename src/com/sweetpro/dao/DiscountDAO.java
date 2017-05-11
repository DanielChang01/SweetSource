package com.sweetpro.dao;

import com.sweetpro.entities.DiscountEntity;

/**
 * Created by danielchang on 2017/5/11.
 */
public class DiscountDAO {

    DbUtils dbUtils = new DbUtils();


    /**
     * 店铺消费情况统计表
     */
    public int insertDiscount(DiscountEntity discount){

        int i = 0;

        return i;
    }

    /**
     * 更新表字段，做统计
     * @param discount
     * @return
     */
    public int updateDiscount(DiscountEntity discount){
        int i = 0;

        return i;
    }

    /**
     * 根据用户和店铺ID，进行表的清除
     * @param discount
     * @return
     */
    public int clearPerDiscount(DiscountEntity discount){
        int i = 0;

        return i;
    }

    public int clearAllDiscount(DiscountEntity discount){
        int i = 0;

        return i;
    }

}
