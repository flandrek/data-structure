package LinkedList;

/**
 * public class ListNode{
 * int val;
 * ListNode next;
 * ListNode(int x){val = x};
 * }
 * 删除链表中所有值为value的元素
 * 使用虚拟头结点dummyHead
 */

public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);//随意赋一个值
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else
                prev = prev.next;
        }
        return dummyHead.next;
    }
}
