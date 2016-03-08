package com.tboys.expressdelivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tboys.expressdelivery.entity.App;
import com.tboys.expressdelivery.entity.Delivery;
import com.tboys.expressdelivery.adapter.DeliveryAdapter;
import com.tboys.expressdelivery.R;
import com.tboys.expressdelivery.entity.RoadConditions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_QUERY = 1;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    private NavigationView navigationView;

    private ListView listView;
    private DeliveryAdapter adapter;
    private List<Delivery> deliveries;

    private SearchView searchView;
    private TextView textView_query_delivery;

    private ArrayList<RoadConditions> roads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("sss", "Main");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        searchView = (SearchView) findViewById(R.id.searchView);
        textView_query_delivery = (TextView) findViewById(R.id.textView_query_delivery);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        listView = (ListView) findViewById(R.id.listView_delivery);
        deliveries = new ArrayList<>();

        adapter = new DeliveryAdapter(this, deliveries);
        listView.setAdapter(adapter);
        setSupportActionBar(toolbar);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_query_delivery.setText("");
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                textView_query_delivery.setText("查询快递");
                return false;
            }
        });

        /**
         * 创建把手 匿名内部类
         */
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        //同步,让把手替代Home的图标
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);
        navigationView.setCheckedItem(R.id.nav_delivery_record);

        navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

//        navigationView.addHeaderView(new View());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.isChecked()) {
                    item.setChecked(true);
                }
                switch (item.getItemId()) {
                    case R.id.nav_delivery_record:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_delivery_query:
                        startActivityForResult(new Intent(MainActivity.this, QueryDeliveryActivity.class),REQUEST_QUERY);
                        break;
                    case R.id.nav_delivery_send:
                        startActivity(new Intent(MainActivity.this, SendDeliveryActivity.class));
                        break;
                    case R.id.nav_delivery_contact:
                        startActivity(new Intent(MainActivity.this, MyContactActivity.class));
                        break;
                    case R.id.nav_delivery_setting:
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        break;
                    case R.id.nav_delivery_law:

                        break;
                    default:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "推荐给 ");
                        intent.putExtra(Intent.EXTRA_TEXT, "I would like to share this with you...");
                        startActivity(Intent.createChooser(intent, getTitle()));
                        break;
                }
                return true;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_QUERY && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            roads = (ArrayList<RoadConditions>) bundle.get(App.ROADS);
            RoadConditions road = roads.get(roads.size()-1);
            Delivery delivery = new Delivery();
            String delivery_num = data.getStringExtra(App.DeLIVERY_DAN);
            String delvery_company_name = data.getStringExtra(App.COMPANY_NAME);
            delivery.setDelivery_company(delvery_company_name);
            delivery.setDelivery_number(delivery_num);
            delivery.setDelivery_context(road.getContext());
            delivery.setTime(road.getTime());

            deliveries.add(delivery);
            adapter = new DeliveryAdapter(MainActivity.this, deliveries);
            listView.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
        }
    }
}
