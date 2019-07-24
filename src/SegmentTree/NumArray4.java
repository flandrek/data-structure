package SegmentTree;

/**
 * 给定一个数组,求出数组从索引i到j范围内元素的总和
 * 基于线段树实现,时间复杂度为 O(logn),效率较高
 * LeetCode 第 307号题,在原题的基础上加入了对区间某个值更新的功能
 *
 * @author wangzhe
 */
public class NumArray4 {

    private SegmentTree<Integer> segmentTree;

    public NumArray4(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++)
                data[i] = nums[i];
            segmentTree = new SegmentTree<Integer>(data, (a, b) -> a + b);
        }
    }

    public void update(int index, int val) {
        if (segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null.");
        segmentTree.set(index, val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null.");
        return segmentTree.query(i, j);
    }
}
