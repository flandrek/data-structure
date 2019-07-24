package LinkedList;

/**
 * public class ListNode{
 * int val;
 * ListNode next;
 * ListNode(int x){val = x};
 * }
 * ɾ������������ֵΪvalue��Ԫ��
 * ʹ�õݹ���õķ������
 * <p>
 * �����⿴Ϊ:����ͷ���+������������
 * ��Ϊ������� 1.���ͷ���Ҫɾ��,���ʱ���صļ�Ϊ������������
 * 2.���ͷ��㲻��ɾ��,���ʱΪͷ���+����ɾ���������
 */

public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        //�������������
        if (head == null)
            return null;
        //��ʼ�ݹ����
        //resΪhead���������������Ľ��
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
