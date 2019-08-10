package com.github.chuangkel.pat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @program: 深度优先搜索
 * @description: 1.从起点出发v 任意选一个与v相连的点w
 * 2.重复一，若w没有可访问点，退回上一个访问的点v
 * @author: chuangkel
 * @create: 2019-08-01 14:16
 **/
public class PublicBikeManagement {

    public static void main(String[] args) {
        //边的邻接矩阵
        int [][] edgesMatrix = null;

        Scanner scanner = new Scanner(System.in);
        //cmax 站的最大容量， n站的总数量， sp问题站的index， m站之间边的条数
        if(scanner.hasNext()){
            String[] line = scanner.nextLine().split(" ");
            List<Integer> lineInt =  Arrays.stream(line).map(a->{return Integer.parseInt(a);}).collect(Collectors.toList());
            edgesMatrix = new int[lineInt.get(1).intValue() + 1][lineInt.get(1).intValue() + 1];
        }
        for(int i = 0;i < edgesMatrix.length;i++){
            for(int j = 0;j < edgesMatrix.length;j++){
                edgesMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
        //每个站车的实际数量 按照index排列 0,1,2 对应的实际数量为 ， ， ，
        if(scanner.hasNext()){
            String[] line = scanner.nextLine().split(" ");
            List<Integer> lineInt =  Arrays.stream(line).map(a->{return Integer.parseInt(a);}).collect(Collectors.toList());
        }
        //两个站之间的距离 index1 index2 距离
        while (scanner.hasNext()){
            String input = scanner.nextLine();
            if("".equals(input)){continue;}
            String[] line = input.split(" ");
            List<Integer> lineInt =  Arrays.stream(line).map(a->{return Integer.parseInt(a);}).collect(Collectors.toList());
            //无向图
            edgesMatrix[lineInt.get(0).intValue()][lineInt.get(1).intValue()] = lineInt.get(2).intValue();
            edgesMatrix[lineInt.get(1).intValue()][lineInt.get(0).intValue()] = lineInt.get(2).intValue();
        }

        floyd(edgesMatrix);
    }

    /**
     *  *遍历  edgesMatrix[0][i] edgesMatrix[i][0]
     *     *遍历edgesMatrix[i][j]
     * @param edgesMatrix
     */
    public static void  floyd(int [][] edgesMatrix){
        System.out.println("edgesMatrix's length " + edgesMatrix.length );
        int [][] paths = new int[edgesMatrix.length][edgesMatrix.length];
        for(int i = 0;i<paths.length;i++){
            for(int j = 0;j<paths.length;j++){
                paths[i][j] = -1;
            }
        }
        for(int i = 0; i < edgesMatrix.length; i++){
            for(int j = 0; j < edgesMatrix.length;j ++){
                if(i == j){ continue;}
                for(int k = 0; k < edgesMatrix.length;k ++){
                    if(i == k){ continue; }
                    if(edgesMatrix[j][i] + edgesMatrix[i][k] < edgesMatrix[j][k]){
                        edgesMatrix[j][k] = edgesMatrix[j][i] + edgesMatrix[i][k];
                        //update paths
                        paths[j][k] = i;
                    }
                }
            }
        }
        findPaths(paths,edgesMatrix);
    }

    public static void findPaths(int[][] paths,int[][] edgesMatrix){
        for(int i = 0;i<paths.length;i++){
            for(int j = i+1;j<paths.length;j++){
                System.out.printf("点%s到点%s,权重w:%s ，paths: %s --> ",i+1,j+1,edgesMatrix[i][j],i+1);
                int temp = paths[i][j];
                int col = j;
                if(temp != -1){
                    while (true){
                        col += 1;
                        if(temp == col){
                            System.out.printf("%s --> ",temp + 1);
                            if(col + 1 < paths.length) {
                                temp = paths[temp][col + 1];
                            }
                        }
                        if(col > paths.length){
                            break;
                        }
                    }
                }else{
                    continue;
                }
                System.out.printf("%s",j+1);
            }
        }
    }

    /**
     *  1.从协调中心带出总的bike数量 2.经过的路径 3.带回去的bike数量
     * @param paths
     * @param edgesMatrix
     */
    public static void findOne2OnePaths(int[][] paths,int[][] edgesMatrix,int goal){
        List<List<Integer>> ps = new ArrayList<>();
        System.out.printf("点%s到点%s,权重w:%s ，paths: %s --> ",1,goal+1,edgesMatrix[0][goal],1);
        int temp = paths[0][goal];
        int col = goal;
        int index = 0;
        List<Integer> tlist = new ArrayList<>();
        for(int i = 1; i < edgesMatrix.length;i++){
            if(paths[index][i] != -1){
                tlist.addAll(tlist);
            }
        }
        if(temp != -1){
            while (true){
                col += 1;
                if(temp == col){
                    System.out.printf("%s --> ",temp + 1);
                    if(col + 1 < paths.length) {
                        temp = paths[temp][col + 1];
                    }
                }
                if(col > paths.length){
                    break;
                }
            }
        }
        System.out.printf("%s",goal + 1);
    }
}
