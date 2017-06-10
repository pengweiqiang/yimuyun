package com.yimuyun.lowraiseapp.model;


import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisResultVo;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentPlanVo;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentVo;
import com.yimuyun.lowraiseapp.model.bean.DrugBean;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.EquipmentInfoVo;
import com.yimuyun.lowraiseapp.model.bean.FeedVo;
import com.yimuyun.lowraiseapp.model.bean.FertilizationBean;
import com.yimuyun.lowraiseapp.model.bean.PersonnelVo;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.model.bean.VarietiesVo;
import com.yimuyun.lowraiseapp.model.db.DBHelper;
import com.yimuyun.lowraiseapp.model.http.HttpHelper;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;
import com.yimuyun.lowraiseapp.model.prefs.PreferencesHelper;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @desciption:
 */

public class DataManager implements HttpHelper, DBHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return mPreferencesHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferencesHelper.setNightModeState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setUserInstance(UserBean userBean) {
        App.getInstance().setUserInstance(userBean);
        mPreferencesHelper.setUserInstance(userBean);
    }


    @Override
    public UserBean getUserInstance() {
        return mPreferencesHelper.getUserInstance();
    }

    @Override
    public Flowable<PadResultResponse<UserBean>> login(String userName, String password) {
        return mHttpHelper.login(userName,password);
    }

    @Override
    public Flowable<PadResultResponse<FeedVo>> feedList(String enterpriseId) {
        return mHttpHelper.feedList(enterpriseId);
    }

    @Override
    public Flowable<PadResultResponse<Object>> feeding(String equipmentIds, String feedId, String feedTime, String grass) {
        return mHttpHelper.feeding(equipmentIds, feedId, feedTime, grass);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertWeight(String equipmentId, String weighTime, String weighPhase, String cultureProcess, String weight, Long weighId) {
        return mHttpHelper.insertWeight(equipmentId, weighTime, weighPhase, cultureProcess, weight, weighId);
    }

    @Override
    public Flowable<PadResultResponse<UserInfo>> getUserInfo(String phoneNumber) {
        return mHttpHelper.getUserInfo(phoneNumber);
    }

    @Override
    public Flowable<PadResultResponse<PersonnelVo>> getUserList(String enterpriseId) {
        return mHttpHelper.getUserList(enterpriseId);
    }

    @Override
    public Flowable<PadResultResponse<EquipmentInfoVo>> getEquipmentInfoByNumber(String equipmentNumber) {
        return mHttpHelper.getEquipmentInfoByNumber(equipmentNumber);
    }

    @Override
    public Flowable<PadResultResponse<VarietiesVo>> getVarietieList(String enterpriseId) {
        return mHttpHelper.getVarietieList(enterpriseId);
    }

    @Override
    public Flowable<PadResultResponse<EquipmentDetailVo>> getEquimentInfoById(String equipmentId) {
        return mHttpHelper.getEquimentInfoById(equipmentId);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertEnvironment(String enterpriseId, String beam, String temperature, String humidity, String so2, String co2, String h2s, String nh3, String ch4) {
        return mHttpHelper.insertEnvironment(enterpriseId, beam, temperature, humidity, so2, co2, h2s, nh3, ch4);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertImmune(String equipmentIds, String immuneTime, String vaccineType, String immuneWay, String immunePersonnelId) {
        return mHttpHelper.insertImmune(equipmentIds, immuneTime, vaccineType, immuneWay, immunePersonnelId);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertBuy(String equipmentIds, String enterpriseId, String personnelId) {
        return mHttpHelper.insertBuy(equipmentIds, enterpriseId, personnelId);
    }

    @Override
    public Flowable<PadResultResponse<FertilizationBean>> transFertilization(String equipmentId, String type) {
        return mHttpHelper.transFertilization(equipmentId, type);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertDisposal(String morbidityTime, String disposalTime, String deathTime, String deathReason, String personnelId, String equipmentId, String disposalMethod) {
        return mHttpHelper.insertDisposal(morbidityTime, disposalTime, deathTime, deathReason, personnelId, equipmentId, disposalMethod);
    }

    @Override
    public Flowable<PadResultResponse<Object>> publishSale(String equipmentIds) {
        return mHttpHelper.publishSale(equipmentIds);
    }

    @Override
    public Flowable<PadResultResponse<Object>> offlineSale(String equipmentIds, String salesTime, String customerName, String contactWay, String unitPrice, String weight) {
        return mHttpHelper.offlineSale(equipmentIds, salesTime, customerName, contactWay, unitPrice, weight);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertDisinfection(String disinfectantName, String disinfectionTime, String disinfectionMethod, String enterpriseId, String disinfectionPersonnelId) {
        return mHttpHelper.insertDisinfection(disinfectantName, disinfectionTime, disinfectionMethod, enterpriseId, disinfectionPersonnelId);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertLivestock(String equipmentId, String livestockMasterId, String type, String state, String initialWeight, String initialTime, String lairageWeight, String lairageTime, String birthplace, String varietiesId, String sex, String isPregnancy, String picture, String parentEquipmentId) {
        return mHttpHelper.insertLivestock(equipmentId, livestockMasterId, type, state, initialWeight, initialTime, lairageWeight, lairageTime, birthplace, varietiesId, sex, isPregnancy, picture, parentEquipmentId);
    }

    @Override
    public Flowable<PadResultResponse<List<DrugBean>>> getDrugList(String enterpriseId, String type) {
        return mHttpHelper.getDrugList(enterpriseId, type);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertDiagnosisTreatment(String equipmentId, String treatmentPlanId, String symptoms, String result, String dragId, String time) {
        return mHttpHelper.insertDiagnosisTreatment(equipmentId, treatmentPlanId, symptoms, result, dragId, time);
    }

    @Override
    public Flowable<PadResultResponse<List<DiagnosisTreatmentVo>>> getDiagnosisTreatmentList(String equipmentId) {
        return mHttpHelper.getDiagnosisTreatmentList(equipmentId);
    }

    @Override
    public Flowable<PadResultResponse<DiagnosisResultVo>> getDiagnosisResultList(String enterpriseId) {
        return mHttpHelper.getDiagnosisResultList(enterpriseId);
    }

    @Override
    public Flowable<PadResultResponse<DiagnosisTreatmentPlanVo>> getDiagnosisTreatmentPlanList(String enterpriseId) {
        return mHttpHelper.getDiagnosisTreatmentPlanList(enterpriseId);
    }
}
