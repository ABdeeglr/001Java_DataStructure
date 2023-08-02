package src.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

import src.lib.api.AbstractQueue;
import src.lib.std.StdRandom;

public class ResizingCircleArrayQueue<Item> implements AbstractQueue<Item> {
    private Item[] contents;
    private int N;
    private int front, rear;
    private final int minQueueLength;
    private int queueLength;

    public ResizingCircleArrayQueue() {
        this.contents = (Item[]) new Object[1];
        this.N = 0;
        this.front = 0;
        this.rear = 0;
        this.queueLength = 1;
        this.minQueueLength = 1;
    }

    public ResizingCircleArrayQueue(int capacity) {
        this.contents = (Item[]) new Object[capacity];
        this.N = 0;
        this.front = 0;
        this.rear = 0;
        this.queueLength = 1;
        this.minQueueLength = capacity; 
    }


    @Override
    public void enqueue(Item item) {
        contents[front % queueLength] = item;
        front++;
        N++;
        if (N == 0) rear++;
        if (isFull()) sizeIncrease();
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Error: Queue is empty.");
        else {
            var ret = contents[rear % queueLength];
            contents[rear % queueLength] = null;
            if (rear != front) rear++;
            N--;
            if (N < queueLength / 4 && N > minQueueLength) sizeDecrease();
            return ret;
        }
    }

    private void sizeIncrease() {
        var newContents = (Item[]) new Object[2 * queueLength];
        for (int i = 0; i < queueLength; i++) {
            newContents[i] = contents[(rear + i) % queueLength];
        }
        contents = newContents;
        queueLength *= 2;
        front -= rear;
        rear = 0;
    }

    private void sizeDecrease() {
        var newContents = (Item[]) new Object[queueLength / 2];
        for (int i = 0; i < N; i++) {
            newContents[i] = contents[(rear + 1) % queueLength];
        }
        contents = newContents;
        queueLength /= 2;
        front -= rear;
        rear = 0;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    private boolean isFull() {
        return N == queueLength;
    }

    @Override
    public int size() {
        return N;
    }

    public int capacity() {
        return queueLength;
    }

    @Override
    public Iterator<Item> iterator() {
        return new HeadToTailIterator();
    }

    private class HeadToTailIterator implements Iterator<Item> {
        
        private int i = N;
        private int bottom = rear;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            var ret = contents[bottom % queueLength];
            bottom++;
            i--;
            return ret;
        }
    }
    
    public static void main(String[] args) {
        var racq = new ResizingCircleArrayQueue<String>();
        int insert = 0,
            withdraw = 0,
            cycle = 0,
            add;
        
        while (cycle < 1000) {
            add = StdRandom.uniformInt(16);
            insert += add;
            for (int i = 0; i < add; i++) racq.enqueue("String: #" + i);
            System.out.printf("Enqueue cycle #%d, insert %d element(s) >> Queue size: %d and capacity: %d.\n", cycle, add, racq.size(), racq.capacity());

            withdraw = StdRandom.uniformInt(insert);
            insert -= withdraw;
            for (int i = 0; i < withdraw; i++) racq.dequeue();
            System.out.printf("Dequeue cycle #%d, withdraw %d element(s) >> Queue size: %d and capacity: %d.\n", cycle, withdraw, racq.size(), racq.capacity());

            cycle++;
        }
        System.out.println("!!! Success! Test Passed!");
    }
}
