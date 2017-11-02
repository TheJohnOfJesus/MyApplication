package com.example;

public class Main {
    public static void main(String[] args) throws Exception {
//        testStrLength();
//        System.out.println("result"+getZeroCount(1001,-1));
        caculate(100);
//        System.out.println("result" + getZeroCount(101));
//        caculate(101);
    }

    public static void testStrpinjie(String[] args) {
        String str = "";
        for (int i = 0; i < args.length; i++) {
            str = str + args[i] + ";";
        }
        System.out.println("John->Main:" + str);
    }

    public static void testStrLength() {
        String str = "测试";
        str = new String();
        System.out.println("testStrLength:" + str.length());
    }

    private static int getZeroCount1(int length, int startNum) {
//        System.out.println("getZeroCount1:"+startNum);
        if (length < 3) return -1;
        if (length == 3) return 20 * length;//(2+2*9)*9
        //(n+1)+(n-2)*9*2+C(n-1)*9=10n-17+C(n-1)*9;
        return 19 * length - 35 + getZeroCount1(length - 1, 9) * startNum;
    }

    private static int getZeroCount(int num,int extraZero) {
//        System.out.println("getZeroCount:"+num);
        if (num < 0) return 0;
        String value = String.valueOf(num);
        int startNum = Character.getNumericValue(value.charAt(0));
        int length = value.length();
        if (length == 1) return 0;
        if (length == 2) return num / 10;
        if (length == 3) return 2 * (startNum) + getZeroCount(Integer.valueOf(value.substring(1, length)),extraZero+1);//(2+2*9)*9
        //(n+1)+(n-2)*9+C(n-1)*firsNum=10n-17+C(n-1)*9;
        return getZeroCount1(length, startNum) +2+ getZeroCount(Integer.valueOf(value.substring(1, length)),extraZero+1);
    }

    private static void caculate(int num) {
//        String value=String.valueOf(num);
        System.out.println("time:" + System.currentTimeMillis());
        String value;
        int count = 0,totalCount=0;
        for (int i = 10; i <= num; i++) {
            totalCount=totalCount+i;
            value = String.valueOf(i);
//            int tempCount=0;
            for (int j = 0; j < value.length(); j++) {
//                tempCount++;
                if ('0' == value.charAt(j)) count++;
            }
//            System.out.print("tempCount:" + tempCount);
        }
        System.out.println("time:" + System.currentTimeMillis());
        System.out.println("count:" + count);
        System.out.println("totalCount:" + totalCount);
    }
}
