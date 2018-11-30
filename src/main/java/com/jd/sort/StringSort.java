package com.jd.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringSort {
    @Test
    public void testComparator(){
        List<String> strings = Arrays.asList("tsbg", "ertb", "ustb", "abr", "ctf");
        Collections.sort(strings,(s1,s2)->{
            return s2.charAt(0)-s1.charAt(0);
        });
        System.out.println(strings);
    }
}
