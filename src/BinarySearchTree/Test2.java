package BinarySearchTree;

import java.util.ArrayList;
import java.util.Random;

/**
 * 测试二分搜索树的添加和删除最大值,最小值功能
 *
 * @author Administrator
 */
public class Test2 {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Random random = new Random();
        int n = 1000;

        for (int i = 0; i < n; i++) {
            binaryTree.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while (!binaryTree.isEmpty())
            nums.add(binaryTree.removeMin());

        System.out.println(nums);

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error");
        }
        System.out.println("removeMin test completed");
    }
}
