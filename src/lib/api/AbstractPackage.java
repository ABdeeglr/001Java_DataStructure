package src.lib.api;

public interface AbstractPackage<Item> extends Iterable<Item> {
    /**
     * 向 package 中添加一个元素
     * @param item
     */
    void add(Item item);

    /**
     * 判断 package 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 了解当前 package 中的元素数量
     * @return
     */
    int size();
}
