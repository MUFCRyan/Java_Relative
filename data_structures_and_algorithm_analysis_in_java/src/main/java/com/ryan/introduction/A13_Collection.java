package com.ryan.introduction;

/**
 * Created by MUFCRyan on 2017/10/16.
 * 泛型类 Collection
 */

public class A13_Collection {

    private Object[] mObjects;
    private int length;

    public void insert(int position, Object object){
        if (position > length)
            throw new RuntimeException("插入位置 " + position + " 错误，超过了当前集合的最大长度：" + length);

        if (position == length){
            mObjects[position] = object;
            length ++;
            return;
        }

        for (int i = length; i > position; i--) {
            mObjects[i + 1] = mObjects[i];
        }

        mObjects[position] = object;
        length ++;
    }

    public Object remove(Object object){
        if (isEmpty())
            throw new RuntimeException("不能从空集合删除元素！");

        Object removeObject = null;
        int removePosition = 0;

        for (int i = 0; i < length; i++) {
            if (object.x == mObjects[i].x){
                removeObject = mObjects[i];
                removePosition = i;
                break;
            }
        }

        for (int i = removePosition; i < length - 1; i++) {
            mObjects[i] = mObjects[i+1];
        }

        mObjects[length - 1] = null;
        length --;

        return removeObject;
    }

    public Object[] makeEmpty(){
        mObjects = new Object[1000000];
        length = 0;
        return mObjects;
    }

    public boolean isEmpty(){
        return mObjects == null || length == 0;
    }

    public boolean isPresent(int x){
        if (isEmpty())
            return false;
        for (Object object : mObjects) {
            if (object.equals(x))
                return true;
        }
        return false;
    }

    private class Object{

        protected int x;

        public boolean equals(java.lang.Object object) {
            int myObjectX = (int) object;
            return x == myObjectX;
        }
    }
}
