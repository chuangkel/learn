package com.github.chuangkel.pat;

import java.util.Arrays;

/**
 * @program: learn
 * @description: 堆排序：大堆排序 堆顶大于2*n+1 、2*n+2,左边大于右边
 * @author: chuangkel
 * @create: 2019-08-10 09:43
 **/
public class HeapSort {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, -1};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void heapSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        for (int j = nums.length - 1; j > 0; j--) {
            createHeap(nums, j);
            swap(nums, 0, j);
        }
        if (nums[0] > nums[1]) {
            swap(nums, 0, 1);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * @param nums
     * @param lastIndex 未排序数组最后一个下标
     */
    public static void createHeap(int[] nums, int lastIndex) {
        for (int i = lastIndex / 2; i >= 0; i--) {
            if (2 * i + 2 <= lastIndex && nums[2 * i + 1] < nums[2 * i + 2]) {
                int temp = nums[2 * i + 1];
                nums[2 * i + 1] = nums[2 * i + 2];
                nums[2 * i + 2] = temp;
            }
            if (2 * i + 1 <= lastIndex && nums[i] < nums[2 * i + 1]) {
                int temp = nums[i];
                nums[i] = nums[2 * i + 1];
                nums[2 * i + 1] = temp;
            }
        }
    }
}
