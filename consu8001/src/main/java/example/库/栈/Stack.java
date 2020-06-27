package example.库.栈;

public class Stack<E> {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
        System.out.println("pop = " + stack.pop());
    }

    private Node<E> head;
    private Node<E> tail;

    public Stack() {
        tail = new Node<>(null, null);
        head = new Node<>(null, tail);
    }

    public void push(E element) {
        for (Node<E> current = head;; current = current.next) {
            if (current.next == tail) {
                current.next = new Node<>(element, tail);
                return;
            }
        }
    }

    public E pop(){
        for (Node<E> current = head;; current = current.next) {
            if (current.next.next == tail) {
                Node<E> temp = current.next;
                current.next = tail;
                return temp.data;
            }
        }
    }

    class Node<E>{
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
        private Node<E> next;
        private E data;
    }
}
