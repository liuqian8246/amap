package com.tboys.expressdelivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tboys.expressdelivery.R;
import com.tboys.expressdelivery.entity.Delivery;

import java.util.List;

/**
 * Created by Dell on 2016/2/24.
 */
public class DeliveryAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Delivery> deliveries;

    public DeliveryAdapter(Context context, List<Delivery> deliveries) {
        this.context = context;
        this.deliveries = deliveries;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return deliveries.size();
    }

    @Override
    public Delivery getItem(int position) {
        return deliveries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DeliveryViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.delivery_item, parent, false);
            holder = new DeliveryViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (DeliveryViewHolder) convertView.getTag();
        }

        holder.bindData(deliveries.get(position));
        return convertView;
    }

    static class DeliveryViewHolder{

        ImageView imageView;
        TextView textView_deliveryNum;
        TextView textView_deliveryCompany;
        TextView textView_deliveryTime;
        TextView textView_deliveryContext;


        public DeliveryViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.imageView_company);
            textView_deliveryNum = (TextView) view.findViewById(R.id.textView_delivery_dan);
            textView_deliveryCompany = (TextView) view.findViewById(R.id.textView_delivery_company_name);
            textView_deliveryTime = (TextView) view.findViewById(R.id.textView_delivery_time);
            textView_deliveryContext = (TextView) view.findViewById(R.id.textView_delivery_context);
        }


        public void bindData(Delivery delivery) {
//            imageView.setImageBitmap(delivery.getBitmap_company());
            textView_deliveryNum.setText(delivery.getDelivery_number());
            textView_deliveryCompany.setText(delivery.getDelivery_company());
            textView_deliveryTime.setText(delivery.getTime());
            textView_deliveryContext.setText(delivery.getDelivery_context());
        }
    }
}
