package LinkedList;

/**
 * public class ListNode{
 * int val;
 * ListNode next;
 * ListNode(int x){val = x};
 * }
 * ɾ������������ֵΪvalue��Ԫ��
 * ʹ������ͷ���dummyHead
 */

public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);//���⸳һ��ֵ
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
