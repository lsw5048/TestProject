package com.jd.algorithm.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * 直接插入排序
 */
public class InsertSorting {

    private int[] arr={35,26,12,34,56,36,35,28,73,38,23,19,34,59,32,35,98,83,35,26,12,34,56,36,35,28,73,38,23,19,34,59,32,35,98,83};
    private long start;
    private long end;
    @Before
    public void start(){
        start = System.currentTimeMillis();
    }

    @After
    public void end(){
        end = System.currentTimeMillis();
        System.out.println(end-start + "ms(总耗时)");
    }
    @Test
    public void directInsertSorting(){
        int n = arr.length;
        //i从1开始，因为第一个已经排好序了，从第二个开始插入
        for (int i = 1; i < n; i++) {
            //缓存arr[i]
            int temp = arr[i];
            //依次与i之前的下标数据比较，遇到第一个<=temp的就插入
            int j;
            for (j = i-1; j >=0; j--) {
                //从最后一个开始，如果大于temp，向后移一位
                if(arr[j] > temp){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void diminishingIncrementSorting(){
        int n = arr.length;
        //step除以三递减
        for (int step = n/3; step >=1; step/=3) {
            for (int i = 0; i < step; i++) {
                stepSorting(arr,step,i);
            }

        }
        stepSorting(arr,1,0);
        System.out.println(Arrays.toString(arr));
    }

    private void stepSorting(int[] arr, int step, int start){
        int n = arr.length;
        for(int i = start+step; i < n; i+=step){
            int temp = arr[i];
            int j;
            for(j = i - step; j>=start; j-=step){
                if(arr[j]> temp){
                    arr[j+step] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+step] = temp;
        }
        //System.out.println(Arrays.toString(arr));
    }

}

