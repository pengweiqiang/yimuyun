package com.yimuyun.lowraiseapp.ui.environmentmonitor;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.enviromentmonitor.EnvironmentMonitorContract;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.presenter.EnvironmentMonitorPresenter;
import com.yimuyun.lowraiseapp.util.NumberFormatCheckUtils;

import org.jsoup.helper.StringUtil;

import butterknife.BindView;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 环境监测
 * @Version
 */
public class EnvironmentMonitorActivity extends RootActivity<EnvironmentMonitorPresenter> implements EnvironmentMonitorContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.et_beam)
    EditText mEtBeam;
    @BindView(R.id.et_temperature)
    EditText mEtTemperature;
    @BindView(R.id.et_humidity)
    EditText mEtHumidity;
    @BindView(R.id.et_so2)
    EditText mEtSO2;
    @BindView(R.id.et_co2)
    EditText mEtCO2;
    @BindView(R.id.et_h2s)
    EditText mEtH2s;
    @BindView(R.id.et_nh3)
    EditText mEtNH3;
    @BindView(R.id.et_ch4)
    EditText mEtCH4;


    String enterpriseId,beam, temperature,humidity,so2,co2,h2s, nh3, ch4;

    @Override
    protected int getLayout() {
        return R.layout.activity_environment_monitor;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "环境监测", "提交", R.mipmap.ic_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    stateLoading();
                    mPresenter.insertEnvironment(enterpriseId, beam, temperature, humidity, so2, co2, h2s, nh3, ch4);
                }
            }
        });

        stateLoading();
        UserBean userBean = App.getInstance().getUserBeanInstance();
        mPresenter.getUserInfo(userBean.getPhoneNumber());
    }

    private boolean checkInput(){
        boolean isPassed = false;
        if(StringUtil.isBlank(enterpriseId)){
            showErrorMsg("企业id获取为空");
            return false;
        }
        beam = mEtBeam.getText().toString().trim();
        if(StringUtil.isBlank(beam)){
            mEtBeam.requestFocus();
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(beam)){
            mEtBeam.requestFocus();
            showErrorMsgToast("请输入正确的光照");
            return false;
        }
        temperature = mEtTemperature.getText().toString().trim();
        if(StringUtil.isBlank(temperature)){
            mEtTemperature.requestFocus();
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(temperature)){
            mEtTemperature.requestFocus();
            showErrorMsgToast("请输入正确的温度");
            return false;
        }
        if(!NumberFormatCheckUtils.checkTemprature(temperature)){
            mEtTemperature.requestFocus();
            showErrorMsgToast("请输入正确的温度");
            return false;
        }
        humidity = mEtHumidity.getText().toString().trim();
        if(StringUtil.isBlank(humidity)){
            mEtHumidity.requestFocus();
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(humidity)){
            mEtHumidity.requestFocus();
            showErrorMsgToast("请输入正确的湿度");
            return false;
        }
        so2 = mEtSO2.getText().toString().trim();
        if(StringUtil.isBlank(so2)){
            mEtSO2.requestFocus();
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(so2)){
            mEtSO2.requestFocus();
            showErrorMsgToast("请输入正确的SO₂浓度");
            return false;
        }
        co2 = mEtCO2.getText().toString().trim();
        if(StringUtil.isBlank(co2)){
            mEtCO2.requestFocus();
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(co2)){
            mEtCO2.requestFocus();
            showErrorMsgToast("请输入正确的CO₂浓度");
            return false;
        }
        h2s = mEtH2s.getText().toString().trim();
        if(StringUtil.isBlank(h2s)){
            mEtH2s.requestFocus();
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(h2s)){
            mEtH2s.requestFocus();
            showErrorMsgToast("请输入正确的H₂S浓度");
            return false;
        }
        nh3 = mEtNH3.getText().toString().trim();
        if(StringUtil.isBlank(nh3)){
            mEtNH3.requestFocus();
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(nh3)){
            mEtNH3.requestFocus();
            showErrorMsgToast("请输入正确的NH3浓度");
            return false;
        }
        ch4 = mEtCH4.getText().toString().trim();
        if(StringUtil.isBlank(ch4)){
            mEtCH4.requestFocus();
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(ch4)){
            mEtCH4.requestFocus();
            showErrorMsgToast("请输入正确的CH4浓度");
            return false;
        }
        isPassed = true;
        return isPassed;
    }



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void setUserInfo(UserInfo userInfo) {
        if(userInfo.getPersonnel()!=null) {
            enterpriseId = String.valueOf(userInfo.getPersonnel().getEnterpriseId());
        }
    }

    @Override
    public void insertEnvironmentSucecess() {
        mEtBeam.setText("");
        mEtTemperature.setText("");
        mEtHumidity.setText("");
        mEtSO2.setText("");
        mEtCO2.setText("");
        mEtH2s.setText("");
        mEtNH3.setText("");
        mEtCH4.setText("");
    }
}
