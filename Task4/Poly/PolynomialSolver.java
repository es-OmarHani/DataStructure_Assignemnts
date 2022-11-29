//-- importing dependencies --
import java.util.*;
import java.math.*;
import java.text.*;
import java.awt.Point;

/**
>> by amr at 10:38, 29.11.2022
**/


interface IPolynomialSolver{
	/**
	 * Set polynomial terms (coefficients & exponents)
	 * @param poly
	 * name of the polynomial
	 * @param terms
	 * array of [coefficients][exponents]
	 */
	void setPolynomial(char poly, int[][] terms);
	/**
	* Print the polynomial in ordered human readable representation
	* @param poly
	* name of the polynomial
	* @return polynomial in the form like 27x^2+x-1
	*/
	String print(char poly);
	/**
	* Clear the polynomial
	* @param poly
	* name of the polynomial
	*/
	void clearPolynomial(char poly);
	/**
	* Evaluate the polynomial
	* @param poly
	* name of the polynomial
	* @param the
	* polynomial constant value
	* @return the value of the polynomial
	*/
	float evaluatePolynomial(char poly, float value);
	/**
	* Add two polynomials
	* @param poly1
	* first polynomial
	* @param poly2
	* second polynomial
	* @return the result polynomial
	*/
	int[][] add(char poly1, char poly2);
	/**
	* Subtract two polynomials
	* @param poly1
	* first polynomial
	* @param poly2
	* second polynomial
	* @return the result polynomial
	*/
	int[][] subtract(char poly1, char poly2);
	/**
	* Multiply two polynomials
	* @param poly1
	* first polynomial
	* @param poly2
	* second polynomial
	* @return the result polynomial
	*/
	int[][] multiply(char poly1, char poly2);
}

class DoubleLinkedList  {
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
    public void add(int index, Object element){

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
    public void remove(int index) {
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

        removeNode(trav);
        
        return ;
    }

    /********************************************* Check Contain  ************************************************/
    // Check is a value is contained within the linked list
    public boolean contains(Object obj) {
        //That go throw function that get index of specific object to check is that in list or not
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
    public DoubleLinkedList sublist(int fromIndex, int toIndex){

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

        //Return sunList that will printed
        return subList;

    }

    /********************************************* Printing List  ************************************************/
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
}


public class PolynomialSolver implements IPolynomialSolver{

	//-- define input polynomial variables as doubly-linked lists --
	public static DoubleLinkedList A = new DoubleLinkedList();
	public static DoubleLinkedList B = new DoubleLinkedList();
	public static DoubleLinkedList C = new DoubleLinkedList();

	//-- define output polynomial variable as doubly-linked lists --
	public static DoubleLinkedList R = new DoubleLinkedList();



	//------------------ sort polynomial ------------------//
	public int[][] sort(DoubleLinkedList list){
		/**
		* sort the terms of the polynomial ascendingly with
		the order of the exponents.
		@param list:
		>>input doubly-linked list representing the polynomial
		@return sorted two-dimentional array
		**/

		//-- convert doubly-linked list to a two-dimentional array --
		int[][] sortedArray = list_to_array(list);

		boolean unsorted = true;
		int temp;

		while(unsorted){

			unsorted = false;

			for(int i = 0; i < sortedArray.length-1 ; i++){

				if(sortedArray[i][1] > sortedArray[i+1][1]) {

					unsorted = true;

					//-- swap exponents --
					temp = sortedArray[i][1];
					sortedArray[i][1] = sortedArray[i+1][1];
					sortedArray[i+1][1] = temp;

					//-- swap coefficients --
					temp = sortedArray[i][0];
					sortedArray[i][0]=sortedArray[i+1][0];
					sortedArray[i+1][0]=temp;
				}
			}
		}

		return (sortedArray);
	}



	//----------- convert list to a 2D array -------------//
	public int[][] list_to_array(DoubleLinkedList X){
		/**
		* convert doubly-linked list into a two-dimentional array.
		where the rows correspond to the nodes of the list and the
		two columns represent the coeffient and the exponent, [#nodes][coeff, expo].
		@param list:
		>>input doubly-linked list representing the polynomial.
		@return result two-dimentional array
		**/

		int [][] result = new int [X.size()][2];

		for (int i = 0 ; i < X.size() ;i++)
		{
			for (int j = 0 ; j < 2;j++)
			{
				java.awt.Point p = (Point) X.get(i);

				//-- get coeffient --
				result[i][0] = p.x;

				//-- get exponent --
				result[i][1] = p.y;
			}
		}

		return result;
	}



	//----------- convert 2D array to a list -------------//
	public DoubleLinkedList array_to_list(int [][] terms ){
		/**
		* convert two-dimentional array into a doubly-linked list.
		where the rows correspond to the nodes of the list and the
		two columns represent the coeffient and the exponent, [#nodes][coeff, expo].
		@param terms:
		>>input 2d array representing the polynomial terms.
		@return result doubly-linked list
		**/

		DoubleLinkedList result = new DoubleLinkedList ();

		for (int i = 0;i < terms.length;i++)
		{
			java.awt.Point p = new java.awt.Point (terms[i][0],terms[i][1]);
			result.add(p);
		}

		return result;
	}



	//------------------ set polynomial ------------------//
	public void setPolynomial(char poly, int[][] terms){
    /**
     * Set polynomial terms (coefficients & exponents)
     * @param poly
     * name of the polynomial
     * @param terms
     * array of [coefficients][exponents]
     */

		 if (poly == 'A')
 			A = array_to_list(terms);

 		else if (poly == 'B')
 			B = array_to_list(terms);

 		else if (poly == 'C')
 			C = array_to_list(terms);
	}



	//---------------- print polynomial -----------------//
  	public String print(char poly){
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly
    >> name of the polynomial
    * @return polynomial in the form like 27x^2+x-1
    */

		String Result = "";
		DoubleLinkedList x = null;
		if (poly =='A')
			x = A;
		else if (poly =='B')
			x = B;
		else if (poly =='C')
			x = C;
		else if (poly =='R')
			x = R;

		for (int i = 0 ; i < x.size() ;i++){
			java.awt.Point p = (java.awt.Point) x.get(x.size()-i-1);
			if (p.x == 0)continue;
			if (p.x >0 && Result!="") Result+=" + ";
			else if (p.x <0  && Result!="")Result+=" - ";
			if (p.x <-1 && Result=="")Result+=p.x;
			else if (p.x ==-1 && p.y!=0 && Result =="")Result+='-';
			else if (p.x ==1 && p.y ==0)Result+='1';
			else if (p.x ==-1 && p.y ==0 &&Result =="")Result+="-1";
			else if (p.x > 1)Result +=p.x;
			else if (p.x <= -1)Result+= (p.x*-1);
			if(p.y!=0 && p.y!=1)Result +="x^"+p.y;
			else if (p.y ==1)Result+='x';
		}

		if(Result== "") Result = "[]";
		Result = Result.replaceAll(" ", "");

		return Result;
  	}



	//--------------- clear polynomial ----------------//
  	public void clearPolynomial(char poly){
    /**
    * Clear the polynomial
    * @param poly
    * name of the polynomial
    */

		if (poly == 'A')
			A.clear();
		else if (poly == 'B')
			B.clear();
		else if (poly == 'C')
			C.clear();
  }



	//-------------- evaluate polynomial --------------//
	public float evaluatePolynomial(char poly, float value){
    /**
    * Evaluate the polynomial
    * @param poly
    * name of the polynomial
    * @param the
    * polynomial constant value
    * @return the value of the polynomial
    */

		try{

			float Result = 0;
			DoubleLinkedList x = null;

			if (poly == 'A')
				x = A;

			else if (poly == 'B')
				x = B;

			else if (poly == 'C')
				x = C;

			else if (poly == 'R')
				x = R;

			else
				throw new RuntimeException("Invalid polynomial name");

			if(x.size() == 0)
				throw new RuntimeException("Cannot evaluate empty polynomials");

			for (int i = 0 ; i < x.size(); i++){
				java.awt.Point p = (java.awt.Point)x.get(i);
				int coff = p.x;
				int exp = p.y;

				Result += coff * Math.pow(value, exp);
			}

			return Result;
		}

		catch(Exception e){
			throw new RuntimeException("[eval]Error");
		}
  	}



	//------------------ add polynomials ------------------//
	public int[][] add(char poly1, char poly2){
    /**
    * Add two polynomials
    * @param poly1
    >> first polynomial
    * @param poly2
    >> second polynomial
    * @return the result polynomial
    */

		DoubleLinkedList X = null;
		DoubleLinkedList Y = null;

		if (poly1 == 'A')	X = A;
		else if (poly1 == 'B') X =B;
		else if(poly1 == 'C') X = C;
		else throw new RuntimeException("Invalid polynomial name");

		if (poly2 == 'A')	Y = A;
		else if (poly2 == 'B') Y = B;
		else if(poly2 == 'C') Y = C;
		else throw new RuntimeException("Invalid polynomial name");

		//-- check for invalid input --
		if (X == null || Y == null || X.size() == 0 || Y.size() == 0){
			throw new IllegalArgumentException("Empty polynomials must be set");
		}

		R = new DoubleLinkedList();

		for (int i = 0; i < X.size() ;i++){
			R.add(X.get(i));
		}

		for (int i = 0 ; i < Y.size() ;i++){
			boolean check = false;

			java.awt.Point ptY = (Point) Y.get(i);
			int expY = ptY.y;
			int coefY = ptY.x;

			for (int j = 0 ; j < R.size() ; j++)
			{
				java.awt.Point ptR = (Point) R.get(j);
				int expR = ptR.y;
				int coefR = ptR.x;

				if (expY == expR)
				{
					check = true;
					R.set(j, new java.awt.Point(coefY + coefR, expR));
					break;
				}
			}

			if (!check)
			{
				R.add(new java.awt.Point(coefY,expY));
			}
		}


		for (int i = 0 ; i < R.size() ;)
		{
			java.awt.Point p = (Point) R.get(i);
			if (p.x == 0) R.remove(i);
			else i++;
		}

		if(R.size() == 0) R.add(new java.awt.Point(0,0));

		return sort(R);
	}



	//--------------- subtract polynomials ----------------//
	public int[][] subtract(char poly1, char poly2){
    /**
    * Subtract two polynomials
    * @param poly1
    * first polynomial
    * @param poly2
    * second polynomial
    * @return the result polynomial
    */

		DoubleLinkedList X = null;
		DoubleLinkedList Y = null;

		if (poly1 == 'A')	X = A;
		else if (poly1 == 'B') X =B;
		else if(poly1 == 'C') X = C;
		if (poly2 == 'A')	Y = A;
		else if (poly2 == 'B') Y = B;
		else if(poly2 == 'C') Y = C;

		//-- check for invalid input --
		if (X == null || Y == null || X.size() == 0 || Y.size() == 0){
			throw new IllegalArgumentException("Empty polynomials must be set");
		}

		R = new DoubleLinkedList();

		for (int i = 0;i < X.size() ;i++){

			R.add(X.get(i));
		}

		for (int i = 0 ; i < Y.size() ;i++){

			boolean check = false;

			java.awt.Point ptY = (Point) Y.get(i);
			int expY = ptY.y;
			int coefY = ptY.x;

			for (int j = 0 ; j < R.size() ;j++){

				java.awt.Point ptR = (Point) R.get(j);
				int expR = ptR.y;
				int coefR = ptR.x;

				if (expY == expR)
				{
					check = true;
					R.set(j, new java.awt.Point(coefR - coefY, expR));
					break;
				}
			}


			if (!check)
			{
				R.add(new java.awt.Point(coefY*-1,expY));
			}
		}


		for (int i = 0 ; i < R.size() ;){

			java.awt.Point p = (Point) R.get(i);
			if (p.x==0)R.remove(i);
			else i++;
		}

		if(R.size() == 0) R.add(new java.awt.Point(0,0));

		return sort(R);
  }



	//-------------- multiply polynomials ---------------//
	public int[][] multiply(char poly1, char poly2){
    /**
    * Multiply two polynomials
    * @param poly1
    >> first polynomial
    * @param poly2
    >> second polynomial
    * @return the result polynomial
    */

		//-- define temporary linked lists to be used in the calculations --
		DoubleLinkedList X = null;
		DoubleLinkedList Y = null;

		//-- get the name of the polynomial X --
		if (poly1 == 'A')	X = A;
		else if (poly1 == 'B') X = B;
		else if (poly1 == 'C') X = C;

		//-- get the name of the polynomial Y --
		if (poly2 == 'A')	Y = A;
		else if (poly2 == 'B') Y = B;
		else if(poly2 == 'C') Y = C;

		//-- through an exception if, at least, one of the polynomials is empty --
		if (X == null || Y == null || X.size() == 0 || Y.size() == 0){
			throw new IllegalArgumentException("Empty polynomials must be set");
		}


		//-- define the polynomail R to store the result --
		R = new DoubleLinkedList();

		for (int i = 0; i < X.size(); i++){
			/**
			Multiply the two polynomials together where each term in
			ploynomial X is multiplied with all terms of Y then stored
			in plynomial R. Then, all terms in R having the same exponent
			are added together (their coefficients are added).
			**/

			//-- define a java point ptX(coeff, exponent) --
			java.awt.Point ptX = (Point) X.get(i);
			int coefX = ptX.x;
			int expX = ptX.y;


			for (int j = 0 ; j < Y.size() ;j++){
				/**
				Multiply term i of polynomial X with all terms of
				polynomial Y then store the result in R.
				**/

				//-- defin a java point ptY(coeff, exponent) --
				java.awt.Point ptY = (Point) Y.get(j);
				int coefY = ptY.x;
				int expY = ptY.y;

				//-- multiply term i with term j and store the result in a new point --
				java.awt.Point result = new java.awt.Point(coefX * coefY, expX + expY);

				//-- append the result directly to R if this is the first term --
				if (R.size() == 0) R.add(result);


				else{

					//-- combine terms having the same exponent --
					for (int k = 0 ; k < R.size(); k++)
					{
						java.awt.Point ptR = (Point) R.get(k);

						if (ptR.y == result.y)
						{
							R.set(k, new java.awt.Point(ptR.x + result.x, ptR.y));
							break;
						}

						//-- append directly to R if this is the last term --
						if (k == R.size()-1)
						{
							R.add(result);
							break;
						}
					}
				}
			}
		}

		//-- remove terms with coefficients equal to zero --
		for (int i = 0 ; i < R.size() ;){
			java.awt.Point p = (Point) R.get(i);
			if (p.x == 0)R.remove(i);
			else i++;
		}

		//-- return zero if R is empty after all zero terms are removed --
		if(R.size() == 0) R.add(new java.awt.Point(0,0));

		//-- return sorted R --
		return sort(R);
  }






//------------- main method -------------//
	public static void main(String[] args) {

	  try{

			PolynomialSolver p = new PolynomialSolver();
			Scanner sc = new Scanner(System.in);
			String sin = "";

			while(sc.hasNextLine()){

				sin = sc.nextLine();

				switch(sin){


					//------------- set -------------//
					case "set":

						//-- get the name of the polynomial --
						sin = sc.nextLine();
						char poly_name = sin.charAt(0);

						//-- get the coefficients of the polynomial --
						sin = sc.nextLine();

						//-- check for empty or invaild coefficients --
						if(sin.length()<2){
							throw new RuntimeException("Invalid coefficients");
						}

						sin = sin.replaceAll("\\[", "");
						sin = sin.replaceAll("\\]", "");

						String[] sp_val = sin.split(",");

						int[][] coef = new int[sp_val.length][2];

						//-- populate the coef array with the input coef in reverse order --
            for(int i = 0; i < sp_val.length; i++){

              coef[i][0] = Integer.parseInt(sp_val[sp_val.length-i-1]);
              coef[i][1] = i;
            }

						//-- set the polynomial --
						p.setPolynomial(poly_name, coef);
						break;


					//------------- print -------------//
					case "print":

						//-- get the name of the polynomial --
						sin = sc.nextLine();
						poly_name = sin.charAt(0);

						//-- print the polynomial --
						System.out.println(p.print(poly_name));
						break;


					//------------- add -------------//
					case "add":

						//-- get the name of the first polynomial --
						sin = sc.nextLine();
						char poly1_name = sin.charAt(0);

						//-- get the name of the second polynomial --
						sin = sc.nextLine();
						char poly2_name = sin.charAt(0);

						//-- add the two polynomials --
						PolynomialSolver.R = p.array_to_list(p.add(poly1_name, poly2_name));

						//-- print the result --
						System.out.println(p.print('R'));
						break;



					//------------- sub -------------//
					case "sub":

						//-- get the name of the first polynomial --
						sin = sc.nextLine();
						poly1_name = sin.charAt(0);

						//-- get the name of the second polynomial --
						sin = sc.nextLine();
						poly2_name = sin.charAt(0);

						//-- subtract the two polynomials --
						PolynomialSolver.R = p.array_to_list(p.subtract(poly1_name, poly2_name));

						//-- print the result --
						System.out.println(p.print('R'));
						break;



					//------------- mult -------------//
					case "mult":

						//-- get the name of the first polynomial --
						sin = sc.nextLine();
						poly1_name = sin.charAt(0);

						//-- get the name of the second polynomial --
						sin = sc.nextLine();
						poly2_name = sin.charAt(0);

						//-- multiply the two polynomials --
						PolynomialSolver.R = p.array_to_list(p.multiply(poly1_name, poly2_name));

						//-- print the result --
						System.out.println(p.print('R'));
						break;



					//------------- clear -------------//
					case "clear":

						//-- get the name of the polynomial --
						sin = sc.nextLine();
						poly_name = sin.charAt(0);

						//-- clear the polynomial --
						p.clearPolynomial(poly_name);

						//-- print the polynomial --
						System.out.println(p.print(poly_name));
						break;



					//------------- eval -------------//
					case "eval":

						//-- get the name of the polynomial --
						sin = sc.nextLine();
						poly_name = sin.charAt(0);

						//-- get the value of x --
						sin = sc.nextLine();
						float x = (float) Integer.parseInt(sin);

						//-- evaluate the polynomial --
						float result = p.evaluatePolynomial(poly_name, x);

						//-- print the result --
						System.out.println( (int) result );
						break;


					default:
						throw new RuntimeException("Invaild operation");
				}
			}
		}

		catch(Exception e){
			
			System.out.println("Error");
		}
	}
}
