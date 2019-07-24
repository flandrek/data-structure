package LinkedList;

/**
 * 链表及其常用功能的实现
 *
 * @param <E>
 * @author wangzhe
 */
public class LinkedList<E> {
    //节点内部类
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;    //虚拟的头结点,为0位置元素的前一个节点
    int size;

    //链表构造器
    public LinkedList() {
        //初始时有一个节点,元素和next均为空
        dummyHead = new Node(null, null);
        size = 0;
    }

    //获取链表中的元素个数
    public int getSize() {
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //在链表的index中添加新元素e
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
		
		/*Node node = new Node(e);
		node.next = prev.next;
		prev.next = node;*/

        prev.next = new Node(e, prev.next);

        size++;
    }

    //在链表头添加新的元素e
    public void addFirst(E e) {
        add(0, e);
    }

    //在链表的末尾添加一个元素
    public void addLast(E e) {
        add(size, e);
    }

    //获得链表第index个位置的元素
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
        //从索引为0的元素开始
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        return cur.e;
    }

    //获得链表第一个元素
    public E getFirst() {
        return get(0);
    }

    //获得链表最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    //修改链表的第index个位置的元素
    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Set failed. Illegal index.");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }

    //查找链表中是否存在元素e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    //从链表中删除index位置的元素，返回删除的元素
    public E remove(int index) {
        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;

        size--;
        return retNode.e;
    }

    //从链表中删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    //从链表中删除最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }

    //从链表中删除元素e
    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e))
                break;
            prev = prev.next;
        }
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        //两种遍历方式
		/*for(Node cur = dummyHead.next;cur != null;cur = cur.next)
			res.append(cur + "->");*/
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }

        res.append("NULL");
        return res.toString();
    }
}
