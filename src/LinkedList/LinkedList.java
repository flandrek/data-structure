package LinkedList;

/**
 * �����䳣�ù��ܵ�ʵ��
 *
 * @param <E>
 * @author wangzhe
 */
public class LinkedList<E> {
    //�ڵ��ڲ���
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

    private Node dummyHead;    //�����ͷ���,Ϊ0λ��Ԫ�ص�ǰһ���ڵ�
    int size;

    //��������
    public LinkedList() {
        //��ʼʱ��һ���ڵ�,Ԫ�غ�next��Ϊ��
        dummyHead = new Node(null, null);
        size = 0;
    }

    //��ȡ�����е�Ԫ�ظ���
    public int getSize() {
        return size;
    }

    //���������Ƿ�Ϊ��
    public boolean isEmpty() {
        return size == 0;
    }

    //�������index�������Ԫ��e
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

    //������ͷ����µ�Ԫ��e
    public void addFirst(E e) {
        add(0, e);
    }

    //�������ĩβ���һ��Ԫ��
    public void addLast(E e) {
        add(size, e);
    }

    //��������index��λ�õ�Ԫ��
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
        //������Ϊ0��Ԫ�ؿ�ʼ
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        return cur.e;
    }

    //��������һ��Ԫ��
    public E getFirst() {
        return get(0);
    }

    //����������һ��Ԫ��
    public E getLast() {
        return get(size - 1);
    }

    //�޸�����ĵ�index��λ�õ�Ԫ��
    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Set failed. Illegal index.");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }

    //�����������Ƿ����Ԫ��e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    //��������ɾ��indexλ�õ�Ԫ�أ�����ɾ����Ԫ��
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

    //��������ɾ����һ��Ԫ��
    public E removeFirst() {
        return remove(0);
    }

    //��������ɾ�����һ��Ԫ��
    public E removeLast() {
        return remove(size - 1);
    }

    //��������ɾ��Ԫ��e
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
        //���ֱ�����ʽ
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
