package LeetCode;

/**
 * 使用递归方法对数组进行求和
 *
 * @author Administrator
 */
public class ArrSum {
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    //计算arr[l....n)这个区间内所有数字的和(递归函数)
    private static int sum(int[] arr, int l) {
        if (l == arr.length)
            return 0;
        return arr[l] + sum(arr, l + 1);
    }

    //测试用例
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(nums));
    }
}
