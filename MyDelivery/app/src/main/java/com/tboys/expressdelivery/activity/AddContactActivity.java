package com.tboys.expressdelivery.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.tboys.expressdelivery.R;

public class AddContactActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button_import_mail_list;
    private Button button_call_records_into;
    private Button button_byHand_into;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        toolbar = (Toolbar) findViewById(R.id.toolbar_add_contact);
        button_import_mail_list = (Button) findViewById(R.id.button_import_mail_list);
        button_call_records_into = (Button) findViewById(R.id.button_call_records_into);
        button_byHand_into = (Button) findViewById(R.id.button_byHand_into);
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

        button_import_mail_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_call_records_into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_byHand_into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
