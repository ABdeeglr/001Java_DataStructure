package src.datastructure;

import java.util.Iterator;

import src.lib.api.AbstractStack;
import src.lib.std.StdIn;
import src.lib.std.StdOut;

/**
 * ResizingArrayStack 的缺点在于某些 push() 和 pop() 操作会调整数组的大小：
 * 这项操作的耗时和栈大小成正比。
 * 它几乎（但还没有）达到了任意集合类数据类型的实现的最佳性能：
 * - 每项操作的用时都与集合大小无关；
 * - 空间需求总是不超过集合大小乘以一个常数
 */
public class ResizingArrayStack<Item> implements AbstractStack<Item> {

    private Item[] a = (Item[]) new Object[1];
    private int N = 0;

    @Override
    public void push(Item item) {
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    @Override
    public Item pop() {
        Item item = a[--N];
        a[N] = null; // 避免对象游离
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;    
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }
    
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
        temp[i] = a[i];
        a = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();    
    }
    
    private class ReverseArrayIterator implements Iterator<Item> {
        // 支持后进先出的迭代
        private int i = N;
        public boolean hasNext() { return i > 0; }
        public Item next() { return a[--i]; }
        public void remove() { }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> maillist = new ResizingArrayStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) maillist.push(item);
            else if (!maillist.isEmpty()) StdOut.print(maillist.pop() + " ");
        }
        StdOut.println("(" + maillist.size() + " left on stack)");
    }
}
