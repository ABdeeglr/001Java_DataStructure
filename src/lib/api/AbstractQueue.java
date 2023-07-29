package src.lib.api;

public interface AbstractQueue<Item> extends Iterable<Item> {

    /**
     * 向 Queue 中添加一个元素
     * @param item
     */
    void enqueue(Item item);

    /**
     * 根据 Queue 数据结构的特性，弹出最早加入的元素
     * @return Item item
     */
    Item dequeue();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 返回队列的长度
     * @return
     */
    int size();
    
}
