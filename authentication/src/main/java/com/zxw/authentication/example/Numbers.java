package com.zxw.authentication.example;


import org.apache.commons.lang3.StringUtils;

public class Numbers {

    private static String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static int scale = 62;

    /**
     * 10进制转为62进制
     *
     * @param num Long 型数字
     * @return 62进制字符串  如果不够6个长度，左侧填充0，例:000asf
     */
    public static String base62Encode(long num) {
        StringBuilder sb = new StringBuilder();
        int remainder = 0;
        //如果值大61，才会进位
        while (num > scale - 1) {
            remainder = Long.valueOf(num % scale).intValue();
            sb.append(chars.charAt(remainder));
            num = num / scale;
        }
        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        String value = sb.reverse().toString();
        //不足6位，以0补全
        return StringUtils.leftPad(value, 6, '0');
    }


    public static void main(String[] args) {
        System.out.println("base62Encode(10) = " + base62Encode(3843));
        System.out.println("ten2sixTwo(10) = " + ten2sixTwo(3843));
        System.out.println("ten2sixteen(255) = " + ten2sixteen(255));
        System.out.println("ten2eight(64) = " + ten2eight(63));
        System.out.println("ten2two(10) = " + ten2two(10));
        System.out.println("st2ten(63) = " + st2ten("zz"));
        System.out.println("eight2ten(63) = " + eight2ten("63"));
    }

    public static String ten2sixTwo(long num) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(chars.charAt(Long.valueOf(num % 62).intValue()));
            num /= 62;
        }
        return sb.reverse().toString();
    }

    public static String sixteen = "0123456789ABCDEF";

    public static String ten2sixteen(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 15) {
            sb.append(sixteen.charAt(Long.valueOf(num & 15).intValue()));
            num >>= 4;
        }
        sb.append(sixteen.charAt(Long.valueOf(num).intValue()));
        return sb.reverse().toString();
    }

    public static String ten2eight(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 7) {
            sb.append(Long.valueOf(num & 7));
            num >>= 3;
        }
        sb.append(num);
        return sb.reverse().toString();
    }


    public static String ten2two(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num & 1);
            num >>= 1;
        }
        return sb.reverse().toString();
    }

    public static long st2ten(String num) {
        int count=0;
        for (int i = num.length()-1; i >= 0; i--) {
            count+=Math.pow(62,num.length()-i-1)*chars.indexOf(num.charAt(i));
        }
        return count;
    }

    public static long eight2ten(String num) {
        int count=0;
        for (int i = 0; i < num.length(); i++) {
            count += Integer.parseInt(String.valueOf(num.charAt(num.length() - i - 1))) * Math.pow(8, i);
        }
        return count;
    }


}
