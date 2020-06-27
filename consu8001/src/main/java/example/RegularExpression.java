package example;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

    /**
     * 指定字符
     * \\w :数字，字母，下划线
     * \\d :数字
     * \\s :空格
     * []   固定匹配         [0-9a-fA-F]     21cdA

     * 字符长度与判断
     * {m,n}   m到n的区间       \\d{3,4}    3456或345
     * {m,}     至少m个         \\d{3,}     2347923
     * .   :一个任意字符        \\d.  3
     * ?   :0个或1个           \\d?  3或没有
     * *   :0或多个任意字符
     * ^$  :开头与结尾          ^\\d{3}$     001,002,023...
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //截取
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String reg = "\\d{4}\\-(\\d{2})\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(time);
        if (matcher.matches()) {
            //提取第一个小括号中的值
            String group = matcher.group(1);
        }

        //比较与替换
        String s = "the quick brown fox jumps over the lazy dog.";
        String r = s.replaceAll("\\s([a-z]{4})\\s", " <b>$1</b> "); //$1表示前面参数的值
        System.out.println(r);  //the quick brown fox jumps <b>over</b> the <b>lazy</b> dog.

    }
}
/**
 * hash算法（摘要算法）：任意长度输入生成固定长度的输出，不同的字符串，hash后不同，相同的字符串，hash后相同
 * 作用：判断两个字符串是否相同，验证数据是否被篡改
 * 常见hash算法：md5,HmacMD5(加盐的md5)，SHA-1,SHA-256,SHA-512,RipeMD-160,bcrypt
 * 工具类bouncyCastle
 *
 * 对称加密：DES,AES,IDEA
 * 算法	密钥长度	工作模式	填充模式
 * DES	56/64	ECB/CBC/PCBC/CTR/...	NoPadding/PKCS5Padding/...
 * AES	128/192/256	ECB/CBC/PCBC/CTR/...	NoPadding/PKCS5Padding/PKCS7Padding/...
 * IDEA	128	ECB	PKCS5Padding/PKCS7Padding/...
 *
 */
