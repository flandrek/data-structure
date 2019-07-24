package SegmentTree;

//将两个元素进行 merge 后返回一个对象
public interface Merger<E> {
    E merge(E a, E b);
}
