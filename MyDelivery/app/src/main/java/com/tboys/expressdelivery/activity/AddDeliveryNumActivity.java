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

public class AddDeliveryNumActivity extends AppCompatActivity {

    Button buttonSave;
    Button buttonQuery;
    ImageView imageViewSave;
    ImageView imageViewQuery;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery_num);
        toolbar = (Toolbar) findViewById(R.id.toolbar_add_delivery_num);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.iconfont_fanhui));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String temp = intent.getStringExtra(App.ADD_NUM);


        initView(temp);
    }

    private void initView(String temp) {

        buttonSave = (Button) findViewById(R.id.button_save);
        buttonQuery = (Button) findViewById(R.id.button_query);
        imageViewSave = (ImageView) findViewById(R.id.imageView_save);
        imageViewQuery = (ImageView) findViewById(R.id.imageView_query);



        setData(temp.equals(buttonSave.getText()));



        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(true);
                sendBroadcast(buttonSave.getText());
                finish();
            }
        });
        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(false);
                sendBroadcast(buttonQuery.getText());
                finish();
            }
        });

    }

    public void setData(boolean isSave) {
        if(isSave) {
            imageViewQuery.setVisibility(View.INVISIBLE);
            imageViewSave.setVisibility(View.VISIBLE);
            buttonSave.setBackgroundResource(R.drawable.activity_addnum_button_press);
            buttonQuery.setBackgroundResource(R.drawable.activity_addnum_button_normal);
        } else {
            imageViewSave.setVisibility(View.INVISIBLE);
            imageViewQuery.setVisibility(View.VISIBLE);
            buttonSave.setBackgroundResource(R.drawable.activity_addnum_button_normal);
            buttonQuery.setBackgroundResource(R.drawable.activity_addnum_button_press);
        }
    }

    public void sendBroadcast(CharSequence name) {
        Intent intent = new Intent(App.ACTION_ADD_NUM);
        intent.putExtra(App.TYPE, name);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
}
