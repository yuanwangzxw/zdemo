package example.库.链表;

public class 反转链表<T> {

    public static void main(String[] args) {
        new 反转链表<Integer>().print();
    }

    public void print() {
        root = new Node(0, null, null);
        Node node1 = new Node(1, root, null);
        Node node2 = new Node(2, node1, null);
        Node node3 = new Node(3, node2, null);
        Node node4 = new Node(4, node3, null);

        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        print(root);
        reverseList4();
        print(root);
    }

    public void print(Node root) {
        for (Node c = root; c != null; c = c.next) {
            System.out.print(c.data);
        }
        System.out.println("--------------");
    }

    private Node<T> root;

    //交换每个node的pre和next指针，最后一个节点变成root
    public void reverseList() {//开始这里写的current=current.next，忘了next和pre已交换了
        for (Node current = root; current != null; current = current.pre) {
            Node temp = current.pre;
            current.pre = current.next;
            current.next = temp;
            if (current.pre == null) {
                root = current;
            }
        }
    }

    //利用递归的后序遍历，还会倒转回来的思想
    //单向链表，所以传入当前与后继节点，
    public void reverseList2(Node pre, Node current) {
        if (current.next != null) {
            reverseList2(current, current.next);
        } else {
            root = current;
        }
        current.next = pre;//必须传2个参数，不然这里没法指向
    }

    //递归的关键在于，搞清楚方法的作用，这里是返回next节点,同样利用后序遍历的回转
    public void reverseList3(Node current) {
        if (current == null || current.next == null) {
            root = current;
            return;
        }
        reverseList3(current.next);
        //后继节点的next指向当前节点，当前节点的next置null，不然会有环
        current.next.next = current;
        current.next = null;
    }

    //遍历的问题就是无法直接将当前节点的next指针，指向前驱节点
    //所以定义临时变量，存储前驱节点，由于将next已经指向前驱节点后，无法向后遍历，
    //所以再定义一个临时变量，存储next指针，
    public void reverseList4() {
        Node temp = null;
        Node temp2;
        for (Node current = root; current != null; current = temp2) {
            temp2 = current.next;
            current.next = temp;
            temp = current;
        }
        root = temp;
    }


    class Node<T> {
        public Node(T data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

        private T data;
        private Node<T> pre;
        private Node<T> next;
    }

}

