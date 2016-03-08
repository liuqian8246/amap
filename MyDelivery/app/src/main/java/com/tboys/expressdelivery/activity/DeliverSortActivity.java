package com.tboys.expressdelivery.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tboys.expressdelivery.R;
import com.tboys.expressdelivery.entity.App;

public class DeliverSortActivity extends AppCompatActivity {

    Button buttonAdd;
    Button buttonUpdate;
    Button buttonShowdong;
    ImageView imageViewAddtime;
    ImageView imageViewUpdate;
    ImageView imageViewShoudong;
    Toolbar toolbar_delivery_sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_sort);

        toolbar_delivery_sort = (Toolbar) findViewById(R.id.toolbar_delivery_sort);
        setSupportActionBar(toolbar_delivery_sort);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.iconfont_fanhui));

        toolbar_delivery_sort.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String temp = intent.getStringExtra(App.SORT_KD);


        initView(temp);
    }

    private void initView(String temp) {

        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonUpdate = (Button) findViewById(R.id.button_update);
        buttonShowdong = (Button) findViewById(R.id.button_shoudong);
        imageViewAddtime = (ImageView) findViewById(R.id.imageView_add_time);
        imageViewUpdate = (ImageView) findViewById(R.id.imageView_update_time);
        imageViewShoudong = (ImageView) findViewById(R.id.imageView_shoudong);

        setData(temp.equals(buttonAdd.getText()) ? R.id.button_add : temp.equals(buttonUpdate.getText()) ? R.id.button_update : R.id.button_shoudong);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(R.id.button_add);
                sendBroadcast(buttonAdd.getText());
                finish();

            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(R.id.button_update);
                sendBroadcast(buttonUpdate.getText());
                finish();
            }
        });
        buttonShowdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(R.id.button_shoudong);
                sendBroadcast(buttonShowdong.getText());
                finish();
            }
        });

    }


    public void setData(int id) {
        switch(id) {
            case R.id.button_add:
                imageViewAddtime.setVisibility(View.VISIBLE);
                imageViewUpdate.setVisibility(View.INVISIBLE);
                imageViewShoudong.setVisibility(View.INVISIBLE);
                buttonAdd.setBackgroundResource(R.drawable.activity_addnum_button_press);
                buttonUpdate.setBackgroundResource(R.drawable.activity_addnum_button_normal);
                buttonShowdong.setBackgroundResource(R.drawable.activity_addnum_button_normal);
                break;
            case R.id.button_update:
                imageViewAddtime.setVisibility(View.INVISIBLE);
                imageViewShoudong.setVisibility(View.INVISIBLE);
                imageViewUpdate.setVisibility(View.VISIBLE);
                buttonShowdong.setBackgroundResource(R.drawable.activity_addnum_button_normal);
                buttonAdd.setBackgroundResource(R.drawable.activity_addnum_button_normal);
                buttonUpdate.setBackgroundResource(R.drawable.activity_addnum_button_press);
                break;
            case R.id.button_shoudong:
                imageViewShoudong.setVisibility(View.VISIBLE);
                imageViewUpdate.setVisibility(View.INVISIBLE);
                imageViewAddtime.setVisibility(View.INVISIBLE);
                buttonShowdong.setBackgroundResource(R.drawable.activity_addnum_button_press);
                buttonAdd.setBackgroundResource(R.drawable.activity_addnum_button_normal);
                buttonUpdate.setBackgroundResource(R.drawable.activity_addnum_button_normal);
                break;
        }
    }

    public void sendBroadcast(CharSequence name) {
        Intent intent = new Intent(App.ACTION_SORT_KD);
        intent.putExtra(App.SORT_TYPE, name);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
}
