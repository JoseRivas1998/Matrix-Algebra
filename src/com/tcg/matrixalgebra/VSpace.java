package com.tcg.matrixalgebra;

/**
 * @author Jose de Jesus Rodriguez Rivas
 * @version 11/10/18
 */
public interface VSpace<T> {

    T vectorAddition(T other);
    T scalarMultiplication(double scalar);

}
