package com.yimuyun.lowraiseapp.ui.weight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseListAdapter;
import com.yimuyun.lowraiseapp.model.bean.WeightBean;
import com.yimuyun.lowraiseapp.util.DateUtil;

import java.util.List;

/**
 * @author will on 2017/6/10 22:52
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class WeightListAdapter extends BaseListAdapter{

    private Context context;
    private List<WeightBean> weightBeanList;
    private long selectedId;

    public WeightListAdapter(Context context, List<WeightBean> weightBeanList){
        this.context = context;
        this.weightBeanList = weightBeanList;
    }

    public void setDatas(List<WeightBean> weightBeanList){
        this.weightBeanList = weightBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return weightBeanList==null?0:weightBeanList.size();
    }

    @Override
    public WeightBean getItem(int position) {
        return weightBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_weight,null);
            viewHolder = new ViewHolder();
            viewHolder.mViewLine = convertView.findViewById(R.id.view_line_vertical);
            viewHolder.mTvWeight = (TextView)convertView.findViewById(R.id.tv_weight);
            viewHolder.mTvWeightTagName = (TextView)convertView.findViewById(R.id.tv_weight_tag_name);
            viewHolder.mTvEditWeight = (TextView)convertView.findViewById(R.id.tv_edit_weight);
            viewHolder.mTvDeleteWeight = (TextView)convertView.findViewById(R.id.tv_delete_weight);

            convertView.setTag(viewHolder);
            if(onViewOnclickListener!=null) {
                viewHolder.mTvDeleteWeight.setOnClickListener(viewHolder);
                viewHolder.mTvEditWeight.setOnClickListener(viewHolder);
            }
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.position = position;
        WeightBean weightBean = getItem(position);
        viewHolder.mTvWeight.setText(weightBean.getWeight());

        String weightDate = DateUtil.formartTime2String(weightBean.getWeighTime());
        viewHolder.mTvWeightTagName.setText(weightBean.getWeighPhase()+"\n"+weightDate);
        if(position == getCount()-1){
            viewHolder.mViewLine.setVisibility(View.GONE);
        }else{
            viewHolder.mViewLine.setVisibility(View.VISIBLE);
        }
        if(weightBean.getId()<=0){
            viewHolder.mTvEditWeight.setVisibility(View.VISIBLE);
            viewHolder.mTvDeleteWeight.setVisibility(View.VISIBLE);
        }else{
            viewHolder.mTvEditWeight.setVisibility(View.GONE);
            viewHolder.mTvDeleteWeight.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder implements View.OnClickListener{
        View mViewLine;
        TextView mTvWeight;
        TextView mTvWeightTagName;
        TextView mTvEditWeight;
        TextView mTvDeleteWeight;

        public int position;

        @Override
        public void onClick(View v) {
            onViewOnclickListener.onItemviewclick(v,position);
        }
    }

    private OnViewOnclickListener onViewOnclickListener;
    public void setOnViewOnclickListener(OnViewOnclickListener onViewOnclickListener){
        this.onViewOnclickListener = onViewOnclickListener;
    }
    public interface OnViewOnclickListener{
        void onItemviewclick(View view,int position);
    }
}
