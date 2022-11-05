import java.util.*;


public class Solution {
    
	public int[] reverse(int[] array){
        //That Function will get array reverse in another array
        int[] temp = new int[array.length];

        //make variable that save
        int size = array.length ;
        
        if(array.length > 0){
            //loop on array then reverse it on another array
            for(int i = 0 ; i < array.length ; i++ ){
                temp[size - 1] = array[i] ;
                size-= 1 ;
            }

            //then return reversed array
            return temp ;
        }

        else 
            return new int[] {} ;
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
        int[] res = new Solution().reverse(arr);
        
        //Then print the average
        System.out.println(Arrays.toString(res));
    }
}