package com.jd.algorithm.sort;

import java.util.Arrays;

public class Heap {

    private int[] arr;

    private int size;

    public Heap(int[] arr) {
        this.arr = arr;
        this.size = arr.length;
    }

    public int[] getArr() {
        return arr;
    }

    public void buildHeap() {
        //n/2-1之后的节点，以其自己为根的子树已经满足堆的条件
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int i) {
        int parentIndex = i;
        int leftChildIndex = leftChildIndex(parentIndex);
        int rightChildIndex;
        while (leftChildIndex < size) {
            rightChildIndex = leftChildIndex + 1;
            int min_index = leftChildIndex;
            //有右子节点，并且右子节点比较小
            if (rightChildIndex < size && arr[rightChildIndex] < arr[leftChildIndex]) {

                min_index = rightChildIndex;

            }
            if(arr[parentIndex] > arr[min_index]) {
                swap(parentIndex, min_index);
                parentIndex = min_index;
                leftChildIndex = leftChildIndex(parentIndex);
            }else{
                break;
            }
        }
    }

    public int moveMin(){
        swap(0,--size);
        siftDown(0);
        return arr[size];
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int leftChildIndex(int i) {
        return 2 * i + 1;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(new int[]{45,29,32,12,34,34,78});
        heap.buildHeap();
        System.out.println(Arrays.toString(heap.getArr()));
    }
}
