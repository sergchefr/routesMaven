package ru.ifmo.server.history;

import java.util.ArrayDeque;

public class History {
    //String filepath;
    ArrayDeque<String> lcoms = new ArrayDeque<>();
    public History() {

    }

    public String showHistory(){
        String c ="";
        for (String lcom : lcoms) {
            c=c+lcom+", ";
        }
        return c.substring(0,c.length()-2);

    }
    public void add(String com){
        lcoms.addFirst(com);
        if(lcoms.size()>12)lcoms.pollLast();
    }
}
