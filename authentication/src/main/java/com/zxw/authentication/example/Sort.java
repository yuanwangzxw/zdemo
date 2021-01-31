package com.zxw.authentication.example;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.Interceptor;

public class Sort {
    /**
     * 插快归堆
     * 选泡插，快归堆希统计基
     * 选：selection n^2          n^2         n^2 不稳
     * 泡：bubble    n^2          n^2         n    稳
     * 插：insertion n^2          n^2         n    稳
     * 快：quick     nlog2^n      n^2       nlog2^n  不稳
     * 归：merge     nlog2^n    nlog2^n     nlog2^n  稳
     * 堆：heap      nlog2^n    nlog2^n     nlog2^n 不稳
     * 希：shell     n^1.3        n^2         n   不稳
     * 桶：bucket    n+k          n^2         n   稳
     * 计：counting  n+k          n+k         n+k 稳
     * 基：radix     n*k          n*k         n*k 稳
     */
    public static void main(String[] args) {
//        int[] a = new int[]{3, 8, 5, 6, 1, 2, 9, 0, 4, 7};
//        sort(a, 0, a.length - 1);
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
        for (int i = 1; i < 32; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 32; j++) {
                sb.append(j==i?1:0);
            }
            System.out.println(sort(Integer.parseInt(sb.toString(),2)));
        }

    }

    public static void bubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean swap = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    /**
     * 选择排序：遍历数组，每次遍历找到最值，与边值替换
     *
     * @param arr
     */
    public static void selection(int[] arr) {
        //注意，这里外层循环可以写成arr.length-1;因为内循环是i+1，最后一位进不去的
//        for (int i=0;i<arr.length-1;i++) {
//            for (int j=i+1;j<arr.length;j++) {
//                if (arr[j] < arr[i]) {
//                    arr[i] ^= arr[j];
//                    arr[j] ^= arr[i];
//                    arr[i] ^= arr[j];
//                }
//            }
//        }
        //第二中，每次遍历找出最大值与最小值与边值互换
        for (int left = 0, right = arr.length - 1; left < right; left++, right--) {
            int min = left;
            int max = right;
            for (int i = left; i <= right; i++) {
                if (arr[min] > arr[i]) {
                    min = i;
                }
                if (arr[max] < arr[i]) {
                    max = i;
                }
            }
            if (arr[left] > arr[min]) {
                arr[left] ^= arr[min];
                arr[min] ^= arr[left];
                arr[left] ^= arr[min];
                //如果最大值索引刚好是left，那么这里换了位置，下面max交换时就会出错
                if (max == left) {
                    max = min;
                }
            }
            if (arr[right] < arr[max]) {
                arr[right] ^= arr[max];
                arr[max] ^= arr[right];
                arr[right] ^= arr[max];
            }
        }
    }

    /**
     * 遍历数组，将当前数据往前插入，和冒泡一样两两替换，直到当前数据比前一个大
     *
     * @param arr
     */
    public static void insertion(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                arr[j] ^= arr[j - 1];
                arr[j - 1] ^= arr[j];
                arr[j] ^= arr[j - 1];
            }
        }
    }

    public static void shell(int[] arr) {
        for (int gap = 4; gap > 0; gap >>= 1) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j >= gap && arr[j] < arr[j - gap]; j -= gap) {
                    arr[j] ^= arr[j - gap];
                    arr[j - gap] ^= arr[j];
                    arr[j] ^= arr[j - gap];
                }
            }
        }
    }

    /**
     * 将两个数组有序放到新数组里，分治的方式，将数据递归二分下去，将数组长度为1则肯定有序
     *
     * @return
     */
    public static int[] merge(int[] arr, int l, int r) {
        //l是在arr左索引，r是在arr右索引
        if (l == r) {
            return new int[]{arr[l]};
        }
        //并不是真的拆数组，而是记录数组二分后，首尾索引位置
        int mid = l + (r - l) / 2;
        int[] leftArr = merge(arr, l, mid);
        int[] rightArr = merge(arr, mid + 1, r);
        int[] temp = new int[leftArr.length + rightArr.length];
        int i = 0, j = 0, k = 0;
        //遍历两个有序数组,将两个数组中更小的值放到新数组，若其中一个数组已经复制完，直接复制另一个数组
        while (i < leftArr.length || j < rightArr.length) {
            if (i == leftArr.length) {
                temp[k++] = rightArr[j++];
                continue;
            }
            if (j == rightArr.length) {
                temp[k++] = leftArr[i++];
                continue;
            }
            temp[k++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
//        while (i < leftArr.length && j < rightArr.length) {
//            temp[k++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
//        }
//        while (i < leftArr.length) {
//            temp[k++] = leftArr[i++];
//        }
//        while (j < rightArr.length) {
//            temp[k++] = rightArr[j++];
//        }
        return temp;
    }

    /**
     * 取一个基数，小于这个基数放数组左边，大于这个基数放数组右边，递归这个基数的左右子序列
     * 左右交换：和多个数交换一样，先用临时变量存第一个值，后面的值依次赋给前面的值，最后一个值存最开始临时变量的值
     */
    public static void quick(int s[], int l, int r) {
        if (l >= r || r >= s.length) {
            return;
        }
        int i = l - 1, k = l, j = r, x = s[l];
        while (k < j) {
            if (s[k] < x) {
                i++;
                s[i] ^= s[k];
                s[k] ^= s[i];
                s[i] ^= s[k];
                k++;
            } else if (s[k] > x) {
                j--;
                s[j] ^= s[k];
                s[k] ^= s[j];
                s[j] ^= s[k];
            } else {
                k++;
            }
        }
//        while (i < j) {
//            //由于取左边的数作为基数， 从右向左找第一个小于x的数
//            while (i < j && s[j] >= x) {
//                j--;
//            }
//            if (i < j){
//                s[i++] = s[j];
//            }
//            // 从左向右找第一个大于等于x的数
//            while (i < j && s[i] < x) {
//                i++;
//            }
//            if (i < j){
//                s[j--] = s[i];
//            }
//        }
//        s[i] = x;
        quick(s, l, i);
        quick(s, j, r);
    }

    public static void heapify(int[] tree, int lastIndex, int i) {
        if (i >= lastIndex) {
            return;
        }
//        int leftIndex = i * 2 + 1;
        int leftIndex = i << 1 | 1;
        int rightIndex = (i << 1) + 2;
        int maxIndex = i;
        if (leftIndex <= lastIndex && tree[leftIndex] > tree[maxIndex]) {
            maxIndex = leftIndex;
        }
        if (rightIndex <= lastIndex && tree[rightIndex] > tree[maxIndex]) {
            maxIndex = rightIndex;
        }
        if (i != maxIndex) {
            tree[maxIndex] ^= tree[i];
            tree[i] ^= tree[maxIndex];
            tree[maxIndex] ^= tree[i];
            //递归向下判断，被交换后的节点是否需要heapify
            heapify(tree, lastIndex, maxIndex);
        }
    }

    public static void heap(int[] tree) {
        int lastIndex = tree.length - 1;
        //(最后一个索引-1)/2等于最后一个非叶子节点
        int lastParent = (lastIndex - 1) >> 1;
        //循环heapify构建大顶堆
        for (int i = lastParent; i >= 0; i--) {
            heapify(tree, lastIndex, i);
        }
        for (int i = lastIndex; i >= 0; ) {
            //顶端元素与数组最后一位交换,像选择排序
            tree[0] ^= tree[i];
            tree[i] ^= tree[0];
            tree[0] ^= tree[i];
            //heapify交换后的顶端元素,这里要先--，因为i已经交换过了,要排除掉
            heapify(tree, --i, 0);
        }

    }

    public static void bucket(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int[] temp = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (j == arr[i]) {
                    temp[j]++;
                }
            }
        }
        int n = 0;
        for (int i = 0; i < temp.length; i++) {
            while (temp[i] > 0) {
                arr[n++] = i;
                temp[i]--;
            }
        }
    }

    public static int sort(int n) {
        System.out.println("commit test 1");
        return 0;
    }


}
