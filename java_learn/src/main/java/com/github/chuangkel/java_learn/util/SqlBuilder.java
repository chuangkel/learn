package com.github.chuangkel.java_learn.util;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-12 11:34
 **/
public class SqlBuilder {

    public static void main(String[] args) {
        String high = "101,112,113,2002,401002,532002,858002,2001002,2203002,2204002,2206002,9908001,10112001,10215001,10311001,10403001,10404001,10407001,10501001,10617001,";
        String middle = "30002002,38003002,100308002,100404002,110021001,110026001,112002002,120527001,122002001,125709002,126017001,150001002,159902002,300001002,300002002,300003002,500002001,";
        String low = "510050001,580010001,580015001,580025001,600001001,600100001,600195001,600398001,600701001,601186001";
        String interCodes = low;
        String[] interCodeArr = interCodes.split(",");
        for (int i = 0; i < interCodeArr.length; i++) {
            StringBuilder sb = new StringBuilder("INSERT INTO `dbhub`.`hub_tstockfinanceinfo` (`roe`, `roa`, `netprofit_growth_yoy_rate`, `revenue_growth_yoy_rate`, `inter_code`, `inform_date`) VALUES ('0.0500000', '0.00000000', '0.0000', '0.00000000', ");
            sb.append("\'").append(interCodeArr[i]).append("\'").append(", ");
            sb.append("\'").append("1").append("\'").append(");");
            System.out.println(sb.toString());
        }
    }
}
