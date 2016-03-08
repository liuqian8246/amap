package com.tboys.expressdelivery.activity;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.tboys.expressdelivery.fragment.NearByFragment;
import com.tboys.expressdelivery.fragment.OrderFragment;
import com.tboys.expressdelivery.R;

import java.util.ArrayList;

public class SendDeliveryActivity extends AppCompatActivity
        implements NearByFragment.Callback {

    private Button buttonNear;
    private Button buttonOrder;
    private NearByFragment fragmentNear;
    private OrderFragment fragmentOrder;
    private Toolbar toolbar;
    boolean price = false;
    boolean time = false;

    private ArrayList<String> list = new ArrayList<>();
    private android.support.v4.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_delivery);

        Log.d("sss","send");

        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");

        initView();

    }

    private void initView() {
        buttonNear = (Button) findViewById(R.id.button_near);
        buttonOrder = (Button) findViewById(R.id.button_order);
        fragmentManager = getSupportFragmentManager();
        fragmentNear = new NearByFragment();
        fragmentNear.setCallback(this);
        fragmentOrder = new OrderFragment();
        toolbar = (Toolbar) findViewById(R.id.send_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.iconfont_fanhui));

        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        fragmentManager.beginTransaction().replace(R.id.framelayout_frag,fragmentNear).commit();
        buttonNear.setBackgroundResource(R.drawable.send_lbutton_press);
        buttonNear.setTextColor(getResources().getColor(R.color.colorPrimary));
        buttonOrder.setBackgroundResource(R.drawable.send_rbutton_normal);
        buttonOrder.setTextColor(getResources().getColor(android.R.color.white));
    }

    public void tabChange(View view) {
        switch(view.getId()) {
            case R.id.button_near:
                fragmentManager.beginTransaction().replace(R.id.framelayout_frag,fragmentNear).commit();
                buttonNear.setBackgroundResource(R.drawable.send_lbutton_press);
                buttonNear.setTextColor(getResources().getColor(R.color.colorPrimary));
                buttonOrder.setBackgroundResource(R.drawable.send_rbutton_normal);
                buttonOrder.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case R.id.button_order:
                fragmentManager.beginTransaction().replace(R.id.framelayout_frag,fragmentOrder).commit();
                buttonNear.setBackgroundResource(R.drawable.send_lbutton_normal);
                buttonNear.setTextColor(getResources().getColor(android.R.color.white));
                buttonOrder.setBackgroundResource(R.drawable.send_rbutton_press);
                buttonOrder.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //可能要用到异步
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View v = LayoutInflater.from(this).inflate(R.layout.sort_menu,null);
        Button button = (Button) v.findViewById(R.id.buttonfrim);
        final RadioButton radioButtonP= (RadioButton) v.findViewById(R.id.radioButton_people);
        final RadioButton radioButtonPrice= (RadioButton) v.findViewById(R.id.radioButton_price);
        final RadioButton radioButtonT= (RadioButton) v.findViewById(R.id.radioButton_time);
        if(item.getItemId() == R.id.sort_menu) {
            final AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("排序")
                    .setView(v)
                    .show();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ssss", "22223");
                    if(radioButtonP.isChecked()) {

                        alertDialog.cancel();
                    }
                    if(radioButtonPrice.isChecked()) {

                        //list 排序
                        //list 显示时间 金钱
                        //对话框取消
                        Log.d("sss", "2222");
                        price = true;
                        time = false;
                        alertDialog.cancel();


                    }
                    if(radioButtonT.isChecked()) {

                        time = true;
                        price = false;
                        alertDialog.cancel();

                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public ArrayList<String> setData() {
        return list;
    }

    @Override
    public boolean getPrice() {
        return price;
    }

    @Override
    public boolean getTime() {
        return time;
    }
}
