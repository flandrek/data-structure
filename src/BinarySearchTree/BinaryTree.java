package BinarySearchTree;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;;

/**
 * 二分搜索树BinarySearchTree
 * 该树的左节点小于根节点，右节点大于根节点
 *
 * @param <E>
 * @author wangzhe
 */
//继承可比较的接口(Comparable)
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

    //向二分搜索树中添加新的元素e
/*	public void add(E e) {
		if(root == null) {
			root = new Node(e);
			size ++;
		}else
			add(root,e);
	}
	//向以Node为根的二分搜索树中插入元素E，递归算法
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
		//开始递归调用
		if(e.compareTo(node.e) < 0)
			add(node.left, e);
		else//e.compareTo(node.e) > 0
			add(node.right, e);
	}*/

    //向二分搜索树中添加新的元素e
    //此时不用判断root为null的情况
    public void add(E e) {
        root = add(root, e);
    }

    //向以Node为根的二分搜索树中插入元素E，递归算法
    //返回插入新节点后二分搜索树的根节点node
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        //node.left 或 node.right为null
        //此时将该节点添加到树的right和left
        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        return node;
    }

    //查询搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    //看以node为根的二分搜索树中是否包含元素e，私有递归实现
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

    //用非递归的方法前序遍历二分搜索树,通过栈来实现
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

    //二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    //前序遍历以node为根的二分搜索树，递归实现
    private void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
        //也可以写成
	/*	if(node != null) {
			System.out.println(node.e);
			preOrder(node.left);
			preOrder(node.right);
		}*/
    }

    //二分搜索树的中序遍历
    //结果为从小到大的顺序排列,也叫排序树
    public void inOrder() {
        inOrder(root);
    }

    //中序遍历以node为根的二分搜索树，递归实现
    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //二分搜索树的中后序遍历
    public void postOrder() {
        postOrder(root);
    }

    //后序遍历以node为根的二分搜索树，递归实现
    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //二分搜索树的层序遍历,使用队列来实现(为广度优先遍历)
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

    //寻找二分搜索树中的最小元素
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return minimum(root).e;
    }

    //返回以node为根的二分搜索树的最小值所在的节点,递归方法
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    //寻找二分搜索树中的最大元素
    public E maxmum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return maxmum(root).e;
    }

    //返回以node为根的二分搜索树的最大值所在的节点,递归方法
    private Node maxmum(Node node) {
        if (node.left == null)
            return node;
        return maxmum(node.left);
    }

    //从二分搜索树中删除最小值所在的节点
    public E removeMin() {
        E ret = minimum();
        removeMin(root);
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;//rightNode为当前节点的右子树
            node.right = null;//将该点删除
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);//将上次递归的rightNode赋值给node.left
        return node;
    }

    //从二分搜索树中删除最大值所在的节点
    public E removeMax() {
        E ret = maxmum();
        removeMax(root);
        return ret;
    }

    //删除掉以node为根的二分搜索树中的最大节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;//leftNode为当前节点的右子树
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);//将上次递归的leftNode赋值给node.right
        return node;
    }

    //从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }

    //删除以node为根的二分搜索树中值为e的节点,递归算法
    //返回删除节点后新的二分搜索树的根
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

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    //生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");

        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    //生成显示二叉树深度的字符串
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }
}
