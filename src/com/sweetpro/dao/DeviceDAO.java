package com.sweetpro.dao;

import com.sweetpro.entities.CommondAttr;
import com.sweetpro.entities.DeviceEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by danielchang on 2017/5/11.
 */
public class DeviceDAO {

    DbUtils dbUtils = new DbUtils();


    /**
     * 用于心跳连接，更新数据表中的IP地址等信息
     * 添加时间2017／5／9
     */

    public int updateDevice(DeviceEntity device){
        Connection conn = dbUtils.getConn();
        int i = 0;
        String sql = "update "+ CommondAttr.TBNAME_DEVICE+" set " +
                CommondAttr.DEVICE_IP +"="+device.getUserIP()+" where " +
                CommondAttr.DEVICE_MAC +"="+device.getUserMac();
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

    public String getDeviceIP(DeviceEntity device){
        Connection conn = dbUtils.getConn();
        String myIP = null;
        ResultSet result = null;

        String sql = "select "+CommondAttr.DEVICE_IP+" from "+CommondAttr.TBNAME_DEVICE+
                " where "+CommondAttr.DEVICE_MAC+"="+ device.getUserMac();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            myIP = result.getString(CommondAttr.DEVICE_IP);
            dbUtils.releaseResource(preparedStatement,conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myIP;
    }

    public int insertDevice(DeviceEntity device){
        Connection conn = dbUtils.getConn();
        int i = 0;
        String sql = "insert into "+CommondAttr.TBNAME_DEVICE+" ("+CommondAttr.DEVICE_MAC+"," +
                CommondAttr.DEVICE_IP +","+ CommondAttr.DEVICE_USER_MOBILE+") values (?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,device.getUserMac());
            preparedStatement.setString(2,device.getUserIP());
            preparedStatement.setString(3,device.getUserMobile());
            i = preparedStatement.executeUpdate();
            dbUtils.releaseResource(preparedStatement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
