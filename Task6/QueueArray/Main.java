import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            //take input from user
            Scanner scan = new Scanner(System.in);
            String arr = scan.nextLine().replaceAll("\\[|\\]", "");
            String [] numbs = arr.split(", ");

            //put the numbers into queue
            ArrayQueue queue = new ArrayQueue();
            if (!arr.isEmpty()) {
                for (int i = numbs.length-1 ; i >= 0 ; i-- )
                    queue.enqueue(Integer.parseInt(numbs[i]));
            }


            //Take methode from user
            String methode = scan.nextLine();

            switch (methode) {
                case "enqueue":
                    int element = scan.nextInt();
                    queue.enqueue(element);
                    queue.display();
                    break;

                case "dequeue":
                    queue.dequeue();
                    queue.display();
                    break;


                case "isEmpty":
                    if(queue.isEmpty())
                        System.out.println("True");
                    else
                        System.out.println("False");
                    break;

                case "size":
                    System.out.println(queue.size());
                    break;
            }


        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}

interface IQueue {
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item) throws Exception;
    /*** Removes the object at the queue rear and returnsit.*/
    public void dequeue() throws Exception;
    /*** Tests if this queue is empty.*/
    public boolean isEmpty();
    /*** Returns the number of elements in the queue*/
    public int size();
}

class ArrayQueue implements IQueue {
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
            tmpFront = ( tmpFront + 1 ) % maxSize ;
            /* when tmpFront = array size then
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
    

