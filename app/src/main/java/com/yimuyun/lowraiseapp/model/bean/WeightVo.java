package com.yimuyun.lowraiseapp.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author will on 2017/6/11 09:44
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class WeightVo {
    private List<WeightBean> weighs=new ArrayList<>();//称重记录
    private LivestockBean livestock;//牲畜信息

    public List<WeightBean> getWeighs() {
        return weighs;
    }

    public void setWeighs(List<WeightBean> weighs) {
        this.weighs = weighs;
    }

    public LivestockBean getLivestock() {
        return livestock;
    }

    public void setLivestock(LivestockBean livestock) {
        this.livestock = livestock;
    }
}
