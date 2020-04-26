package com.github.chuangkel.java_learn.mercylibz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b)->b.sum-a.sum);
        for(int i = 0;i < nums1.length;i++){
            for(int j = 0; j < nums2.length; j++){
                int sum = nums1[i] + nums2[j];
                if(queue.size() >= k && queue.peek().sum > sum){
                    queue.poll();
                }
                Node n = new Node();
                n.sum = sum;
                List<Integer> list = Arrays.asList(nums1[i],nums2[j]);
                n.arr = list;
                queue.offer(n);
            }
        }
        List<List<Integer>> re = new ArrayList<>();
        while(!queue.isEmpty()){
            re.add(queue.poll().arr);
        }
        return re;
    }

    class Node{
        int sum;
        List<Integer> arr;
    }
}