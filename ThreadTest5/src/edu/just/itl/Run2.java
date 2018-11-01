package edu.just.itl;

import java.util.Date;

class InheritableThreadLocaltTest extends InheritableThreadLocal {

    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }

    @Override
    protected Object childValue(Object parentValue) {
        return parentValue;
    }
}

public class Run2 {

    public static void main(String[] args) {
        InheritableThreadLocaltTest itll = new InheritableThreadLocaltTest();
        Object o = itll.initialValue();
        System.out.println(o);
        itll.childValue("luwenhe");
        System.out.println(o);
    }

}
