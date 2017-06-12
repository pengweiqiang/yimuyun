package com.yimuyun.lowraiseapp.model.bean;

import java.io.Serializable;

/**
 * @author will on 2017/6/10 21:39
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class DiagnosisTreatmentVo implements Serializable{

    private DiagnosisTreatmentBean diagnosisTreatment;
    private DiagnosisTreatmentPlanBean diagnosisTreatmentPlan;
    private DiagnosisResultBean diagnosisResult;
    private DrugBean drug;


    public DiagnosisTreatmentBean getDiagnosisTreatment() {
        return diagnosisTreatment;
    }

    public void setDiagnosisTreatment(DiagnosisTreatmentBean diagnosisTreatment) {
        this.diagnosisTreatment = diagnosisTreatment;
    }

    public DiagnosisTreatmentPlanBean getDiagnosisTreatmentPlan() {
        return diagnosisTreatmentPlan;
    }

    public void setDiagnosisTreatmentPlan(DiagnosisTreatmentPlanBean diagnosisTreatmentPlan) {
        this.diagnosisTreatmentPlan = diagnosisTreatmentPlan;
    }

    public DiagnosisResultBean getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(DiagnosisResultBean diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    public DrugBean getDrug() {
        return drug;
    }

    public void setDrug(DrugBean drug) {
        this.drug = drug;
    }
}
