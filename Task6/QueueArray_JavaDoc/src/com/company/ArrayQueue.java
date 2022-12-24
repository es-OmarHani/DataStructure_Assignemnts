package com.company;

public class ArrayQueue implements IQueue{
    int maxSize = 1000;
    int [] arr = new int[maxSize];
    int front ;
    int rear ;
    int count ;

    ArrayQueue(){
        front = maxSize-1 ;
        rear = maxSize-1 ;
        count = 0 ;
    }

    public void display(){
        int tmpFront = front ;
        System.out.print("[");
        for (int i = 0; i < count; i++) {
            System.out.print(arr[tmpFront]);

            //to get all item in the circular array
            tmpFront = ( tmpFront + 1 ) % maxSize ; /* when tmpFront = array size then
                                                            tmpFront = ( array size + 1 ) % maxSize
                                                            tmpFront = ( maxSize ) % maxSize
                                                            tmpFront = 0
                                                    */

            //to avoid print "," after last item
            if( i != count-1 )
                System.out.print(", ");
        }
        System.out.print("]");
    }

    @Override
    public void enqueue(Object item) throws Exception {
        if ( count == maxSize ) {
            throw new Exception();
        }
        //to make array circular
        if (front == 0)
            front = maxSize-1 ;
        else
            front -= 1 ;
        arr[front] = (int) item ;
        count++ ;
    }

    @Override
    public void dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        //to make array circular
        if (rear == 0)
            rear = maxSize-1 ;
        else
            rear -= 1 ;
        --count ;

    }

    @Override
    public boolean isEmpty() {
        if ( count == 0 )
            return true ;
        else
            return false;
    }

    @Override
    public int size() {
        return count ;
    }
}