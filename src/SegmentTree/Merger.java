package SegmentTree;

//������Ԫ�ؽ��� merge �󷵻�һ������
public interface Merger<E> {
    E merge(E a, E b);
}
