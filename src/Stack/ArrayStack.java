package Stack;

import Array.Array;

public class ArrayStack<E> implements Stack<E> {
    Array<E> array;

    //有参构造器
    public ArrayStack(int capacity) {
        array = new Array<E>(capacity);
    }

    //无参构造器
    public ArrayStack() {
        array = new Array<E>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    //返回堆栈的容积
    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    //返回栈顶的元素
    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1)
                res.append(',');
        }
        res.append("] top");
        return res.toString();
    }
}
