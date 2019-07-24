package SegmentTree;

/**
 * 给定一个数组,求出数组从索引i到j范围内元素的总和
 * 使用线段树进行解答
 * LeetCode 第303号题
 *
 * @author wangzhe
 */
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++)
                data[i] = nums[i];
            segmentTree = new SegmentTree<Integer>(data, (a, b) -> a + b);
        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null.");
        return segmentTree.query(i, j);
    }
}
