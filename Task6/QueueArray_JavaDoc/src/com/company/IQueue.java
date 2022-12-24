package com.company;

public interface IQueue {
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item) throws Exception;
    /*** Removes the object at the queue rear and returnsit.*/
    public void dequeue() throws Exception;
    /*** Tests if this queue is empty.*/
    public boolean isEmpty();
    /*** Returns the number of elements in the queue*/
    public int size();
}