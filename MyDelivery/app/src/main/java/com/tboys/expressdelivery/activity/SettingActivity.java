package com.tboys.expressdelivery.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.tboys.expressdelivery.R;
import com.tboys.expressdelivery.adapter.SettingAdapter;
import com.tboys.expressdelivery.entity.App;

public class SettingActivity extends AppCompatActivity {

    private ListView listView;
    private SettingAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        listView = (ListView) findViewById(R.id.listView_setting);
        toolbar = (Toolbar) findViewById(R.id.toolbar_setting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.iconfont_fanhui));
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new SettingAdapter(this,null,null,null);

        listView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("sss", "onStart");
        IntentFilter filter = new IntentFilter();
        filter.addAction(App.ACTION_SHOW_TYPE);
        filter.addAction(App.ACTION_SORT_KD);
        filter.addAction(App.ACTION_ADD_NUM);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("sss", "onStop");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        String temp = null;
        String temp2 = null;
        String temp3 = null;
        @Override
        public void onReceive(Context context, Intent intent) {
            switch(intent.getAction()) {
                case App.ACTION_ADD_NUM:
                    temp2 = intent.getStringExtra(App.TYPE);
                    break;
                case App.ACTION_SHOW_TYPE:
                    temp = intent.getStringExtra(App.PAI_LIE);
                    break;
                case App.ACTION_SORT_KD:
                    temp3 = intent.getStringExtra(App.SORT_TYPE);
                    break;
            }

            adapter = new SettingAdapter(SettingActivity.this,temp,temp2,temp3);
            listView.setAdapter(adapter);
            Log.d("sss","listView重新设置");
        }
    };
}
