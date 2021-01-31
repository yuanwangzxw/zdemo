package example.库.链表;

import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/solution/161huan-xing-lian-biao-javajian-ji-ti-jie-hashkuai/
 * 没有尾节点，找到链表重复节点
 * 1.快慢指针，速度不同，某个节点会相遇
 */
public class 判断链表有环 {

    private Node root;

    public static void main(String[] args) {
        new 判断链表有环().test();
    }

    public void test() {
        Node node3 = new Node(3, null);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        root = new Node(0, node1);
        node3.next = node1;
        boolean result = containCycle2(root);
        System.out.println(result);
    }

    //节点计数
    public boolean containCycle(Node head) {
        HashSet<Node> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    public boolean containCycle2(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    class Node {
        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

        private Integer data;
        private Node next;
    }
}
