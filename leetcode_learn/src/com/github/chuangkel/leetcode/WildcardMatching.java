package com.github.chuangkel.leetcode;

import java.util.regex.Pattern;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-06-10 10:09
 **/
public class WildcardMatching {

    public static boolean isMatch(String ss, String pp) {
        int m = ss.length();
        int n = pp.length();
        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();
        boolean[][] f = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0 && j == 0) {
                    f[i][j] = true;
                    continue;
                }
                if (j == 0) {
                    f[i][j] = false;
                    continue;
                }
                if (p[j - 1] != '*') {
                    if (i > 0 && (s[i - 1] == p[j - 1] || p[j - 1] == '?')) {
                        f[i][j] |= f[i - 1][j - 1];
                    }
                } else {
                    if (j > 0) {
                        f[i][j] |= f[i][j - 1];
                    }
                    if (i > 0) {
                        f[i][j] |= f[i - 1][j];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static void main(String[] args) {
        String s,p;
        s = "acdcb";
        p = "a*c?b";
        isMatch(s,p);
    }
}
