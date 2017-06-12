package com.yimuyun.lowraiseapp.base.contract.feed;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.FeedBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;

import java.util.List;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface FeedListContract {
    interface View extends BaseView {
        void setUserInfo(UserInfo userInfo);
        void setFeedList(List<FeedBean> feedList);

    }

    interface Presenter extends BasePresenter<View> {
        void getUserInfo(String phoneNumber);
        void getFeedList(String enterpriseId);

    }

}
