package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/10 21:13
 * @email pengweiqiang64@163.com
 * @description 耳标详情
 * @Version
 */

public class EquipmentDetailVo {
    private Varieties varieties;
    private LivestockBean livestock;
    private EquipmentBean parentEquipment;


    public Varieties getVarieties() {
        return varieties;
    }

    public void setVarieties(Varieties varieties) {
        this.varieties = varieties;
    }

    public LivestockBean getLivestock() {
        return livestock;
    }

    public void setLivestock(LivestockBean livestock) {
        this.livestock = livestock;
    }


    public EquipmentBean getParentEquipment() {
        return parentEquipment;
    }

    public void setParentEquipment(EquipmentBean parentEquipment) {
        this.parentEquipment = parentEquipment;
    }
}
