package LinkedList;

/**
 * public class ListNode{
 * int val;
 * ListNode next;
 * ListNode(int x){val = x};
 * }
 * 删除链表中所有值为value的元素
 * 使用递归调用的方法解决
 * <p>
 * 将问题看为:链表头结点+后面跟随的链表
 * 分为两种情况 1.如果头结点要删除,则此时返回的即为后面跟随的链表。
 * 2.如果头结点不用删除,则此时为头结点+后面删除后的链表。
 */

public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        //解决最基本的情况
        if (head == null)
            return null;
        //开始递归调用
        //res为head后面跟随的链表处理后的结果
		/*ListNode res = removeElements(head.next,val);
		if(head.val == val)
			return res;
		else {
			head.next = res;
			return head;
		}*/
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        System.out.println((new Solution3()).removeElements(head, 6));
    }
}
