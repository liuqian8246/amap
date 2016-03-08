package com.tboys.expressdelivery.Utils;



import com.tboys.expressdelivery.entity.Delivery;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/3/4.
 */
public class DeliveryUtils {
    public void save(Delivery delivery) {
        long id = delivery.save();
        delivery.setId(id);
    }

    public void delete(Delivery delivery) {
        delivery.delete();
    }

    public ArrayList<Delivery> findAll() {
        ArrayList<Delivery> deliverys = (ArrayList<Delivery>) Delivery.listAll(Delivery.class);
        return deliverys;
    }

    public void update(Delivery delivery) {
        Delivery de = Delivery.findById(Delivery.class,delivery.getId());
        de.save();
    }
}
