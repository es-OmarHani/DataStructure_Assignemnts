import java.util.Scanner;

public class Calculator {

    public static void IO() {
        
        //loop to show main menu
        while(true){
            
            //make scanner object that scan input from user
            Scanner input = new Scanner(System.in);

            //That try for validate input from user
            try{
                //show main menu
                System.out.println("###################################################");
                System.out.println("Welcome To Our Calculator ....");
                System.out.println("\t 1.Add ");
                System.out.println("\t 2.Subtraction");
                System.out.println("\t 3.Multiplication");
                System.out.println("\t 4.Divide");
                System.out.println("\t 5.Exit");
                System.out.println("###################################################");
                
                //print out message for user
                System.out.println();
                System.out.print("\t Ur Choice => ");
                
                //take from user input
                int input_user = input.nextInt() ;
                System.out.println();
                
                //check on input
                switch(input_user) {
                    case 1 :
                        //calling addition method
                        addition();
                        break;
                    case 2 :
                        //calling subtraction method
                        subtraction();
                        break;
                    case 3 :
                        //calling multiplication function
                        multiplication();
                        break;
                    case 4 :
                        //calling division function
                        division();
                        break;
                    case 5 :
                        return;

                    default:
                    //that message showing for user to indicate that input not valid
                        printing("Please Enter Valid Number .......");
                }
                
            }

            //if user input any un_valid value like inter string will show that message for him
            catch(Exception e){
                System.out.println("Please Enter Valid Integer .........");
                input.next();
            }

        }
        
    }

    public static void addition() {
        //calling method function that do all operations on 2 elements enter by user
        method("+");
        }
    

    public static void subtraction() {
        //calling method function that do all operations on 2 elements enter by user
        method("-");    
    }

    public static void multiplication() {
        //calling method function that do all operations on 2 elements enter by user
        method("*");    
    }

    public static void division() {
        //calling method function that do all operations on 2 elements enter by user
        method("/");    
    }

    //that method for decorating print sentences in programme by adding space before and after sentence
    public static void printing(String sentence) {
        System.out.println();
        System.out.println(sentence);
        System.out.println();
    }

    //That Method take operator if + or - or * or / and do that operations on user inputs
    public static void method(String operator) {
        //make Scanner object
        Scanner input = new Scanner(System.in);

        //declare result
        double result = 0 ;

        while(true){
            //print message
            System.out.print("Enter Equation like That 'x " +operator+ " y' with space between each parameter  => ");
            //scan value from user and get every Element in array 
            String[] number = input.nextLine().split(" ");

            //That check on user input is like that (x + y) or (x - y) or (x * y) , ....... 
            if (number.length == 3 && number[1].equals(operator) ){
                //That for validate user input
                try{
                    double number_int1 = Double.parseDouble(number[0]);
                    double number_int2 = Double.parseDouble(number[2]);
                    //make addition on elements
                    if (operator.equals("+")){
                        result = number_int1 + number_int2 ;}
                    //make subtraction on elements
                    else if (operator.equals("-")){
                        result = number_int1 - number_int2 ;}
                    //make multiplication on elements
                    else if (operator.equals("*")){
                        result = number_int1 * number_int2 ;}    
                    //make division on elements
                    else if (operator.equals("/")){
                        result = number_int1 / number_int2 ;}

                    //print messuage telling result
                    printing("result = " + result);
                }

                //That handling the user un_valid input like Enter any String no numeric
                catch(Exception e){
                    printing("Please Enter Valid Integer .........");
                }
            }

            //if enter q or Q will break only will break and show main menu again
            else if (number.length == 1 && (number[0].equals("q") || number[0].equals("Q"))){
                break;
            }

            //if user not input Equation like shown message will out message reminder him
            else{
                printing("Enter the Equation like That \"x "+operator+" y\" , pLease.........");
            }
        }
        
    }
}
