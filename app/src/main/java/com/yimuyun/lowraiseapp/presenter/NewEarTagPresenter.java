package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.neweartag.NewEarTagContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.EquipmentInfoVo;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.model.bean.VarietiesVo;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;
import com.yimuyun.lowraiseapp.util.CommonSubscriber;
import com.yimuyun.lowraiseapp.util.RxUtil;
import com.yimuyun.lowraiseapp.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

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
    public void getEquipmentInfoByNumber(final String equipmentNumber, final boolean isParent) {
        addSubscribe(mDataManager.getEquipmentInfoByNumber(equipmentNumber)
                .compose(RxUtil.<PadResultResponse<EquipmentInfoVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<EquipmentInfoVo>(mView, true) {
                    @Override
                    public void dataHandle(EquipmentInfoVo equipmentInfoVo) {
                        //耳标授权企业 start
                        if(equipmentInfoVo.getApplicationEquipment()!=null){
                            int applicationEnterPriseId = equipmentInfoVo.getApplicationEquipment().getEnterpriseId();
                            UserBean userBean = App.getInstance().getUserBeanInstance();
                            if(userBean.getUserInfo()!=null && applicationEnterPriseId!=userBean.getUserInfo().getEnterprise().getId()){
                                mView.showErrorMsg("该耳标没有授权给该企业"+applicationEnterPriseId);
                                return;
                            }
                        }
                        //耳标授权企业 end
                        if(equipmentInfoVo!=null) {
                            mView.setEquipmentId(equipmentInfoVo,isParent);
                        }else{
                            ToastUtil.show("耳标"+equipmentNumber+"查询结果为空");
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
    public void getVarietiesList(String enterpriseId,String type) {
        addSubscribe(mDataManager.getVarietieList(enterpriseId,type)
                .compose(RxUtil.<PadResultResponse<VarietiesVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<VarietiesVo>(mView, true) {
                    @Override
                    public void dataHandle(VarietiesVo varietiesVo) {
                        if(varietiesVo!=null && varietiesVo.getVarieties()!=null && !varietiesVo.getVarieties().isEmpty()) {
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
    public void insertLiveStock(String enterpriseId,String equipmentId, String livestockMasterId,String livestockName, String type, String state, String initialWeight, String initialTime,String initialShowTime, String lairageWeight, String lairageTime,String lairageShowTime, String birthplace, String varietiesId,String varietiesName, String sex, String isPregnancy, String picture, String parentEquipmentId) {
        final Map<String,Object> params = new HashMap<>();
        params.put("livestockName",livestockName);
        params.put("livestockMasterId",livestockMasterId);
        params.put("type",type);
        params.put("state",state);
        params.put("initialWeight",initialWeight);
        params.put("initialTime",initialTime);
        params.put("initialShowTime",initialShowTime);
        params.put("lairageWeight",lairageWeight);
        params.put("lairageTime",lairageTime);
        params.put("lairageShowTime",lairageShowTime);
        params.put("birthplace",birthplace);
        params.put("varietiesId",varietiesId);
        params.put("varietiesName",varietiesName);
        params.put("sex",sex);
        params.put("isPregnancy",isPregnancy);
        addSubscribe(mDataManager.insertLivestock(enterpriseId,equipmentId, livestockMasterId, type, state, initialWeight, initialTime, lairageWeight, lairageTime, birthplace, varietiesId, sex, isPregnancy, picture, parentEquipmentId)
                .compose(RxUtil.<PadResultResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandle(Object body) {
                        if(body==null) {
                            mView.showErrorMsgToast("提交成功");
                            mView.insertLiveStockSuccess();

                            saveLastSelectData(params);
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
    public void saveLastSelectData(Map<String,Object> params) {
        mDataManager.saveLastNewEarTagData(params);
    }

    @Override
    public Map<String, Object> getLastSelectData() {
        return mDataManager.getLastNewEarTagData();
    }
}
