package com.tcg.matrixalgebra;

/**
 * @author Jose de Jesus Rodriguez Rivas
 * @version 11/10/18
 */
public class ElemRowOps {

    public static Matrix swap(Matrix A, int row1, int row2) {
        Vector[] rows = A.rowSet();
        Vector temp = rows[row1 - 1];
        rows[row1 - 1] = rows[row2 - 1];
        rows[row2 - 1] = temp;
        return new Matrix(rows);
    }

    public static Matrix addRows(Matrix A, int row1, int row2) {
        Vector[] rows = A.rowSet();
        rows[row2 - 1] = rows[row1 - 1].vectorAddition(rows[row2 - 1]);
        return new Matrix(rows);
    }

    public static Matrix scaleRow(Matrix A, double scalar, int row) {
        Vector[] rows = A.rowSet();
        rows[row - 1] = rows[row - 1].scalarMultiplication(scalar);
        return new Matrix(rows);
    }

    public static Matrix scaleRowAndAdd(Matrix A, double scalar, int row1, int row2) {
        Vector[] rows = A.rowSet();
        rows[row2 - 1] = rows[row1 - 1].scalarMultiplication(scalar).vectorAddition(rows[row2 - 1]);
        return new Matrix(rows);
    }

}
