package com.yimuyun.lowraiseapp.util;

import org.jsoup.helper.StringUtil;

/**
 * @author will on 2017/7/22 12:15
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class NumberFormatCheckUtils {


    public static boolean checkNumber(String str){
        if(StringUtil.isBlank(str)){
            return false;
        }
        try {
            double number = Double.valueOf(str);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 输入温度判断
     * @param string
     * @return
     */
    public static boolean checkTemprature(String string){
        Double number = Double.valueOf(string);
        if(number>70 && number<-70){
            return false;
        }
        return true;
    }
}
