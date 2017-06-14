package com.yimuyun.lowraiseapp.model.http.api;

import com.yimuyun.lowraiseapp.model.bean.DiagnosisResultVo;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentPlanVo;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentVo;
import com.yimuyun.lowraiseapp.model.bean.DrugBean;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.EquipmentInfoVo;
import com.yimuyun.lowraiseapp.model.bean.FeedVo;
import com.yimuyun.lowraiseapp.model.bean.FertilizationBean;
import com.yimuyun.lowraiseapp.model.bean.VarietiesVo;
import com.yimuyun.lowraiseapp.model.bean.WeightVo;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author will on 2017/6/10 14:29
 * @email pengweiqiang64@163.com
 * @description 饲养端
 * @Version
 */

public interface FeedApis {


    /**
     * 饲料列表
     * @param enterpriseId
     * @return
     */
    @GET("culture/v1/feeding/feed/list")
    Flowable<PadResultResponse<FeedVo>> feedList(@Query("enterpriseId") String enterpriseId);

    /**
     * 设备信息
     * @param equipmentNumber
     * @return
     */
    @POST("culture/v1/equipment/info")
    Flowable<PadResultResponse<EquipmentInfoVo>> getEquipmentInfoByNumber(@Query("equipmentNumber")String equipmentNumber);

    /**
     * 获取种类
     * @param enterpriseId
     * @return
     */
    @GET("culture/v1/livestock/varieties/list")
    Flowable<PadResultResponse<VarietiesVo>> getVarietieList(@Query("enterpriseId")String enterpriseId);
    /**
     * 喂养录入
     * @param equipmentIds
     * @param feedId
     * @param feedTime
     * @param grass
     * @return
     */
    //uid=1&token=2e53f0d9b84155c2daa1abd5735aad15&equipmentIds=1%2C2&feedId=1&feedTime=20170909&grass=%E5%90%84%E7%A7%8D%E8%8D%89
    @POST("culture/v1/feeding/feeding")
    Flowable<PadResultResponse<Object>> feeding(@Query("equipmentIds")String equipmentIds,@Query("feedId")String feedId,@Query("feedTime")String feedTime,@Query("grass")String grass);


    /**
     * 称重记录
     * @param equipmentId
     * @return
     */
    @GET("culture/v1/weigh/weigh/list")
    Flowable<PadResultResponse<WeightVo>> getWeightList(@Query("equipmentId") String equipmentId);

    /**
     * 称重
     */
    @POST("culture/v1/weigh/insert")
    Flowable<PadResultResponse<Object>> insertWeight(@Query("equipmentId") String equipmentId,
                                                @Query("weighTime") String weighTime,@Query("weighPhase") String weighPhase,
                                                @Query("cultureProcess") String cultureProcess,@Query("weight") String weight,@Query("weighId") Long weighId);


    /**
     * 耳标详情
     * @param equipmentId
     * @return
     */
    @POST("culture/v1/livestock/info")
    Flowable<PadResultResponse<EquipmentDetailVo>> getEquimentInfoById(@Query("equipmentId")String equipmentId);

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
    @POST("culture/v1/environment/insert")
    Flowable<PadResultResponse<Object>> insertEnvironment(@Query("enterpriseId")String enterpriseId,@Query("beam")String beam,
                                                          @Query("temperature")String temperature,@Query("humidity")String humidity,
                                                          @Query("so2")String so2,@Query("co2")String co2,@Query("h2s")String h2s,
                                                          @Query("nh3")String nh3,@Query("ch4")String ch4);

    /**
     * 免疫录入
     * @param equipmentIds
     * @param immuneTime
     * @param vaccineType
     * @param immuneWay
     * @param immunePersonnelId
     * @return
     */
    @POST("culture/v1/immune/insert")
    Flowable<PadResultResponse<Object>> insertImmune(@Query("equipmentIds")String equipmentIds,@Query("immuneTime")String immuneTime,
                                                          @Query("vaccineType")String vaccineType,@Query("immuneWay")String immuneWay,
                                                          @Query("immunePersonnelId")String immunePersonnelId);

    /**
     * 收购录入
     * @param equipmentIds
     * @param enterpriseId
     * @param personnelId
     * @return
     */
    @POST("culture/v1/sale/buy")
    Flowable<PadResultResponse<Object>> insertBuy(@Query("equipmentIds")String equipmentIds,@Query("enterpriseId")String enterpriseId,
                                                     @Query("personnelId")String personnelId);

    /**
     * 受孕转换
     * @param equipmentId
     * @param type
     * @return
     */
    @POST("culture/v1/livestock/update")
    Flowable<PadResultResponse<FertilizationBean>> transFertilization(@Query("equipmentId")String equipmentId,@Query("type")String type);

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
    //morbidityTime=20170909&disposalTime=20170909&deathTime=20170909&deathReason=%E6%AD%BB%E4%BA%A1&personnelId=1&equipmentId=1&disposalMethod=%E7%81%AB%E7%83%A7
    @POST("culture/v1/disposal/insert")
    Flowable<PadResultResponse<Object>> insertDisposal(@Query("morbidityTime")String morbidityTime,@Query("disposalTime")String disposalTime,
                                                       @Query("deathTime")String deathTime,@Query("deathReason")String deathReason,
                                                       @Query("personnelId")String personnelId,@Query("equipmentId")String equipmentId,
                                                       @Query("disposalMethod")String disposalMethod);

    /**
     * 线上销售
     * @param equipmentIds
     * @return
     */
    @POST("culture/v1/sale/publish")
    Flowable<PadResultResponse<Object>> publishSale(@Query("equipmentIds")String equipmentIds);

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
    //equipmentIds=1%2C2&salesTime=20170909&customerName=%E5%AE%89%E6%A1%90&contactWay=18611368626&unitPrice=20.12&weight=100.00
    @POST("culture/v1/sale/offlineSale")
    Flowable<PadResultResponse<Object>> offlineSale(@Query("equipmentIds")String equipmentIds,@Query("salesTime")String salesTime,
                                                    @Query("customerName")String customerName,@Query("contactWay")String contactWay,
                                                    @Query("unitPrice")String unitPrice,@Query("weight")String weight);

    /**
     * 消毒插入
     * @param disinfectantName
     * @param disinfectionTime
     * @param disinfectionMethod
     * @param enterpriseId
     * @param disinfectionPersonnelId
     * @return
     */
    //disinfectantName=%E6%B6%88%E6%AF%92&disinfectionTime=20170909&disinfectionMethod=%E6%B6%88%E6%AF%92%E8%8D%AF&enterpriseId=1&disinfectionPersonnelId=1
    @POST("culture/v1/disinfection/insert")
    Flowable<PadResultResponse<Object>> insertDisinfection(@Query("disinfectantName")String disinfectantName,@Query("disinfectionTime")String disinfectionTime,
                                                    @Query("disinfectionMethod")String disinfectionMethod,@Query("enterpriseId")String enterpriseId,
                                                    @Query("disinfectionPersonnelId")String disinfectionPersonnelId);

    /**
     * 新建耳标
     * @param enterpriseId
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
    //equipmentId=3&enterpriseId=1&livestockMasterId=1&type=1&state=00&initialWeight=111&initialTime=20170922&lairageWeight=112&lairageTime=20170923&birthplace=%E5%8C%97%E4%BA%AC&varietiesId=1&sex=1&isPregnancy=1&picture=1&parentEquipmentId=2
    @POST("culture/v1/livestock/insert")
    Flowable<PadResultResponse<Object>> insertLivestock(@Query("enterpriseId")String enterpriseId,@Query("equipmentId")String equipmentId,@Query("livestockMasterId")String livestockMasterId,
                                                           @Query("type")String type,@Query("state")String state,
                                                           @Query("initialWeight")String initialWeight,@Query("initialTime")String initialTime,
                                                        @Query("lairageWeight")String lairageWeight,@Query("lairageTime")String lairageTime,
                                                        @Query("birthplace")String birthplace,@Query("varietiesId")String varietiesId,
                                                        @Query("sex")String sex,@Query("isPregnancy")String isPregnancy,
                                                        @Query("picture")String picture,@Query("parentEquipmentId")String parentEquipmentId);

    /**
     * 获取药品种类
     * @param enterpriseId
     * @param type
     * @return
     */
    @GET("culture/v1/drug/drug/list")
    Flowable<PadResultResponse<List<DrugBean>>> getDrugList(@Query("enterpriseId")String enterpriseId, @Query("type")String type);


    /**
     * 诊疗插入
     * @param equipmentId
     * @param personnelId
     * @param treatmentPlanId
     * @param symptoms
     * @param result
     * @param dragId
     * @param time
     * @return
     */
    //equipmentId=1&personnelId=1&treatmentPlanId=1&symptoms=1&result=1&dragId=1&time=20170808
    @POST("culture/v1/diagnosis/diagnosisTreatment/insert")
    Flowable<PadResultResponse<Object>> insertDiagnosisTreatment(@Query("equipmentId")String equipmentId,@Query("personnelId")String personnelId,@Query("treatmentPlanId")String treatmentPlanId,
                                                           @Query("symptoms")String symptoms,@Query("result")String result,
                                                           @Query("dragId")String dragId,@Query("time")String time);


    /**
     * 获取诊疗记录列表
     * @param equipmentId
     * @return
     */
    @GET("culture/v1/diagnosis/diagnosisTreatment/list")
    Flowable<PadResultResponse<List<DiagnosisTreatmentVo>>> getDiagnosisTreatmentList(@Query("equipmentId")String equipmentId);

    /**
     * 获取诊疗结果列表
     * @param enterpriseId
     * @return
     */
    @GET("culture/v1/diagnosis/diagnosisResult/list")
    Flowable<PadResultResponse<DiagnosisResultVo>> getDiagnosisResultList(@Query("enterpriseId")String enterpriseId);


    /**
     * 获取治疗方案列表
     * @param enterpriseId
     * @return
     */
    @GET("culture/v1/diagnosis/diagnosisTreatmentPlan/list")
    Flowable<PadResultResponse<DiagnosisTreatmentPlanVo>> getDiagnosisTreatmentPlanList(@Query("enterpriseId")String enterpriseId);

    @POST("culture/v1/quarantine/insert")
    Flowable<PadResultResponse<Object>> insertQuarantine(@Query("equipmentIds")String equipmentIds,@Query("quarantinePicture")String quarantinePicture,@Query("personnelId")long personnelId);
}
