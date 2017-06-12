package com.yimuyun.lowraiseapp.base.contract.diagnosis;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentVo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface AddDiagnosisContract {
    interface View extends BaseView {

        void insertDiagnosisSucecess(DiagnosisTreatmentVo diagnosisTreatmentVo);
    }

    interface Presenter extends BasePresenter<View> {


        void insertDiagnosisTreatment(String equipmentId,String treatmentPlanId,
                                      String symptoms,String result,
                                      String dragId,String time);

    }

}
