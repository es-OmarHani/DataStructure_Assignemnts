import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
 * Class DoublyLinkedList
 */
class DoubleLinkedList {
    /* Implement your linked list class here*/
    private int size = 0;
    private Node head = null;
    private Node tail = null;

    // Internal node class to represent data

    /**
     * Class For creating Nodes
     */
    private static class Node {
        private Object data;
        private Node prev, next;

        public Node (Object data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }

    }

    /**
     * Method append Elements at End of linked list
     * @param elem which will be appended
     */
    public void add(Object elem) {
        addLast(elem);
    }

    // Add a node to the tail of the linked list, O(1)

    /**
     * Method That add Elements from last of linkedList
     * @param elem which will be added
     */
    public void addLast(Object elem) {
        if (isEmpty()) {
            //If empty then will change tail and head at the same new node
            head = tail = new Node(elem, null, null);
        } else {
            //Else will change next of tail to new node
            tail.next = new Node(elem, tail, null);
            //Then will make tail pointer pointing to last added element
            tail = tail.next;
        }
        //Increase size with one
        size++;
    }


    //Add Element at start of list

    /**
     * Method That add Elements from start of linkedList
     * @param elem which will be added
     */
    public void addFirst(Object elem){
        /*  */
        if (isEmpty()) {
            //If empty then will change tail and head at the same new node
            head = tail = new Node(elem, null , null);
        } else {
            //Else will change next of head to new node
            head.prev = new Node(elem , null , head );
            //Then will make head pointer pointing to first added element
            head = head.prev;
        }
        //Increase size with one
        size++;
    }

    /**
     * Method That get Size of linkedList
     * @return int represent size
     */
    public int size() {
        /* Method That get Size of linkedList  */
        return size;
    }

    /**
     * Method That Check if LinkedList Empty Or not
     * @return boolean represent is Empty
     */
    public boolean isEmpty() {
        /* Method That Check if LinkedList Empty Or not */
        return size() == 0;
    }

    /**
     * Method That remove Element from start of linkedList
     * @return Object that represent removed elem
     */
    public Object removeFirst() {

        // Can't remove data from an empty list
        if (isEmpty()){
            throw new RuntimeException("Empty List");
        }

        // Extract the data at the head and move
        // the head pointer forwards one node
        Object data = head.data;
        //Removing will be from the first so it will make head the next element
        head = head.next;
        --size;

        // If the list is empty set the tail to null
        if (isEmpty()) tail = null;

            // Do a memory cleanup of the previous node
        else head.prev = null;

        // Return the data that was at the first node we just removed
        return data;
    }


    /**
     * Method That get First Element from linkedList but without remove that Element
     * @return Object that represent elem
     */
    public Object peekFirst() {
        /* Method That get First Element from linkedList but without remove that Element */

        //If empty will out error message
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }


    /**
     * Method That Print Linked LIST
     * @return string represent printed LinkedList
     */
    @Override
    public String toString() {
        /* Method That Print Linked LIST */

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node trav = head;
        while (trav.next != null) {
            sb.append(trav.data + ", ");
            trav = trav.next;
        }
        sb.append(trav +  "]");
        return sb.toString();
    }

}


interface IStack {

    /*** Removes the element at the top of stack and returnsthat element.
     * @return top of stack element, or through exception if empty
     */

    public Object pop();

    /*** Get the element at the top of stack without removing it from stack.
     * @return top of stack element, or through exception if empty
     */

    public Object peek();

    /*** Pushes an item onto the top of this stack.
     * @param object to insert*
     */

    public void push(Object element);

    /*** Tests if this stack is empty
     * @return true if stack empty
     */
    public boolean isEmpty();

    /**
     * Get size of list
     * @return int represent size
     */
    public int size();
}

/**
 * Main Class For Stack which will inherit all methods from DoubleLinkedList Class
 */
public class Main extends DoubleLinkedList implements IStack {

    // Create an empty stack

    /**
     * Default Constructor
     */
    public Main() {}

    // Create a Stack with an initial element

    /**
     * Constructor For creating list with Element
     * @param firstElem element will push to stack
     */
    public Main(Object firstElem) {
        push(firstElem);
    }

    // Push an element on the stack
    /*** Pushes an item onto the top of this stack.
     * @param elem object to insert*
     */
    public void push(Object elem) {
        addFirst(elem);
    }

    // Pop an element off the stack
    // Throws an error is the stack is empty
    /*** Removes the element at the top of stack and returnsthat element.
     * @return top of stack element, or through exception if empty
     */
    public Object pop() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return removeFirst();
    }

    // Peek the top of the stack without removing an element
    // Throws an exception if the stack is empty
    /*** Get the element at the top of stack without removing it from stack.
     * @return top of stack element, or through exception if empty
     */
    public Object peek() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return peekFirst();
    }

    /**
     * Here the main code
     * @param args which is array if user enter parameter from terminal when run  code
     */
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //Create object from scanning class
        Scanner input = new Scanner(System.in) ;

        //Create object from stack
        // DoubleLinkedList obj = new DoubleLinkedList(); 

        Main list = new Main();

        try{
            //Create array with input numbers
            String numsString = input.nextLine().replace("[", "").replace("]", "");

            String nums[] = numsString.split(", ");

            //Create variable take method needed
            String method = input.next();

            if(nums.length >= 1 && !nums[0].equals("") ){
                // loop to append Entered Elements in list 
                for(int i = 0 ; i < nums.length ; i++){
                    list.add(Integer.parseInt(nums[i]));
                }
            }

            //Switch on method Entered from user
            switch(method){

                case "size" :
                    //print out size
                    System.out.println(list.size());
                    break;

                case "isEmpty" :
                    //Get return value from function 
                    boolean flag = list.isEmpty();

                    //Check on flag true or false
                    if(flag)
                        System.out.println("True");
                    else
                        System.out.println("False");

                    break;

                case "push":
                    //Get Index & elem from user
                    Object elem = input.next() ;

                    //Push element
                    list.push(elem);

                    //Print list
                    System.out.println(list.toString());
                    break;

                case "pop" :
                    //pop Element
                    elem = list.pop();
                    //Print list
                    System.out.println(list.toString());
                    break;


                case "peek" :
                    //peek top element
                    elem = list.peek();
                    //Print list
                    System.out.println(elem);
                    break;

                default :
                    throw new RuntimeException("Invalid Method");
            }

        }

        catch(Exception e){
            System.out.println("Error");
        }

    }

}
