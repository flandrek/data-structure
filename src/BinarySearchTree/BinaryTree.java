package BinarySearchTree;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;;

/**
 * ����������BinarySearchTree
 * ��������ڵ�С�ڸ��ڵ㣬�ҽڵ���ڸ��ڵ�
 *
 * @param <E>
 * @author wangzhe
 */
//�̳пɱȽϵĽӿ�(Comparable)
public class BinaryTree<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BinaryTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //�����������������µ�Ԫ��e
/*	public void add(E e) {
		if(root == null) {
			root = new Node(e);
			size ++;
		}else
			add(root,e);
	}
	//����NodeΪ���Ķ����������в���Ԫ��E���ݹ��㷨
	private void add(Node node,E e) {
		if(e.equals(node.e))
			return;
		else if(e.compareTo(node.e) < 0 && node.left == null){
			node.left = new Node(e);
			size ++;
			return;
		}
		else if(e.compareTo(node.e) > 0 && node.right == null) {
			node.right = new Node(e);
			size ++;
			return;
		}
		//��ʼ�ݹ����
		if(e.compareTo(node.e) < 0)
			add(node.left, e);
		else//e.compareTo(node.e) > 0
			add(node.right, e);
	}*/

    //�����������������µ�Ԫ��e
    //��ʱ�����ж�rootΪnull�����
    public void add(E e) {
        root = add(root, e);
    }

    //����NodeΪ���Ķ����������в���Ԫ��E���ݹ��㷨
    //���ز����½ڵ������������ĸ��ڵ�node
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        //node.left �� node.rightΪnull
        //��ʱ���ýڵ���ӵ�����right��left
        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        return node;
    }

    //��ѯ���������Ƿ����Ԫ��e
    public boolean contains(E e) {
        return contains(root, e);
    }

    //����nodeΪ���Ķ������������Ƿ����Ԫ��e��˽�еݹ�ʵ��
    private boolean contains(Node node, E e) {
        if (node == null)
            return false;
        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else//e.compareTo(node.e) > 0
            return contains(node.right, e);
    }

    //�÷ǵݹ�ķ���ǰ���������������,ͨ��ջ��ʵ��
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    //������������ǰ�����
    public void preOrder() {
        preOrder(root);
    }

    //ǰ�������nodeΪ���Ķ������������ݹ�ʵ��
    private void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
        //Ҳ����д��
	/*	if(node != null) {
			System.out.println(node.e);
			preOrder(node.left);
			preOrder(node.right);
		}*/
    }

    //�������������������
    //���Ϊ��С�����˳������,Ҳ��������
    public void inOrder() {
        inOrder(root);
    }

    //���������nodeΪ���Ķ������������ݹ�ʵ��
    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //�������������к������
    public void postOrder() {
        postOrder(root);
    }

    //���������nodeΪ���Ķ������������ݹ�ʵ��
    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //�����������Ĳ������,ʹ�ö�����ʵ��(Ϊ������ȱ���)
    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    //Ѱ�Ҷ����������е���СԪ��
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return minimum(root).e;
    }

    //������nodeΪ���Ķ�������������Сֵ���ڵĽڵ�,�ݹ鷽��
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //Ѱ�Ҷ����������е����Ԫ��
    public E maxmum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return maxmum(root).e;
    }

    //������nodeΪ���Ķ��������������ֵ���ڵĽڵ�,�ݹ鷽��
    private Node maxmum(Node node) {
        if (node.left == null)
            return node;
        return maxmum(node.left);
    }

    //�Ӷ�����������ɾ����Сֵ���ڵĽڵ�
    public E removeMin() {
        E ret = minimum();
        removeMin(root);
        return ret;
    }

    //ɾ������nodeΪ���Ķ����������е���С�ڵ�
    //����ɾ���ڵ���µĶ����������ĸ�
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;//rightNodeΪ��ǰ�ڵ��������
            node.right = null;//���õ�ɾ��
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);//���ϴεݹ��rightNode��ֵ��node.left
        return node;
    }

    //�Ӷ�����������ɾ�����ֵ���ڵĽڵ�
    public E removeMax() {
        E ret = maxmum();
        removeMax(root);
        return ret;
    }

    //ɾ������nodeΪ���Ķ����������е����ڵ�
    //����ɾ���ڵ���µĶ����������ĸ�
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;//leftNodeΪ��ǰ�ڵ��������
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);//���ϴεݹ��leftNode��ֵ��node.right
        return node;
    }

    //�Ӷ�����������ɾ��Ԫ��Ϊe�Ľڵ�
    public void remove(E e) {
        root = remove(root, e);
    }

    //ɾ����nodeΪ���Ķ�����������ֵΪe�Ľڵ�,�ݹ��㷨
    //����ɾ���ڵ���µĶ����������ĸ�
    private Node remove(Node node, E e) {
        if (node == null)
            return null;
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {//e.compareTo(node.e) = 0
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

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    //������nodeΪ���ڵ㣬���Ϊdepth���������������ַ���
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");

        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    //������ʾ��������ȵ��ַ���
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }
}
