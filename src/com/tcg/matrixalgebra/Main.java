package com.tcg.matrixalgebra;

/**
 * @author Jose de Jesus Rodriguez Rivas
 * @version 11/10/18
 */
public class Main {

    public static void main(String[] args) {
        Matrix A = new Matrix(
                new double[]{1, 2, 3},
                new double[]{4, 5, 6}
        );
        Matrix B = new Matrix(
                new double[]{7, 8},
                new double[]{9, 10},
                new double[]{11, 12}
        );
        Matrix C = new Matrix(new double[][] {
                {2, 4, 8},
                {16, 32, 64},
                {128, 256, 512}
        });
        System.out.println(A);
        System.out.println();
        System.out.println(B);
        System.out.println();
        System.out.println(A.multiply(B));
        System.out.println();
        System.out.println(B.multiply(A));
        System.out.println();
        System.out.println(A.transpose());
        System.out.println();
        System.out.println(Matrix.zero(3));
        System.out.println();
        System.out.println(Matrix.identity(3));
        System.out.println();
        System.out.println(A.multiply(Matrix.identity(3)));
        System.out.println();
        System.out.println(ElemRowOps.swap(A, 1, 2));
        System.out.println();
        System.out.println(ElemRowOps.swap(B, 1, 3));
        System.out.println();
        System.out.println(ElemRowOps.addRows(A, 1, 2));
        System.out.println();
        System.out.println(ElemRowOps.addRows(A, 2, 1));
        System.out.println();
        System.out.println(ElemRowOps.addRows(B, 1, 3));
        System.out.println();
        System.out.println(ElemRowOps.addRows(B, 3, 1));
        System.out.println();
        System.out.println(ElemRowOps.scaleRowAndAdd(A, -1, 1, 2));
        System.out.println();
//        System.out.println(A.RREF());
        System.out.println();
        System.out.println(C.RREF());

    }

}
