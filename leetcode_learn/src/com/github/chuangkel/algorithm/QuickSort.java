package com.algorithm.sortalgrithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * @program: javaNewCharacteristic
 * @description: 快速排序算法
 * @author: yaoqb23911
 * @create: 2019-01-10
 **/
public class QuickSort {

    @Test
    public void test(){
        int[] nums = {2,1};
        quickSorted(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
    public void quickSorted(int nums[],int left,int right){
        if(left > right)
            return;
        int i = left;
        int j = right;
        //left不能填0
        int key = nums[left];
        while(i != j){
            //等号是排序相等的数造成死循环
            while (nums[j] >= key && i < j){
                j --;
            }

            while(nums[i] <= key && i < j){
                i ++;
            }
            if(i < j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }

        }

        nums[left] = nums[i];
        nums[i] = key;

        quickSorted(nums,left,i-1);
        quickSorted(nums,i+1,right);
    }
}
