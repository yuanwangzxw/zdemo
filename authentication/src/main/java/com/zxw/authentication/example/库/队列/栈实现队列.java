package example.库.队列;

import java.util.Stack;

/**
 * 栈先进后出，两个栈就是顺序双重颠倒，就是先进先出
 * 定义两个栈InStack,OutStack,入队时push到InStack，出队时，
 * OutStack不为空，就从OutStack弹出栈顶元素
 * OutStack为空，先从InStack弹出所有元素，入栈到OutStack,最后弹出栈顶
 */
public class 栈实现队列 {

    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> outStack = new Stack<>();

    public void offer(Integer data) {
        inStack.push(data);
    }

    public Integer poll() {
        if (outStack.size() == 0) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

}
