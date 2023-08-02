# 基于 Java 实现的各种数据结构

## 索引

`src.lib.std` 存放的是各类基本输入输出函数，不作介绍

`src.lib.api` 将存放各类数据结构的 API

`src.datastructure` 将存放各类数据结构的具体实现

### 基本集合类型

1. `Stack.java` | 泛型, 不定容, 可迭代栈, 基于链表实现
2. `Queue.java` | 泛型, 不定容, 可迭代队列, 基于链表实现
3. `FixedCapacityArrayStack.java` | 定容泛型栈, 可迭代, 基于数组实现
4. `ResizingArrayStack.java` | 不定容泛型栈, 可迭代，基于数组实现
5. `ArrayPackage.java` | 不定容包, 基于不定容数组泛型栈实现
6. `ResizingCircleArrayQueue` | 不定容队列，基于循环数组实现，支持泛型和迭代