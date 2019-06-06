package com.github.chuangkel.leetcode;

/**
 * @program: learn
 * @description: 42. Trapping Rain Water
 * @author: chuangkel
 * @create: 2019-06-06 09:23
 **/
public class TrappingRainWater {

    public static void main(String[] args) {

        //int[] height = new int[]{9,6,8,8,5,6,3};
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }

    /**
     * 左边高 -- 按照右边的高度算
     * 右边高 -- 按照左边的高度算
     * 都是要寻找到左右两边
     */
    public static int trap(int[] height) {

        if(height.length <3) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int j = 0;
        while (j < height.length - 1 && height[j] <= height[j + 1]) {
            j++;
        }
        left = j;

        int k = height.length - 1;
        while (k > 0 && height[k] <= height[k - 1]) {
            k--;
        }
        right = k;

        for (int i = left + 1; i <= right; i++) {

            if (height[left] > height[i]) {
                if (i != right) {
                    continue;
                }
                //左边高  按照右边的高度算
                for (int n = right - 1; n > left; n--) {
                    if (height[n] < height[right]) {
                        sum += height[right] - height[n];
                        continue;
                    }
                    right = n;
                }
                return sum;
            }
            //右边高  按照左边的高度算
            for (int n = left + 1; n < i; n++) {
                sum += height[left] - height[n];
            }
            left = i;
        }
        return sum;
    }
}
//把可能性都列出来
//恒多时候都是去做的时候才知道事情不是想象的那个样子，