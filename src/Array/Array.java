package Array;

/**
 * 数组及其常用功能的实现
 *
 * @param <E>
 * @author wangzhe
 */
public class Array<E> {
    private E[] data;
    private int size;

    //有参构造器
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //无参构造器
    public Array() {
        this(10);
    }

    //传入一个数组,将其转换为动态数组
    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];
        size = arr.length;
    }

    //返回数组的元素个数
    public int getSize() {
        return size;
    }

    //返回数组的容量
    public int getCapacity() {
        return data.length;
    }

    //判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //在数组的头部添加一个元素
    public void addFirst(E e) {
        add(0, e);
    }

    //向所有元素后添加一个新元素
    public void addLast(E e) {
		/*if(size == data.length)
			throw new IllegalArgumentException("AddLast failed. Array is full.");
		data[size] = e;
		size ++;*/
        add(size, e);
    }

    //删除最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }

    //删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    //将index位置元素删除,返回删除元素
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal");
        E ret = data[index];
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];
        size--;
        //释放内存空间(引用对象)
        data[size] = null;    // loitering objects != memory leak
        //减小数组内存容量
        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    //在第index个位置插入一个新元素e
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require "
                    + "index >= 0 and index <= size.");
        if (size == data.length)
            //throw new IllegalArgumentException("Add failed. Array is full.");
            //对数组空间进行扩容
            resize(2 * data.length);
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];
        data[index] = e;
        size++;
    }

    //删除数组中的e(只删除一个)
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    //获取index索引位置的元素
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal");
        return data[index];
    }

    //获取结尾元素
    public E getLast() {
        return get(size - 1);
    }

    //获取最开头元素
    public E getFirst() {
        return get(0);
    }

    //数组index位置元素更新
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal");
        data[index] = e;
    }

    //查找数组中是否存在元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    //寻找元素e对应的索引,不存在则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    //交换数组中的两个元素
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("index is illegal");
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    //重写toString方法
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d,capacity = %d\n",
                size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(",");
        }
        res.append(']');
        return res.toString();
    }

    //调整数组容量大小
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }
}
