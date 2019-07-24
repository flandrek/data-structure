package Heap;
/**
 * ���ڶ�̬����ʵ������
 * ���ѵ�ÿ���ڵ�ֵ�������ӽڵ��
 *
 * @author wangzhe
 * 1.���һ���µ�Ԫ��ʱ add
 * ������������ĩβ,Ȼ������ĸ��ڵ�Ƚ�,�ٰ��նѵ����ʽ��� SiftUp
 * 2.ɾ������Ԫ�� extractMax
 * ����������Ԫ�ط���������λ,Ȼ������ӽڵ�Ƚ�,�������ӽڵ㽻�� SiftDown
 * add ��  extractMax ʱ�临�ӶȾ�Ϊ O(logn) ��Ϊ��ȫ�������ĸ߶�
 * 3.ȡ�����Ԫ�ز��滻һ����Ԫ�� replace
 * ���Ѷ���Ԫ���滻�Ժ�SiftDown,һ�� O(logn) �Ĳ���
 * ��ȡ�����ֵ,�������Ԫ��,ִ������ O(logn) ����
 * 4.��������������ɶѵ���״ heapify
 * �ӵ�����һ����Ҷ�ӽڵ���ǰ����(���һ���ڵ�ĸ��ڵ�),����ִ�� SiftDown,���Ӷ�Ϊ O(n)
 * һ���������������Ԫ�صķ������Ӷ�Ϊ O(nlogn)
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

    //��������������ɶѵ���״
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);//��������ת��Ϊ��̬����
        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }

    //���ض��е�Ԫ�ظ���
    public int size() {
        return data.getSize();
    }

    //����һ������ֵ��ʾ�����Ƿ�Ϊ��
    public boolean isEmpty() {
        return data.isEmpty();
    }

    //������ȫ�������������ʾ��,ĳ����������ʾ��Ԫ�صĸ��׽ڵ������
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    //������ȫ�������������ʾ��,ĳ����������ʾ��Ԫ�ص����ӽڵ������
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //������ȫ�������������ʾ��,ĳ����������ʾ��Ԫ�ص��Һ��ӽڵ������
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    //��������Ԫ��
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) { //ʵ�������Ԫ�ص��ϸ�,ά�����ѵ�����
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    //�����е����Ԫ��
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    //ȡ�����е����Ԫ��
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) { //���ӽڵ�Խ��,���û���ӽڵ�
            int j = leftChild(k);
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0)
                j = rightChild(k);
            // data[j] Ϊ letfChild �� rightChild �е����ֵ
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            data.swap(k, j);
            k = j;
        }
    }

    //ȡ�����е����Ԫ��,�����滻��Ԫ��e
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
