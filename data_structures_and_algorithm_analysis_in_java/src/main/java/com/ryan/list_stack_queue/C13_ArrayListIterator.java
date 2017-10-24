package com.ryan.list_stack_queue;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Created by MUFCRyan on 2017/10/24.
 * 练习 3.13 添加 ListIterator 对 MyArrayList 的支持
 */

public class C13_ArrayListIterator<T> {

    public ListIterator<T> listIterator(){
        return new MyArrayListIterator<T>();
    }

    public ListIterator<T> iterator(){
        return new MyArrayListIterator<T>();
    }

    public int size(){
        return 0;
    }

    private T[] theItems;

    private class MyArrayListIterator<T> implements ListIterator<T>{

        private int current = 0;
        private boolean backwards = false;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (T) theItems[current ++];
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            return (T) theItems[--current];
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }
}
