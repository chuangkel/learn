package com.github.chuangkel.leetcode;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: javaNewCharacteristic
 * @description: 468. Validate IP Address
 * @author: chuangkel
 * @create: 2019-01-14
 **/
public class ValidateIPAddress_468 {
    public String validIPAddress(String IP) {
        if(IP == null || "".equals(IP))
            return "Neither";
        String[] ips = null;
        if (IP.contains(":")) {
            ips = IP.split(":");
            if (8 == ips.length && isIpV6(IP)) {
                return "IPv6";
            }
        }
        if (IP.contains(".")) {
            ips = IP.split("\\.");
            if (4 == ips.length && ipV4(IP)) {
                return "IPv4";
            }
        }
        return "Neither";
    }

    /**
     *
     * @param IP
     * @return
     */
    public boolean ipV4(String IP) {
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|1\\d|[1-9])"
                + "(\\.(1\\d{2}|2[0-4]\\d|25[0-5]|1\\d|\\d)){3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(IP);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private boolean isIpV6(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        String[] splits = ip.split(":", - 1);
        if (splits.length != 8) return false;
        for (int i = 0; i < 8; i++) {
            try {
                int val = Integer.parseInt(splits[i], 16);
                //1 << 16 即1左移16位
                if (val < 0 || val > (1 << 16) - 1) return false;
            } catch (Exception e) {
                return false;
            }
            if (splits[i].charAt(0) == '-' || splits[i].charAt(0) == '+') return false;
            if (splits[i].length() > 4) return false;
        }
        return true;
    }

    @Test
    public void test() {
        validIPAddress("192.168.1.");
        validIPAddress("2001:db8:85a3:0::8a2E:0370:7334");
        System.out.println(1 << 16);
    }
}
