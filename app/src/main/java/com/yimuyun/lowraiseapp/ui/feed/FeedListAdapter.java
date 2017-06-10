package com.yimuyun.lowraiseapp.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseListAdapter;
import com.yimuyun.lowraiseapp.model.bean.FeedBean;

import java.util.List;

/**
 * @author will on 2017/6/10 22:52
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class FeedListAdapter extends BaseListAdapter{

    private Context context;
    private List<FeedBean> feedBeanList;
    private long selectedId;

    public FeedListAdapter(Context context,List<FeedBean> feedBeanList,long selectedId){
        this.context = context;
        this.feedBeanList = feedBeanList;
        this.selectedId = selectedId;
    }

    public void setDatas(List<FeedBean> feedBeanList){
        this.feedBeanList = feedBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return feedBeanList==null?0:feedBeanList.size();
    }

    @Override
    public FeedBean getItem(int position) {
        return feedBeanList.get(position);
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
        FeedBean feedBean = getItem(position);
        viewHolder.mTvFeedName.setText(feedBean.getName());
        if(selectedId == feedBean.getId()){
            viewHolder.mIvSelectedOk.setVisibility(View.VISIBLE);
        }else{
            viewHolder.mIvSelectedOk.setVisibility(View.GONE);
        }

        return super.getView(position, convertView, parent);
    }

    class ViewHolder{
        ImageView mIvSelectedOk;
        TextView mTvFeedName;
    }
}
