package com.tcg.matrixalgebra;

import java.util.Collection;
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
        if(!isMatrix(elements)) {
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
        if(!isMatrix(elements)) {
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
        if(!isMatrix(rows)) {
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

    @Override
    public Matrix vectorAddition(Matrix other) {
        if(this.rows != other.getRows() || this.cols != other.getCols()) {
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

    private static boolean isMatrix(Vector... rows) {
        if(rows.length <= 0) return false;
        int size = rows[0].getLength();
        boolean result = true;
        for (int i = 1; i < rows.length; i++) {
            result = (rows[i].getLength() == size);
        }
        return result;
    }

    private static boolean isMatrix(double[]... elements) {
        if(elements.length <= 0) return false;
        int size = elements[0].length;
        boolean result = true;
        for(int i = 1; i < elements.length && result; i++) {
            result = (elements[i].length == size);
        }
        return result;
    }

    private static boolean isMatrix(List<List<Double>> elements) {
        if(elements.isEmpty()) return false;
        int size = elements.get(0).size();
        boolean result = true;
        for (int i = 1; i < elements.size() && result; i++) {
            result = (elements.get(i).size() == size);
        }
        return result;
    }

}
