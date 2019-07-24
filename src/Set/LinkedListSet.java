package Set;
/**
 * 基于链表实现的集合
 * 该实现的增,删,查的时间复杂度均为O(n)
 * 因为链表的查询功能为时间复杂度为O(n)
 *
 * @author Administrator
 * @param <E>
 */

import LinkedList.LinkedList;

public class LinkedListSet<E> implements Set<E> {
    public LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<E>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
        ;
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
