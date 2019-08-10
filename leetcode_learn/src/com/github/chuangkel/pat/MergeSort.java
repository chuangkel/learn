package com.github.chuangkel.pat;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-08-05 12:36
 **/
public class MergeSort {

    public static void mergeSort(int[] nums, int begin, int end) {
        if (begin == end) {
            return;
        }
        if (begin + 1 == end) {
            if (nums[begin] > nums[end]) {
                int temp = nums[begin];
                nums[begin] = nums[end];
                nums[end] = temp;
            }
            return;
        }
        int medium = (begin + end) / 2;
        mergeSort(nums, begin, medium);
        mergeSort(nums, medium + 1, end);
        int[] tempArr = new int[end - begin + 1];
        int i = begin, j = medium + 1;
        int index = 0;
        while (i <= medium && j <= end ) {
            if (nums[i] <= nums[j]) {
                tempArr[index] = nums[i];
                i += 1;
            } else {
                tempArr[index] = nums[j];
                j += 1;
            }
            index += 1;
        }
        while (i <= medium) {
            tempArr[index++] = nums[i++];
        }
        while (j <= end){
            tempArr[index++] = nums[j++];
        }
        for(int k = begin; k <= end;k++){
            nums[k] = tempArr[k-begin];
        }
    }

    public static void main(String[] args) {
        int [] nums = {1,1,1,1,1,1,1,1,1};
        mergeSort(nums,0,nums.length - 1);
        System.out.println(nums);
    }
}

