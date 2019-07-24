package Heap;
/**
 * 基于动态数组实现最大堆
 * 最大堆的每个节点值都比其子节点大
 *
 * @author wangzhe
 * 1.添加一个新的元素时 add
 * 将其加在数组的末尾,然后和他的父节点比较,再按照堆的性质交换 SiftUp
 * 2.删除最大的元素 extractMax
 * 将数组最后的元素放在数组首位,然后将其和子节点比较,和最大的子节点交换 SiftDown
 * add 和  extractMax 时间复杂度均为 O(logn) 即为完全二叉树的高度
 * 3.取出最大元素并替换一个新元素 replace
 * 将堆顶的元素替换以后SiftDown,一次 O(logn) 的操作
 * 先取出最大值,再添加新元素,执行两次 O(logn) 操作
 * 4.将任意数组整理成堆的形状 heapify
 * 从倒数第一个非叶子节点向前遍历(最后一个节点的父节点),依次执行 SiftDown,复杂度为 O(n)
 * 一个个的往堆中添加元素的方法复杂度为 O(nlogn)
 */

import Array.Array;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<E>(capacity);
    }

    public MaxHeap() {
        data = new Array<E>();
    }

    //将任意数组整理成堆的形状
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);//传入数组转换为动态数组
        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }

    //返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    //返回一个布尔值表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    //返回完全二叉树的数组表示中,某个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    //返回完全二叉树的数组表示中,某个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //返回完全二叉树的数组表示中,某个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    //向堆中添加元素
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) { //实现新添加元素的上浮,维持最大堆的性质
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //看堆中的最大元素
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    //取出堆中的最大元素
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) { //左子节点越界,则必没有子节点
            int j = leftChild(k);
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0)
                j = rightChild(k);
            // data[j] 为 letfChild 和 rightChild 中的最大值
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            data.swap(k, j);
            k = j;
        }
    }

    //取出堆中的最大元素,并且替换成元素e
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
