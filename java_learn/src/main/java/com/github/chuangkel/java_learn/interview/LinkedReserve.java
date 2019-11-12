package com.github.chuangkel.java_learn.interview;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-11-07 09:12
 **/
public class LinkedReserve {

    public static void main(String[] args) {
        Node head = new Node();
        head.value = 1;
        Node n = head;
        for(int i = 2;i<10;i++){
            Node node = new Node();
            node.value = i;
            n.next = node;
            n = n.next;
        }
        n.next = null;
        for(Node i = head;i != null;i = i.next){
            System.out.print(String.valueOf(i.value)+" ");
        }
        Node newHead = reserveLinked(head);
        for(Node i = newHead;i != null;i = i.next){
            System.out.print(String.valueOf(i.value)+" ");
        }

    }
    public static Node reserveLinked(Node head){
        Node p = head.next;
        head.next = null;
        while (p != null){
            Node q = p.next;
            p.next = head;
            head = p;
            p = q;
        }
        return head;
    }
    private static class Node{
        Node next;
        int value;
    }
}
