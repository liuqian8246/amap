package com.tboys.expressdelivery.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tboys.expressdelivery.activity.DeliverSortActivity;
import com.tboys.expressdelivery.R;
import com.tboys.expressdelivery.activity.ShowTypeActivity;
import com.tboys.expressdelivery.activity.AboutUsActivity;
import com.tboys.expressdelivery.activity.AddDeliveryNumActivity;
import com.tboys.expressdelivery.entity.App;

/**
 * Created by Dell on 2016/3/1.
 */
public class SettingAdapter extends BaseAdapter{

    Context context;
    LayoutInflater inflater;
    String string_show_type;
    String string_add_num;
    String string_kd_sort;
    public SettingAdapter(Context context,String str,String str2,String str3) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.string_show_type = str;
        this.string_add_num = str2;
        this.string_kd_sort = str3;

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.setting_list_item,parent,false);
            holder = new MyHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        holder.setData(string_show_type,string_add_num,string_kd_sort);
        return convertView;
    }

    public class MyHolder{
        LinearLayout showType;
        TextView textView_showType;
        LinearLayout linearLayout_sort_kd;
        TextView textView_kd_showType;
        LinearLayout addNum;
        TextView textView_save;
        LinearLayout update;
        TextView textView_version;
        LinearLayout sound;
        TextView textView_state;
        LinearLayout clear_cache;
        TextView textView_image_cache;
        LinearLayout account_change;
        TextView textView_add_account;
        LinearLayout about_us;
        String open = "打开";
        String stop = "关闭";

        public MyHolder(View v) {
            Log.d("sss", String.valueOf(string_show_type == null));
            showType = (LinearLayout) v.findViewById(R.id.showType);
            linearLayout_sort_kd = (LinearLayout) v.findViewById(R.id.linearLayout_sort_kd);
            addNum = (LinearLayout) v.findViewById(R.id.addNum);
            update = (LinearLayout) v.findViewById(R.id.update);
            sound = (LinearLayout) v.findViewById(R.id.sound);
            clear_cache = (LinearLayout) v.findViewById(R.id.clear_cache);
            account_change = (LinearLayout) v.findViewById(R.id.account_change);
            about_us = (LinearLayout) v.findViewById(R.id.about_us);
            textView_add_account = (TextView) v.findViewById(R.id.textView_add_account);
            textView_image_cache = (TextView) v.findViewById(R.id.textView_image_cache);
            textView_state = (TextView) v.findViewById(R.id.textView_state);
            textView_version = (TextView) v.findViewById(R.id.textView_version);
            textView_save = (TextView) v.findViewById(R.id.textView_save);
            textView_kd_showType = (TextView) v.findViewById(R.id.textView_kd_showType);
            textView_showType = (TextView) v.findViewById(R.id.textView_showType);

            showType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShowTypeActivity.class);
                    intent.putExtra(App.SHOW_TYPE,textView_showType.getText());
                    context.startActivity(intent);
                }
            });
            addNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddDeliveryNumActivity.class);
                    intent.putExtra(App.ADD_NUM,textView_save.getText());
                    context.startActivity(intent);
                }
            });
            linearLayout_sort_kd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DeliverSortActivity.class);
                    intent.putExtra(App.SORT_KD,textView_kd_showType.getText());
                    context.startActivity(intent);
                }
            });
            about_us.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AboutUsActivity.class);
                    context.startActivity(intent);
                }
            });

            sound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temp = (String) textView_state.getText();
                    if(temp.equals(open)) {
                        textView_state.setText(stop);
                    } else {
                        textView_state.setText(open);
                    }
                }
            });

            clear_cache.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView_image_cache.setText("0MB");
                }
            });

            account_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AboutUsActivity.class);
                    context.startActivity(intent);
                }
            });

            about_us.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AboutUsActivity.class);
                    context.startActivity(intent);
                }
            });

        }

        public void setData(String string_show_type, String string_add_num, String string_kd_sort) {
            if(string_show_type != null) {
                textView_showType.setText(string_show_type);
            }
            if(string_add_num != null) {
                textView_save.setText(string_add_num);
            }
            if(string_kd_sort != null) {
                textView_kd_showType.setText(string_kd_sort);
            }
        }

    }
}
