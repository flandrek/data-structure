package SegmentTree;

/**
 * ��������ʵ�ֵ��߶���
 *
 * @param <E>
 * @author wangzhe
 * 1.������������,���һ��ڵ������µ���ǰ�����в�ڵ�֮��
 * 2.�����߶���,n = 2^k ����Ҫ 2n �Ŀռ�
 * ������,n = 2^k + 1,�����һ��,�����Ҫ 4n �Ŀռ�
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;//�ں���,�����߶���������������ں�

    public SegmentTree(E[] arr, Merger<E> merger) {

        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    //�� treeIndex λ�õݹ鴴����ʾ���� [l...r]���߶���
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) { //�ݹ��������,���䳤��Ϊ1
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    //������ȫ�������������ʾ��,ĳ����������ʾ��Ԫ�ص����ӽڵ������
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //������ȫ�������������ʾ��,ĳ����������ʾ��Ԫ�ص��Һ��ӽڵ������
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    //�������� [queryL,queryR] ��ֵ
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    //���� treeID Ϊ�����߶����� [l...r]�ķ�Χ��,�������� [queryL...queryR]��ֵ
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) //�ݹ����ֹ����
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //��ѯ����ֻ����߻�ֻ���ұߵ����
        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);
        //��ѯ����������������������
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    //�� indexλ�õ�ֵ����Ϊe
    public void set(int index, E e) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    //���� treeIndexΪ�����߶����и��� index��ֵΪe
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) { //�ݹ����������
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else // index <= mid
            set(leftTreeIndex, l, mid, index, e);
        //�ڵ���º������еĸ��ڵ�Ҳ��Ӧ�ĸ���
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");
            if (i != tree.length - 1)
                res.append(',');
            else
                res.append(']');
        }
        return res.toString();
    }
}
