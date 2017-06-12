package com.yimuyun.lowraiseapp.ui.diagnosis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseListAdapter;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentPlanBean;

import java.util.List;

/**
 * @author will on 2017/6/10 22:52
 * @email pengweiqiang64@163.com
 * @description 诊疗方案适配器
 * @Version
 */

public class DiagnosisTreatmentPlanListAdapter extends BaseListAdapter{

    private Context context;
    private List<DiagnosisTreatmentPlanBean> diagnosisTreatmentPlanBeanList;
    private long selectedId;

    public DiagnosisTreatmentPlanListAdapter(Context context, List<DiagnosisTreatmentPlanBean> diagnosisTreatmentPlanBeanList, long selectedId){
        this.context = context;
        this.diagnosisTreatmentPlanBeanList = diagnosisTreatmentPlanBeanList;
        this.selectedId = selectedId;
    }

    public void setDatas(List<DiagnosisTreatmentPlanBean> diagnosisTreatmentPlanBeanList){
        this.diagnosisTreatmentPlanBeanList = diagnosisTreatmentPlanBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return diagnosisTreatmentPlanBeanList==null?0:diagnosisTreatmentPlanBeanList.size();
    }

    @Override
    public DiagnosisTreatmentPlanBean getItem(int position) {
        return diagnosisTreatmentPlanBeanList.get(position);
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
        DiagnosisTreatmentPlanBean diagnosisTreatmentPlanBean = getItem(position);
        viewHolder.mTvFeedName.setText(diagnosisTreatmentPlanBean.getTreatmentPlan());
        if(selectedId == diagnosisTreatmentPlanBean.getId()){
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
