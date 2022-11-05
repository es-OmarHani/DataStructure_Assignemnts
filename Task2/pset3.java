import java.util.*;


public class Solution {
	public int[] sumEvenOdd(int[] array){
        //That Function will get array sum and even values

        //that variables will save sum of array elements
        int sumEven = 0 , sumOdd = 0;

        try {

            if(array.length > 0){
                for(int i = 0 ; i < array.length ; i++ ){
                    //Check the elements is even or odd then add it to sum variables
                    if ( array[i] % 2 == 0 )
                        sumEven += array[i] ;
                    else
                        sumOdd += array[i] ;     
                }

                //save sum values into array
                int[] result = {sumEven , sumOdd} ;

                //then return average
                return result ;
            }

            //if user add array with out any elements
            else 
                throw new Exception();

        }

        catch (Exception e){
            //that will return anonymous array
            return new int[]{0,0} ;
        }
    }

	public static void main(String[] args) {
        //create Object from scanner
		Scanner sc = new Scanner(System.in);
        //Then will take input from user like that [1,2,3,........] then replace [ and ] with nothing 
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        //Then split all numbers into string array
        String[] s = sin.split(", ");
        //Create new array with same length of input numbers 
		int[] arr = new int[s.length];
        //if length is nothing inside it will create empty anonymous array 
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        //else will create array with same numbers but must be integers values
        else {
            for(int i = 0; i < s.length; ++i)
                arr[i] = Integer.parseInt(s[i]);
        }
        //Then calling sumEvenOdd function that will return average value
        int[] res = new Solution().sumEvenOdd(arr);
        
        //Then will print result array which have 2 variables evenSum & oddSum 
        System.out.println(Arrays.toString(res));

    }
}