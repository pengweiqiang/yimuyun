package com.yimuyun.lowraiseapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.UserListContract;
import com.yimuyun.lowraiseapp.model.bean.Personnel;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.presenter.UserListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 用户列表
 * @Version
 */
public class UserListActivity extends RootActivity<UserListPresenter> implements UserListContract.View{
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.listview)
    ListView mListView;
    UserListAdapter userListAdapter;
    private List<Personnel> personnelList = new ArrayList<>();


    private String enterpriseId;
    @Override
    protected int getLayout() {
        return R.layout.activity_user_list;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar,"选择畜主");

        stateLoading();
        UserBean userBean = App.getInstance().getUserBeanInstance();
        String phoneNumber = userBean.getPhoneNumber();
        mPresenter.getUserInfo(phoneNumber);


        userListAdapter = new UserListAdapter(mContext,personnelList);
        mListView.setAdapter(userListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personnel personnel = personnelList.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECTED_USER_ITEM,personnel);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public static void open(Activity activity){
        Intent intent = new Intent(activity,UserListActivity.class);
        activity.startActivityForResult(intent, Constants.REQUEST_USER_ITEM);
    }




    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void setUserInfo(UserInfo userInfo) {
        enterpriseId = String.valueOf(userInfo.getPersonnel().getEnterpriseId());

        stateLoading();
        mPresenter.getUserList(enterpriseId);
    }

    @Override
    public void setUserList(List<Personnel> personnelList) {
        this.personnelList = personnelList;
        userListAdapter.setDatas(personnelList);
    }
}
