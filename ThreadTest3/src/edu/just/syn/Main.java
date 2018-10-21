package edu.just.syn;

public class Main {

    public void print() {
        Object o = this;
        System.out.println(o);
    }

    public static void main(String[] args) {
        Main main1 = new Main();
        main1.print();

        Main main = main1;
        main.print();

        System.out.println(main.getClass().getAnnotations());
        System.out.println(main1.getClass().getAnnotations());
    }

}
