package cn.pigdreams.blogdemo;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        SPUtils.getInstance(Constant.SP_NAME).put(Constant.SP_USER_LANG,LangUtils.FOLLOW_SYSTEM);
    }
}
