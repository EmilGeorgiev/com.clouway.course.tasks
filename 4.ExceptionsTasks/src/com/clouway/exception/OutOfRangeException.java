package com.clouway.exception;

public class OutOfRangeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
    private final int min;
    private final int max;

    public OutOfRangeException(int min, int max) {
        super();
        this.min = min;
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }
}
