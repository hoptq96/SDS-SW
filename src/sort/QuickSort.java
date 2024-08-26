package sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 1, 0, 4, 7, 9, 2, 6};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    static void quickSort(int[] arr, int low, int height) {
        if (low >= height) return;

        int pivotIndex = sortPivotAndGetPivotIndex(arr, low, height);
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, height);
    }

    private static int sortPivotAndGetPivotIndex(int[] arr, int low, int height) {
        int pivotValue = arr[height];
        int i = low;
        for (int j = low; j < height; j++) {
            if (arr[j] < pivotValue) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        arr[height] = arr[i];
        arr[i] = pivotValue;

        return i;
    }
}