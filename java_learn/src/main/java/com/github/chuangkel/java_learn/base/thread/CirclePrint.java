package com.github.chuangkel.java_learn.base.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class CirclePrint{

    private static ReentrantLock lock = new ReentrantLock();
  	private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();
    private static String c;
  	
  	private static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args){
    	PrintThread task1 = new PrintThread(conditionA,conditionB,conditionC,"阿",latch);
        PrintThread task2 = new PrintThread(conditionB,conditionA,conditionC,"里",latch);
        PrintThread task3 = new PrintThread(conditionC,conditionA,conditionB,"巴巴",latch);
      
       new Thread(task1).start();
       new Thread(task2).start();
       new Thread(task3).start();
    }
  
  	public static class PrintThread implements Runnable{
      ReentrantLock lock;
      Condition conditionA;
      Condition conditionB;
      Condition conditionC;
      CountDownLatch latch;
      String c;
      public PrintThread(Condition conditionA,Condition conditionB,Condition conditionC,String c,CountDownLatch latch){
      	this.conditionA = conditionA;
        this.conditionB = conditionB;
        this.conditionC = conditionB;
        this.c = c;
        this.latch = latch;
      }
      public void run(){
        
      	try{
          if("阿".equals(c)){
              lock.lock();
              latch.countDown();
          }else{
              latch.wait();
              lock.lock();
          }

          for(int i = 0;i < 10; i++){
            System.out.print(c);
            if("阿".equals(c)){
            	conditionB.signal();
              	if(i < 9){
                  conditionA.wait(); 
                }
            }
            if("里".equals(c)){
              	conditionC.signal();
              	if(i < 9){
                  conditionB.wait(); 
                }
            }
            if("巴巴".equals(c)){
              	conditionA.signal();
              	if(i < 9){
                  conditionC.wait(); 
                }
            }
          	
            
          }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
        	lock.unlock();
        }
      }
    }
}