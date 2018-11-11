package com.tcg.matrixalgebra;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose de Jesus Rodriguez Rivas
 * @version 11/10/18
 */
public class Matrix implements VSpace<Matrix> {

    private double items[][];
    private int rows;
    private int cols;

    public Matrix(double[]... elements) {
        if (!isMatrix(elements)) {
            throw new IncompatibleVectorException(IncompatibleVectorException.Type.INVALID_MATRIX);
        }
        this.rows = elements.length;
        this.cols = elements[0].length;
        this.items = new double[this.rows][this.cols];
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                this.items[r][c] = elements[r][c];
            }
        }
    }

    public Matrix(List<List<Double>> elements) {
        if (!isMatrix(elements)) {
            throw new IncompatibleVectorException(IncompatibleVectorException.Type.INVALID_MATRIX);
        }
        this.rows = elements.size();
        this.cols = elements.get(0).size();
        this.items = new double[this.rows][this.cols];
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                this.items[r][c] = elements.get(r).get(c);
            }
        }
    }

    public Matrix(Vector... rows) {
        if (!isMatrix(rows)) {
            throw new IncompatibleVectorException(IncompatibleVectorException.Type.INVALID_MATRIX);
        }
        this.rows = rows.length;
        this.cols = rows[0].getLength();
        this.items = new double[this.rows][this.cols];
        for (int r = 0; r < this.rows; r++) {
            double[] row = rows[r].toArray();
            for (int c = 0; c < this.cols; c++) {
                this.items[r][c] = row[c];
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Vector[] rowSet() {
        Vector[] rowSp = new Vector[this.rows];
        for (int r = 0; r < this.items.length; r++) {
            rowSp[r] = new Vector(this.items[r]);
        }
        return rowSp;
    }

    public Vector[] colSet() {
        Vector[] colSet = new Vector[this.cols];
        for(int c = 0; c < this.cols; c++) {
            ArrayList<Double> col = new ArrayList<>();
            for (int r = 0; r < this.rows; r++) {
                col.add(this.items[r][c]);
            }
            colSet[c] = new Vector(col);
        }
        return colSet;
    }

    @Override
    public Matrix vectorAddition(Matrix other) {
        if (this.rows != other.getRows() || this.cols != other.getCols()) {
            throw new IncompatibleVectorException(IncompatibleVectorException.Type.INCORRECT_DIMENSIONS);
        }
        double[][] newMatrix = new double[this.rows][this.cols];
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                newMatrix[r][c] = this.items[r][c] + other.items[r][c];
            }
        }
        return new Matrix(newMatrix);
    }

    @Override
    public Matrix scalarMultiplication(double scalar) {
        double[][] newMatrix = new double[this.rows][this.cols];
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                newMatrix[r][c] = this.items[r][c] * scalar;
            }
        }
        return new Matrix(newMatrix);
    }

    public Matrix multiply(Matrix other) {
        if(this.cols != other.getRows()) {
            throw new IncompatibleVectorException(IncompatibleVectorException.Type.INCORRECT_DIMENSIONS);
        }
        double[][] newMatrix = new double[this.rows][other.getCols()];
        Vector[] rowSet = this.rowSet();
        Vector[] colSet = other.colSet();
        for (int r = 0; r < newMatrix.length; r++) {
            for (int c = 0; c < newMatrix[r].length; c++) {
                newMatrix[r][c] = rowSet[r].dotProduct(colSet[c]);
            }
        }
        return new Matrix(newMatrix);
    }

    public Matrix transpose() {
        return new Matrix(this.colSet());
    }

    public Matrix RREF() {
        Matrix rref = new Matrix(this.items);
        for (int c = 0; c < rref.cols; c++) {
            for (int r = 0; r < rref.rows; r++) {
                if(c == r) continue;
                if(c >= this.rows) continue;
                if(rref.colSet()[c].equals(Vector.zeros(this.cols))) continue;
                double scalar = - (rref.items[r][c] / rref.items[c][c]);
                rref = ElemRowOps.scaleRowAndAdd(rref, scalar, c + 1, r + 1);
            }
        }
        for (int r = 0; r < this.rows; r++) {
            rref = ElemRowOps.scaleRow(rref, 1 / rref.items[r][r], r + 1);
        }
        return rref;
    }

    public static Matrix zero(int size) {
        Vector[] rows = new Vector[size];
        for (int i = 0; i < size; i++) {
            rows[i] = Vector.zeros(size);
        }
        return new Matrix(rows);
    }

    public static Matrix identity(int size) {
        Vector[] rows = new Vector[size];
        for (int i = 0; i < size; i++) {
            rows[i] = Vector.singleOne(size, i + 1);
        }
        return new Matrix(rows);
    }

    private static boolean isMatrix(Vector... rows) {
        if (rows.length <= 0) return false;
        int size = rows[0].getLength();
        boolean result = true;
        for (int i = 1; i < rows.length; i++) {
            result = (rows[i].getLength() == size);
        }
        return result;
    }

    private static boolean isMatrix(double[]... elements) {
        if (elements.length <= 0) return false;
        int size = elements[0].length;
        boolean result = true;
        for (int i = 1; i < elements.length && result; i++) {
            result = (elements[i].length == size);
        }
        return result;
    }

    private static boolean isMatrix(List<List<Double>> elements) {
        if (elements.isEmpty()) return false;
        int size = elements.get(0).size();
        boolean result = true;
        for (int i = 1; i < elements.size() && result; i++) {
            result = (elements.get(i).size() == size);
        }
        return result;
    }

    @Override
    public String toString() {
        Vector[] rowSp = this.rowSet();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < rowSp.length; i++) {
            str.append(rowSp[i]);
            if (i != rowSp.length - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }
}
