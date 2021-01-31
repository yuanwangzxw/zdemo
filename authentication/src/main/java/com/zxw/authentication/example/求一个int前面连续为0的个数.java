package com.zxw.authentication.example;

/**
 * @author zxw
 * 10的二进制表示为 0000 0000 0000 0000 0000 0000 0000 1010,返回28
 * 这是Integer的numberOfLeadingZeros方法
 */
public class 求一个int前面连续为0的个数 {

    /**
     * 思路：找最高位1的位置，找数据二分法最快了
     * 精典：if修改让代码结构扁平化,找最低位1可以<<
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 1; i < 32; i++) {
            StringBuilder base = new StringBuilder();
            for (int j = 0; j < 32; j++) {
                base.append(i==j?1:0);
            }
        System.out.println("leftZeroCount = " + whileRightZeroCount(Integer.parseInt(base.toString(),2)));
        }
    }

    /**
     * 求一个int前面连续为0的个数
     * @param n
     * @return
     */
    public static int whileLeftZeroCount(int n) {
        if (n == 0) {
            return 32;
        }
        int count = 0;
        for (int gap = 16; gap > 0; gap >>= 1) {
            if (n >>> (32 - gap) == 0) {
                count += gap;
                n <<= gap;
            }
        }
        return count;
    }

    /**
     * 求一个int后面连续为0的个数
     * @param n
     * @return
     */
    public static int whileRightZeroCount(int n) {
        if (n == 0) {
            return 32;
        }
        int count=0;
        for (int gap = 16; gap > 0; gap >>= 1) {
            if (n << 32 - gap == 0) {
                count += gap;
                n >>= gap;
            }
        }
        return count;
    }


    public static int leftZeroCount(int n) {
        if (n == 0) {
            return 32;
        }
        int count = 0;
//        if (n << 16 == 0) {
//            //左移来判断是错的，最高1后面还有1会影响判断，左移应该用来判断最低位1
//        }
        if (n >>> 16 == 0) {
            //最高位1在低16位
            count += 16;
            //这里将n左移16位，这样ifelse判断的结果数据都是一样的，
            //命名为if修改，如果直接用ifelse，不分二分下去，整个代码结构的ifelse类型于二叉树，太丑
            //将ifelse的结果弄成一样，反正最后只是统计计数，不影响，关键代码结构扁平化了
            n <<= 16;
        }
        if (n >>> 24 == 0) {
            //最高位1在9-16位上
            count += 8;
            n <<= 8;
        }
        if (n >>> 28 == 0) {
            //最高位在5-8位上
            count += 4;
            n <<= 4;
        }
        if (n >>> 30 == 0) {
            //最高位在3-4位上
            count += 2;
            n <<= 2;
        }
        if (n >>> 31 == 0) {
            count += 1;
        }
        return count;
    }


}
