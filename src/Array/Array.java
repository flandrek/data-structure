package Array;

/**
 * ���鼰�䳣�ù��ܵ�ʵ��
 *
 * @param <E>
 * @author wangzhe
 */
public class Array<E> {
    private E[] data;
    private int size;

    //�вι�����
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //�޲ι�����
    public Array() {
        this(10);
    }

    //����һ������,����ת��Ϊ��̬����
    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];
        size = arr.length;
    }

    //���������Ԫ�ظ���
    public int getSize() {
        return size;
    }

    //�������������
    public int getCapacity() {
        return data.length;
    }

    //�ж��Ƿ�Ϊ��
    public boolean isEmpty() {
        return size == 0;
    }

    //�������ͷ�����һ��Ԫ��
    public void addFirst(E e) {
        add(0, e);
    }

    //������Ԫ�غ����һ����Ԫ��
    public void addLast(E e) {
		/*if(size == data.length)
			throw new IllegalArgumentException("AddLast failed. Array is full.");
		data[size] = e;
		size ++;*/
        add(size, e);
    }

    //ɾ�����һ��Ԫ��
    public E removeLast() {
        return remove(size - 1);
    }

    //ɾ����һ��Ԫ��
    public E removeFirst() {
        return remove(0);
    }

    //��indexλ��Ԫ��ɾ��,����ɾ��Ԫ��
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal");
        E ret = data[index];
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];
        size--;
        //�ͷ��ڴ�ռ�(���ö���)
        data[size] = null;    // loitering objects != memory leak
        //��С�����ڴ�����
        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    //�ڵ�index��λ�ò���һ����Ԫ��e
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require "
                    + "index >= 0 and index <= size.");
        if (size == data.length)
            //throw new IllegalArgumentException("Add failed. Array is full.");
            //������ռ��������
            resize(2 * data.length);
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];
        data[index] = e;
        size++;
    }

    //ɾ�������е�e(ֻɾ��һ��)
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    //��ȡindex����λ�õ�Ԫ��
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal");
        return data[index];
    }

    //��ȡ��βԪ��
    public E getLast() {
        return get(size - 1);
    }

    //��ȡ�ͷԪ��
    public E getFirst() {
        return get(0);
    }

    //����indexλ��Ԫ�ظ���
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal");
        data[index] = e;
    }

    //�����������Ƿ����Ԫ��e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    //Ѱ��Ԫ��e��Ӧ������,�������򷵻�-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    //���������е�����Ԫ��
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("index is illegal");
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    //��дtoString����
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

    //��������������С
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }
}
