package Map;

/**
 * ��������ʵ�ֵ�Map
 *
 * @author wangzhe
 */
public class LinkedListMap<K, V> implements Map<K, V> {
    //�ڵ��ڲ���
    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    //���캯��
    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    //����һ����,���ظü���Ӧ�Ľڵ�
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else
            node.value = value;//�������ظ��ļ�,����valueֵ����
    }

    //ɾ����key��Ӧ�Ľڵ�
    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    //��ѯ����,ͨ��key������Ӧ��valueֵ
    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    //��ָ���ļ������µ�ֵ
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null)
            throw new IllegalArgumentException(key + "dosen't exist!");
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}