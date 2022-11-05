import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class Transpose {

    public String[][] transpose(String[][] array) {
        
        String[][] transposedMatrix = new String [array[0].length][array.length] ;
        for (int i = 0 ; i < array[0].length ; i++){
            for(int j = 0 ; j < array.length ; j++){
                transposedMatrix[i][j] = array[j][i] ;
            }
        }

        return transposedMatrix ;
    }


    public static void main(String[] args) {
        //create object from scanner
        Scanner sc = new Scanner(System.in);
        //Then Take input 2d array from user then replace all [ or ] with space
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        //Then split into one array 2 arrays to make index 0 is first array and second array at index 1 
        String[] s = sin.split(", ");
        
        if (s.length > 1 ){
            //Number of row and cols
            double num = Math.sqrt((double) s.length) ;
            //Then make 2d array
            String[][] arr = new String[(int)num][(int)num] ;
            //Make counter to access on s array 
            int counter = 0 ;
            //loop to assign value of s in 2d array
            for (int i = 0 ; i < num ; i++){
                for(int j = 0 ; j < num ; j++){
                    arr[i][j] = s[counter];
                    counter ++;
                }
            }

            //Then call transpose function
            String[][] res = new Transpose().transpose(arr);
            //out transposed matrix
            System.out.println(Arrays.deepToString(res));

        }

        // if user input empty or array has one element
        else{
            String[][] arr = new String[1][1] ;
            
            //if array is empty will print empty 2d array 
            if (s[0].split(" ").length == 0)
                System.out.println("[[]]");
            
                //If not will out 2d array with the user elements added
            else{
                arr[0][0] = s[0] ;
                System.out.println(Arrays.deepToString(arr));
            }
        }

    }
}   

