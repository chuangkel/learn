package com.github.chuangkel.java_learn.leetcode;

class Solution {

    public static void main(String[] args) {
        int [] cardPoints = {1,79,80,1,1,1,200,1};
        int k = 3;
        int re = maxScore(cardPoints,k);
        System.out.println();
    }
    public static int maxScore(int[] cardPoints, int k) {

        int sum = 0;
        for(int i = 0;i < k;i++){
            sum += cardPoints[i];
        }
        int t = k-1;
        int m = sum;
        for(int i = cardPoints.length-1; i >= cardPoints.length - k; i--){
            sum = sum  - cardPoints[t--] + cardPoints[i];
            if(sum > m){
                m = sum;
            }
        }
        return m;
    }
}