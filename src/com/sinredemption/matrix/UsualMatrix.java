package com.sinredemption.matrix;

import java.util.ArrayList;
import java.util.Random;

public final class UsualMatrix{
    ArrayList<ArrayList<Integer>> array;
    int rows, columns;

    public UsualMatrix(int row_count, int column_count){
        this.rows = row_count;
        this.columns = column_count;
        ArrayList<ArrayList<Integer>> formas = new ArrayList<>();
        Random rdn = new Random();
        for(int column = 0; column < this.columns; column++){
            ArrayList<Integer> arr = new ArrayList<>();
            for(int row = 0; row < this.rows; row++)
                arr.add(rdn.nextInt(10));
            formas.add(arr);
        }
        array = formas;
    }

    public void setElement(int row, int column, int value) { array.get(column).set(row, value); }

    public int getElement(int row, int column) { return array.get(column).get(row); }

    public int getRows(){ return rows; }

    public int getColumns(){ return columns; }

    public ArrayList<Integer> getLine(int nRow){ return array.get(nRow); }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            sb.append("[ ");
            sb.append(getElement(0, i));
            for (int n = 1; n < rows; n++)
                sb.append(", " + Integer.toString(getElement(n, i)));
            sb.append(" ]\n");
        }
        return sb.toString();
    }

    public UsualMatrix product(UsualMatrix a){
        if(this.rows != a.columns || this.columns != a.rows)
            throw new RuntimeException("The matrices don't match in size.");
        UsualMatrix tmpOMatrix = new UsualMatrix(a.rows, this.columns);
        for (int i = 0; i < this.columns; i++) {
            for (int n = 0; n < a.rows; n++) {
                int result = 0;
                for (int j = 0; j < this.rows; j++) {
                    result += this.getElement(j, i) * a.getElement(n, j);
                }
                tmpOMatrix.setElement(n, i, result);
            }
        }
        return tmpOMatrix;
    }
    
}
