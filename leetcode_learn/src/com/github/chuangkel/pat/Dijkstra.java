//package com.github.chuangkel.pat;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @program: learn
// * @description:
// * @author: chuangkel
// * @create: 2019-08-11 10:16
// **/
//public class Dijkstra {
//    static int m = Integer.MAX_VALUE;
//    static int edges[][] = {
//            {0, 10, m, 5, m},
//            {m, 0, 1, 2, m},
//            {m, m, 0, m, 4},
//            {m, 3, 9, 0, 2},
//            {7, m, 6, m, 0}
//    };
//    static int[] d = {0,m,m,m,m};
//    static List<Integer> V1 = Arrays.asList(0);
//    static List<Integer> V2 = Arrays.asList(1,2,3,4);
//    static boolean [] visited ;
//    static int [] pre = new int[edges.length];
//
//    public static void main(String[] args) {
//        for(int i = 0;i<edges.length;i++){
//            int cost = m;
//            int cost_index;
//            for(int j = 0;j<edges.length;j++){
//                if(!visited[j] && edges[i][j] < cost){
//                    cost = edges[i][j];
//                    cost_index = j;
//                }
//            }
//            for(int j = 0;j<edges.length;j++){
//                if(d[i] + edges[i][cost_index] + edges[cost_index][j] < edges[i][j]){
//                    d[j] = d[i] + edges[i][cost_index] + edges[cost_index][j];
//                    pre[j] = cost_index;
//                }
//            }
//        }
//    }
//}
