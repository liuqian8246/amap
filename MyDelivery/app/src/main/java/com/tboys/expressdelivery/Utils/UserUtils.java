package com.tboys.expressdelivery.Utils;

import com.tboys.expressdelivery.entity.Record;
import com.tboys.expressdelivery.entity.User;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/3/4.
 */
public class UserUtils {
    public void save(User user) {
        long id = user.save();
        user.setId(id);
    }

    public void delete(User user) {
        user.delete();
    }

    public ArrayList<User> findAll() {
        ArrayList<User> users = (ArrayList<User>) User.listAll(User.class);
        return users;
    }

    public void update(User user) {
        User us = User.findById(User.class,user.getId());
        us.save();
    }
}
