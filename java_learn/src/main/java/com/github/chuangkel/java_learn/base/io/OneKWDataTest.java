package com.github.chuangkel.java_learn.base.io;

import java.io.*;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2020-03-09 16:25
 **/
public class OneKWDataTest {

    public static void main(String[] args) throws IOException {
        String item = "20180926092601400000000000010003|618201809260926014010003|00658323492388598|225010|926|098|001|00961180200000001|926|20180926|20180926140003000000|300.00|0.00|1|投资人003T0赎回1000拆\n";
        int total = 100*100 * 1000;//1000W行 笔交易
        FileOutputStream fos = new FileOutputStream("F:\\01_926_trade_apply_20180926_14.txt");
        FileInputStream fis = new FileInputStream("F:\\01_926_trade_apply_20180926_14.txt");


        //BufferedReader br = new BufferedReader(new InputStreamReader(fis)) ;
        //String a = br.readLine();
        //System.out.println(a);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

        StringBuffer sb = new StringBuffer();
        int count = 0;
        for(int i = 1;i <= total;i++){
            sb.append(item);
            if(i % 500 == 0){
                count ++;
                writer.append(sb.toString());
                sb.delete(0,sb.length());
            }
        }
        System.out.printf("总：%s * 500",count);
        writer.flush();
        writer.close();
    }
}
