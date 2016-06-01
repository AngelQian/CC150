import java.util.HashSet;

/**
 * Created by AngelQian on 5/30/16.
 */
public class Chapter2 {
    public static void main(String[] args){
        LinkedList ll = new LinkedList();
        ll.appendLinkedNode(new LinkedNode(2));
        ll.appendLinkedNode(new LinkedNode(9));
        ll.appendLinkedNode(new LinkedNode(5));
        ll.appendLinkedNode(new LinkedNode(3));
        ll.appendLinkedNode(new LinkedNode(6));
        ll.appendLinkedNode(new LinkedNode(4));
        ll.appendLinkedNode(new LinkedNode(1));
        showLinkedList(ll);
        showLinkedNode(ll.partitionLinkedList(4));
//        ll.rotateRight(ll.getHead(),1);
    }

    private static void showLinkedList(LinkedList l){
        showLinkedNode(l.getHead());
    }

    private static void showLinkedNode(LinkedNode nd){
        LinkedNode h = nd;
        while(h!=null) {
            System.out.print(h.val + "->");
            h=h.next;
        }
        System.out.println("null");
    }

    public static LinkedNode addTwoLinkedList(LinkedList ll1, LinkedList ll2){
        LinkedNode h1=ll1.getHead(), h2 = ll2.getHead(), dummy = new LinkedNode(0), cur = dummy;
        int add=0;
        while(h1!=null || h2!=null){
            int v1 = h1!=null? h1.val:0;
            int v2 = h2!=null? h2.val:0;
            int v = v1+v2+add;
            add = v%10;
            cur.next = new LinkedNode(v/10);
            cur = cur.next;
            if(h1!=null)
                h1 = h1.next;
            if(h2!=null)
                h2 = h2.next;
        }
        if(add>0){
            cur.next = new LinkedNode(1);
        }
        return dummy.next;
    }

    public static LinkedNode beginOfLoop(LinkedList ll){
        // fast slow meet at p1
        // same pace from p1 and head, meet at the rt
        LinkedNode fast = ll.getHead(), slow = ll.getHead();
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                break;
        }
        if(fast==null || fast.next==null)
            return  null; // no cycle

        fast = ll.getHead();
        while (fast!=slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
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
        HashSet<Integer> set = new HashSet<>();
        LinkedNode cur=head, dummy = new LinkedNode(0), pre=dummy;
        dummy.next=cur;
        while (cur!=null){
            if(set.contains(cur.val)){
                //removeCur();
                pre.next = cur.next;
            }else {
                set.add(cur.val);
                pre = cur;
            }
            cur=cur.next;
        }
    }

    public LinkedNode kthToLastNode(int k){
        LinkedNode cur=head;

        while (k>0 && cur!=null){
            cur=cur.next;
            k--;
        }

        LinkedNode rt = head;
        while (cur!=null){
            cur = cur.next;
            rt = rt.next;
        }
        return rt;
    }

    public void deleteMiddleNode(){
        if (head==null)
            return;

        LinkedNode slow=head, fast=head, pre = new LinkedNode(0);
        pre.next = slow;
        while (fast.next!=null && fast.next.next!=null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = slow.next;
    }

    public LinkedNode partitionLinkedList(int pivtal){
        // only when meet the smaller one behind the larger one, execute the insert action
        LinkedNode dummy = new LinkedNode(0), pre = dummy, cur = head, insertAfter = dummy;
        dummy.next = head;
        boolean toInsert = false;
        while(cur!=null){
            LinkedNode saved = cur.next;
            if(cur.val<pivtal){
                if(toInsert == true){
                    //remove
                    pre.next = cur.next;
                    LinkedNode insertBefore = insertAfter.next;
                    cur.next = insertBefore;
                    insertAfter.next = cur;
                }
                insertAfter = insertAfter.next;
            }else {
                pre = cur;
                toInsert = true;
            }
            cur = saved;
        }
        return  dummy.next;
    }

    public boolean isPalindrome(){
        // reverse linked List as a new one, compare two linked lists
        // cut the linked list into half, reverse one of the halves, compare two halves.
        LinkedNode reverseHead = copyreverseLinkedList(head), cur = head;
        while (reverseHead!=null && cur!=null) {
            if(reverseHead.val==cur.val){
                reverseHead = reverseHead.next;
                cur = cur.next;
            }else
                return false;
        }
        return reverseHead==cur;
    }

    public LinkedNode reverseLinkedList(LinkedNode head){
        LinkedNode dummy = null, cur=head;
        while (cur!=null){
            LinkedNode save = cur.next;
            cur.next = dummy;
            dummy = cur;
            cur = save;
        }
        return dummy;
    }

    public LinkedNode copyreverseLinkedList(LinkedNode head){
        LinkedNode dummy = null, cur=head, copy = null;
        while (cur!=null){
            LinkedNode save = cur.next;
            copy = new LinkedNode(cur.val);
            copy.next = dummy;
            dummy = copy;
            cur = save;

        }
        return dummy;
    }

    public LinkedNode rotateRight(LinkedNode head, int k) {
        int n=0;
        LinkedNode cur = head;
        while(cur!=null){
            n++;
            cur = cur.next;
        }
        if(n==0 || k==0 || k%n==0)
            return head;

        k = k%n;

        LinkedNode dummy = new LinkedNode(0), pre=dummy;
        dummy.next = head;
        cur=head;
        while(k>1){
            cur = cur.next;
            k--;
        }

        while(cur.next!=null){
            pre = pre.next;
            cur = cur.next;
        }

        LinkedNode newHead = pre.next;
        pre.next = null;
        cur.next = dummy.next;
        dummy.next = newHead;

        return dummy.next;
    }
}
