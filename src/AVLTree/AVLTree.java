package AVLTree;

import java.util.ArrayList;

/**
 * AVLTree ����ڵ�����������ĸ߶Ȳ���� 1
 * ������ƽ�����,ÿ���ڵ㶼����ýڵ��������ĸ߶�ֵ,�Լ������������ĸ߶Ȳ�
 *
 * @param <K>
 * @param <V>
 * @author wangzhe
 */
public class AVLTree<K extends Comparable<K>, V> {
    //�ڵ��ڲ���
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    //��ȡ�ڵ� node�ĸ߶�ֵ
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    //��ȡ�ڵ� node��ƽ������
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
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

    //�жϵ�ǰ�Ķ������Ƿ�Ϊ����������
    public boolean isBST() {

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++)
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    //�Զ��������������������
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //�жϸö������Ƿ�Ϊһ��ƽ�������
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //�ж���NodeΪ���Ķ������Ƿ���һ��ƽ�������,�ݹ��㷨
    private boolean isBalanced(Node node) {
        if (node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
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

        Node retNode;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else { // key.compareTo(node.key) = 0
            //��ɾ���Ľڵ�������Ϊ��
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            //��ɾ���Ľڵ�������Ϊ��
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else { //��ɾ���Ľڵ�������������Ϊ��ʱ,�ҵ��ȴ�ɾ���ڵ�����С�Ľڵ�
                //����ɾ���ڵ�����������С�ڵ�,Ȼ��������ڵ㶥���ɾ���ڵ��λ��
                Node successor = minimum(node.right); //�ҵ���̽ڵ�successor
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;

                retNode = successor;
            }
        }

        if (retNode == null)
            return null;

        //���� height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //����ƽ������
        int balanceFactor = getBalanceFactor(retNode);
        if (Math.abs(balanceFactor) > 1)
            System.out.println("unbalanced : " + balanceFactor);

        //ƽ��ά��
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);
        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);
        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;

    }

    //������������������Ԫ��(key,value)
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

        //���� height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //����ƽ������
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            System.out.println("unbalanced : " + balanceFactor);

        //ƽ��ά��
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //�Խڵ� y����������ת,������ת���µĸ��ڵ� x
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        //������ת�Ĺ���
        x.right = y;
        y.left = T3;

        //���� height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    //�Խڵ� y����������ת,������ת���µĸ��ڵ� x
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        //������ת�Ĺ���
        x.left = y;
        y.right = T2;

        //���� height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "doesn't exist!");
        node.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
