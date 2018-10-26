package edu.just;

import java.util.ArrayList;
import java.util.List;

class MyList1 {

    private List list = new ArrayList();

    public void setList(List list) {
        this.list = list;
    }

    public List getList() {
        return list;
    }
}

class Add {

    private String lock;
    private MyList1 list1;

    public Add(String lock) {
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
        }
    }
}

public class MyRun {



}
