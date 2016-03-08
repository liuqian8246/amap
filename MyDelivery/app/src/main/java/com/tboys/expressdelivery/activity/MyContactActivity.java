package com.tboys.expressdelivery.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.tboys.expressdelivery.entity.Courier;
import com.tboys.expressdelivery.adapter.MyContactAdapter;
import com.tboys.expressdelivery.R;

import java.util.ArrayList;
import java.util.List;

public class MyContactActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private List<Courier> couriers;

    private MyContactAdapter adapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contact);
        toolbar = (Toolbar) findViewById(R.id.toolbar_contact);
        listView = (ListView) findViewById(R.id.listView_contact);
        couriers = new ArrayList<>();
        couriers.add(new Courier("刘谦", "圆通快递","13566666666"));
        adapter = new MyContactAdapter(this, couriers);
        listView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.iconfont_fanhui));

        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_my_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.aciton_add_people) {
            startActivity(new Intent(MyContactActivity.this, AddContactActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
