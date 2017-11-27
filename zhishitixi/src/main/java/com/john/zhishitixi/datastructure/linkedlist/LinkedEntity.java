package com.john.zhishitixi.datastructure.linkedlist;

/**
 * Created by John on 2017/11/15.
 */

public class LinkedEntity<T> {
    private int index;
    private T data;
    private int nextIndex=-1;
    public LinkedEntity(int index,T data){
        this.index=index;
        this.data=data;
    }
    public boolean hasNext(){
        return nextIndex>=0?true:false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }
}
