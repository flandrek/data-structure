package SegmentTree;

/**
 * 给定一个数组,求出数组从索引i到j范围内元素的总和
 * 对数据进行预处理
 * LeetCode 第303号题
 *
 * @author wangzhe
 */
public class NumArray2 {

    private int[] sum; //sum[i]存储前i个元素和,sum[0] = 0

    //sum[i]存储 nums[0...i-1]的和
    public NumArray2(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
