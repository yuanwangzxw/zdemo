package example;

public class Sort {
    public static void main(String[] args) {
        int[] a = new int[]{3, 8, 5, 6, 1, 2, 9, 0, 4, 7};
        insert(a);
        for (int i = 0; i < 10; i++) {
            System.out.println(a[i]);
        }
    }

    public static void bundle(int[] arr) {
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

    public static void min(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length - 1; j++) {
                if (arr[j + 1] < arr[min]) {
                    min = (j + 1);
                }
            }
            if (arr[i] > arr[min]) {
                arr[i] ^= arr[min];
                arr[min] ^= arr[i];
                arr[i] ^= arr[min];
            }
        }
    }

    public static void insert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                //一直往前找，直到比当前数大，或者走到第一位
                int tmp = arr[i];
                int j = i - 1;
                for (; j >= 0 && arr[j] > tmp; arr[j + 1] = arr[j], j--) ;
                arr[j + 1] = tmp;
            }
        }
    }

    public static void binaryInsert(int[] arr) {

    }
}
