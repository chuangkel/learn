package com.github.chuangkel.leetcode;

import org.junit.Test;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @program: javaNewCharacteristic
 * @description: Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 * @author: yaoqb23911
 * @create: 2019-01-08
 **/
public class ContainsDuplicate3_220 {

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums==null||nums.length<2||k<0||t<0)
            return false;

        TreeSet<Long> set = new TreeSet<Long>();
        for(int i=0; i<nums.length; i++){
            long curr = (long) nums[i];
            long leftBoundary = (long) curr-t;
            long rightBoundary = (long) curr+t+1; //right boundary is exclusive, so +1
            SortedSet<Long> sub = set.subSet(leftBoundary, rightBoundary);
            if(sub.size()>0)
                return true;
            set.add(curr);
            if(i>=k){ // or if(set.size()>=k+1)
                set.remove((long)nums[i-k]);
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] test = {1,2,3,1};

        System.out.println(ContainsDuplicate3_220.containsNearbyAlmostDuplicate(test,3,0));;
    }

}
