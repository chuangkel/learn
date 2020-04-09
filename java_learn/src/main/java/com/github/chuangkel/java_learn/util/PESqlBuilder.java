package com.github.chuangkel.java_learn.util;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-12 11:34
 **/
public class PESqlBuilder {

    public static void main(String[] args) {
        //INSERT INTO `dbhub`.`his_hub_tstockindexinfo` (`business_date`, `inter_code`, `pe`, `pb_rate`, `pcf`, `ps`) VALUES ('20090112', '2002', '1.00000000', '3.00000000', '1.00000000', '3.00000000');
        String high = " 2204002,2206002,9908001,10112001";//,10215001,10311001,10403001,10404001,10407001,10501001,10617001";//,30002002,38003002,100308002,100404002,110021001,110026001,112002002,120527001,122002001,125709002,126017001,150001002,159902002,300001002,300002002,300003002,500002001,510050001,580010001,580015001,580025001,600001001,600100001,600195001,600398001,600701001,601186001";
        String middle = "100308002,100404002,110021001,110026001,112002002,120527001,122002001,125709002,126017001,150001002,159902002,300001002,300002002,300003002,500002001,";
        String low = "510050001,580010001,580015001,580025001,600001001,600100001,600195001,600398001,600701001,601186001";
        String interCodes = middle;
        String[] interCodeArr = interCodes.split(",");
        for (int i = 0; i < interCodeArr.length; i++) {
            StringBuilder sb = new StringBuilder("INSERT INTO `dbhub`.`his_hub_tstockindexinfo` (`business_date`, `inter_code`, `pe`, `pb_rate`, `pcf`, `ps`) VALUES ('20090112',");

            sb.append("\'").append(interCodeArr[i]).append("\'").append(", ");
            sb.append("'26.00000000', '2.500000000', '1.00000000', '3.00000000');");
            System.out.println(sb.toString());
        }
    }
}
