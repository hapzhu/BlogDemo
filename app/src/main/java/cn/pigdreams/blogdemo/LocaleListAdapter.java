package cn.pigdreams.blogdemo;

import android.content.Context;

import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;
import java.util.Locale;

public class LocaleListAdapter extends CommonAdapter<Locale> {
    public LocaleListAdapter(Context context, int layoutId, List<Locale> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, Locale item, int position) {
            viewHolder.setText(R.id.tv_data1,item.getLanguage()+"-"+item.getCountry()+"-"+item.getScript());
            viewHolder.setText(R.id.tv_data2,item.getDisplayLanguage());
            viewHolder.setText(R.id.tv_data3,item.getDisplayCountry());
            viewHolder.setText(R.id.tv_data4,item.getDisplayScript());
    }
}
