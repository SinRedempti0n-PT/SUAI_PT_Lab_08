package com.sinredemption;

import com.sinredemption.matrix.*; //Import all Classes from ./matrix
import com.sinredemption.queen.Queen;

public class Main {

    public static void main(String[] args) {
        /*UsualMatrix a = new UsualMatrix(2000, 2000);    //Create first Matrix
        System.out.println(a.toString());               //Print that Matrix
        UsualMatrix b = new UsualMatrix(2000, 2000);    //Create second matrix
        System.out.println(b.toString());               //Print that Matrix


        long temp = System.currentTimeMillis();                         //Save current time
        UsualMatrix res = a.product(b);                                 //Product Matrix and place result in res
        temp = System.currentTimeMillis() - temp;                       //Calculate processing time
        System.out.println("Time : " + temp);                           //Print elapsed time

        ParallelMatrixProduct newObj = new ParallelMatrixProduct(12);   //New product class 12 threads
        temp = System.currentTimeMillis();                              //Save current time
        res = newObj.product(a, b);                                     //Product Matrix and place result in res
        temp = System.currentTimeMillis() - temp;                       //Calculate processing time
        System.out.println("Time : " + temp);     */                      //Print elapsed time

        int n = Integer.parseInt(args[0]);
        Queen queens = new Queen(n, 1, 1, new int[n+1]);
        long startedTime = System.currentTimeMillis();
        queens.start();
        try {
            queens.join();
        }catch (Exception e){
            System.err.println(e.getCause());
        }
        System.out.println("Solutions: " + Queen.getSolution());
        System.out.println("Working time: " + (float)(System.currentTimeMillis() - startedTime) / 1000 + " s.");

        int maxThreads = Integer.parseInt(args[1]);
        Queen queensMulti = new Queen(n, maxThreads, 1, new int[n+1]);
        startedTime = System.currentTimeMillis();
        queensMulti.start();
        try {
            queensMulti.join();
        }catch (Exception e){
            System.err.println(e.getCause());
        }
        System.out.println("Solutions: " + Queen.getSolution());
        System.out.println("Working time: " + (float)(System.currentTimeMillis() - startedTime) / 1000 + " s.");
    }
}
