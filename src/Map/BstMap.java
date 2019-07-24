package Map;

/**
 * 基于二分搜索树实现的Map
 *
 * @param <K>
 * @param <V>
 * @author wangzhe
 */

public class BstMap<K extends Comparable<K>, V> implements Map<K, V> {
    //节点内部类
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

    //返回以node为根节点的二分搜索树中,key所在的节点
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

    //返回以node为根的二分搜索树的最小值所在的节点,递归方法
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;//rightNode为当前节点的右子树
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);//将上次递归的rightNode赋值给node.left
        return node;
    }

    //从二分搜索树中删除键为key的节点
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    //删除以node为根的二分搜索树中键为key的节点,递归算法
    //返回删除节点后新的二分搜索树的根
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
            //待删除的节点左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //待删除的节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //待删除的节点左右子树均不为空时,找到比待删除节点大的最小的节点
            //即待删除节点右子树的最小节点,然后用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);//找到后继节点successor
            successor.right = removeMin(node.right);
            size++;//removeMin()中删除的节点被successor指着多减了1
            successor.left = node.left;

            node.left = node.right = null;
            size--;//node被删除所以size--,与上面的size++抵消,可以都不写
            return successor;
        }
    }

    //向二分搜索树中添加新元素(key,value)
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //向以node为根的二分搜索树中插入元素(key,value),递归算法
    //返回插入新节点后二分搜索树的根
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
