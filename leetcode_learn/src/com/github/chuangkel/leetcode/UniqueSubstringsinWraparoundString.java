package com.github.chuangkel.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: javabase
 * @description: 467. Unique Substrings in Wraparound String
 * @author: chuangkel
 * @create: 2019-02-09 15:15
 **/
public class UniqueSubstringsinWraparoundString {

    public int findSubstringInWraproundString(String p) {

        int res = 0;
        int i = 0;
        int[] sarr = new int[26];
        while (i < p.length()) {
            int n = i;
            while (n + 1 < p.length() && (int) p.charAt(n + 1) % 26 == (p.charAt(n) + 1) % 26) {
                n++;
            }
            for (int a = i; a <= n; a++) {
                sarr[p.charAt(a) - 'a'] = Math.max(sarr[p.charAt(a) - 'a'], n - a + 1);
            }
            i = n + 1;
        }
        for (int j = 0; j < 26; j++) {
            res += sarr[j];
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(('a' + 25));
        int a = findSubstringInWraproundString("cac");
        System.out.println(a);
    }
}
