package com.yimuyun.lowraiseapp.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.handheld.UHFLonger.UHFLongerManager;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.SimpleActivity;
import com.yimuyun.lowraiseapp.util.ToastUtil;
import com.yimuyun.lowraiseapp.util.Util;
import com.yimuyun.lowraiseapp.util.longer.EPC;
import com.yimuyun.lowraiseapp.util.longer.MyApplication;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * @author will on 2017/6/11 13:43
 * @email pengweiqiang64@163.com
 * @description 高频
 * @Version
 */

public class LongerGetEarTagActivity extends SimpleActivity{

    private static UHFLongerManager manager  ;
    private boolean runFlag = true;
    private boolean startFlag = false;
    private boolean connectFlag = false;
    private KeyReceiver keyReceiver;
    private ArrayList<EPC> listEPC = new ArrayList<>();
    private ArrayList<Map<String, Object>> listMap;
    private MyApplication myAppli ;


    public static final String FROM = "from";

    private String className = "";

    private String tagId = "";

    @BindView(R.id.tv_ear_tag)
    TextView mTvEarTag;
    @Override
    protected int getLayout() {
        return R.layout.activity_loading_get_ear_tag;
    }

    @Override
    protected void initEventAndData() {
        className = getIntent().getStringExtra(FROM);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent();
//                intent.putExtra(Constants.EQUPIMENT_ID,getEquipmentId());
//                intent.setClassName(mContext,className);
//                startActivity(intent);
//                finish();
//            }
//        },2000);

        initLonger();

        startConnect();


        //开始扫描
//        Scan();
    }

    private void startConnect(){
        try {
            manager = UHFLongerManager.getInstance();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    SharedPreferences shared = getSharedPreferences("power", 0);
                    final int value = shared.getInt("value", 30);
                    if (manager.setOutPower((short) value)) {
                        runOnUiThread( new Runnable() {
                            public void run() {
                                Toast.makeText(LongerGetEarTagActivity.this, getString(R.string._power_now)+value, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }, 1000);

        } catch (Exception e) {

            e.printStackTrace();
        }
        if(manager == null){
            Toast.makeText(this, getString(R.string.serialport_init_fail_), Toast.LENGTH_SHORT).show();

            return ;
        }
        myAppli.setmanager(manager);
        Util.play(1, 0);

    }

    private void initLonger(){
        myAppli = new MyApplication();
        MyApplication.myapp = myAppli;
        listEPC = new ArrayList<EPC>();
        myAppli.setListEpc(new ArrayList<String>());

        Util.initSoundPool(this);//
        Thread thread = new InventoryThread();
        thread.start();

        keyReceiver = new KeyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.rfid.FUN_KEY");
        registerReceiver(keyReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        startFlag = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        if(manager != null)
            manager.clearSelect();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        startFlag = false;
        runFlag = false;
        if(manager != null){
            manager.close();
        }
//		myAppli
        tagId = "";
        super.onDestroy();
        unregisterReceiver(keyReceiver);
    }

    /**
     * invertory thread
     * @author Administrator
     *
     */
    class InventoryThread extends Thread{
        private List<String> epcList;

        @Override
        public void run() {
            super.run();
            while(runFlag){

                if(startFlag ){
//					manager.stopInventoryMulti()
                    epcList = manager.inventoryRealTime(); //
                    if(epcList != null && !epcList.isEmpty()){
                        //
                        Util.play(1, 0);
                        for(String epc:epcList){
//							String epcStr = new String(epc);
                            addToList(listEPC, epc);
                        }
                    }
                    epcList = null ;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //EPC and to LISTVIEW
    private void addToList(final List<EPC> list, final String epc){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tagId = epc;
                count ++;
                goNextActivity();
//                if(list.isEmpty()){
//                    EPC epcTag = new EPC();
//                    epcTag.setEpc(epc);
//                    epcTag.setCount(1);
//                    list.add(epcTag);
//                    myAppli.addEPC(epc);
//                }else{
//                    for(int i = 0; i < list.size(); i++){
//                        EPC mEPC = list.get(i);
//                        if(epc.equals(mEPC.getEpc())){
//                            mEPC.setCount(mEPC.getCount() + 1);
//                            list.set(i, mEPC);
//                            break;
//                        }else if(i == (list.size() - 1)){
//                            EPC newEPC = new EPC();
//                            newEPC.setEpc(epc);
//                            newEPC.setCount(1);
//                            list.add(newEPC);
//                            //��ӵ�application
//                            myAppli.addEPC(epc);
//                        }
//                    }
//                }
//                listMap = new ArrayList<Map<String,Object>>();
//                int idcount = 1;
//                for(EPC epcdata:list){
//                    Map<String, Object> map = new HashMap<String, Object>();
//                    map.put("ID", idcount);
//                    map.put("EPC", epcdata.getEpc());
//                    map.put("COUNT", epcdata.getCount());
//
//                    idcount++;
//                    listMap.add(map);
//                }

            }
        });
    }

    private void setButtonClickable(Button button, boolean flag){
        button.setClickable(flag);
        if(flag){
            button.setTextColor(Color.BLACK);
        }else{
            button.setTextColor(Color.GRAY);
        }
    }


    public static UHFLongerManager getUhfmanager() {
        return manager;
    }
    public class KeyReceiver extends BroadcastReceiver {

        private String TAG = "KeyReceiver" ;
        @Override
        public void onReceive(Context context, Intent intent) {
            int keyCode = intent.getIntExtra("keyCode", 0) ;
            boolean keyDown = intent.getBooleanExtra("keydown", false) ;
//			Log.e("down", ""+keyDown);
            if(keyDown){
                switch (keyCode) {
                    case KeyEvent.KEYCODE_F1:
                        Scan();
                        break;
                    case KeyEvent.KEYCODE_F2:
                        Scan();
                        break;
                    case KeyEvent.KEYCODE_F3:
                        Scan();
                        break;
                    case KeyEvent.KEYCODE_F4:
                        Scan();
                        break;
                    case KeyEvent.KEYCODE_F5:
                        Scan();
                        break;
                }
            }
        }
    }

    private void Scan() {
        count = 0;
        if (manager==null) return;
        if(!startFlag){
            startFlag = true ;
        }else{
            startFlag = false;
        }
    }


    private String getEquipmentId(){
        //TODO 读卡获取（高频、低频）
//        if(className.equals(NewEarTagManageActivity.class.getName())){
//            return System.currentTimeMillis()+"";
//        }
        return tagId;
    }
    private int count = 0 ;
    private synchronized void goNextActivity(){
        if(count==1) {
            ToastUtil.shortShow(tagId);
            if(StringUtil.isBlank(className)){
                return;
            }
            Intent intent = new Intent();
            intent.putExtra(Constants.EQUPIMENT_ID, getEquipmentId());
            intent.setClassName(mContext, className);
            startActivity(intent);
            finish();
        }
    }

    public static void open(Context context, String className){
        Intent intent = new Intent(context,LongerGetEarTagActivity.class);
        intent.putExtra(FROM,className);
        context.startActivity(intent);
    }
}
