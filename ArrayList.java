package com.company;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
    int size;
    E[] arr;

    // Constructor
    public ArrayList() {
        this.size = 0;
        this.arr = (E[]) new Object[10];
    }

    // @Override
    public int size() {
        return this.size;
    }

    // Grows the array
    private void grow_array () {
        E [] new_arr = (E[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++)
            new_arr[i] = arr[i];
        arr = new_arr;
    }

   public void add(int pos, E item) {
        for (int i = size; i > pos; i--)
            arr[i] = arr[i-1];
        arr[pos] = item;
        ++size;
    }
    // Adds a value at the end of the List

    public boolean add(E item) {
        if (size == arr.length)
            grow_array();
        arr[size++] = item;
        return true;
    }


    public E get(int pos) {
        try {
            if (pos < 0 || pos >= size)
                throw new Exception("Invalid position");
        } catch (Exception e) {
            System.out.println("Error out of bounds");
            return null;
        }

        return arr[pos];
    }

    
    public E remove(int pos) {
        try {
            if (pos < 0 || pos >= size)
                throw new Exception("Invalid position");
        } catch (Exception e) {
            System.out.println("Error out of bounds");
            return null;
        }

        E obj = arr[pos];
        for (int i=pos; i<size; i++) {
            arr[i] = arr[i+1];
          
        }

        --size;
        return obj;
    }

}