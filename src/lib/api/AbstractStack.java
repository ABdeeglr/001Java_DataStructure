package src.lib.api;

public interface AbstractStack<Item> extends Iterable<Item> {
    
    /**
     * 向栈中压入一个元素
     * @param item
     */
    void push(Item item);

    /**
     * 从栈顶弹出一个元素
     * @return
     */
    Item pop();

    /**
     * 判断栈是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 返回栈的大小
     * @return
     */
    int size();
    
}
