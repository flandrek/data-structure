package Map;

/**
 * ���ڶ���������ʵ�ֵ�Map
 *
 * @param <K>
 * @param <V>
 * @author wangzhe
 */

public class BstMap<K extends Comparable<K>, V> implements Map<K, V> {
    //�ڵ��ڲ���
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BstMap() {
        root = null;
        size = 0;
    }

    //������nodeΪ���ڵ�Ķ�����������,key���ڵĽڵ�
    private Node getNode(Node node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // key.compareTo(node.key) < 0
            return getNode(node.right, key);
    }

    //������nodeΪ���Ķ�������������Сֵ���ڵĽڵ�,�ݹ鷽��
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //ɾ������nodeΪ���Ķ����������е���С�ڵ�
    //����ɾ���ڵ���µĶ����������ĸ�
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;//rightNodeΪ��ǰ�ڵ��������
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);//���ϴεݹ��rightNode��ֵ��node.left
        return node;
    }

    //�Ӷ�����������ɾ����Ϊkey�Ľڵ�
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    //ɾ����nodeΪ���Ķ����������м�Ϊkey�Ľڵ�,�ݹ��㷨
    //����ɾ���ڵ���µĶ����������ĸ�
    private Node remove(Node node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { // key.compareTo(node.key) = 0
            //��ɾ���Ľڵ�������Ϊ��
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //��ɾ���Ľڵ�������Ϊ��
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //��ɾ���Ľڵ�������������Ϊ��ʱ,�ҵ��ȴ�ɾ���ڵ�����С�Ľڵ�
            //����ɾ���ڵ�����������С�ڵ�,Ȼ��������ڵ㶥���ɾ���ڵ��λ��
            Node successor = minimum(node.right);//�ҵ���̽ڵ�successor
            successor.right = removeMin(node.right);
            size++;//removeMin()��ɾ���Ľڵ㱻successorָ�Ŷ����1
            successor.left = node.left;

            node.left = node.right = null;
            size--;//node��ɾ������size--,�������size++����,���Զ���д
            return successor;
        }
    }

    //������������������Ԫ��(key,value)
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //����nodeΪ���Ķ����������в���Ԫ��(key,value),�ݹ��㷨
    //���ز����½ڵ������������ĸ�
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "doesn't exist!");
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
