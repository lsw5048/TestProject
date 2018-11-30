package com.jd.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class SelectionSort {

    private int[] arr={45,29,32,12,34,34,78};

    @Test
    public void directSelectionSort(){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int min_index = i;
            //找出后面的最小值的索引位置
            for (int j = i+1; j < n; j++) {
                if(arr[min_index]>arr[j]){
                    min_index = j;
                }
            }
            swap(arr, i,min_index);
        }
        System.out.println(Arrays.toString(arr));
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Test
    public void heapSort(){
        Heap minHeap = new Heap(arr);
        minHeap.buildHeap();
        for (int i = 0; i < arr.length-1; i++) {
            minHeap.moveMin();
        }
        System.out.println(Arrays.toString(arr));
    }
}
