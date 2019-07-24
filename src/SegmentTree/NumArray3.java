package SegmentTree;

/**
 * 给定一个数组,求出数组从索引i到j范围内元素的总和
 * 对数据进行预处理的方法,时间复杂度为 O(n),效率较低
 * LeetCode 第 307号题,在原题的基础上加入了对区间某个值更新的功能
 *
 * @author wangzhe
 */
public class NumArray3 {

    private int[] sum; //sum[i]存储前i个元素和,sum[0] = 0
    //sum[i]存储 nums[0...i-1]的和
    private int[] data;

    public NumArray3(int[] nums) {

        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            data[i] = nums[i];

        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    public void update(int index, int val) {
        data[index] = val;
        for (int i = index + 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + data[i - 1];
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
