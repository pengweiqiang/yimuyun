package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.diagnosis.AddDiagnosisContract;
import com.yimuyun.lowraiseapp.model.DataManager;

import javax.inject.Inject;

/**
 * @author will on 2017/5/4 21:55
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class AddDiagnosisPresenter extends RxPresenter<AddDiagnosisContract.View> implements AddDiagnosisContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public AddDiagnosisPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void insertDiagnosisTreatment(String equipmentId, String treatmentPlanId, String symptoms, String result, String dragId, String time) {


    }

}
