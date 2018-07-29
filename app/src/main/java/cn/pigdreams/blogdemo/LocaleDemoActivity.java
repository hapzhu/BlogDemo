package cn.pigdreams.blogdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LocaleDemoActivity extends AppCompatActivity {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LocaleDemoActivity.this);
                builder.setTitle(R.string.dialog_title)
                        .setSingleChoiceItems(R.array.array_lang, SPUtils.getInstance(Constant.SP_NAME).getInt(Constant.SP_USER_LANG), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(SPUtils.getInstance(Constant.SP_NAME).getInt(Constant.SP_USER_LANG)==i){
                                    return;
                                }
                                dialog.dismiss();
                                changeLanguage(i);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null);
                dialog = builder.create();
                dialog.show();
            }
        });
    }



    private void changeLanguage(int language) {
        //将选择的language保存到SP中
        SPUtils.getInstance(Constant.SP_NAME).put(Constant.SP_USER_LANG, language);
        //重新启动Activity,并且要清空栈
        Intent intent = new Intent(this, LocaleDemoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LangUtils.getAttachBaseContext(newBase, SPUtils.getInstance(Constant.SP_NAME).getInt(Constant.SP_USER_LANG)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_locales) {
            Intent intent = new Intent(this,LocaleListActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_link) {
            Intent intent = new Intent();
            intent.setClass(this,SayingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
