import java.util.Scanner;

public class Calculator {
    public static void main(String args[]) {
        //Make Object from Scanner Class
        Scanner input = new Scanner(System.in) ;

        //scan value from user and get every Element in array
        String[] number = input.nextLine().split(" ");
        
        if(number.length != 3)
            System.out.println("Error"); 

        //That check on user input is like that (x + y) or (x - y) or (x * y) , ....... 
        if (number.length == 3 ){

            // if(number[2].equals("0")){
            //     System.out.println("Error"); 
            //     return;
            // }

            //That for validate user input if not enter numeric input
            try{
                //convert 2 numbers to Int to can do operations on them
                int number_int1 = Integer.parseInt(number[0]);
                int number_int2 = Integer.parseInt(number[2]);

                //check if user input is + will call add function
                if(number[1].equals("+")) {
                    int result1 = add(number_int1 , number_int2);
                    System.out.println(result1);
                    return;
                }

                //check if user input is / will call add function
                if(number[1].equals("/")) {
                    divide(number_int1 , number_int2);
                    // System.out.println(result2);
                    // return;
                }
            }

            //That handling the user un_valid input like Enter any String no numeric
            catch(Exception e){
                System.out.println("Error");
                return;
            }
            
            


        }


    }

    static int add(int x , int y) {
        return x+y;
    }

    static void divide(int x , int y)  {
        
        try{
            float result = (float) x / y ;
            System.out.println(result);
        }
        
        catch(ArithmeticException e){
            System.out.println("Error");
            return;
        }


    }
}
