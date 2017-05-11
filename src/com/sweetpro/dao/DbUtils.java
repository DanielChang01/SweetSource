package com.sweetpro.dao;

import com.sweetpro.entities.*;
import java.sql.*;

public class DbUtils {

    public Connection getConn(){
        String driver = CommondAttr.DB_DRIVER;
        String url = CommondAttr.DB_URL;
        String userName = CommondAttr.DB_USERNAME;
        String passWord = CommondAttr.DB_PASSWORD;
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


    public void releaseResource(PreparedStatement preparedStatement, Connection conn) throws SQLException {
        preparedStatement.close();
        conn.close();
    }

}
