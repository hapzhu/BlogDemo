package cn.pigdreams.blogdemo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Locale;
import java.util.Objects;

public class SayingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saying);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(R.string.app_name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.color_action_bar));
        }
        Button btnReload = findViewById(R.id.btn_reload);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources resources = SayingActivity.this.getResources();
                Locale locale =new  Locale.Builder().setLanguage("fr").setRegion("FR").build();
                Configuration configuration = resources.getConfiguration();
                configuration.setLocale(locale);
                resources.updateConfiguration(configuration,resources.getDisplayMetrics());
                Intent intent = new Intent(SayingActivity.this, LocaleDemoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
            Resources resources = newBase.getResources();
            Locale locale =new  Locale.Builder().setLanguage("fr").setRegion("FR").build();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(locale);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            super.attachBaseContext(newBase.createConfigurationContext(configuration));
            return;
        }else {
            resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        }
       super.attachBaseContext(newBase);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:////主键id 必须这样写
                onBackPressed();//按返回图标直接回退上个界面
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
