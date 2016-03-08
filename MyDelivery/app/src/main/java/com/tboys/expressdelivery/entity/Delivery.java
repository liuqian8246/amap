package com.tboys.expressdelivery.entity;

import android.graphics.Bitmap;

import com.orm.SugarRecord;

/**
 * Created by Dell on 2016/2/24.
 */
public class Delivery extends SugarRecord{

    private String delivery_number;
    private String delivery_company;
    private Bitmap bitmap_company;
    private String time;
    private String delivery_context;

    public Delivery() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDelivery_number() {
        return delivery_number;
    }

    public void setDelivery_number(String delivery_number) {
        this.delivery_number = delivery_number;
    }

    public Bitmap getBitmap_company() {
        return bitmap_company;
    }

    public void setBitmap_company(Bitmap bitmap_company) {
        this.bitmap_company = bitmap_company;
    }

    public String getDelivery_company() {
        return delivery_company;
    }

    public void setDelivery_company(String delivery_company) {
        this.delivery_company = delivery_company;
    }

    public String getDelivery_context() {
        return delivery_context;
    }

    public void setDelivery_context(String delivery_context) {
        this.delivery_context = delivery_context;
    }

    public Delivery(String delivery_number, Bitmap bitmap_company, String delivery_company, String time, String delivery_context) {
        this.delivery_number = delivery_number;
        this.bitmap_company = bitmap_company;
        this.delivery_company = delivery_company;
        this.time = time;
        this.delivery_context = delivery_context;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "delivery_number='" + delivery_number + '\'' +
                ", delivery_company='" + delivery_company + '\'' +
                ", bitmap_company=" + bitmap_company +
                ", time='" + time + '\'' +
                ", delivery_context='" + delivery_context + '\'' +
                '}';
    }
}
