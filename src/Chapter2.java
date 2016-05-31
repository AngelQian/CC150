/**
 * Created by AngelQian on 5/30/16.
 */
public class Chapter2 {
    public static void main(String[] args){
        LinkedList ll = new LinkedList();
        ll.appendLinkedNode(new LinkedNode(2));
        ll.appendLinkedNode(new LinkedNode(3));
        ll.appendLinkedNode(new LinkedNode(4));
        ll.appendLinkedNode(new LinkedNode(5));
        showLinkedList(ll);

    }

    private static void showLinkedList(LinkedList l){
        LinkedNode h = l.getHead();
        while(h!=null) {
            System.out.print(h.val + "->");
            h=h.next;
        }
        System.out.println("null");
    }

    public static LinkedList addTwoLinkedList(LinkedList ll1, LinkedList ll2){
        return null;
    }

    public static LinkedNode beginOfLoop(LinkedList ll){
        return null;
    }

}

class LinkedNode{
    public int val;
    public LinkedNode next;

    public LinkedNode(int data){
        val = data;
        next = null;
    }
}

class LinkedList{
    private LinkedNode head;
    public LinkedList(){
        head = null;
    }

    public LinkedNode getHead() {
        return head;
    }

    public void appendLinkedNode(LinkedNode nd){
        if(head == null){
            head=nd;
            return;
        }
        LinkedNode cur = head;
        while(cur.next!=null)
            cur = cur.next;

        cur.next = nd;
    }

    public void deleteFirstLinkedNode(int data){
        if(head==null)
            return;
        LinkedNode dummy = new LinkedNode(0);
        dummy.next=head;
        LinkedNode pre=dummy, cur=head;
        while(cur!=null && cur.val!=data){
            pre=cur;
            cur=cur.next;
        }
        if(cur!=null)
            pre.next=cur.next;
        if(cur==head)
            head=head.next;
    }

    public void removeDuplicateNode(){
        // sort , remove
    }

    public LinkedNode kthToLastNode(){
        return null;
    }

    public void deleteMiddleNode(LinkedNode midnd){

    }

    public LinkedNode getMiddleLinkedNode(){
        return null;
    }

    public void partitionLinkedList(int pivtal){

    }

    public boolean isPalindrome(){
        // convert to double linked list
        return false;
    }

}
