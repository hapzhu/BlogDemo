package cn.pigdreams.blogdemo;

import android.os.LocaleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Locale;

public class LocaleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locale_list);
        ListView lvData= findViewById(R.id.lv_data);
        LocaleListAdapter adapter = new LocaleListAdapter(this,R.layout.listitem_locale, Arrays.asList(Locale.getAvailableLocales()));
        lvData.setAdapter(adapter);
    }
}
