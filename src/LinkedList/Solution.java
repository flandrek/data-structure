package LinkedList;

/**
 * public class ListNode{
 * int val;
 * ListNode next;
 * ListNode(int x){val = x};
 * }
 * ɾ������������ֵΪvalue��Ԫ��
 * ʹ����ͨ�������
 */

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        //ɾ����ͷ���ֵ�Ԫ��
        while (head != null && head.val == val) {
			/*ListNode delNode = head;
			head = head.next;
			delNode.next = null;*/
            head = head.next;
        }
        //ɾ���м䲿�ֵ�Ԫ��
        if (head == null)//˵������һ�������е�Ԫ�ض��Ѿ�ɾ��
            return null;
        ListNode prev = head;//��ʱͷ��㲻��Ҫɾ���Ľڵ�
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

    //��������
    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        System.out.println((new Solution()).removeElements(head, 6));
    }
}
