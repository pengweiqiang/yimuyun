package com.yimuyun.lowraiseapp.base.contract.feed;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.FeedBean;

import java.util.List;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface FeedListContract {
    interface View extends BaseView {
        void setFeedList(List<FeedBean> feedList);

    }

    interface Presenter extends BasePresenter<View> {
        void getFeedList(String enterpriseId);

    }

}
