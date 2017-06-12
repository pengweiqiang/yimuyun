package com.yimuyun.lowraiseapp.ui.disinfect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseListAdapter;

import java.util.List;

/**
 * @author will on 2017/6/10 22:52
 * @email pengweiqiang64@163.com
 * @description 消毒方法适配器
 * @Version
 */

public class DisinfectWayListAdapter extends BaseListAdapter{

    private Context context;
    private List<String> waysList;
    private String selectedName;

    public DisinfectWayListAdapter(Context context, List<String> waysList, String selectedName){
        this.context = context;
        this.waysList = waysList;
        this.selectedName = selectedName;
    }

    public void setDatas(List<String> waysList){
        this.waysList = waysList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return waysList==null?0:waysList.size();
    }

    @Override
    public String getItem(int position) {
        return waysList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_feed,null);
            viewHolder = new ViewHolder();
            viewHolder.mIvSelectedOk = (ImageView)convertView.findViewById(R.id.iv_selected_feed);
            viewHolder.mTvFeedName = (TextView)convertView.findViewById(R.id.tv_feed_name);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        String wayName = getItem(position);
        viewHolder.mTvFeedName.setText(wayName);
        if(wayName.equals(selectedName)){
            viewHolder.mIvSelectedOk.setVisibility(View.VISIBLE);
        }else{
            viewHolder.mIvSelectedOk.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder{
        ImageView mIvSelectedOk;
        TextView mTvFeedName;
    }
}
