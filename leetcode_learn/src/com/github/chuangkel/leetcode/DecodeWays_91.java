package com.leetcode;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: javaNewCharacteristic
 * @description: 91. Decode Ways
 * @author: chuangkel
 * @create: 2019-01-15
 **/
public class DecodeWays_91 {
        int count = 0;
        public void numDecodingsCircle(String s) {
            if(s == null || "".equals(s)){
                return ;
            }
            //只剩一个
            if( 1 == s.length()){
                count ++;
                return ;
            }
            //只剩两个
            if(s.length() == 2 ){
                if(s.charAt(1) != '0'){
                    count ++;
                }
                if((s.charAt(0) == '2' && s.charAt(1) < '7') || s.charAt(0) == '1'){
                    count ++;
                }
                return;
            }
            //一个数字字符
            if(s.length() > 1){
                numDecodingsCircle(s.substring(1));
            }
            //两个数字字符
            if(s.length() > 2 && ((s.charAt(0) == '2' && s.charAt(1) < '7') || s.charAt(0) == '1') ){
                numDecodingsCircle(s.substring(2));
            }

        }

        public int numDecodings(String s) {
            if(s.length() >= 2){
                s = s.replace("10","");
                s = s.replace("20","");
                if("".equals(s))
                    count ++;
            }
            Pattern pattern = Pattern.compile("\\d0");
            Matcher matcher = pattern.matcher(s);
            if(matcher.find())
                return count;
            if( "".equals(s) || s.charAt(0) > '9' || s.charAt(0) < '1'){
                return count;
            }
            numDecodingsCircle(s);
            return count;
        }
    @Test
    public void test(){
        int re = numDecodings("12120");
        System.out.println(count);
    }
}
