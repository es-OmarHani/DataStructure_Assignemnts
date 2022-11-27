import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DoubleLinkedList   {
	/* Implement your linked list class here*/
    private int size = 0;
    private Node head = null;
    private Node tail = null;

    // Internal node class to represent data
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

    /********************************************* CLEAR  ************************************************/
    // Empty this linked list, O(n)
    public void clear() {
        //Creating traversal pointer to loop on nodes in list
        Node trav = head;
        //As long trav not at end [null] then remove elements
        while (trav != null) {
            //Creating node that will point to next node that traversal point to it to save chains of list
            Node next = trav.next;
            //Change value of next and previous to null but that node will remove so next pointer is saving chain of reminder of list
            trav.prev = trav.next = null;
            //will remove data from node
            trav.data = 0 ;
            //Then change trav to the same pointing of next which means the next node
            trav = next;
        }
        //After all of that will change tail , head , trav to null to remove all that pointers
        head = tail = trav = null;
        //change size to zero because list is removed
        size = 0;
    }

    /********************************************* ADDToIndex  ************************************************/
    //Add Element at specific index 
    public void addToIndex(int index, Object element){

        //If index at first will call function that will add to first
        //Here if index = 0 and size of list = 0 then that will be add from last
        if(index == 0 ){
            if(size == 0 ){
                addFirst(element);
                return;
            }

            else{
                addFirst(element);
                return;
            }
        }

        // Make sure the index provided is valid
        if (index < 0 || index >= size) {
            System.out.println("Error");
        }
    
        //Create trav pointer to point to index that will enter
    Node trav;
    int i;

    // Search from the front of the list
    if (index < size / 2) {
        for (i = 0, trav = head; i != index; i++) {
            trav = trav.next;
        }
    } 
    // Search from the back of the list
    else{
        for (i = size - 1, trav = tail; i != index; i--) {
            trav = trav.prev;
        }
    }

    //create new node that will pointed by previous node at index = Entered index
    trav.prev.next = new Node(element , trav.prev , trav );

    //Now make pointer on node at index = index Entered no any pointer on it
    trav.prev = trav.prev.next ;

    //Increase size with one
    size++;
    //Make trav point to nothing
    trav = null ;

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
    public void addFirst(Object elem){
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

        // Search from the front of the list
        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++) {
                trav = trav.next;
            }
        } 
        // Search from the back of the list
        else{
            for (i = size - 1, trav = tail; i != index; i--) {
                trav = trav.prev;
            }
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

        // Search from the front of the list
        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++) {
                trav = trav.next;
            }
        } 
        // Search from the back of the list
        else{
            for (i = size - 1, trav = tail; i != index; i--) {
                trav = trav.prev;
            }
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

    // Remove the last value at the tail of the linked list, O(1)
    public Object removeLast() {
        // Can't remove data from an empty list
        if (isEmpty())  System.out.println("Error");

        // Extract the data at the tail and move
        // the tail pointer backwards one node
        Object data = tail.data;
        //Removing will be from the first so it will make head the next element
        tail = tail.prev;
        --size;

        // If the list is now empty set the head to null
        if (isEmpty()) head = null;

            // Do a memory clean of the node that was just removed
        else tail.next = null;

        // Return the data that was in the last node we just removed
        return data;
    }

    // Remove an arbitrary node from the linked list, O(1)
    private Object removeNode(Node node) {
        // If the node to remove is somewhere either at the
        // head or the tail handle those independently
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        // Make the pointers of adjacent nodes skip over 'node'
        node.next.prev = node.prev;
        node.prev.next = node.next;

        // Temporarily store the data we want to return
        Object data = node.data;

        // Memory cleanup
        node.data = null;
        node = node.prev = node.next = null;

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
        Node trav;

        // Search from the front of the list
        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++) {
                trav = trav.next;
            }
            // Search from the back of the list
        } else
            for (i = size - 1, trav = tail; i != index; i--) {
                trav = trav.prev;
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
        DoubleLinkedList subList = new DoubleLinkedList() ;
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


    // public ILinkedList sublist(int fromIndex, int toIndex)

    public static void main(String[] args) {
        //Create object from scanning class
        Scanner input = new Scanner(System.in) ;
        
        //Create object from DoubleLinkedList class
        DoubleLinkedList list = new DoubleLinkedList(); 

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
                    int elem = input.nextInt() ;

                    //Put Elem at given index 
                    list.addToIndex(index, elem);

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
