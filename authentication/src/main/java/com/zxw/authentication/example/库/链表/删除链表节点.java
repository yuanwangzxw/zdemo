package example.库.链表;

/*
 *https://leetcode-cn.com/problems/delete-node-in-a-linked-list
 * 1.修改当前节点的前驱节点的next指针，指向当前节点的后继节点就行
 * 2.修改当前节点的值为后继节点的值，当前节点的next指针，指向后继节点的后继节点
 */
public class 删除链表节点 {

    //这种方法有问题，前提是删除节点不能是尾节点
    public void deleteNode(Node node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }

    class Node<T> {
        private Node<T> next;
        private T data;
    }
}
