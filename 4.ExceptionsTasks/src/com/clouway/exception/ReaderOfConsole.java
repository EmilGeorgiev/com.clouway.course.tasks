package com.clouway.exception;

public class ReaderOfConsole {
	public static void main(String[] args) {
		Reader read = new Reader(5, 17);
        try {
            read.readOfConsole();
        } catch (OutOfRangeException e){
            System.out.println("The number is out of range (" + e.getMin() + " - " + e.getMax() + ")");
        }

	}
}
