package sol4;

import java.util.ArrayList;
import java.util.Scanner;

public class MoveArrayElement {
    public ArrayList<String> moveValue(ArrayList<String> array , String value){
        //That Function will move all values that equaled with passing value to end of array

        //that variable will save number of times that found value in array
        int counter = 0 ;

        for(int i = 0 ; i < array.size() ; i++ ){
            if (array.contains(value))
                counter ++;
            array.remove(value) ;
        }
        
        //After that will add values that removed at end of array
        for(int j = 0 ; j < counter ; j++ ){
            //Here will append value to array with same number removed from array
            array.add(value) ;
        }

        return array ;
    }

    public static void main(String[] args) {
        //create Object from scanner
		Scanner sc = new Scanner(System.in);

        //Then will take input from user like that [1,2,3,........] then replace [ and ] with nothing then get value
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String value = sc.next() ; 

        //Then split all numbers into string array
        String[] s = sin.split(", ");

        //Create new arraylist  
        ArrayList <String> arr = new ArrayList<>() ;

        //Here will create arraylist with same numbers but must be integers values
        for(int i = 0; i < s.length; ++i){
            arr.add(i, s[i] ) ;
        }

        // Then calling sumEvenOdd function that will return average value
        ArrayList<String> res = new MoveArrayElement().moveValue(arr, value) ;
        
        // Then will print result array which have 2 variables evenSum & oddSum 
        System.out.println(res);

    }
    
}
