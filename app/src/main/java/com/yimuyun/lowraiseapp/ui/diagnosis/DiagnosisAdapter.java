package com.yimuyun.lowraiseapp.ui.diagnosis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseListAdapter;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentVo;
import com.yimuyun.lowraiseapp.util.DateUtil;

import java.util.List;

/**
 * @author will on 2017/6/10 22:52
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class DiagnosisAdapter extends BaseListAdapter{

    private Context context;
    private List<DiagnosisTreatmentVo> diagnosisTreatmentVoList;
    private long selectedId;

    public DiagnosisAdapter(Context context, List<DiagnosisTreatmentVo> diagnosisTreatmentVoList){
        this.context = context;
        this.diagnosisTreatmentVoList = diagnosisTreatmentVoList;
    }

    public void setDatas(List<DiagnosisTreatmentVo> diagnosisTreatmentVoList){
        this.diagnosisTreatmentVoList = diagnosisTreatmentVoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return diagnosisTreatmentVoList==null?0:diagnosisTreatmentVoList.size();
    }

    @Override
    public DiagnosisTreatmentVo getItem(int position) {
        return diagnosisTreatmentVoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_diagnosis,null);
            viewHolder = new ViewHolder();
            viewHolder.mViewLine = convertView.findViewById(R.id.view_line_vertical);
            viewHolder.mTvDiagnosis = (TextView)convertView.findViewById(R.id.tv_diagnosis);
            viewHolder.mTvDiagnosisDetail = (TextView)convertView.findViewById(R.id.tv_diagnosis_detail);
            viewHolder.mTvEdit = (TextView)convertView.findViewById(R.id.tv_edit);
            viewHolder.mTvDelete = (TextView)convertView.findViewById(R.id.tv_delete);

            convertView.setTag(viewHolder);
            if(onViewOnclickListener!=null) {
                viewHolder.mTvDelete.setOnClickListener(viewHolder);
                viewHolder.mTvEdit.setOnClickListener(viewHolder);
            }
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.position = position;
        DiagnosisTreatmentVo diagnosisTreatmentVo = getItem(position);


        StringBuffer sbDetail = new StringBuffer();
        if(diagnosisTreatmentVo.getDiagnosisTreatmentPlan()!=null){
            sbDetail.append("诊疗方案："+diagnosisTreatmentVo.getDiagnosisTreatmentPlan().getTreatmentPlan()+"\n");
        }

        if(diagnosisTreatmentVo.getDrug()!=null){
            sbDetail.append("药品名称："+diagnosisTreatmentVo.getDrug().getDrugName()+"\n");
        }
        if(diagnosisTreatmentVo.getDiagnosisTreatment()!=null){
            sbDetail.append(DateUtil.formartTime2String(diagnosisTreatmentVo.getDiagnosisTreatment().getTime()));
        }

        if(diagnosisTreatmentVo.getDiagnosisResult()!=null){
            viewHolder.mTvDiagnosis.setText(diagnosisTreatmentVo.getDiagnosisResult().getDiagnosisResult());
        }



        viewHolder.mTvDiagnosisDetail.setText(sbDetail.toString());
        if(position == getCount()-1){
            viewHolder.mViewLine.setVisibility(View.GONE);
        }else{
            viewHolder.mViewLine.setVisibility(View.VISIBLE);
        }
        if(diagnosisTreatmentVo.getDiagnosisTreatment().getId()<=0){
            viewHolder.mTvEdit.setVisibility(View.VISIBLE);
            viewHolder.mTvDelete.setVisibility(View.VISIBLE);
        }else{
            viewHolder.mTvEdit.setVisibility(View.GONE);
            viewHolder.mTvDelete.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder implements View.OnClickListener{
        View mViewLine;
        TextView mTvDiagnosis;
        TextView mTvDiagnosisDetail;
        TextView mTvEdit;
        TextView mTvDelete;

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
        void onItemviewclick(View view, int position);
    }
}
