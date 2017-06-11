package com.yimuyun.lowraiseapp.model.http;


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
import com.yimuyun.lowraiseapp.model.bean.WeightVo;
import com.yimuyun.lowraiseapp.model.http.api.FeedApis;
import com.yimuyun.lowraiseapp.model.http.api.UserApis;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by pengweiqiang on 2017/05/08.
 */
public class RetrofitHelper implements HttpHelper {

    private UserApis mUserApisService;
    private FeedApis feedApis;

    @Inject
    public RetrofitHelper(UserApis userApisService,FeedApis feedApis) {
        this.mUserApisService = userApisService;
        this.feedApis = feedApis;

    }

    @Override
    public Flowable<PadResultResponse<UserBean>> login(String userName, String password) {
        return mUserApisService.login(userName, password);
    }

    @Override
    public Flowable<PadResultResponse<FeedVo>> feedList(String enterpriseId) {
        return feedApis.feedList(enterpriseId);
    }

    @Override
    public Flowable<PadResultResponse<Object>> feeding(String equipmentIds, String feedId, String feedTime, String grass) {
        return feedApis.feeding(equipmentIds, feedId, feedTime, grass);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertWeight(String equipmentId, String weighTime, String weighPhase, String cultureProcess, String weight, Long weighId) {
        return feedApis.insertWeight(equipmentId, weighTime, weighPhase, cultureProcess, weight, weighId);
    }

    @Override
    public Flowable<PadResultResponse<WeightVo>> getWeightList(String equipmentId) {
        return feedApis.getWeightList(equipmentId);
    }

    @Override
    public Flowable<PadResultResponse<UserInfo>> getUserInfo(String phoneNumber) {
        return mUserApisService.getUserInfo(phoneNumber);
    }

    @Override
    public Flowable<PadResultResponse<PersonnelVo>> getUserList(String enterpriseId) {
        return mUserApisService.getUserList(enterpriseId);
    }

    @Override
    public Flowable<PadResultResponse<EquipmentInfoVo>> getEquipmentInfoByNumber(String equipmentNumber) {
        return feedApis.getEquipmentInfoByNumber(equipmentNumber);
    }

    @Override
    public Flowable<PadResultResponse<VarietiesVo>> getVarietieList(String enterpriseId) {
        return feedApis.getVarietieList(enterpriseId);
    }

    @Override
    public Flowable<PadResultResponse<EquipmentDetailVo>> getEquimentInfoById(String equipmentId) {
        return feedApis.getEquimentInfoById(equipmentId);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertEnvironment(String enterpriseId, String beam, String temperature, String humidity, String so2, String co2, String h2s, String nh3, String ch4) {
        return feedApis.insertEnvironment(enterpriseId, beam, temperature, humidity, so2, co2, h2s, nh3, ch4);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertImmune(String equipmentIds, String immuneTime, String vaccineType, String immuneWay, String immunePersonnelId) {
        return feedApis.insertImmune(equipmentIds, immuneTime, vaccineType, immuneWay, immunePersonnelId);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertBuy(String equipmentIds, String enterpriseId, String personnelId) {
        return feedApis.insertBuy(equipmentIds, enterpriseId, personnelId);
    }

    @Override
    public Flowable<PadResultResponse<FertilizationBean>> transFertilization(String equipmentId, String type) {
        return feedApis.transFertilization(equipmentId, type);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertDisposal(String morbidityTime, String disposalTime, String deathTime, String deathReason, String personnelId, String equipmentId, String disposalMethod) {
        return feedApis.insertDisposal(morbidityTime, disposalTime, deathTime, deathReason, personnelId, equipmentId, disposalMethod);
    }

    @Override
    public Flowable<PadResultResponse<Object>> publishSale(String equipmentIds) {
        return feedApis.publishSale(equipmentIds);
    }

    @Override
    public Flowable<PadResultResponse<Object>> offlineSale(String equipmentIds, String salesTime, String customerName, String contactWay, String unitPrice, String weight) {
        return feedApis.offlineSale(equipmentIds, salesTime, customerName, contactWay, unitPrice, weight);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertDisinfection(String disinfectantName, String disinfectionTime, String disinfectionMethod, String enterpriseId, String disinfectionPersonnelId) {
        return feedApis.insertDisinfection(disinfectantName, disinfectionTime, disinfectionMethod, enterpriseId, disinfectionPersonnelId);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertLivestock(String equipmentId, String livestockMasterId, String type, String state, String initialWeight, String initialTime, String lairageWeight, String lairageTime, String birthplace, String varietiesId, String sex, String isPregnancy, String picture, String parentEquipmentId) {
        return feedApis.insertLivestock(equipmentId, livestockMasterId, type, state, initialWeight, initialTime, lairageWeight, lairageTime, birthplace, varietiesId, sex, isPregnancy, picture, parentEquipmentId);
    }

    @Override
    public Flowable<PadResultResponse<List<DrugBean>>> getDrugList(String enterpriseId, String type) {
        return feedApis.getDrugList(enterpriseId, type);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertDiagnosisTreatment(String equipmentId, String treatmentPlanId, String symptoms, String result, String dragId, String time) {
        return feedApis.insertDiagnosisTreatment(equipmentId, treatmentPlanId, symptoms, result, dragId, time);
    }

    @Override
    public Flowable<PadResultResponse<List<DiagnosisTreatmentVo>>> getDiagnosisTreatmentList(String equipmentId) {
        return feedApis.getDiagnosisTreatmentList(equipmentId);
    }

    @Override
    public Flowable<PadResultResponse<DiagnosisResultVo>> getDiagnosisResultList(String enterpriseId) {
        return feedApis.getDiagnosisResultList(enterpriseId);
    }

    @Override
    public Flowable<PadResultResponse<DiagnosisTreatmentPlanVo>> getDiagnosisTreatmentPlanList(String enterpriseId) {
        return feedApis.getDiagnosisTreatmentPlanList(enterpriseId);
    }


}
