package example.库.栈;

import java.util.Stack;

/**
 * 要求字符串中，括号前后匹配，如a{[]afjieo()}
 * https://leetcode-cn.com/problems/valid-parentheses/solution
 */
public class 判断字符中括号是否对称 {
    public static void main(String[] args) {
        boolean result = new 判断字符中括号是否对称().test();
        System.out.println("括号是否前后匹配 = " + result);
    }

    //利用栈先入后出，类似递归后序遍历，方向相反的特性
    //遍历字符串，左括号入栈，右括号就与出栈元素匹配，最后如果栈是空的，就完全匹配
    private boolean test() {
        String str = "a{[afjieo]()}";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                Character pop = stack.pop();
                if (pop == null || (c == '}' && pop != '{' || c == ']' && pop != '[' || c == ')' && pop != '(')) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}
