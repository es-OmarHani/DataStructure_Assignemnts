import java.util.*;


public class Solution {
	public double average(int[] array){
        //That Function will get array average
        double sumValues = 0;

        try {

            if(array.length > 0){
                for(int i = 0 ; i < array.length ; i++ ){
                    sumValues += array[i] ;
                }
    
                //then return average
                return (sumValues/array.length) ;
            }

            else 
                throw new Exception();

        }

        catch (Exception e){
            return 0 ;
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
        //Then calling average function that will return average value
        double res = new Solution().average(arr);
        //Then print the average
        System.out.println(res);
    }
}