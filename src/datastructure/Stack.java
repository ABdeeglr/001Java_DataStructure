package src.datastructure;

import java.util.Iterator;
import src.lib.api.*;
import src.lib.std.StdOut;
import src.lib.std.StdRandom;

public class Stack<Item> implements AbstractStack<Item> {

    private Node head;
    private int N;

    public Stack() {
        this.head = null;
        this.N = 0;
    }

    private class Node {
        Item item;
        Node next;

        public Node (Item item) {
            this.item = item;
            this.next = null;
        }
    }

    @Override
    public void push(Item item) {
            Node old = head;
            head = new Node(item);
            head.next = old;
            old = null;
            N++;
    }

    /**
     * 从 Stack 顶部弹出一个元素。
     * 然而这个方法存在一个隐患，如果 Stack 为空，会导致 nullPointerException，
     * 但我认为，这一部分的错误检查或许应该由调用方决定，试试看吧。
     */
    @Override
    public Item pop() {
        Node ret = head;
        head = ret.next;
        N--;
        return ret.item;
    }


    @Override
    public boolean isEmpty() {
        return head == null;    
    }


    @Override
    public int size() {
        return N;
    }

    
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();    
    }
    
    private class ReverseArrayIterator implements Iterator<Item> {
        // 支持后进先出的迭代
        private int i = N;
        private Node next = head;
        public boolean hasNext() { return i > 0; }
        public Item next() {
            var ret = next.item;
            next = next.next;
            i--;
            return ret;
        }
        public void remove() { }
    }

    public static void main(String[] args) {
        // 单元测试 1
        /* 
        Stack<String> s = new Stack<String>();
        while (!StdIn.isEmpty()) { 
            String item = StdIn.readString(); 
            if (!item.equals("-")) {
                s.push(item);
                StdOut.print("Stack -> ");
                for (String p : s) {
                    StdOut.printf("%s ", p);
                }
                StdOut.println();
            } 
            else if (!s.isEmpty()) StdOut.print(s.pop() + " \n"); 
        }
        StdOut.println("(" + s.size() + " left on stack)");
        */

        // 单元测试 2
        var maillist = new Stack<String>();

        // StdOut.println(maillist.pop());
        for (int i = 0; i < 15; i++) {
            maillist.push("Arg: " + StdRandom.uniformInt(1000));
        }

        StdOut.println("Size: " + maillist.size());
        
        StdOut.println(maillist.pop());
        StdOut.println(maillist.pop());
        StdOut.println("____________");

        for (String i : maillist) {
            StdOut.println(i);
        }
        StdOut.println("Size: " + maillist.size());
    }
}
