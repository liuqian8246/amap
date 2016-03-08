package com.tboys.expressdelivery.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tboys.expressdelivery.R;

import java.util.ArrayList;

/**
 * Created by Dell on 2016/2/26.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> companyList;
    public boolean show;

    public ListViewAdapter(Context context,ArrayList<String> companyList,boolean show) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.companyList = companyList;
        this.show = show;
    }

    @Override
    public int getCount() {
        return companyList.size();
    }

    @Override
    public String getItem(int position) {
        return companyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        MyHolder holder;
        if(v == null) {
            v = inflater.inflate(R.layout.list_item,parent,false);
            holder = new MyHolder(v,show);
            v.setTag(holder);
        } else {
            holder = (MyHolder) v.getTag();
        }
        String string = companyList.get(position);
        holder.bindData(string,position);
        return v;
    }

    public static class MyHolder{
        ImageView imageViewIcon;
        TextView textViewName;
        TextView textViewType;
        TextView textViewRound;
        TextView textViewt;
        TextView textViewp;
        TextView textView_time;
        TextView textView_price;
        View layout;

        public MyHolder(View v,boolean show) {
            textViewName = (TextView) v.findViewById(R.id.textView_name);
            textViewType = (TextView) v.findViewById(R.id.textView_type);
            textViewRound = (TextView) v.findViewById(R.id.textView_round);
            textViewt = (TextView) v.findViewById(R.id.textViewt);
            textViewp = (TextView) v.findViewById(R.id.textViewp);
            textView_time = (TextView) v.findViewById(R.id.textView_time);
            textView_price = (TextView) v.findViewById(R.id.textView_price);
            imageViewIcon = (ImageView) v.findViewById(R.id.imageView_icon);
            if(show) {
                textView_price.setVisibility(View.VISIBLE);
                textViewt.setVisibility(View.VISIBLE);
                textViewp.setVisibility(View.VISIBLE);
                textView_time.setVisibility(View.VISIBLE);
            }

            layout = v.findViewById(R.id.relative);
        }

        public void bindData(String string,int position) {
            if(position % 2 == 0) {
                layout.setBackgroundColor(Color.argb(255, 230, 230, 230));
            }else {
                layout.setBackgroundColor(Color.WHITE);
            }

        }
    }
}
