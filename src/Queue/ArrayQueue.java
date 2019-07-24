package Queue;

import Array.Array;

/**
 * 用动态数组实现队列
 *
 * @param <E>
 * @author wangzhe
 */
public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    //有参构造器
    public ArrayQueue(int capacity) {
        array = new Array<E>(capacity);
    }

    //无参构造器
    public ArrayQueue() {
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

    //返回队列的容量
    public int getCapacity() {
        return array.getCapacity();
    }

    //元素加入队列
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    //元素移出队列
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue:");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1)
                res.append(",");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
