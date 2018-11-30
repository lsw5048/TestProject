package com.jd.inner;

public class Outer {
    private String name;
    public  int age;

    public class Builder {
        private String name;
        private int age;


        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }


    }

    public static void main(String[] args){
        Outer out = new Outer();
        Builder i = out.new Builder();
        i.age = 1;
        Builder i1 = out.new Builder();
        System.out.println(i1.age);
    }
}


