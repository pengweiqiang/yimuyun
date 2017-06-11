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
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @date: 2017/5/04
 * @description:
 */

public interface HttpHelper {

    /**
     * 登陆
     *
     * @param userName
     * @param password
     * @return
     */
    Flowable<PadResultResponse<UserBean>> login(String userName, String password);

    //饲料
    Flowable<PadResultResponse<FeedVo>> feedList(String enterpriseId);

    //喂养
    Flowable<PadResultResponse<Object>> feeding(String equipmentIds, String feedId, String feedTime, String grass);

    //称重
    Flowable<PadResultResponse<Object>> insertWeight(String equipmentId,
                                                String weighTime,String weighPhase,
                                                String cultureProcess,String weight,Long weighId);

    //称重记录
    Flowable<PadResultResponse<WeightVo>> getWeightList(String equipmentId);

    Flowable<PadResultResponse<UserInfo>> getUserInfo(String phoneNumber);

    /**
     * 用户列表
     * @param enterpriseId
     * @return
     */
    Flowable<PadResultResponse<PersonnelVo>> getUserList(String enterpriseId);

    Flowable<PadResultResponse<EquipmentInfoVo>> getEquipmentInfoByNumber(String equipmentNumber);

    Flowable<PadResultResponse<VarietiesVo>> getVarietieList(String enterpriseId);


    Flowable<PadResultResponse<EquipmentDetailVo>> getEquimentInfoById(String equipmentId);

    /**
     * 环境监测录入
     * @param enterpriseId
     * @param beam
     * @param temperature
     * @param humidity
     * @param so2
     * @param co2
     * @param h2s
     * @param nh3
     * @param ch4
     * @return
     */
    Flowable<PadResultResponse<Object>> insertEnvironment(String enterpriseId,String beam,
                                                          String temperature,String humidity,
                                                          String so2,String co2,String h2s,
                                                          String nh3,String ch4);

    /**
     * 免疫录入
     * @param equipmentIds
     * @param immuneTime
     * @param vaccineType
     * @param immuneWay
     * @param immunePersonnelId
     * @return
     */
    Flowable<PadResultResponse<Object>> insertImmune(String equipmentIds,String immuneTime,
                                                     String vaccineType,String immuneWay,
                                                     String immunePersonnelId);

    /**
     * 收购录入
     * @param equipmentIds
     * @param enterpriseId
     * @param personnelId
     * @return
     */
    Flowable<PadResultResponse<Object>> insertBuy(String equipmentIds,String enterpriseId,
                                                  String personnelId);

    /**
     * 受孕转换
     * @param equipmentId
     * @param type
     * @return
     */
    Flowable<PadResultResponse<FertilizationBean>> transFertilization(String equipmentId,String type);

    /**
     * 无害化处理录入
     * @param morbidityTime
     * @param disposalTime
     * @param deathTime
     * @param deathReason
     * @param personnelId
     * @param equipmentId
     * @param disposalMethod
     * @return
     */
    Flowable<PadResultResponse<Object>> insertDisposal(String morbidityTime,String disposalTime,
                                                       String deathTime,String deathReason,
                                                       String personnelId,String equipmentId,
                                                       String disposalMethod);

    /**
     * 线上销售
     * @param equipmentIds
     * @return
     */
    Flowable<PadResultResponse<Object>> publishSale(String equipmentIds);

    /**
     * 线下销售
     * @param equipmentIds
     * @param salesTime
     * @param customerName
     * @param contactWay
     * @param unitPrice
     * @param weight
     * @return
     */
    Flowable<PadResultResponse<Object>> offlineSale(String equipmentIds,String salesTime,
                                                    String customerName,String contactWay,
                                                    String unitPrice,String weight);

    /**
     * 消毒插入
     * @param disinfectantName
     * @param disinfectionTime
     * @param disinfectionMethod
     * @param enterpriseId
     * @param disinfectionPersonnelId
     * @return
     */
    Flowable<PadResultResponse<Object>> insertDisinfection(String disinfectantName,String disinfectionTime,
                                                           String disinfectionMethod,String enterpriseId,
                                                           String disinfectionPersonnelId);

    /**
     * 新建耳标
     * @param equipmentId
     * @param livestockMasterId
     * @param type
     * @param state
     * @param initialWeight
     * @param initialTime
     * @param lairageWeight
     * @param lairageTime
     * @param birthplace
     * @param varietiesId
     * @param sex
     * @param isPregnancy
     * @param picture
     * @param parentEquipmentId
     * @return
     */
    Flowable<PadResultResponse<Object>> insertLivestock(String equipmentId,String livestockMasterId,
                                                        String type,String state,
                                                        String initialWeight,String initialTime,
                                                        String lairageWeight,String lairageTime,
                                                        String birthplace,String varietiesId,
                                                        String sex,String isPregnancy,
                                                        String picture,String parentEquipmentId);

    /**
     * 获取药品种类
     * @param enterpriseId
     * @param type
     * @return
     */
    Flowable<PadResultResponse<List<DrugBean>>> getDrugList(String enterpriseId, String type);


    /**
     * 诊疗插入
     * @param equipmentId
     * @param treatmentPlanId
     * @param symptoms
     * @param result
     * @param dragId
     * @param time
     * @return
     */
    Flowable<PadResultResponse<Object>> insertDiagnosisTreatment(String equipmentId,String treatmentPlanId,
                                                                 String symptoms,String result,
                                                                 String dragId,String time);


    /**
     * 获取诊疗记录列表
     * @param equipmentId
     * @return
     */
    Flowable<PadResultResponse<List<DiagnosisTreatmentVo>>> getDiagnosisTreatmentList(String equipmentId);

    /**
     * 获取诊疗结果列表
     * @param enterpriseId
     * @return
     */
    Flowable<PadResultResponse<DiagnosisResultVo>> getDiagnosisResultList(String enterpriseId);


    /**
     * 获取治疗方案列表
     * @param enterpriseId
     * @return
     */
    Flowable<PadResultResponse<DiagnosisTreatmentPlanVo>> getDiagnosisTreatmentPlanList(String enterpriseId);





}
