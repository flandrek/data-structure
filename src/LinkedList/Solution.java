package LinkedList;

/**
 * public class ListNode{
 * int val;
 * ListNode next;
 * ListNode(int x){val = x};
 * }
 * 删除链表中所有值为value的元素
 * 使用普通方法解决
 */

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        //删除开头部分的元素
        while (head != null && head.val == val) {
			/*ListNode delNode = head;
			head = head.next;
			delNode.next = null;*/
            head = head.next;
        }
        //删除中间部分的元素
        if (head == null)//说明在上一步中所有的元素都已经删除
            return null;
        ListNode prev = head;//此时头结点不是要删除的节点
        while (prev.next != null) {
            if (prev.next.val == val) {
				/*ListNode delNode = prev.next;
				prev.next = delNode.next;
				delNode.next = null;*/
                prev.next = prev.next.next;
            } else
                prev = prev.next;
        }
        return head;
    }

    //测试用例
    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        System.out.println((new Solution()).removeElements(head, 6));
    }
}
