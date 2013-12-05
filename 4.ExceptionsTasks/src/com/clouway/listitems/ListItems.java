package com.clouway.listitems;

public class ListItems implements ListOperation {

    static final int HOME_INDEX = -1;
    private Object[] arrayList;
    private int lenght;
    /**
     * Shows which place in the array are
     */
    private int index = -1;

    public ListItems(int lenghtOfArrayList) {
        arrayList = new Object[lenghtOfArrayList];
        this.lenght = lenghtOfArrayList - 1;

    }

    public Object getObject(int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }

        return arrayList[value];
    }

    /**
     * Print all list items.
     */
    @Override
    public void printAllElements() {
        for (int i = 0; i <= index; i++) {
            System.out.println(arrayList[i] + " ");
        }
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param value is value added.
     * @return true if new value is added or false if not.
     */
    @Override
    public boolean add(Object value) throws FullArrayException {
        if (isFull()) {
            throw new FullArrayException();
        }
        index++;
        arrayList[index] = value;
        return true;
    }

    /**
     * Removes the last item in the list.
     *
     * @return true if operation is successful or false if not.
     */
    @Override
    public boolean remove() throws EmptyArrayException {
        if (isEmpty()) {
            throw new EmptyArrayException();
        }
        arrayList[index] = null;
        index--;
        return true;
    }

    /**
     * Check that the item list is full.
     *
     * @return true if item list is full or false if not.
     */
    private boolean isFull() {
        if (index == lenght) {
            return true;
        }
        return false;
    }

    /**
     * Check that the item list is empty.
     *
     * @return true if the array is empty or false is not.
     */
    private boolean isEmpty() {
        if (index == HOME_INDEX) {
            return true;
        }
        return false;
    }
	//1
	//2
	//3
}
