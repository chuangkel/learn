package com.github.chuangkel.java_learn.base.socket;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-17 18:58
 **/
public class InterAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress[] a = Inet4Address.getAllByName("www.baidu.com");
        for(int i = 0; i < a.length;i++){
            System.out.println(a[i].getHostAddress());
        }
        InetAddress[] localhost = Inet4Address.getAllByName(null);
        for(int i = 0; i < localhost.length;i++){
            System.out.println(localhost[i].getHostAddress());
        }
    }
}
