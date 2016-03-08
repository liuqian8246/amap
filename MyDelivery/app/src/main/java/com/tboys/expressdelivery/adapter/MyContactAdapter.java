package com.tboys.expressdelivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tboys.expressdelivery.R;
import com.tboys.expressdelivery.entity.Courier;

import java.util.List;

/**
 * Created by Dell on 2016/2/26.
 */
public class MyContactAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    private List<Courier> couriers;

    public MyContactAdapter(Context context, List<Courier> couriers) {
        this.context = context;
        this.couriers = couriers;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return couriers.size();
    }

    @Override
    public Courier getItem(int position) {
        return couriers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_contact_item, parent, false);
            holder = new ContactViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ContactViewHolder) convertView.getTag();
        }
        holder.bindData(couriers.get(position));

        return convertView;
    }

    static class ContactViewHolder {

        ImageView imageView;
        TextView textView_contact_name;
        TextView textView_contact_company;
        TextView textView_contact_phone;

        public ContactViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.imageView_company);
            textView_contact_name = (TextView) view.findViewById(R.id.textView_contact_name);
            textView_contact_company = (TextView) view.findViewById(R.id.textView_company_name);
            textView_contact_phone = (TextView) view.findViewById(R.id.textView_contact_phone);
        }

        public void bindData(Courier courier) {
//            imageView
            textView_contact_name.setText(courier.getName());
            textView_contact_company.setText(courier.getDeliveryCompany());
            textView_contact_phone.setText(courier.getPhone());
        }
    }
}
