package org.example.ch02;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        int[] arr = {5,2,1,7,8,6,4,1,9,0};
        System.out.println("arr = " + Arrays.toString(arr));
        selectSort.selectionSort(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }
    /**
     * i와 j의 위치에 있는 값을 swap
     */
    private void swapElements(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * start 로부터 시작하는 최소값의 위치를 찾고, (start 포함)
     * 배열의 마지막 위치로 간다.
     */
    private int indexLowest(int[] arr, int st) {
        int lowIndex = st;
        for (int i = st; i < arr.length; i++) {
            if (arr[i] < arr[lowIndex]) {
                lowIndex = i;
            }
        }
        return lowIndex;
    }

    /**
     * 선택 정렬을 사용하여 요소 정렬
     */
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = indexLowest(arr, i);
            swapElements(arr, i, j);
        }
    }
}