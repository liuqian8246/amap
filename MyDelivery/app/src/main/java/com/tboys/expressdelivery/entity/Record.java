package com.tboys.expressdelivery.entity;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by lenovo on 2016/3/4.
 */
@Table
public class Record extends SugarRecord{
    private String delivery_start;
    private String delivery_end;
    private String delivery_time;
    private boolean order_state;
    private Long id;

    public Record() {
    }

    public Record(String delivery_start, String delivery_end, String delivery_time, boolean order_state) {
        this.delivery_start = delivery_start;
        this.delivery_end = delivery_end;
        this.delivery_time = delivery_time;
        this.order_state = order_state;
    }
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return delivery_start+"  "+delivery_time+"  "+delivery_end+"  "+order_state+ "" + id;
    }
}
