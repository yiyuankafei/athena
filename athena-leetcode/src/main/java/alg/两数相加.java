package alg;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 */
public class 两数相加 {

    public static void main(String[] args) {


    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode();
        ListNode currentNode = head;
        boolean boolean1 = true;
        boolean boolean2 = true;
        int p = 0;

        while (true) {
            int value1 = boolean1 ? l1.val : 0;
            int value2 = boolean2 ? l2.val : 0;

            int sum = value1 + value2 + p;
            int currentValue = sum % 10;
            p = sum / 10;
            currentNode.val = currentValue;

            if (l1.next == null) {
                boolean1 = false;
            } else {
                l1 = l1.next;
            }


            if (l2.next == null) {
                boolean2 = false;
            } else {
                l2 = l2.next;
            }


            if (!boolean1 && !boolean2 && p == 0) {
                break;
            } else {
                ListNode newNode = new ListNode();
                currentNode.next = newNode;
                currentNode = newNode;
            }

        }
        return head;
    }



    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

}
