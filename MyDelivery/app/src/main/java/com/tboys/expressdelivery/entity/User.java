package com.tboys.expressdelivery.entity;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by lenovo on 2016/3/4.
 */
@Table
public class User extends SugarRecord{
    private String account;
    private String password;
    private Long id;

    public User() {
    }

    public User(String account,String password) {
        this.account = account;
        this.password = password;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
