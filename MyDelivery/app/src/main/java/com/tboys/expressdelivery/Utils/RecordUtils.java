package com.tboys.expressdelivery.Utils;


import com.tboys.expressdelivery.entity.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/3/4.
 */

public class RecordUtils {
    public void save(Record record) {
        long id = record.save();
        record.setId(id);
    }

    public void delete(Record record) {
        record.delete();
    }

    public ArrayList<Record> findAll() {
        ArrayList<Record> records = (ArrayList<Record>) Record.listAll(Record.class);
        return records;
    }

    public void update(Record record) {
        Record re = Record.findById(Record.class,record.getId());
        re.save();
    }
}
