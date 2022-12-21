import java.util.Scanner;

/**
 * Implement your linked list class here
 */
class DoubleLinkedList {
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

    /**
     * Method That add Elements from start of linkedList
     * @param elem which will be added to linked list from start
     */
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

    /**
     * Method That get Size of linkedList
     * @return size of this linked list
     */
    public int size() {
        /*   */
        return size;
    }

    /**
     * Is this linked list empty?
     * @return boolean flag indicate empty or not
     */
    public boolean isEmpty() {
        /* Method That Check if LinkedList Empty Or not */
        return size() == 0;
    }

    /**
     * Method That remove Element from start of linkedList
     * @return object that removed
     */
    public Object removeFirst() {
        /*  */

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
     * @return Top object of the stack
     */
    public Object peekFirst() {
        /* Method That get First Element from linkedList but without remove that Element */

        //If empty will out error message
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    /**
     * Method That Print Linked LIST
     * @return string that represent printed linked list
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

class MyStack extends DoubleLinkedList {

    // Create an empty stack
    public MyStack() {}

    // Create a Stack with an initial element
    public MyStack(Object firstElem) {
        push(firstElem);
    }

    // Push an element on the stack
    /*** Pushes an item onto the top of this stack.
     * @param elem to insert
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
}


interface IExpressionEvaluator {
    /*** Takes a symbolic/numeric infix expression as inputand converts it to* postfix notation.
     * There is no assumption on spacesbetween terms or the* length of the term (e.g., two digits symbolic ornumeric term)**
     * @param  expression infix expression*
     * @return  postfix expression
     */
    public String infixToPostfix(String expression);
    /*** Evaluate a postfix numeric expression, with a singlespace separator*
     * @param expression postfix expression*
     * @return the expression evaluated value
     */
    public int evaluate(String expression);
}


/* Java implementation to convert
infix expression to postfix*/

/**
 * Main class that For Evaluation
 */
public class Main implements IExpressionEvaluator{
    //global variable
    private static int countOperators = 0;
    private static int countVariables = 0;

    /**
     * Default Constructor
     */
    Main(){}

    /**
     * Takes a symbolic/numeric infix expression as inputand converts it to* postfix notation.
     * There is no assumption on spacesbetween terms or the* length of the term (e.g., two digits symbolic ornumeric term)
     * @param exp infix expression*
     * @return postfix expression
     */
    public String infixToPostfix(String exp){
        //count to check on '(' & ')'
        int count = 0 ;

        // initializing empty String for result
        String result = "";

        // initializing empty stack
        MyStack stack = new MyStack();

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);

            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c)){
                result += c;
            }

            // If the scanned character is an '(',
            // push it to the stack.
            else if (c == '('){
                stack.push(c);
                //increase count
                count++;
            }

            // If the scanned character is an ')',
            // pop and output from the stack
            // until an '(' is encountered.
            else if (c == ')') {
                while (!stack.isEmpty() && !stack.peek().equals('(') ) {
                    result += stack.peek();
                    stack.pop();
                    //Decrease count
                    count--;
                }

                stack.pop();
            }
            // an operator is encountered
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence((Character) stack.peek())) {

                    result += stack.peek();
                    stack.pop();
                }
                stack.push(c);
            }
        }

        //check on count
        if(count != 0)
            throw new RuntimeException("Invalided Expression");

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek().equals('(') )
                return "Invalid Expression";
            result += stack.peek();
            stack.pop();
        }
        return result;
    }

    /**
     * Precedence of a given operator Higher returned value means higher precedence
     * @param ch which is operator that will be checked
     * @return integer represent precedence
     */
    static int precedence(char ch)
    {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    /**
     * Evaluate a postfix numeric expression, with a singlespace separator
     * @param exp that will be postfix expression
     * @return int which represent evaluated value
     */
    public int evaluate(String exp) {

        MyStack stack = new MyStack();
        Scanner input = new Scanner(exp);

        //make variable for operation
        String op = "" ;

        while (input.hasNext()) {
            // As next in exp integer push it into stack
            if (input.hasNextInt()) {
                stack.push(input.nextInt());
            }

            // That for if we have expressions like that ab* which have 2 variables and 1 operator
            else if(countVariables == 2 && countOperators <=2  && stack.size() == 1 ){
                // System.out.println("Else");

                if (!input.hasNextInt()) {
                    op = input.next();
                }

                if (input.hasNextInt()){
                    stack.push(input.nextInt());
                }

                int num2 = (int) stack.pop();
                int num1 = (int) stack.pop();

                if (op.equals("+"))
                    stack.push(num1 + num2);
                else if (op.equals("-"))
                    stack.push(num1 - num2);
                else if (op.equals("*"))
                    stack.push(num1 * num2);
                else if (op.equals("^"))
                    stack.push( (int) Math.pow(num1 , num2));
                else if (op.equals("/")){
                    if(num2 == 0)
                        throw new RuntimeException("can't Divide by zero");
                    stack.push( (int) ((float)num1 / (float)num2));
                }
                return (int) stack.pop();
            }

            else  {
                int num2 = (int) stack.pop();
                int num1 = (int) stack.pop();
                op = input.next();

                if (op.equals("+"))
                    stack.push(num1 + num2);
                else if (op.equals("-"))
                    stack.push(num1 - num2);
                else if (op.equals("*"))
                    stack.push(num1 * num2);
                else if (op.equals("^"))
                    stack.push( (int) Math.pow(num1 , num2));
                else if (op.equals("/")){
                    if(num2 == 0)
                        throw new RuntimeException("can't Divide by zero");
                    stack.push( (int) ((float)num1 / (float)num2));
                }
            }
        }
        return (int) stack.pop();
    }

    /**
     * Copy string to stringBuilder that can replace at specific indexes
     * @param exp String that will copy in StringBuilder
     * @return StringBuilder that have expression
     */
    public static StringBuilder copyString(String exp) {

        //create string builder
        StringBuilder str = new StringBuilder();
        //copy string to stringBuilder
        for(int i = 0 ; i < exp.length() ; i++ ){
            str.append(exp.charAt(i));
        }
        return str;
    }

    /**
     * That check on string about some issues will out wrong Evaluation
     * @param exp String That will be checked
     * @return checked Expression
     */
    public static String checkStr(String exp) {
        /* That check om string about some issues will out wrong Evaluation
         * @param Expression String That will be checked
         *@return checked Expression
         **/
        //create string builder
        StringBuilder str = new StringBuilder();
        //copy exp to string builder
        str = copyString(exp);

        //loop on exp
        for(int i = 0 ; i < str.length(); i++ ){
            if( (i+1) < str.length() && !Character.isDigit(str.charAt(i)) && str.charAt(i) != ' ' && str.charAt(i) != '-' &&
                    (Character.isDigit(str.charAt(i+1)) || str.charAt(i+1) == '-') ){
                str.replace(i, i+1 , str.charAt(i) + " ");
                //increase i
                i++;
            }

            //Check Expression doesn't have any something like that '-30 +*' that will be '-30 + *'
            //So Will separate between each operators
            if(i+1 < str.length() && !Character.isDigit(str.charAt(i)) && !Character.isDigit(str.charAt(i+1)) &&
                    str.charAt(i+1) != '-' && str.charAt(i) != ' ' && str.charAt(i) != '-'){
                str.replace(i, i+1 , str.charAt(i) + " " );
                //increase i
                i++;
            }

            if(i+1 < str.length() && str.charAt(i) == '-' && str.charAt(i+1) == '-' ){
                str.replace(i, i+1 , "- ");
                //increase i
                i++;
            }

        }
        //copy stringBuilder to string
        exp = str.toString();
        return exp;
    }

    /**
     * That Method will substitute Every char with its value
     *  But Here we Don;t Know every time passed value
     *  So will create Length Argument list and Extract values from it
     * @param exp which will be substituted
     * @param numbers which is array has dynamic length based on passed values
     * @return String with its substitute
     */
    public static String substituteStr(String exp , int... numbers ) {
        //create string builder
        StringBuilder str = new StringBuilder();
        //copy exp to string builder
        str = copyString(exp);

        //Length is one that means we have a char only in exp so will substitute a only with passed value
        if(numbers.length == 1 ){
            if(numbers[0] >= 0)
                return exp.replaceAll("a", numbers[0] + " ");
            else
                return exp.replaceAll("a", numbers[0] + " ");
        }

        else if(numbers.length == 2 ){
            if(numbers[0] >= 0 && numbers[1] > 0){
                exp = exp.replaceAll("a", numbers[0] + " ");
                exp = exp.replaceAll("b", numbers[1] + " ");
            }
            else{
                exp = exp.replaceAll("a", numbers[0] + " ");
                exp = exp.replaceAll("b", numbers[1] + " ");
            }
            return exp ;
        }

        else if(numbers.length == 3 ){

            if(numbers[0] >= 0 && numbers[1] > 0 && numbers[2] > 0){
                exp = exp.replaceAll("a", numbers[0] + " ");
                exp = exp.replaceAll("b", numbers[1] + " ");
                exp = exp.replaceAll("c", numbers[2] + " ");
            }
            else{
                exp = exp.replaceAll("a", numbers[0] + " ");
                exp = exp.replaceAll("b", numbers[1] + " ");
                exp = exp.replaceAll("c", numbers[2] + " ");
            }

            //loop on exp
            for(int i = 0 ; i < str.length(); i++ ){
                //check on sign when have a only
                if(numbers.length == 1 && numbers[0] > 0 ){
                    // read number
                    String num = String.valueOf(numbers[0]);
                    //check if there was something like that '-num'
                    //but num is positive so will make space
                    if( (i+1) < str.length() && str.charAt(i) == '-'
                            && str.charAt(i+1) == num.charAt(0) ){
                        //Then make space
                        str.replace(i, i+1, "- ");
                        // System.out.println("Replaced Str =" + str);
                    }
                }
                //check on sign when have a only
                else if(numbers.length == 2 && numbers[0] > 0 && numbers[1] > 0 ){
                    // read number
                    String num1 = String.valueOf(numbers[0]);
                    String num2 = String.valueOf(numbers[1]);
                    //check if there was something like that '-num'
                    //but num is positive so will make space
                    if( (i+1) < str.length() && str.charAt(i) == '-'
                            && (str.charAt(i+1) == num1.charAt(0) || str.charAt(i+1) == num2.charAt(1))){
                        //Then make space
                        str.replace(i, i+1, "- ");
                        // System.out.println("Replaced Str =" + str);
                    }
                }
                //check on sign when have a only
                else if(numbers.length == 3 && numbers[0] > 0 && numbers[1] > 0 && numbers[2] > 0  ){
                    // read number
                    String num1 = String.valueOf(numbers[0]);
                    String num2 = String.valueOf(numbers[1]);
                    String num3 = String.valueOf(numbers[2]);
                    //check if there was something like that '-num'
                    //but num is positive so will make space
                    if( (i+1) < str.length() && str.charAt(i) == '-'
                            && (str.charAt(i+1) == num1.charAt(0) || str.charAt(i+1) == num2.charAt(0)
                            || str.charAt(i+1) == num3.charAt(0) ) ){
                        //Then make space
                        str.replace(i, i+1, "- ");
                        // System.out.println("Replaced Str =" + str);
                    }
                }
            }
            return exp ;
        }

        else
            throw new RuntimeException("Invalided Expression");
    }

    /**
     * Here the main code
     * @param args which is array if user enter parameter from terminal when run  code
     */
    public static void main(String[] args) {

        try {
            //Create object from scanning class
            Scanner input = new Scanner(System.in) ;
            //create object from class
            Main obj = new Main();
            //Scan expression from user
            String exp = input.nextLine();
            //flag check on operators
            boolean opFlag = false;

            //create string builder
            StringBuilder newStr = new StringBuilder();
            //copy exp to stringBuilder
            newStr = copyString(exp);

            //create flags that indicate if there was - at first of Expression
            boolean flagA = false , flagB = false , flagC = false , flagNothing = false;
            //create flag to check if at start of exp '-('
            boolean minusFlag = false;

            //Loop that check on some issues on expression that will convert to postfix
            for(int i = 0 ; i < exp.length() ; i++){
                // System.out.println("i = " + i + "& char = " + exp.charAt(i));
                //check if there was any -- in exp then replace them with +
                //But it will replaced by '+' if that wasn't begin of Expression
                if( (i+1) < exp.length() && exp.charAt(i) == '-' && exp.charAt(i+1) == '-'){
                    // System.out.println("Yes");
                    // System.out.println("Exp = " + exp);
                    if(i + 1 == 1){
                        exp = exp.replaceFirst("--", "");
                        newStr = copyString(exp);
                        // System.out.println("one");
                    }
                    else{
                        // System.out.println("two");
                        if( i-1 > 0 && (i+2) < exp.length()  && (exp.charAt(i-1) == '+' || exp.charAt(i-1) == '-' || exp.charAt(i-1) == '*' ||
                                exp.charAt(i-1) == '/' || exp.charAt(i-2) == '^')){
                            //change flag
                            flagNothing = true;
                            // System.out.println("yes");
                        }
                        else {
                            newStr = newStr.replace(i, i+2, "+");
                            // System.out.println("Str = " + newStr2);
                            exp = newStr.toString();
                        }
                    }
                }

                //check if exp like that a(b) or something like that
                if((i+1) < exp.length() && (exp.charAt(i)=='a' || exp.charAt(i)=='b'||exp.charAt(i)=='c' )
                        && exp.charAt(i+1)=='(')
                    throw new RuntimeException("Invalided Expression");

                // check start not have ')' or last not '(' r last is operator
                if(exp.charAt(0) == ')' || exp.charAt(exp.length()-1) == '(' )
                    throw new RuntimeException("Invalided Expression");

                //check if expression has operator aat start on end like that "/a+.." or "..+a/"
                if(exp.charAt(0)=='*'|| exp.charAt(0)=='/'|| exp.charAt(0)=='^' ||
                        exp.charAt(exp.length()-1)=='*'|| exp.charAt(exp.length()-1)=='/'|| exp.charAt(exp.length()-1)=='^' ||
                        exp.charAt(exp.length()-1)=='+' || exp.charAt(exp.length()-1)=='-')
                    throw new RuntimeException("Invalided Expression");

                if(newStr.charAt(i) <= 'z' && newStr.charAt(i) >= 'a')
                    countVariables ++;

                if(!(newStr.charAt(i) <= 'z' && newStr.charAt(i) >= 'a'))
                    countOperators ++;

                //check if there was -a or -b or -c at the first
                if(newStr.charAt(0)=='-' && (newStr.charAt(1)=='a' ||
                        newStr.charAt(1)=='b' || newStr.charAt(1)=='c')){
                    // System.out.println("In one");
                    //check if a or b or c and change flag to pass them values with negative sign
                    //Then delete '-'sign with nothing
                    if(newStr.charAt(1)=='a')
                        //change flag
                        flagA = true;
                    else if(newStr.charAt(1)=='b')
                        //change flag
                        flagB = true ;
                    else if(newStr.charAt(1)=='c')
                        //change flag
                        flagC = true;
                    //Delete - sign
                    newStr.deleteCharAt(0);

                    //break loop
                    break;
                }

                //check if there was something like that 'a+-b' that should make '-b'
                if( (i+1)<exp.length() && (i+2)<exp.length() && (exp.charAt(i) == '+' || exp.charAt(i) == '*'||
                        exp.charAt(i) == '/' || exp.charAt(i) == '^') && exp.charAt(i+1) == '-' &&
                        (exp.charAt(i+2) == 'a' || exp.charAt(i+2) == 'b' || exp.charAt(i+2) == 'c') ){
                    // System.out.println("In two");
                    // check if a or b or c and change flag to pass them values with negative sign
                    //Then delete '-'sign with nothing
                    if(newStr.charAt(i+2)=='a')
                        //change flag
                        flagA = true;
                    else if(newStr.charAt(i+2)=='b')
                        //change flag
                        flagB = true ;
                    else if(newStr.charAt(i+2)=='c')
                        //change flag
                        flagC = true;
                    //Delete - sign
                    newStr.deleteCharAt(i+1);

                }

                //check if there was '-(...)' something like that in Expression
                if(exp.charAt(0) == '-' && exp.charAt(1) == '(' ){
                    //Change flag
                    minusFlag = true ;
                }
            }

            //convert to postfix which will out as a result
            exp = obj.infixToPostfix(exp).trim().replaceAll(" ", "");
            //variable that save postfix Result
            String postResult = exp;

            //convert to postfix which will help to evaluate expression but will out wrong postfix Expression
            exp = obj.infixToPostfix(newStr.toString()).trim().replaceAll(" ", "");
            //copy to stringBuilder
            newStr = copyString(exp);

            //loop on Expression for check
            for(int i = 0 ; i < newStr.length() ; i++){

                //check if there was any operator
                if(newStr.charAt(i) == '+' || newStr.charAt(i) == '-' || newStr.charAt(i) == '*' ||
                        newStr.charAt(i) == '/' || newStr.charAt(i) == '^')
                    //change flag
                    opFlag = true;

                //check if there was +(--) at first index will remove it
                if(newStr.charAt(0) == '+'){
                    //Delete '+' sign
                    newStr.deleteCharAt(0);
                }

                //that will replace something like that a^+b with a^b
                if(exp.charAt(newStr.length() - 1 ) == '-' && minusFlag){
                    //remove negative Then put space
                    newStr.setCharAt(newStr.length() -1 , ' ');
                }

                // that will replace something like that a^+b with a^b
                if(exp.charAt(newStr.length() - 1 ) == '+' && flagNothing){
                    //remove negative Then put space
                    newStr.setCharAt(newStr.length() -1 , ' ');
                }

                if(countOperators == 2 && countVariables == 2 && exp.charAt(newStr.length() - 1 ) == '+'){
                    //remove negative Then put space
                    newStr.setCharAt(newStr.length() -1 , '\0');
                    //change flag
                    flagNothing = true;
                }

                //check if there was something like that 'a+-b' that should make '-b'
                if( (i+1)<exp.length() && (i+2)<exp.length() && (exp.charAt(i) == '+' || exp.charAt(i) == '-' ||
                        exp.charAt(i) == '*'|| exp.charAt(i) == '/' || exp.charAt(i) == '^') && exp.charAt(i+1) == '-'  ){
                    // check if a or b or c and change flag to pass them values with negative sign
                    //Then delete '-'sign with nothing
                    if(newStr.charAt(i+2)=='a')
                        //change flag
                        flagA = true;
                    else if(newStr.charAt(i+2)=='b')
                        //change flag
                        flagB = true ;
                    else if(newStr.charAt(i+2)=='c')
                        //change flag
                        flagC = true;
                    //Delete - sign
                    newStr.deleteCharAt(i+1);
                }
            }

            //After that copy stringBuilder to exp
            exp = newStr.toString().trim();
            //copy to string
            String postResult2 = exp ;

            //check if have something like that when flagNOthing is true => a+b that will be ab+
            if( postResult2.length()==3 && (postResult2.charAt(postResult2.length()-1)=='a' || (postResult2.charAt(postResult2.length()-1)=='b') || (postResult2.charAt(postResult2.length()-1)=='c')) &&
                    ((postResult2.charAt(postResult2.length()-2)=='+')||(postResult2.charAt(postResult2.length()-2)=='-')
                            ||(postResult2.charAt(postResult2.length()-2)=='*')|| (postResult2.charAt(postResult2.length()-2)=='/')
                            ||(postResult2.charAt(postResult2.length()-2)=='^')) ){
                //replace that a+b that will be ab+
                String variable = String.valueOf(postResult2.charAt(postResult2.length()-1));
                String operator = String.valueOf(postResult2.charAt(postResult2.length()-2));
                postResult2 = postResult2.replace(operator+variable , variable+operator );
            }

            //variables that will get from user
            int a = 0 , b = 0 , c  = 0 , count = 0 ;
            //variable that will save exp but with numbers of a & b & c

            //Save Values
            for(int i = 0 ; i < 3 ; i++){
                //get equation from user
                String eqn = input.nextLine();

                //check on variable of equation every time and save value to it
                if(eqn.charAt(0) == 'a') {
                    //replace any nonDigit with "" to extract int and assign it to a
                    eqn = eqn.replaceAll("[^0-9 |^-]", "").trim();
                    //if var = a then save to it int value
                    //First check if a have - sign before it
                    if(flagA)
                        a = Integer.parseInt(eqn)*-1;
                    else
                        a = Integer.parseInt(eqn);
                    //increase count to pass a in substituteStr Method
                    count++;
                }

                else if(eqn.charAt(0) == 'b') {
                    //replace any nonDigit with "" to extract int and assign it to b
                    eqn = eqn.replaceAll("[^0-9 |^-]", "").trim();
                    //if var = b then save to it int value
                    //First check if b have - sign before it
                    if(flagB)
                        b = Integer.parseInt(eqn)*-1;
                    else
                        b= Integer.parseInt(eqn);
                    //increase count to pass b in substituteStr Method
                    count++;
                }

                else if(eqn.charAt(0) == 'c') {
                    //replace any nonDigit with "" to extract int and assign it to c
                    eqn = eqn.replaceAll("[^0-9 |^-]", "").trim();
                    //if var = c then save to it int value
                    //First check if a have - sign before it
                    if(flagC)
                        c = Integer.parseInt(eqn)*-1;
                    else
                        c = Integer.parseInt(eqn);
                    //increase count to pass c in substituteStr Method
                    count++;
                }

                else
                    throw new RuntimeException("Invalid variable");
            }


            //Then Substitute values with check on count
            if(count == 1)
                exp = substituteStr(exp, a);
            else if (count == 2)
                exp = substituteStr(exp, a , b);
            else if (count == 3)
                exp = substituteStr(exp, a , b , c);
            else
                throw new RuntimeException("Invalid variable");

            //checkString
            exp = checkStr(exp) ;

            //Evaluate postfix
            int result = obj.evaluate(exp);

            //Printing Out From code
            //First  => postfix
            if(flagNothing == true)
                System.out.println(postResult2);
            else
                System.out.println(postResult);
            //Second => Print Evaluate
            if(minusFlag)
                System.out.println(-1*result);
            else
                System.out.println(result);

            //exit from code
            System.exit(0);

        }

        catch(Exception e){
            System.out.println("Error");
        }
    }

}
