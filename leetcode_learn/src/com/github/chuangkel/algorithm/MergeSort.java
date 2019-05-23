package com.algorithm;

import java.util.Arrays;

/**
 * @program: javaNewCharacteristic
 * @description: 归并排序,需开辟同等大小的空间。
 * 归并排序采用分治法的思想：分而治之
 * @author: yaoqb23911
 * @create: 2019-01-13
 **/
public class MergeSort {

    public static void main(String[] args) {

        int [] nums = {1,3,2,4,5,0};
        int [] temp = new int[nums.length];
        mergeSortSplit(nums,0,nums.length-1,temp);
        System.out.println(Arrays.toString(nums));
    }

    public static void mergeSortSplit(int [] nums, int x, int y, int [] temp){
        if(x < y){
            int medium =(x + y) / 2;
            mergeSortSplit(nums,x,medium,temp);//拆分
            mergeSortSplit(nums,medium + 1,y,temp);
            mergeSortMerge(nums,x,medium,y,temp);// 合并
        }
    }

    public static void mergeSortMerge(int [] nums, int x, int medium, int y, int [] temp){
        int i = 0;
        int p = x;
        int q = medium + 1;
        while(p <= medium && q <= y){
            if(nums[p] < nums[q]){
                temp[i] = nums[p];
                p ++;
            }else{
                temp[i] = nums[q];
                q ++;
            }
            i ++;
        }
        while(p <= medium){
            temp[i] = nums[p];
            p ++;
            i ++;
        }
        while(q <= y){
            temp[i] = nums[q];
            q ++;
            i ++;
        }
        i = 0;
        while(x <= y){
            nums[x++] = temp[i++];
        }
    }
}
