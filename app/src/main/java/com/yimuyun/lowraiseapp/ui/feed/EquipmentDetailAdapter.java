package com.yimuyun.lowraiseapp.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseListAdapter;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;

import java.util.List;

/**
 * @author will on 2017/6/10 22:52
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class EquipmentDetailAdapter extends BaseListAdapter{

    private Context context;
    private List<EquipmentDetailVo> equipmentDetailVoList;

    public EquipmentDetailAdapter(Context context, List<EquipmentDetailVo> equipmentDetailVoList){
        this.context = context;
        this.equipmentDetailVoList = equipmentDetailVoList;
    }

    public void setDatas(List<EquipmentDetailVo> equipmentDetailVoList){
        this.equipmentDetailVoList = equipmentDetailVoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return equipmentDetailVoList==null?0:equipmentDetailVoList.size();
    }

    @Override
    public EquipmentDetailVo getItem(int position) {
        return equipmentDetailVoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_equipment_feed,null);
            viewHolder = new ViewHolder();
            viewHolder.mTvEquipmentNumber = (TextView)convertView.findViewById(R.id.tv_equipment_number);
            viewHolder.mTvLiveStockName = (TextView)convertView.findViewById(R.id.tv_livestock_name);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        EquipmentDetailVo equipmentDetailVo = getItem(position);
        StringBuffer liveStockNameSb = new StringBuffer();
        if(equipmentDetailVo.getVarieties()!=null) {
            liveStockNameSb.append(equipmentDetailVo.getVarieties().getName());
        }
        if(equipmentDetailVo.getLivestock()!=null){
            liveStockNameSb.append(" "+equipmentDetailVo.getLivestock().getLairageWeight()+" 公斤");

            viewHolder.mTvEquipmentNumber.setText(String.valueOf(equipmentDetailVo.getLivestock().getEquipmentId()));
        }

        viewHolder.mTvLiveStockName.setText(liveStockNameSb.toString());


        return convertView;
    }

    class ViewHolder{
        TextView mTvLiveStockName;
        TextView mTvEquipmentNumber;
    }
}
