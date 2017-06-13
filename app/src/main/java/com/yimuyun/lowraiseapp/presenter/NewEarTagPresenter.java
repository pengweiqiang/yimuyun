package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.neweartag.NewEarTagContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.model.bean.VarietiesVo;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;
import com.yimuyun.lowraiseapp.util.CommonSubscriber;
import com.yimuyun.lowraiseapp.util.RxUtil;
import com.yimuyun.lowraiseapp.util.ToastUtil;

import javax.inject.Inject;

/**
 * @author will on 2017/5/4 21:55
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class NewEarTagPresenter extends RxPresenter<NewEarTagContract.View> implements NewEarTagContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public NewEarTagPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getUserInfo(String phoneNumber) {
        addSubscribe(mDataManager.getUserInfo(phoneNumber)
                .compose(RxUtil.<PadResultResponse<UserInfo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserInfo>(mView, true) {
                    @Override
                    public void dataHandle(UserInfo userInfo) {
                        if(userInfo!=null) {
                            mView.setUserInfo(userInfo);
                        }else{
                            ToastUtil.show("获取企业id失败");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mView.stateMain();
                    }
                }));
    }

    @Override
    public void getVarietiesList(String enterpriseId) {
        addSubscribe(mDataManager.getVarietieList(enterpriseId)
                .compose(RxUtil.<PadResultResponse<VarietiesVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<VarietiesVo>(mView, true) {
                    @Override
                    public void dataHandle(VarietiesVo varietiesVo) {
                        if(varietiesVo!=null) {
                            mView.setVarietiesList(varietiesVo.getVarieties());
                        }else{
                            ToastUtil.show("查询品种为空");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mView.stateMain();
                    }
                }));
    }

    @Override
    public void insertLiveStock(String enterpriseId,String equipmentId, String livestockMasterId, String type, String state, String initialWeight, String initialTime, String lairageWeight, String lairageTime, String birthplace, String varietiesId, String sex, String isPregnancy, String picture, String parentEquipmentId) {
        addSubscribe(mDataManager.insertLivestock(enterpriseId,equipmentId, livestockMasterId, type, state, initialWeight, initialTime, lairageWeight, lairageTime, birthplace, varietiesId, sex, isPregnancy, picture, parentEquipmentId)
                .compose(RxUtil.<PadResultResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandle(Object body) {
                        if(body==null) {
                            mView.showErrorMsgToast("提交成功");
                            mView.insertLiveStockSuccess();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mView.stateMain();
                    }
                }));
    }
}
