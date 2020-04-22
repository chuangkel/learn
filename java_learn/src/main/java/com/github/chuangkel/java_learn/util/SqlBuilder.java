package com.github.chuangkel.java_learn.util;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-12 11:34
 **/
public class SqlBuilder {
    static String table1 = "hsfundPmsIndustryConfigCauseTable";
    static String col1 = "name\n" +
            "port_closed_weight\n" +
            "port_mean_weight\n" +
            "bench_closed_weight\n" +
            "bench_mean_weight\n" +
            "over_base_ratio\n" +
            "investment_return_contribution\n" +
            "bench_return_contribution\n" +
            "excess_return_contribution\n" +
            "asset_allocation_return_contribution\n" +
            "stock_selection_return_contribution\n" +
            "interaction_return_contribution\n" +
            "adjust_asset_allocation_return_contribution\n" +
            "adjust_interaction_return_contribution\n" +
            "new_stock_return_contribution\n" +
            "port_opening_weight\n" +
            "bench_opening_weight\n" +
            "return_amount\n" +
            "exchange_return_contribution\n" +
            "other_return_contribution";

    static String table2 = "hsfundPmsAssertConfigCauseTable";
    static String col2  = "name\n" +
            "port_closed_weight\n" +
            "port_mean_weight\n" +
            "bench_closed_weight\n" +
            "bench_mean_weight\n" +
            "over_base_ratio\n" +
            "investment_return_contribution\n" +
            "bench_return_contribution\n" +
            "excess_return_contribution\n" +
            "asset_allocation_return_contribution\n" +
            "stock_selection_return_contribution\n" +
            "interaction_return_contribution\n" +
            "adjust_asset_allocation_return_contribution\n" +
            "adjust_interaction_return_contribution\n" +
            "new_stock_return_contribution\n" +
            "port_opening_weight\n" +
            "bench_opening_weight\n" +
            "return_amount\n" +
            "exchange_return_contribution\n" +
            "other_return_contribution";
    public static void main(String[] args) {

        printMeth(table1,col1);
        printMeth(table2,col2);
    }

    private static void printMeth(String table,String cols){
        String col = cols;
        String str = "` decimal(18,2) DEFAULT NULL,";

        String [] arr = col.split("\n");
        System.out.println("create table "+table+"(\n");
        for(int i = 0;i < arr.length;i++){
            System.out.println("`"+arr[i]+str);
        }
        System.out.println(");\n");
    }

    //public static void main(String[] args) {
    //    String high = "101,112,113,2002,401002,532002,858002,2001002,2203002,2204002,2206002,9908001,10112001,10215001,10311001,10403001,10404001,10407001,10501001,10617001,";
    //    String middle = "30002002,38003002,100308002,100404002,110021001,110026001,112002002,120527001,122002001,125709002,126017001,150001002,159902002,300001002,300002002,300003002,500002001,";
    //    String low = "510050001,580010001,580015001,580025001,600001001,600100001,600195001,600398001,600701001,601186001";
    //    String interCodes = low;
    //    String[] interCodeArr = interCodes.split(",");
    //    for (int i = 0; i < interCodeArr.length; i++) {
    //        StringBuilder sb = new StringBuilder("INSERT INTO `dbhub`.`hub_tstockfinanceinfo` (`roe`, `roa`, `netprofit_growth_yoy_rate`, `revenue_growth_yoy_rate`, `inter_code`, `inform_date`) VALUES ('0.0500000', '0.00000000', '0.0000', '0.00000000', ");
    //        sb.append("\'").append(interCodeArr[i]).append("\'").append(", ");
    //        sb.append("\'").append("1").append("\'").append(");");
    //        System.out.println(sb.toString());
    //    }
    //}
}
