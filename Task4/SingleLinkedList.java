import javax.management.RuntimeErrorException;

public class SingleLinkedList<T> implements Iterable<T> {
    private int size = 0 ;
    private Node<T> head , tail ;

    // Internal node class to represent data
    private static class Node<T>{
        private T data;
        private Node<T> next = null ;

        public Node( T data , Node<T> next) {
            this.data = data ;
            this.next = next ;
        }
    }

    //Empty Linked List with O(n)
    public void clear() {
        //Create traversal pointer to iterate on list 
        Node<T> trav = head ;
        //As long trav not at end [null] then remove elements
        while(trav != null ){
            //Create next to traversal pointer to save chains of list
            Node<T> next = head ;
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

    // Return the size of this linked list
    public int size() {
        return size;
    }

    // Is this linked list empty ?
    public boolean isEmpty() {
        return size() == 0;
    }

    // Add an Element at start of tail of list
    public void add(T elem) {
        addLast(elem);
    }

    //Add Element at last of list
    public void addLast(T elem){
        if (isEmpty()) {
            //If empty then will change tail and head at the same new node
            head = tail = new Node<T>(elem, null);
        } else {
            //Else will change next of tail to new node
            tail.next = new Node<T>(elem, null);
            //Then will make tail pointer pointing to last added element
            tail = tail.next;
        }
        //Increase size with one
        size++;
    }

    //Add Element at start of list
    public void addFirst(T elem){
        if (isEmpty()) {
            //If empty then will change tail and head at the same new node
            head = tail = new Node<T>(elem, null);
        } else {
            //Create trav to point to new node
            Node<T> trav = new Node<T>(elem , head);
            //Then will make head pointer pointing to first added element
            head = trav;
            //Make trav point to null 
            trav = null ;
        }
        //Increase size with one
        size++;
    }

    //To get first element of list 
    public T peekFirst(){
        //If empty will outing error message
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    //To get last element of list 
    public T peekLast(){
        //If empty will outing error message
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }

    // Remove the first value at the head of the linked list, O(1)
    public T removeFirst() {
        // Can't remove data from an empty list
        if (isEmpty()) throw new RuntimeException("Empty list");

        // Extract the data at the head and move
        // the head pointer forwards one node
        T data = head.data;
        //Make Trav point to first node
        Node<T> trav = head ;
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

    // Remove the Last value at the tail of the linked list, O(n)
    public T removeLast() {
        // Can't remove data from an empty list
        if (isEmpty()) throw new RuntimeException("Empty list");

        // Extract the data at the head and move
        T data = tail.data;
        //Make Trav point to first node
        Node<T> trav = head;

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
    //Here pass pointer to previous node of node that will removed
    private T remove (Node<T> trav) {
        //At the first will get node needed to remove
        Node<T> node = trav.next ;

        // If the node to remove is somewhere either at the
        // head or the tail handle those independently
        if (node.next == null) return removeLast();

        // Make the pointers of adjacent nodes skip over 'node'
        trav.next = node.next ;

        // Temporarily store the data we want to return
        T data = node.data;

        // Memory cleanup
        node.data = null;

        --size;

        // Return the data in the node we just removed
        return data;
    }

    // Remove a node at a particular index, O(n)
    public T removeAt(int index) {
        // Make sure the index provided is valid
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        int i;
        Node<T> trav;

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

        return remove(trav);
    }



}
