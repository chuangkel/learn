package com.github.chuangkel.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: learn
 * @description: Combination Sum II
 * @author: chuangkel
 * @create: 2019-06-04 18:23
 **/
public class CombinationSumII {


    public static void main(String[] args) {
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(combinationSum2(candidates,target));
        //System.out.println(combinationSum21(candidates,target));;
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        for(int i = 0;i < candidates.length;i++){
            dfs(candidates, i, 0, target, result, temp);
            while ((i+1) < candidates.length && candidates[i] == candidates[i+1]){
                i++;
            }
        }
        return result;
    }

    /**
     * candidates是由小到大
     */

    public static boolean dfs(int[] candidates, int nowIndex, int sum, int target, List<List<Integer>> result, List<Integer> temp) {
        //传入 目标值 起始数组位置
        //目标剩余
        if(sum + candidates[nowIndex] > target){
            return false;
        }
        if (sum + candidates[nowIndex] == target) {
            temp.add(candidates[nowIndex]);
            result.add(new ArrayList<>(temp));
            temp.remove(temp.size() > 0 ? temp.size() - 1 : null);
            return true;
        }

        //寻找刚好比目标小的
        for (int i = nowIndex + 1; i < candidates.length; i++) {
            temp.add(candidates[i]);
            boolean go = dfs(candidates, i, sum + candidates[i], target, result, temp);
            temp.remove(temp.size() > 0 ? temp.size() - 1 : null);
            if(!go){
                return true;
            }
            while (i+1 < candidates.length && candidates[i] == candidates[i+1]){
                i++;
            }
        }
        return true;
    }
    /**
     * 总结：
     * 整体的思路很重要
     * 内心比较静，这个思路不行那就换个
     * 先写注解
     * 这么做有什么后果*/

    //public static List<List<Integer>> combinationSum21(int[] candidates, int target) {
    //    Arrays.sort(candidates);
    //    List<List<Integer>> rs = new ArrayList<List<Integer>>();
    //    List<Integer> record = new ArrayList<Integer>();
    //    for(int i=0;i<candidates.length;i++){
    //        dfs1(rs,record,candidates,target,i,0);
    //        while((i+1)<candidates.length && candidates[i+1] == candidates[i])i++;
    //    }
    //    return rs;
    //}
    //public static boolean dfs1(List<List<Integer>> rs, List<Integer> record, int[] candidates, int target, int idx, int sum){
    //    if(sum + candidates[idx] > target)return false;
    //    if(sum + candidates[idx] == target){
    //        record.add(candidates[idx]);
    //        rs.add(new ArrayList<Integer>(record));
    //        record.remove(record.size()-1);
    //        return true;
    //    }
    //    for(int i = idx+1; i<candidates.length;i++){
    //        record.add(candidates[idx]);
    //        boolean go = dfs1(rs,record,candidates,target,i,sum+candidates[idx]);
    //        record.remove(record.size()-1);
    //        if(!go)return true;
    //        while((i+1)<candidates.length && candidates[i+1] == candidates[i])i++;
    //    }
    //    return true;
    //}
}
