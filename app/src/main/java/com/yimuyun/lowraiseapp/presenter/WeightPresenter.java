package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.weight.WeightContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.WeightVo;
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

public class WeightPresenter extends RxPresenter<WeightContract.View> implements WeightContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public WeightPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getWeightList(String equipmentId) {
        addSubscribe(mDataManager.getWeightList(equipmentId)
                .compose(RxUtil.<PadResultResponse<WeightVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<WeightVo>(mView, true) {
                    @Override
                    public void dataHandle(WeightVo weightVo) {
                        if(weightVo!=null) {
                            mView.setWeightList(weightVo);
                        }else{
                            ToastUtil.show("查询结果为空");
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
    public void insertWeight(String equipmentId, String weighTime, String weighPhase, String cultureProcess, String weight, Long weighId) {
        addSubscribe(mDataManager.insertWeight(equipmentId, weighTime, weighPhase, cultureProcess, weight, weighId)
                .compose(RxUtil.<PadResultResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandle(Object body) {
                        if(body==null) {
                            ToastUtil.show("提交成功");
                            mView.insertWeightSuccess();
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
