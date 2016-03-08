package com.tboys.expressdelivery.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.tboys.expressdelivery.fragment.LoginFragment;
import com.tboys.expressdelivery.fragment.RegisterFragment;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String PHONE = "phone";
    private static final String PWD = "pwd";
    private Toolbar toolbar;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    FragmentManager fm;
    TextView textView_toolbar_title;
    boolean isFlag = false;
    Button button_login;
    Button button_register;

    public String phoneNum;
    public String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("OnCreate", "onCreate");

        toolbar = (Toolbar) findViewById(R.id.toolBar_login);
        textView_toolbar_title = (TextView) findViewById(R.id.textView_title);
        button_login = (Button) findViewById(R.id.button_login);
        button_register = (Button) findViewById(R.id.button_register);

        fm = getSupportFragmentManager();
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.iconfont_fanhui));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //首次显示界面
        fm.beginTransaction().replace(R.id.frameLayout_login, loginFragment).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        button_login.setBackgroundResource(R.drawable.login_button_press);
        button_register.setBackgroundResource(R.drawable.register_button_normal);
        button_login.setTextColor(getResources().getColor(android.R.color.white));
        button_register.setTextColor(getResources().getColor(R.color.colorBlue2));
    }

    public void changeTab(View view) {

        switch (view.getId()) {
            case R.id.button_login:
                fm.beginTransaction().replace(R.id.frameLayout_login, loginFragment).commit();
                textView_toolbar_title.setText("登录快捷快递");
                toolbar.getMenu().removeItem(R.menu.menu_acitivity_register);
                button_login.setBackgroundResource(R.drawable.login_button_press);
                button_register.setBackgroundResource(R.drawable.register_button_normal);
                button_login.setTextColor(getResources().getColor(android.R.color.white));
                button_register.setTextColor(getResources().getColor(R.color.colorBlue2));
                isFlag = false;
                invalidateOptionsMenu();
                break;
            case R.id.button_register:
                Log.d("sss", "222");
                textView_toolbar_title.setText("注册账号");
                fm.beginTransaction().replace(R.id.frameLayout_login, registerFragment).commit();
                isFlag = true;
                button_register.setBackgroundResource(R.drawable.register_button_press);
                button_login.setBackgroundResource(R.drawable.login_button_normal);
                button_login.setTextColor(getResources().getColor(R.color.colorBlue2));
                button_register.setTextColor(getResources().getColor(android.R.color.white));
                invalidateOptionsMenu();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isFlag) {
            getMenuInflater().inflate(R.menu.menu_acitivity_register, menu);
            Log.d("menu", "222");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_next) {

            phoneNum = registerFragment.editText_phone.getText().toString();
            password = registerFragment.editText_password.getText().toString();

            if (!phoneNum.isEmpty() && !password.isEmpty()) {

                String url = "http://192.168.43.138:8080/Delivery/DoRegisterServlet";
                Volley.newRequestQueue(this).add(
                        new StringRequest(Request.Method.POST,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String s) {
                                        if (s.equals("200")) {
                                            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    registerFragment.editText_phone.setText("");
                                                    registerFragment.editText_password.setText("");
                                                }
                                            });
                                        } else {
                                            Toast.makeText(LoginActivity.this, "该手机号已被注册", Toast.LENGTH_SHORT).show();
                                            registerFragment.editText_phone.setText("");
                                            registerFragment.editText_password.setText("");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(LoginActivity.this, "注册失败,该手机号已被注册", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> map = new HashMap<>();

                                if (!phoneNum.isEmpty() && !password.isEmpty()) {
                                    map.put(PHONE, phoneNum);
                                    map.put(PWD, password);
                                } else {
                                    Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                                }
                                return map;
                            }
                        });
            } else {
                Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
