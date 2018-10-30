package edu.test1;

public class Test {

    public int method() {
        Integer a = 1;
        return a;
    }


    public static void main(String[] args) {
        Test test = new Test();
        Test test1 = new Test();

        int method = test.method();
        int method1 = test1.method();

        System.out.println(method == method1);
    }

}
