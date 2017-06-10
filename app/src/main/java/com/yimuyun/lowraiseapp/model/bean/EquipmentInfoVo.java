package com.yimuyun.lowraiseapp.model.bean;

/**
 * @author will on 2017/6/10 21:20
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class EquipmentInfoVo {
    private ApplicationEquipmentBean applicationEquipment;
    private EquipmentBean equipment;


    public ApplicationEquipmentBean getApplicationEquipment() {
        return applicationEquipment;
    }

    public void setApplicationEquipment(ApplicationEquipmentBean applicationEquipment) {
        this.applicationEquipment = applicationEquipment;
    }

    public EquipmentBean getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentBean equipment) {
        this.equipment = equipment;
    }
}
