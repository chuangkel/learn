package com.github.chuangkel.leetcode;

import org.junit.Test;

import java.util.Map;

/**
 * 1.对应的理论算法是什么，不可凭空猜想
 *
 * @program: javaNewCharacteristic
 * @description: 152. Maximum Product Subarray
 * @author: chuangkel
 * @create: 2019-01-24 10:57
 **/
public class MaximumProductSubarray_152 {

    /**
     * @param nums
     * @return 最大子数组的乘积
     */
    public int maxProduct(int[] nums) {

        if (nums.length == 2) {
            return Math.max(Math.max(nums[0], nums[1]),nums[0] * nums[1]);
        }
        return cricle(0, nums);
    }

    /**
     * @param head
     * @param nums
     * @return head右边的最大值
     */
    public int cricle(int head, int[] nums) {
        int i = head;

        //条件
        int zero = nums[i];
        int first = zero * nums[i + 1];

        for (; i < nums.length; i++) {
            int second = first * nums[i + 2];

            //当前是0 从下一位开始
            if(nums[i] == 0){
                return Math.max(zero,cricle(i+1,nums));
            }

            //A < B --> A=B B=C C往后移动
            if (zero < first) {
                zero = first;
                continue;
            }
            //A > B, if A < C, A = C;
            if (zero > first && zero <= second) {
                zero = second;
                i++;
                continue;
            }
            //if A > C,迭代C开头
            if (zero > second) {
                return Math.max(zero, cricle(i + 1, nums));
            }
        }
        //已经最后
        if (i == nums.length)
            return zero;
        //结尾处理
        if (i == nums.length - 1) {
            //结尾中有0

        }
        return zero;
    }

    @Test
    public void test() {
//        int[] nums = {1,3,-1,-9,8};
//        int[] nums = {-2,0,-1};
//        int[] nums = {2,3,-2,4};
//        int[] nums = {-2,-3,7};
//        int[] nums = {-5,0,2};
        int[] nums = {2,-5,-2,-4,3};
//        int[] nums = {-3, 0, 1, -2};
        System.out.println(maxProduct(nums));
    }
}
