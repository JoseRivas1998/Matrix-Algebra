package com.tcg.matrixalgebra;

/**
 * @author Jose de Jesus Rodriguez Rivas
 * @version 11/10/18
 */
public class IncompatibleVectorException extends RuntimeException {

    public enum Type {
        INCORRECT_DIMENSIONS("The operation is not allowed because the two vectors do not have correct dimensions"),
        INVALID_MATRIX("The given 2D array is not a matrix");

        final String message;

        Type(String message) {
            this.message = message;
        }
    }


    public IncompatibleVectorException(Type type) {
        super(type.message);
    }

}
