package com.tboys.expressdelivery.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tboys.expressdelivery.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    public EditText editText_phone;
    public EditText editText_password;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        editText_phone = (EditText) view.findViewById(R.id.editText_phone);
        editText_password = (EditText) view.findViewById(R.id.editText_psd);

        Log.d("222", "2222");
        return view;
    }

}
