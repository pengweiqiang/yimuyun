package com.yimuyun.lowraiseapp.ui.diagnosis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseListAdapter;
import com.yimuyun.lowraiseapp.model.bean.DrugBean;

import java.util.List;

/**
 * @author will on 2017/6/10 22:52
 * @email pengweiqiang64@163.com
 * @description 药品列表适配器
 * @Version
 */

public class DrugListAdapter extends BaseListAdapter{

    private Context context;
    private List<DrugBean> drugBeanList;
    private long selectedId;

    public DrugListAdapter(Context context, List<DrugBean> drugBeanList, long selectedId){
        this.context = context;
        this.drugBeanList = drugBeanList;
        this.selectedId = selectedId;
    }

    public void setDatas(List<DrugBean> drugBeanList){
        this.drugBeanList = drugBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return drugBeanList==null?0:drugBeanList.size();
    }

    @Override
    public DrugBean getItem(int position) {
        return drugBeanList.get(position);
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
        DrugBean drugBean = getItem(position);
        viewHolder.mTvFeedName.setText(drugBean.getDrugName());
        if(selectedId == drugBean.getId()){
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
