package com.github.chuangkel.java_learn.interview;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: learn
 * @description: 评测题目: 无
 * 1. 请用java写代码实现如下的linux命令：cat /home/a.log | grep 'abc' | sort | uniq
 * @author: chuangkel
 * @create: 2019-10-22 09:19
 **/
public class GrepSortUniq {

    public static void main(String[] args) throws IOException {
        String target = args[0];
        int rows = Integer.parseInt(args[1]);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("F://a.txt"));
        String line;
        String[] heap = new String[rows];
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.contains(target)) {
                continue;
            }
            //入堆 [0]不存放
            if (rows > 1) {
                heap[rows - 1] = line;
                rows--;
                continue;
            }
            if (rows == 1) {
                initHeap(heap);
                rows--;
                continue;
            }
            if (line.compareTo(heap[heap.length - 1]) <= 0) {
                continue;
            }
            if(line.compareTo(heap[1])<=0){
                String temp = heap[1];
                heap[1] = line;
                heap[heap.length - 1] = temp;
                changeHeap(heap,heap.length-1);
                continue;
            }
            heap[heap.length - 1] = line;
            changeHeap(heap,heap.length-1);
        }
        System.out.println(heap.toString());

        //lines.add("abcd");
        //lines.add("abcd");
        //lines.add("3abcdf");
        //lines.add("abcdg");
        //lines.add("1abcdd");
        //List<String> result = lines.stream().filter(a -> {
        //    return a.contains("abc");
        //})
        //        .collect(Collectors.toSet()).stream()
        //        .sorted((a, b) -> {
        //            return a.compareTo(b);
        //        })
        //        .collect(Collectors.toList());
        //
        //System.out.println(result.toString());
    }

    /**
     * 初始化 1到heap.length 堆
     * @param heap
     */
    private static void initHeap(String[] heap) {
        for(int i = 1;i<=heap.length / 2;i++){
            int end = heap.length - i;
            for (int j = end / 2 ; j > 0; j --) {
                if(j*2 <= end ) {
                    if (heap[j].compareTo(heap[j * 2]) < 0) {
                        swap(heap, j, j * 2);
                    }
                    if(j * 2 + 1 <= end){
                        if (heap[j].compareTo(heap[j * 2 + 1]) < 0) {
                            swap(heap, j, j * 2 + 1);
                        }
                        if (heap[j * 2].compareTo(heap[j * 2 + 1]) < 0) {
                            swap(heap, j * 2, j * 2 + 1);
                        }
                    }
                }
            }
            swap(heap,1,heap.length-i);
        }
    }

    /**
     * 在1到end范围内，最小元素上浮
     * @param heap
     * @param end
     */
    private static void changeHeap(String[] heap,int end) {
        for (int i = end >> 1 ; i > 0; i --) {
            if(i*2 <= end ) {
                if (heap[i].compareTo(heap[i * 2]) > 0) {
                    swap(heap, i, i * 2);
                }
                if(i * 2 + 1 <= end){
                    if (heap[i].compareTo(heap[i * 2 + 1]) > 0) {
                        swap(heap, i, i * 2 + 1);
                    }
                    if (heap[i * 2].compareTo(heap[i * 2 + 1]) > 0) {
                        swap(heap, i * 2, i * 2 + 1);
                    }
                }
            }
        }

    }

    private static void swap(String[] heap, int i, int j) {
        if (i >= heap.length || j >= heap.length || i < 1 || j < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        String temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

}
