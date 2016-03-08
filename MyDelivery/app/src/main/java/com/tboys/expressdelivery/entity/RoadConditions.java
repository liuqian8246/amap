package com.tboys.expressdelivery.entity;

import java.io.Serializable;

/**
 * Created by Dell on 2016/3/4.
 */
public class RoadConditions implements Serializable{

    String context;

    String time;

    public RoadConditions() {
    }

    public RoadConditions(String context, String time) {
        this.context = context;
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RoadConditions{" +
                "context='" + context + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
