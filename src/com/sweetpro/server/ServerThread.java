package com.sweetpro.server;

import com.sweetpro.dao.DbUtils;
import com.sweetpro.entities.CommondAttr;
import com.sweetpro.entities.DishEntity;
import com.sweetpro.entities.ShopEntity;
import com.sweetpro.entities.UsersEntity;

import java.io.*;
import java.net.Socket;


public class ServerThread extends Thread {

    Socket socket = null;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        String re_info = null;

        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;

            while((info = bufferedReader.readLine()) != null){
                re_info = getInfoHandled(info);
            }

            socket.shutdownInput();
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.write(re_info);
            printWriter.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (printWriter != null){
                    printWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedReader != null){
                    bufferedReader.close();
                }
                if (inputStreamReader != null){
                    inputStreamReader.close();
                }
                if (inputStream != null){
                    inputStream.close();
                }
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private String getInfoHandled(String info) {
        String[] strings = info.split(";");
        int handleAttr = Integer.parseInt(strings[0]);
        DbUtils dbUtils = new DbUtils();
        UsersEntity user = null;
        ShopEntity shop = null;
        ShopEntity re_shop = null;
        DishEntity dish = null;
        DishEntity re_dish = null;

        String str_back = null;
        int int_back = 0;

        /**
         * 依据Commend数字对接收到的命令进行区分
         */

        switch(handleAttr%10)
        {
            case 1: //将数据进行持久化操作，返回持久化list集合
                user = setUsersValue(info);
                break;
            case 2:
                shop = setShopsValue(info);
                break;
            case 3:
                dish = setDishesValue(info);
                break;
            default:
                break;
        }


        switch(handleAttr)
        {
            case 11://对持久化类进行插入数据库操作
                int_back = dbUtils.insertUsers(user);
                break;
            case 13:
                int_back = dbUtils.insertDish(dish);
                break;
            case 31:
                str_back = dbUtils.userLogin(user);
                break;
            case 23:
                int_back = dbUtils.updateDish(dish);
                break;
            case 22:
                int_back = dbUtils.updateShop(shop);
                break;
            case 32:
                re_shop = (ShopEntity) dbUtils.queryShop();
                break;
            case 33:
                re_dish = (DishEntity) dbUtils.queryDish(dish);
            default:
                break;
        }
        if (str_back != null){
            return CommondAttr.SIGN_LOGIN+str_back;
        } else if (int_back != 0){
            return CommondAttr.SIGN_COMMON+String.valueOf(int_back);
        } else if (re_dish != null){
            return getStrDish(re_dish);
        } else if (re_shop != null){
            return getStrShop(re_shop);
        } else {
            return null;
        }
    }

    /**
     * 对数据进行打包
     * @param re_shop
     * @return
     */
    private String getStrShop(ShopEntity re_shop) {
        StringBuilder sb = new StringBuilder();
        sb.append(CommondAttr.SIGN_SHOP);
        sb.append(re_shop.getShopName()+";");
        sb.append(re_shop.getShopAddr()+";");
        sb.append(re_shop.getShopPic()+";");
        sb.append(re_shop.getShopPoint()+";");

        return sb.toString();
    }

    private String getStrDish(DishEntity re_dish) {
        StringBuilder sb = new StringBuilder();
        sb.append(CommondAttr.SIGN_DISH);
        sb.append(re_dish.getDishShopID()+";");
        sb.append(re_dish.getDishName()+";");
        sb.append(re_dish.getDishCategory()+";");
        sb.append(re_dish.getDishDescribe()+";");
        sb.append(re_dish.getDishPic()+";");
        sb.append(re_dish.getDishPrePrice()+";");
        sb.append(re_dish.getDishCutPrice()+";");

        return sb.toString();
    }

    /**
     * 将来自客户端的数据进行持久化操作
     * @param info
     * @return
     */
    private DishEntity setDishesValue(String info) {
        String[] strs = info.split(";");
        DishEntity dish = new DishEntity();
        dish.setDishShopID(strs[1]);
        dish.setDishName(strs[2]);
        dish.setDishCategory(strs[3]);
        dish.setDishDescribe(strs[4]);
        dish.setDishPic(strs[5]);
        dish.setDishPrePrice(strs[6]);
        dish.setDishCutPrice(strs[7]);
        return dish;

    }

    private ShopEntity setShopsValue(String info) {
        String[] strs = info.split(";");
        ShopEntity shop = new ShopEntity();
        shop.setShopID(strs[1]);
        shop.setShopName(strs[2]);
        shop.setShopAddr(strs[3]);
        shop.setShopPic(strs[4]);
        shop.setShopPoint(strs[5]);
        return shop;
    }

    private UsersEntity setUsersValue(String info) {
        String[] strs = info.split(";");
        UsersEntity user = new UsersEntity();
        user.setUserName(strs[1]);
        user.setPassWord(strs[2]);
        user.setMobile(strs[3]);
        user.setShopID(strs[4]);
        return user;
    }
}
