package com.yimuyun.lowraiseapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseListAdapter;
import com.yimuyun.lowraiseapp.model.bean.Personnel;

import org.jsoup.helper.StringUtil;

import java.util.List;

import circletextimage.viviant.com.circletextimagelib.view.CircleTextImage;

/**
 * @author will on 2017/6/10 22:52
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class UserListAdapter extends BaseListAdapter{

    private Context context;
    private List<Personnel> personnelList;

    public UserListAdapter(Context context, List<Personnel> personnelList){
        this.context = context;
        this.personnelList = personnelList;
    }

    public void setDatas(List<Personnel> personnelList){
        this.personnelList = personnelList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return personnelList==null?0:personnelList.size();
    }

    @Override
    public Personnel getItem(int position) {
        return personnelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user,null);
            viewHolder = new ViewHolder();
            viewHolder.mCircleTextImage = (CircleTextImage) convertView.findViewById(R.id.ctimage);
            viewHolder.mTvUserName = (TextView)convertView.findViewById(R.id.tv_user_name);
            viewHolder.mTvUserPhone = (TextView)convertView.findViewById(R.id.tv_user_phone);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Personnel personnel = getItem(position);
        String name = personnel.getName();
        if(!StringUtil.isBlank(name)){
            viewHolder.mCircleTextImage.setText4CircleImage(String.valueOf(name.charAt(0)));
        }
        viewHolder.mTvUserName.setText(name);
        viewHolder.mTvUserPhone.setText(personnel.getPhoneNumber());

        return convertView;
    }

    class ViewHolder{
        CircleTextImage mCircleTextImage;
        TextView mTvUserName;
        TextView mTvUserPhone;
    }
}
