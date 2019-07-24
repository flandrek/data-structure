package Set;
/**
 * 基于二分搜索树bst的集合
 * 该实现的时间复杂度为O(h)=O(log(n)),h为树的深度
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
