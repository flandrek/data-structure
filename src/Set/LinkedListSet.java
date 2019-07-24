package Set;
/**
 * ��������ʵ�ֵļ���
 * ��ʵ�ֵ���,ɾ,���ʱ�临�ӶȾ�ΪO(n)
 * ��Ϊ����Ĳ�ѯ����Ϊʱ�临�Ӷ�ΪO(n)
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
