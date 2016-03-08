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

public class ShowTypeActivity extends AppCompatActivity {

    Button buttonShunxu;
    Button buttonDaoxu;
    ImageView imageViewDaoxu;
    ImageView imageViewShunxu;

    Toolbar toolbar_show_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_type);

        toolbar_show_type = (Toolbar) findViewById(R.id.toolbar_show_type);
        setSupportActionBar(toolbar_show_type);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.iconfont_fanhui));

        toolbar_show_type.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String temp = intent.getStringExtra(App.SHOW_TYPE);


        initView(temp);

    }

    private void initView(String temp) {
        buttonDaoxu = (Button) findViewById(R.id.button_daoxu);
        buttonShunxu = (Button) findViewById(R.id.button_shunxu);
        imageViewDaoxu = (ImageView) findViewById(R.id.imageView_daoxu);
        imageViewShunxu = (ImageView) findViewById(R.id.imageView_shun_xu);

        setData(temp.equals(buttonDaoxu.getText()));


        buttonDaoxu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(true);
                sendBroadcast(buttonDaoxu.getText());
                finish();
            }
        });
        buttonShunxu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(false);
                sendBroadcast(buttonShunxu.getText());
                finish();

            }
        });
    }

    public void setData(boolean isDaoXu) {
        if(isDaoXu) {
            imageViewDaoxu.setVisibility(View.VISIBLE);
            imageViewShunxu.setVisibility(View.INVISIBLE);
            buttonDaoxu.setBackgroundResource(R.drawable.activity_addnum_button_press);
            buttonShunxu.setBackgroundResource(R.drawable.activity_addnum_button_normal);
        } else {
            imageViewDaoxu.setVisibility(View.INVISIBLE);
            imageViewShunxu.setVisibility(View.VISIBLE);
            buttonDaoxu.setBackgroundResource(R.drawable.activity_addnum_button_normal);
            buttonShunxu.setBackgroundResource(R.drawable.activity_addnum_button_press);
        }
    }

    public void sendBroadcast(CharSequence name) {
        Intent intent = new Intent(App.ACTION_SHOW_TYPE);
        intent.putExtra(App.PAI_LIE, name);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
}
