import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// interface ILinkedList {
// /**
// * Inserts a specified element at the specified position in the list.
// * @param index
// * @param element
// */
// public void add(int index, Object element);
// /**
// * Inserts the specified element at the end of the list.
// * @param element
// */
// public void add(Object element);
// /**
// * @param index
// * @return the element at the specified position in this list.
// */
// public Object get(int index);

// /**
// * Replaces the element at the specified position in this list with the
// * specified element.
// * @param index
// * @param element
// */
// public void set(int index, Object element);
// /**
// * Removes all of the elements from this list.
// */
// public void clear();
// /**
// * @return true if this list contains no elements.
// */
// public boolean isEmpty();
// /**
// * Removes the element at the specified position in this list.
// * @param index
// */
// public void remove(int index);
// /**
// * @return the number of elements in this list.
// */
// public int size();
// /**
// * @param fromIndex
// * @param toIndex
// * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
// */
// public ILinkedList sublist(int fromIndex, int toIndex);
// /**
// * @param o
// * @return true if this list contains an element with the same value as the specified element.
// */
// public boolean contains(Object o);
// }


public class SinlgleLinkedList {
	/* Implement your linked list class here*/
    private int size = 0;
    private Node head = null;
    private Node tail = null;

    // Internal node class to represent data
    private static class Node {
        private Object data;
        private Node next;

        public Node (Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    /********************************************* CLEAR  ************************************************/
    // Empty this linked list, O(n)
    public void clear() {
        //Create traversal pointer to iterate on list 
        Node trav = head ;
        //As long trav not at end [null] then remove elements
        while(trav != null ){
            //Create next to traversal pointer to save chains of list
            Node next = trav.next ;
            //make next pointer in node that trav point to it point to null
            trav.next = null;
            //Make Data in node is n ull
            trav.data = null; 
            //Now will make trav point to next that will point to next node to remove it
            trav = next ;
        }
        
        //After all of that will change tail , head , trav to null to remove all that pointers
        head = tail = trav = null;
        //Size will be = 0 
        size = 0 ;
    }

    /********************************************* ADDToIndex  ************************************************/
    //Add Element at specific index 
    public void add(int index, Object element){
        // Make sure the index provided is valid
        if (index < 0 || index >= size) {
            System.out.println("Error");
        }

    //Create trav pointer to point to index that will enter
    Node trav;
    int i;

    // Search from the front of the list until reach node before given index to point on it
    for (i = 0, trav = head; i != index - 1 ; i++) {
        trav = trav.next;
    }


    //create new node that will pointed by temporary pointer
    Node temp = new Node(element , trav.next );

    //Now make pointer on node at index = index Entered no any pointer on it
    trav.next = temp ;
    
    //Increase size with one
    size++;
    //Make trav & temp point to nothing
    trav = null ;
    temp = null ;

    }

    /********************************************* ADD  ************************************************/
    // Add an element to the tail of the linked list, O(1)
    public void add(Object elem) {
        addLast(elem);
    }
    
    // Add a node to the tail of the linked list, O(1)
    public void addLast(Object elem) {
        if (isEmpty()) {
            //If empty then will change tail and head at the same new node
            head = tail = new Node(elem, null);
        } else {
            //Else will change next of tail to new node
            tail.next = new Node(elem, null);
            //Then will make tail pointer pointing to last added element
            tail = tail.next;
        }
        //Increase size with one
        size++;
    }

    //Add Element at start of list
    public void addFirst(Object elem){
        if (isEmpty()) {
            //If empty then will change tail and head at the same new node
            head = tail = new Node(elem, null);
        } else {
            //Create trav to point to new node
            Node trav = new Node(elem , head);
            //Then will make head pointer pointing to first added element
            head = trav;
            //Make trav point to null 
            trav = null ;
        }
        //Increase size with one
        size++;
    }

    /********************************************* GET  ************************************************/
    public Object get(int index){
        
        // Make sure the index provided is valid
        if (index < 0 || index >= size) {
            System.out.println("Error");
            return "Error" ;
        }

        //Create trav pointer to point to index that will enter
        Node trav;
        int i;

        // Search from the front of the list until reach node
        for (i = 0, trav = head; i != index; i++) {
            trav = trav.next;
        }

        //return data pointed by trv pointer
        return trav.data ;
        
    }

    /********************************************* SET  ************************************************/
    //Replaces the element at the specified position in this list with the
    public void set(int index, Object element){
        // Make sure the index provided is valid
        if (index < 0 || index >= size) {
            System.out.println("Error");
        }

        //Create trav pointer to point to index that will enter
        Node trav;
        int i;

        // Search from the front of the list until reach node
        for (i = 0, trav = head; i != index; i++) {
        trav = trav.next;
        }

        //Change data pointed by trav to new data
        trav.data = element ;
    }

    /********************************************* SIZE  ***************************************************/
    // Return the size of this linked list
    public int size() {
        return size;
    }

    /********************************************* isEMPTY  ************************************************/
    // Is this linked list empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    /********************************************* Remove  ************************************************/
    // Remove the first value at the head of the linked list, O(1)
    public Object removeFirst() {
        // Can't remove data from an empty list
        if (isEmpty()){
            System.out.println("Error");
        } 

        // Extract the data at the head and move
        // the head pointer forwards one node
        Object data = head.data;
        //Make Trav point to first node
        Node trav = head ;
        //Removing will be from the first so it will make head the next element
        head = head.next;
        --size;

        // If the list is empty set the tail to null
        if (isEmpty()) tail = null;

        // Do a memory cleanup of the previous node for next pointer 
        trav.next = null;
        trav = null;

        // Return the data that was at the first node we just removed
        return data;
    }

    // Remove the last value at the tail of the linked list, O(1)
    public Object removeLast() {
        // Can't remove data from an empty list
        if (isEmpty())  System.out.println("Error");
        
        // Extract the data at the head and move
        Object data = tail.data;
        //Make Trav point to first node
        Node trav = head;

        //Loop until reach node that will be before node of tail
        for( ; trav.next != tail ; trav = trav.next){}

        //Now trav have node that will be before node of tail 
        //So will make tail point to the same node of trav
        tail = trav ;
        //Decrement size 
        size --;

        // If the list is empty set the tail to null
        if (isEmpty()) tail = null;
        
        //Do a memory cleanup of the node that before removed node for next pointer 
        tail.next = null;

        // Return the data that was at the last node we just removed
        return data;
    }

    // Remove an arbitrary node from the linked list, O(1)
    private Object removeNode(Node trav) {
        //At the first will get node needed to remove
        Node node = trav.next ;

        // If the node to remove is somewhere either at the
        // head or the tail handle those independently
        if (node.next == null) return removeLast();

        // Temporarily store the data we want to return
        Object data = node.data;

        // Make the pointers of adjacent nodes skip over 'node'
        trav.next = node.next ;

        // Memory cleanup
        node.data = null;
        node = null ;
        trav = null;

        --size;

        // Return the data in the node we just removed
        return data;
    }

    // Remove a node at a particular index, O(n)
    public Object remove(int index) {
        // Make sure the index provided is valid
        if (index < 0 || index >= size) {
            System.out.println("Error");
        }

        int i;
        Node trav = head ;

        if(index == 0 ){
            trav = null ;
            return removeFirst();
        }

        else if(index == 1)
            trav = head;

        // Search from the front of the list until reach node
        else {
            for (i = 0, trav = head; i != index-1 ; i++) {
                trav = trav.next;
                }
        }

        

        return removeNode(trav);
    }

    /********************************************* Check Contain  ************************************************/
    // Check is a value is contained within the linked list
    public boolean contains(Object obj) {
        //That go throw function that get index of specific object to check is that in list or not
        boolean flag = indexOf(obj) != -1 ;
        return indexOf(obj) != -1;
    }

    // Find the index of a particular value in the linked list, O(n)
    public int indexOf(Object obj) {
        int index = 0;
        Node trav = head;

        // Support searching for null
        if (obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null) {
                    return index;
                }
            }
            // Search for non null object
        } else
            for (; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) {
                    return index;
                }
            }

        return -1;
    }

    /********************************************* subList  ************************************************/
    public void subList(int fromIndex, int toIndex){
        // Make sure the index provided is valid
        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size) {
            System.out.println("Error");
        }
        
        //Create Array will save subList in it
        SinlgleLinkedList subList = new SinlgleLinkedList() ;
        Node trav = head ;
        
        //Loop on list 
        for(int i = 0 ; i <= toIndex ; i++ ){

            if(i >= fromIndex){
                //Add from that index all Elements in new list
                subList.add(trav.data);
            }

            //Make trav point to next
            trav = trav.next ;
        }

        //Print list
        System.out.println(subList.toString());

    }

    @Override
    public String toString() {
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

    public static void main(String[] args) {
        //Create object from scanning class
        Scanner input = new Scanner(System.in) ;
        
        //Create object from DoubleLinkedList class
        SinlgleLinkedList list = new SinlgleLinkedList() ;

        try{
            //Create array with input numbers
            String numsString = input.nextLine().replace("[", "").replace("]", "");
            // System.out.println(numsString);
            String nums[] = numsString.split(", ");
            //Create variable take method needed
            String method = input.next();

            if( nums.length >= 1 && !nums[0].equals("")){
                // loop to append Entered Elements in list 
                for(int i = 0 ; i < nums.length ; i++){
                    list.add(Integer.parseInt(nums[i]));
                }
            }

            // System.out.println(list.toString());

            //Switch on method Entered from user
            switch(method){
                case "clear" :
                    list.clear();
                    System.out.println("[]");
                    return;

                case "size" :
                    //print out size
                    System.out.println(list.size());
                    return;    

                case "addToIndex":
                    //Get Index & elem from user
                    int index = input.nextInt() ;
                    Object elem = input.nextInt() ;
                    //Append Elem at given index 
                    list.add(index, elem);
                    //Print list
                    System.out.println(list.toString());
                    return;

                case "add" :
                    // System.out.println("add");
                    //Get Index 
                    int index1 = input.nextInt() ;
                    //Append Elem at given index 
                    list.add(index1);
                    //Print list
                    System.out.println(list.toString());
                    return;

                case "set" : 
                    //Get Index & elem from user
                    int index3 = input.nextInt() ;
                    Object elem2 = input.nextInt() ;
                    //set list with value
                    list.set(index3, elem2);   
                    //Print list
                    System.out.println(list.toString());
                    return;

                case "get" : 
                    //Get Index & elem from user
                    int index4 = input.nextInt() ;
                    //set list with value
                    Object element = list.get(index4);   
                    //Print elem
                    if(!element.equals("Error"))
                        System.out.println(element);
                    return;        

                case "remove" : 
                    //Get Index from user
                    int index5 = input.nextInt() ;
                    
                    //If size is 1 and index = 0 will remove first element so out will empty list 
                    if(index5 == 0 && list.size == 1){
                        System.out.println("[]");
                        return;
                    }

                    //set list with value
                    list.remove(index5);   
                    //Print list
                    System.out.println(list.toString());
                    return; 
                    
                case "isEmpty" : 
                    //Get return value from function 
                    boolean flag = list.isEmpty();
                    if(flag){
                        System.out.println("True");
                        return;
                    }
                    
                    System.out.println("False");
                    return;
                
                case "contains" : 
                    //Get Index from user
                    Object elemObject = input.nextInt() ;
                    //set list with value
                    boolean flag2 = list.contains(elemObject);   
                    //Print flag
                    if (flag2)
                        System.out.println("True");
                    
                    else    
                        System.out.println("False");

                    return;     

                case "sublist" : 
                    //Get Index & elem from user
                    int fromIndex = input.nextInt() ;
                    int toIndex = input.nextInt() ;
                    list.subList(fromIndex, toIndex);
                    return;     
                
                default :
                    System.out.println("Error");
            }
        }   

        catch(Exception e){
            // System.out.println("Error");
        }

    }
}
