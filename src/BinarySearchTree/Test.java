package BinarySearchTree;

/**
 * 测试树的添加和前、中、后序遍历
 *
 * @author Administrator
 */
public class Test {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums)
            binaryTree.add(num);
        binaryTree.preOrder();
        System.out.println();

        binaryTree.preOrderNR();
        //binaryTree.inOrder();
        //System.out.println();
        //System.out.println(binaryTree);
    }
}
