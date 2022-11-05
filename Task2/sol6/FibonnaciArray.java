import java.util.*;

public class Solution {
    public int fib(int n) {
        //That Method create array with width 40 for fibonacci
        
        //Two base case
        //If base case 1 == 0 will return 0 
        if (n == 0)
            return 0 ;
        //If base case 2 == 0 will return 1 
        else if (n == 1)
            return 1 ;
        //That will be recursive case
        else 
            return fib(n-1)+fib(n-2);       
    }

    public static void main(String[] args) {
        //Create object from scanner
        Scanner input = new Scanner(System.in) ;
        //Take value from user
        int value = input.nextInt();

        //create array with 40 elements
        int[] array = new int[40] ;
        
        //Calling Function that insert fibonacci series in array
        for (int i = 0 ; i < array.length ; i++){
            array[i] = new Solution().fib(i);
        }

        //Then will out value from array
        System.out.println(array[value-1]);
        
        
    }
}
