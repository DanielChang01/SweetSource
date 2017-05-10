package com.sweetpro.entities;

/**
 * Created by danielchang on 2017/5/9.
 */
public class DiscountEntity {
    public void DiscountEntity(){}
    private String DisUserMobile;
    private String DisShopID;
    private String DisConsumeTimes;
    private String DissoncumeSUM;

    public String getDisUserMobile() {
        return DisUserMobile;
    }

    public void setDisUserMobile(String disUserMobile) {
        DisUserMobile = disUserMobile;
    }

    public String getDisShopID() {
        return DisShopID;
    }

    public void setDisShopID(String disShopID) {
        DisShopID = disShopID;
    }

    public String getDisConsumeTimes() {
        return DisConsumeTimes;
    }

    public void setDisConsumeTimes(String disConsumeTimes) {
        DisConsumeTimes = disConsumeTimes;
    }

    public String getDissoncumeSUM() {
        return DissoncumeSUM;
    }

    public void setDissoncumeSUM(String dissoncumeSUM) {
        DissoncumeSUM = dissoncumeSUM;
    }
}
