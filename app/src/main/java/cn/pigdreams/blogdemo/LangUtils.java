package cn.pigdreams.blogdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import java.util.Locale;

/**
 * Create by pigdreams on 2018-07-26
 * website:pigdreams.cn
 * 语言自适应工具
 * 数组资源中设定语言,现为4个类型
 * 1.跟随系统
 * 2.简体中文
 * 3.繁体中文
 * 4.English
 * 然后每次选择的语言都会存入SP中,根据SP中保存的语言类型进行资源语言设置
 * 根据语言的整数值来匹配对应的Locale对象，却省值为简体中文Locale.SIMPLIFIED_CHINESE
 */
public class LangUtils {
    public static final int FOLLOW_SYSTEM = 0;
    private static final int SIMPLE_CHINESE = 1;
    private static final int TRADITION_CHINESE = 2;
    private static final int ENGLISH = 3;

    private static Locale getCurrentLang(int userLang) {
        switch (userLang) {
            case FOLLOW_SYSTEM:
                return Locale.getDefault();
            case SIMPLE_CHINESE:
                return Locale.SIMPLIFIED_CHINESE;
            case TRADITION_CHINESE:
                return Locale.TRADITIONAL_CHINESE;
            case ENGLISH:
                return Locale.ENGLISH;
            default:
                return Locale.SIMPLIFIED_CHINESE;
        }
    }

    public static Context getAttachBaseContext(Context context, int lang) {
        Log.d("pigdreams", "配置语言...默认locale=" + Locale.getDefault() + ";用户设置的为=" + getCurrentLang(lang));
        //Android 7.0之后需要用另一种方式更改res语言,即配置进context中
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return updateResources(context, lang);
            } else {
                //7.0之前的更新语言资源方式
                changeResLanguage(context, lang);
                return context;
            }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, int lang) {
        Resources resources = context.getResources();
        Locale locale = getCurrentLang(lang);
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    private static void changeResLanguage(Context context, int lang) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getCurrentLang(lang);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

}

