package utils;

import java.util.Arrays;

public class Utils {
    public static void printArr(int[] arr) {
        for (int i: arr) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }

    /**
     * 快速排序
     * @param arr
     * @param start
     * @param end
     */
    public static void qSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int i = start, j = end;
        int key = arr[i];
        while (i < j) {
            // right
            while (i < j && key <= arr[j]) {
                j--;
            }
            arr[i] = arr[j];

            // left
            while (i < j && arr[i] <= key) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = key;

        qSort(arr, start, i - 1);
        qSort(arr, i + 1, end);
    }

    /**
     * 基数排序，适用于自然数数组
     * @param arr
     */
    public static void radixSort(int[] arr) {
        int len = arr.length;
        int exp = 1;
        int maxNum = Arrays.stream(arr).max().getAsInt();
        int[] buf = new int[len];

        while (exp <= maxNum) {
            // 统计个数
            int[] digitCount = new int[10];
            for (int num: arr) {
                int digit = (num / exp) % 10;
                digitCount[digit] ++;
            }

            // 堆叠，以便按照序号插入到新数组
            for (int i = 1; i < 10; i++) {
                digitCount[i] += digitCount[i-1];
            }

            // 倒序原数组，根据序号digitCount[index]-1插入到新数组
            for (int i = len - 1; i >= 0; i--) {
                int curNum = arr[i];
                int digit = (curNum / exp) % 10;

                buf[digitCount[digit] - 1] = curNum;
                digitCount[digit]--;
            }
            System.arraycopy(buf, 0, arr, 0, len);
            exp *= 10;
        }
    }
}


















