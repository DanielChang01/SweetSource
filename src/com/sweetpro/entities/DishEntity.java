package com.sweetpro.entities;


public class DishEntity {
    public void DishEntity(){}

    private String dishID;
    private String shopID;
    private String dishName;
    private String dishDescribe;
    private String dishCategory;
    private String dishPrePrice;
    private String dishCutPrice;
    private String dishPic;

    public String getDishID() {
        return dishID;
    }

    public void setDishID(String dishID) {
        this.dishID = dishID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishDescribe() {
        return dishDescribe;
    }

    public void setDishDescribe(String dishDescribe) {
        this.dishDescribe = dishDescribe;
    }

    public String getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(String dishCategory) {
        this.dishCategory = dishCategory;
    }

    public String getDishPrePrice() {
        return dishPrePrice;
    }

    public void setDishPrePrice(String dishPrePrice) {
        this.dishPrePrice = dishPrePrice;
    }

    public String getDishCutPrice() {
        return dishCutPrice;
    }

    public void setDishCutPrice(String dishCutPrice) {
        this.dishCutPrice = dishCutPrice;
    }

    public String getDishPic() {
        return dishPic;
    }

    public void setDishPic(String dishPic) {
        this.dishPic = dishPic;
    }

}
