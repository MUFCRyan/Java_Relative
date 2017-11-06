package com.ryan.hash;

/**
 * Created by zhaofengchun on 2017/11/6.
 * 练习 5.17 实现支持 put 和 get 操作的泛型 Map。该实现方法将存储（关键字，定义）对的散列表
 */

public class E17_Map<K, V> {
    public class Map {
        private QuadraticProbingHashTable<Entry<K, V>> items;

        public Map() {
            items = new QuadraticProbingHashTable<>();
        }

        public void put(K key, V value) {
            Entry<K, V> entry = new Entry<>(key, value);
            items.insert(entry);
        }

        public V get(K key){
            V value = (V) new Object();
            Entry<K, V> entry = new Entry<>(key, value);
            entry = items.find(entry);
            return entry.mValue;
        }

        public boolean isEmpty(){
            return items.isEmpty();
        }

        public void makeEmpty(){
            items.makeEmpty();
        }
    }

    private static class Entry<K, V> extends ElementType<Entry<K,V>> {

        private final K mKey;
        private final V mValue;

        Entry(K key, V value){
            mKey = key;
            mValue = value;
        }

        @Override
        public int hashCode() {
            return mKey.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Entry && mKey.equals(((Entry<K, V>)o).mKey);
        }
    }

    private static class QuadraticProbingHashTable<AnyType extends ElementType<AnyType>>{
        private int currentSize = 0;
        private AnyType[] array;
        public boolean isEmpty(){
            return currentSize == 0;
        }

        public void insert(AnyType x){
            int hashCode = x.hashCode();
            hashCode %= array.length;
            array[hashCode] = x;
        }

        public AnyType find(AnyType x){
            int position = findPos(x);
            return isActivated(position) ? array[position].element : null;
        }

        private int findPos(AnyType x){
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(x))
                    return i;
            }
            return -1;
        }

        private boolean isActivated(int position){
            return array[position].isActivated;
        }

        public void makeEmpty() {
            for (int i = 0; i < array.length; i++) {
                array[i] = null;
                currentSize = 0;
            }
        }
    }
}
