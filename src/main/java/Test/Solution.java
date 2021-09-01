package Test;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2021年06月08日 20:10:00
 */
public class Solution {



    public ListNode reverseWithRecursion(ListNode node){
        ListNode p = node;

        if( p == null){
            return null;
        }
        ListNode pnext = p.next;

        if(pnext == null){
            return null;
        }

        pnext.next = p;
        p = pnext;
        return reverseWithRecursion(p);
    }

    // 反转链表
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
    public static void printAll(ListNode head){
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        printAll(listNode1);
        System.out.println("********");
        ListNode listNode = reverseList(listNode1);
        printAll(listNode);
    }
}
