package com.yimuyun.lowraiseapp.ui.diagnosis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseListAdapter;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisResultBean;

import java.util.List;

/**
 * @author will on 2017/6/10 22:52
 * @email pengweiqiang64@163.com
 * @description 诊疗结果适配器
 * @Version
 */

public class DiagnosisResultListAdapter extends BaseListAdapter{

    private Context context;
    private List<DiagnosisResultBean> diagnosisResultBeanList;
    private long selectedId;

    public DiagnosisResultListAdapter(Context context, List<DiagnosisResultBean> diagnosisResultBeanList, long selectedId){
        this.context = context;
        this.diagnosisResultBeanList = diagnosisResultBeanList;
        this.selectedId = selectedId;
    }

    public void setDatas(List<DiagnosisResultBean> diagnosisResultBeanList){
        this.diagnosisResultBeanList = diagnosisResultBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return diagnosisResultBeanList==null?0:diagnosisResultBeanList.size();
    }

    @Override
    public DiagnosisResultBean getItem(int position) {
        return diagnosisResultBeanList.get(position);
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
        DiagnosisResultBean diagnosisResultBean = getItem(position);
        viewHolder.mTvFeedName.setText(diagnosisResultBean.getDiagnosisResult());
        if(selectedId == diagnosisResultBean.getId()){
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
