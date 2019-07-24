package SegmentTree;

/**
 * ����һ������,������������i��j��Χ��Ԫ�ص��ܺ�
 * �����ݽ���Ԥ����ķ���,ʱ�临�Ӷ�Ϊ O(n),Ч�ʽϵ�
 * LeetCode �� 307����,��ԭ��Ļ����ϼ����˶�����ĳ��ֵ���µĹ���
 *
 * @author wangzhe
 */
public class NumArray3 {

    private int[] sum; //sum[i]�洢ǰi��Ԫ�غ�,sum[0] = 0
    //sum[i]�洢 nums[0...i-1]�ĺ�
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
