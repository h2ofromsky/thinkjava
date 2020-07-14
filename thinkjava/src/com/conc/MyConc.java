package com.conc;

public class MyConc {
    public static void main(String[] args) {
        System.out.println("hello world");
        String str = "cf-workphone.oss-cn-hangzhou.aliyuncs.com/ originalRecord/P00000000644/           202007/ac27a5ce052d3a0e4e5c3657cc8043d8-4b2c19b5969bab4e31eccd5e73960b94.mp3";
        System.out.println(str);
        String str2 = str.replace(" ","");
        System.out.println(str2);
        System.out.println(str2.length());

    }
}
