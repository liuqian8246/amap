package com.tboys.expressdelivery.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tboys.expressdelivery.R;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private static final String PHONE = "phone";
    private static final String PWD = "pwd";
    EditText editText_phone;
    EditText editText_password;
    CheckBox checkBox;
    Button button_login;

    Button button_other_login_type;
    Spinner spinner_other_login_type;
    ArrayAdapter<String> adapter;
    String[] types = {"QQ登录", "微博登录"};

    int count = 0;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        editText_phone = (EditText) view.findViewById(R.id.editText_phone);
        editText_password = (EditText) view.findViewById(R.id.editText_psd);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        button_login = (Button) view.findViewById(R.id.button_login);
        button_other_login_type = (Button) view.findViewById(R.id.button_other_login_type);
        spinner_other_login_type = (Spinner) view.findViewById(R.id.spinner_other_login_type);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                types);
        spinner_other_login_type.setAdapter(adapter);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    editText_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    editText_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = editText_phone.getText().toString();
                String pwd = editText_password.getText().toString();
                if (!phoneNum.isEmpty() && !pwd.isEmpty()) {

                    String url = "http://192.168.43.138:8080/Delivery/LoginServlet";
                    Volley.newRequestQueue(getActivity()).add(
                            new StringRequest(
                                    Request.Method.POST,
                                    url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String s) {
                                            if (s.equals("200")) {
                                                Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();

                                            } else if (s.equals("403")) {
                                                Toast.makeText(getActivity(), "密码错误", Toast.LENGTH_SHORT).show();
                                            } else if (s.equals("405")) {
                                                Toast.makeText(getActivity(), "该手机未注册", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(getActivity(), "服务器故障", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                    Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put(PHONE, editText_phone.getText().toString());
                                    map.put(PWD, editText_password.getText().toString());
                                    return map;
                                }
                            });
                } else {
                    Toast.makeText(getActivity(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_other_login_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count % 2 == 0) {
                    spinner_other_login_type.setVisibility(View.INVISIBLE);
                } else {

                    spinner_other_login_type.setVisibility(View.VISIBLE);
                }
            }
        });

        spinner_other_login_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = types[position];
                switch (type) {
                    case "QQ登录":
                        break;
                    case "微博登录":
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
}
