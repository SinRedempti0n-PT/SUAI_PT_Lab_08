package com.sinredemption.queen;

import java.util.Stack;

public class Queen extends Thread {
    private int n;
    private int places[];
    private int column;
    private static Integer solutions = 0;
    private static Integer startedThreads = 0;
    private static int maxThreads;
    private Stack<Queen> stack;

    public Queen(int n, int threads, int column, int places[]) {
        this.n = n;
        this.maxThreads = threads;
        this.column = column;
        this.places = places;
        stack = new Stack<Queen>();
    }

    private boolean tryqueen(int row, int column) {
        boolean ok = true;
        int tmpColumn = column-1;
        for(; tmpColumn>=1; tmpColumn--) {
            if ((places[tmpColumn]-row)==0) {
                ok = false;
                break;
            }

            int rowDiff = places[column] - places[tmpColumn];

            if (rowDiff < 0)
                rowDiff = 0 - rowDiff;

            if (rowDiff == column-tmpColumn) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    public void calcQueenNum() {
        for(int i = 0; i < n ; i++ ) {
            places[column] = i;
            if (tryqueen(i, column)) {
                if (column < n) {
                    int gSk;
                    synchronized (startedThreads) {
                        gSk = startedThreads;
                    }
                    if (gSk < maxThreads) {
                        Queen kar = new Queen(n, maxThreads, column+1, places.clone());
                        kar.start();
                        stack.add(kar);
                    }
                    else {
                        column++;
                        calcQueenNum();
                        column--;
                    }
                }
                if (column==n) {
                    Queen.solutionplus();
                }
            }
        }

        while (stack.size()!=0) {
            try {
                stack.pop().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        Queen.threadplus();
        calcQueenNum();
        Queen.threadminus();
    }

    private static synchronized void solutionplus() {
        solutions++;
    }

    private static synchronized void threadplus() {
        startedThreads++;
    }

    private static synchronized void threadminus() {
        startedThreads--;
    }
    public static int getSolution() {
        int tmp = solutions;
        solutions = 0;
        return tmp;
    }
}