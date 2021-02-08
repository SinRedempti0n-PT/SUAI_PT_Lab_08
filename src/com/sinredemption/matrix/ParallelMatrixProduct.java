package com.sinredemption.matrix;

import java.util.ArrayList;

public class ParallelMatrixProduct{

    int ThreadCount;
    UsualMatrix A, BTrans;

    class ParallelProduct extends Thread{

        int threads, operations, workingOn, columns;
        UsualMatrix ResObj;

        //Thread class init
        ParallelProduct(int startPoint, UsualMatrix Res){
            ResObj = Res;
            this.threads = ThreadCount;
            this.operations = ResObj.getRows() * ResObj.getColumns();
            this.workingOn = startPoint;
            this.columns = ResObj.getColumns();
        }


        //Thread start
        @Override
        public void run() {
            ArrayList<Integer> ALine, BLine;

            while (workingOn < operations) {
                ALine = A.getLine(workingOn / columns);
                BLine = BTrans.getLine(workingOn % columns);
                int tmp = 0;
                for (int i = 0; i < ALine.size(); i++)
                        tmp += ALine.get(i) * BLine.get(i);
                ResObj.setElement(workingOn % columns, workingOn / columns, tmp);
                workingOn += threads;
            }
        }
    }

    //Class constructor
    public ParallelMatrixProduct(int threads_count){
        this.ThreadCount = threads_count;
    }

    public UsualMatrix product (UsualMatrix AMatrx, UsualMatrix BMatrx){
        //Size equal exception
        if(AMatrx.getRows() != BMatrx.getColumns())
            throw new RuntimeException("The matrices don't match in size.");
            
        //Obj Init
        UsualMatrix ResObj = new UsualMatrix(BMatrx.getRows(), AMatrx.getColumns());
        this.A = AMatrx;
        this.BTrans = new UsualMatrix(BMatrx.getColumns(), BMatrx.getRows());
        for(int column = 0; column < BTrans.getColumns(); column++)
            for(int row = 0; row < this.BTrans.getRows(); row++)
                BTrans.setElement(row, column, BMatrx.getElement(column, row));

        //Treads start
        ArrayList<ParallelProduct> threadList = new ArrayList<>();
        for(int startPoint = 0; startPoint < this.ThreadCount; startPoint++){
            ParallelProduct newThread = new ParallelProduct(startPoint, ResObj);
            threadList.add(newThread);
            newThread.start();
        }

        for(int threadNum = 0; threadNum < this.ThreadCount; threadNum++){
            try{
                threadList.get(threadNum).join();
            }catch (Exception ex){
                System.out.println("Exception has been" + " caught" + ex);
            }
        }

        return ResObj;
    }

}