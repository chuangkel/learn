package com.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @program: javaNewCharacteristic
 * @description: 332. Reconstruct Itinerary
 * @author: chuangkel
 * @create: 2019-01-21
 **/
public class ReconstructItinerary_332 {
    public List<String> findItinerary(String[][] tickets) {return null;}
    public List<String> findItineraryCircle(String key ,int count,Map<String,List<String>> map) {return null;}


    @Test
    public void test() {
//        String[][] inp = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
//        String[][] inp = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
//        String[][] inp = {{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}};
//        List<String> list = findItineraryCircle(inp);
//        System.out.println(list.toString());
//        System.out.println("aab".compareTo("aac"));
//
        Map<String, String> map = new HashMap<>();
        map.put("a","B");

        Map<String,String> cloneMap = (Map<String,String>) ((HashMap<String, String>) map).clone();
        cloneMap.put("b","C");

        System.out.println(map.entrySet().toString());

    }
}
