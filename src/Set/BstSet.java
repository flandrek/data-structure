package Set;
/**
 * ���ڶ���������bst�ļ���
 * ��ʵ�ֵ�ʱ�临�Ӷ�ΪO(h)=O(log(n)),hΪ�������
 * h=log2(n+1)=O(logN)
 *
 * @author Administrator
 */

import BinarySearchTree.BinaryTree;

public class BstSet<E extends Comparable<E>> implements Set<E> {
    private BinaryTree<E> bst;

    public BstSet() {
        bst = new BinaryTree<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override

    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
