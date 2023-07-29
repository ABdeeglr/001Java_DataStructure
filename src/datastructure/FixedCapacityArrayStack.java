package src.datastructure;

import java.util.Iterator;

import src.lib.api.AbstractStack;

public class FixedCapacityArrayStack<Item> implements AbstractStack<Item>{

    private Item[] contents;
    private int N;

    public FixedCapacityArrayStack(int capacity) {
        this.contents = (Item[]) new Object[capacity];
        this.N = 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return contents[--i];
        }

    }

    @Override
    public void push(Item item) {
        if (this.isFull()) {
            System.out.println("Error! Stack is full.");
        } else {
            contents[N] = item;
            N++;
        }
    }

    @Override
    public Item pop() {
        if (N >= 0) return contents[--N];
        else return null;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return N == contents.length;
    }

    @Override
    public int size() {
        return N;
    }    
}
