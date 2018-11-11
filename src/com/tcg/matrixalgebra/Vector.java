package com.tcg.matrixalgebra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Jose de Jesus Rodriguez Rivas
 * @version 11/10/18
 */
public class Vector implements VSpace<Vector> {

    private double[] items;
    private int length;

    public Vector(double... elements) {
        this.length = elements.length;
        this.items = new double[this.length];
        for (int i = 0; i < this.length; i++) {
            this.items[i] = elements[i];
        }
    }

    public Vector(List<Double> elements) {
        this.length = elements.size();
        this.items = new double[this.length];
        for (int i = 0; i < this.length; i++) {
            this.items[i] = elements.get(i);
        }
    }

    public Vector(Set<Double> elements) {
        this(new ArrayList<>(elements));
    }

    public static Vector zeros(int length) {
        double[] elements = new double[length];
        for (int i = 0; i < length; i++) {
            elements[i] = 0;
        }
        return new Vector(elements);
    }

    public static Vector singleOne(int length, int onePos) {
        int onePosIndex = onePos - 1;
        double[] elements = new double[length];
        for (int i = 0; i < length; i++) {
            elements[i] = (onePosIndex == i) ? 1 : 0;
        }
        return new Vector(elements);
    }

    public int getLength() {
        return length;
    }

    public double dotProduct(Vector other) {
        if(this.length != other.length) {
            throw new IncompatibleVectorException(IncompatibleVectorException.Type.INCORRECT_DIMENSIONS);
        }
        double result = 0;
        for (int i = 0; i < this.length; i++) {
            result += (this.items[i] * other.items[i]);
        }
        return result;
    }

    public double[] toArray() {
        return Arrays.copyOf(items, this.length);
    }

    public List<Double> toList() {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < this.length; i++) {
            list.add(items[i]);
        }
        return list;
    }

    @Override
    public Vector vectorAddition(Vector other) {
        if(this.length != other.length) {
            throw new IncompatibleVectorException(IncompatibleVectorException.Type.INCORRECT_DIMENSIONS);
        }
        double[] newVector = new double[this.length];
        for (int i = 0; i < this.length; i++) {
            newVector[i] = this.items[i] + other.items[i];
        }
        return new Vector(newVector);
    }

    @Override
    public Vector scalarMultiplication(double scalar) {
        double[] newVector = new double[length];
        for (int i = 0; i < this.length; i++) {
            newVector[i] = items[i] * scalar;
        }
        return new Vector(newVector);
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if(o == null || o.getClass() != this.getClass()) {
            result = false;
        } else if(o == this) {
            result = true;
        } else {
            Vector other = (Vector) o;
            if(other.getLength() != this.getLength()) {
                result = false;
            } else {
                result = true;
                for (int i = 0; i < this.getLength() && result; i++) {
                    result = (Double.compare(this.items[i], other.items[i]) == 0);
                }
            }
        }

        return result;
    }
}
