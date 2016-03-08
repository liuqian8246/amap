package com.tboys.expressdelivery.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tboys.expressdelivery.R;

public class AboutUsActivity extends AppCompatActivity {

    Toolbar toolbar_aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar_aboutUs = (Toolbar) findViewById(R.id.toolbar_aboutUs);
        setSupportActionBar(toolbar_aboutUs);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.iconfont_fanhui));
        getSupportActionBar().setTitle("");
        toolbar_aboutUs.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_about_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_agree) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "推荐给 ");
            intent.putExtra(Intent.EXTRA_TEXT, "I would like to share this with you...");
            startActivity(Intent.createChooser(intent, getTitle()));
        }
        return super.onOptionsItemSelected(item);
    }
}
