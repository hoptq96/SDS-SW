package sort;

import java.util.Arrays;

public class QuickSort2 {

    static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = arr[(left + right) / 2];
        int i = left, j = right;
        do {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        } while (i < j);

        quickSort(arr, left, j );
        quickSort(arr, i, right);
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 1, 0, 4, 7, 9, 2, 6};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
