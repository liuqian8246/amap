package com.tboys.expressdelivery.entity;

/**
 * Created by Dell on 2016/2/26.
 */
public class Courier {

    private String name;
    private String DeliveryCompany;
    private String phone;

    public Courier() {
    }

    public Courier(String deliveryCompany, String name, String phone) {
        DeliveryCompany = deliveryCompany;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryCompany() {
        return DeliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        DeliveryCompany = deliveryCompany;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "name='" + name + '\'' +
                ", DeliveryCompany='" + DeliveryCompany + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
