package com.tboys.expressdelivery.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tboys.expressdelivery.R;
import com.tboys.expressdelivery.entity.App;
import com.tboys.expressdelivery.entity.RoadConditions;
import com.tboys.expressdelivery.entity.TimeUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QueryDeliveryActivity extends AppCompatActivity {



    private final static int SCANNIN_GREQUEST_CODE = 3;

    private Toolbar toolbar;

    private Button button;
    private EditText editText_delivery_dan;
    private TextView textView_delivery_company_name;

    private ArrayList<RoadConditions> roads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aaa","Query");
        setContentView(R.layout.activity_query_delivery);
        toolbar = (Toolbar) findViewById(R.id.toolbar_query);
        setSupportActionBar(toolbar);
        button = (Button) findViewById(R.id.button);
        editText_delivery_dan = (EditText) findViewById(R.id.knum);
        textView_delivery_company_name = (TextView) findViewById(R.id.textView_com);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.iconfont_fanhui));
        getSupportActionBar().setTitle("查询快递");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String delivery_dan = editText_delivery_dan.getText().toString();
                final String delivery_company_name = textView_delivery_company_name.getText().toString();
                if (!delivery_dan.isEmpty()) {

                    String url = "http://route.showapi.com/64-19";
                    Volley.newRequestQueue(QueryDeliveryActivity.this).add(new StringRequest(Request.Method.POST,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String s) {
                                    JSONObject object;
                                    RoadConditions road;
                                    roads = new ArrayList<>();
                                    try {
                                        object = new JSONObject(s);
                                        JSONArray array = object.getJSONArray("data");
                                        for (int i = 0; i < array.length(); i++) {

                                            String context = array.getJSONObject(i).getJSONObject("context").toString();
                                            String time = array.getJSONObject(i).getJSONObject("time").toString();
                                            road = new RoadConditions(context, time);
                                            roads.add(road);
                                        }
//                                        Intent intent = new Intent(QueryDeliveryActivity.this, MainActivity.class);
//                                        Bundle bundle = new Bundle();
//                                        bundle.putSerializable(App.ROADS, roads);
//                                        intent.putExtras(bundle);
//                                        intent.putExtra(App.DeLIVERY_DAN, delivery_dan);
//                                        intent.putExtra(App.COMPANY_NAME, delivery_company_name);
//                                        QueryDeliveryActivity.this.setResult(RESULT_OK, intent);
//                                        startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Toast.makeText(QueryDeliveryActivity.this, "服务器失败", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put(App.SHOWAPI_ID, App.APP_ID);
                            map.put(App.KEY_WORD, App.APP_KEY);
                            map.put(App.SHOWAPI_TIME, TimeUtil.DateFormat(new Date()));
                            map.put(App.COM, delivery_company_name);
                            map.put(App.NU, delivery_dan);
                            return map;
                        }
                    });
                } else {


                }
            }
        });
    }




    //扫描条形码


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    editText_delivery_dan.setText(bundle.getString("result"));
                }
        }
    }


    public void SaoMiao(View view) {
            Intent intent = new Intent(getApplicationContext(),SaoMiaoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent,SCANNIN_GREQUEST_CODE);
    }
}
