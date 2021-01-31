package example.库;

import lombok.Data;

@Data
public class FibT斐波那契数 {

    private String name;

    public static void main(String[] args) {
        System.out.println(fib2(6));
    }

    //斐波那契数，每个数=前两项之和,0,1,1,2,3,5,8
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        return fib(n - 1) + fib(n - 2);
    }

    //遍历比递归快
    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int sum = 0, first = 0, second = 1;
        for (int i = 0; i < n - 1; i++) {
            sum = first + second;
            //第一个数变成第二个数，第二个数变成第三个数，也就是和
            first = second;
            second = sum;
        }
        return sum;
    }
}
