package cn.pigdreams.blogdemo;

import android.app.Application;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        if( SPUtils.getInstance(Constant.SP_NAME).getInt(Constant.SP_USER_LANG)==-1){
            SPUtils.getInstance(Constant.SP_NAME).put(Constant.SP_USER_LANG,LangUtils.FOLLOW_SYSTEM);
        }
    }
}
