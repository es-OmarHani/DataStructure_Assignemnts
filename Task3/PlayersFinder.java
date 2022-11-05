import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


// interface IPlayersFinder {

//     /**
//      * Search for players locations at the given photo
//      * @param photo
//      *     Two dimension array of photo contents
//      *     Will contain between 1 and 50 elements, inclusive
//      * @param team
//      *     Identifier of the team
//      * @param threshold
//      *     Minimum area for an element
//      *     Will be between 1 and 10000, inclusive
//      * @return
//      *     Array of players locations of the given team
//      */
//     java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
    
// }

public class PlayersFinder {

    private static ArrayList<int[]> saved = new ArrayList<>();
    private static ArrayList<Integer> finalResult = new ArrayList<>();
    private static int[][] savedData = new int[50][2];
    private static int counter = 1 , count = -1 , counting = 0 ;
    private static int counterChain = 0;
    private static boolean flag = true ;
    // private static int count = 0;
    public static void thershDetection(int count , int threshValue ) {

        //variables min and max y
        int minY = savedData[0][0] , maxY = savedData[0][0] , minX = savedData[0][1] , maxX = savedData[0][1] ;
        System.out.println();
        System.out.println(Arrays.deepToString(savedData));
        //counter for valid points
        for(int i = 0 ; i < savedData.length ; i++){
            if(  i != 0 && savedData[i][0] == 0  && savedData[i][1] == 0  ){
                PlayersFinder.counting ++;
            }
        }
        counting = 50 - counting ;
        System.out.println("counting = " + counting);
        //Check if all cells is greater than thresholdValue
        // System.out.println("4 * Counter => " + (4*counter) );
        if ((4 * counter) >= threshValue){
            
            //show array for me
            // System.out.println(Arrays.deepToString(savedData));
            
            //Now will get min y of chain and max y 
            for (int i = 0 ; i < counting ; i++){
                //get minY
                if (savedData[i][0] < minY ){
                    System.out.println(" i1 = " + savedData[i][0]);
                    minY = savedData[i][0];
                    System.out.println(" min  = " + minY);
                }
                // get minX
                if (savedData[i][1] < minX )
                    minX = savedData[i][1];    
                
                //get maxY
                if(savedData[i][0] > maxY && i != 0){
                    System.out.println(" i2 = " + savedData[i][0]);
                    maxY = savedData[i][0];
                    System.out.println(" max  = " + maxY);
                }    

                
                //get maxX
                if(savedData[i][1] > maxX)
                    maxX = savedData[i][1];
            }

            System.out.println("min y => " + minY);
            System.out.println("max y => " + maxY);
            System.out.println("min X => " + minX);
            System.out.println("max X => " + maxX);

            //Updated Value of minY and maxY
            minY = minY + (minY+1) ;
            maxY = maxY + 1 + (maxY+1) ;

            // //Updated Value of minX and maxX
            minX = minX + (minX+1) ;
            maxX = maxX + 1 + (maxX+1) ;

            System.out.println("min y => " + minY);
            System.out.println("max y => " + maxY);
            System.out.println("min X => " + minX);
            System.out.println("max X => " + maxX);

            
            //get main of cols
            int meanCols = (minY + maxY ) / 2  ;
            int meanRows = (minX + maxX ) / 2  ;

            System.out.println("Mean Cols => " + meanCols) ;
            System.out.println("Mean Rows => " + meanRows) ;

            finalResult.add(meanRows);
            finalResult.add(meanCols);

            counterChain++;
        }


        }
    

    public static int[][] recursiveFind(int[][] arr, int new_i, int new_j , int caller_i , int caller_j  ) {
        /*
        >> documentation goes here
        */
        System.out.println();
        System.out.println( "Caller  => " +" "+ caller_i + " " + caller_j);
        System.out.println( "New     => " +" "+ new_i + " " + new_j);
        System.out.println( "Counter => " + counter);
        // System.out.println( "Old    => " +" "+ old_i + " " + old_j);
        //base case
        
        try{
            if(new_i == caller_i && new_j == caller_j&& (arr[new_i][new_j+1] == 0 || arr[new_i][new_j+1] == 2 ) && (arr[new_i+1][new_j] == 0 || arr[new_i+1][new_j] == 2 ) && ( arr[new_i][new_j-1] == 0 || arr[new_i][new_j-1] == 2 ) && (arr[new_i-1][new_j] == 0 || arr[new_i-1][new_j] == 2) )
            {
                // counter = 0 ;
                return arr ;
            }
        }

        catch(Exception e){
            int x = 1 ;
        }
        
                
        
        
    
        //Increment Element array
        if(arr[new_i][new_j] == 1)
            arr[new_i][new_j] ++;
        
        // //create arraylist
        // ArrayList<Array> saved = new ArrayList<>();

        //check on size then remove it
        // if (Test.saved.size() >= 1)
        //     Test.saved.remove(Test.saved.size() - 1);


    
        try {
    
            //-- search right --
            if ( new_j != (arr[0].length - 1)  && (arr[new_i][new_j+1] == 1  ) ) {
                System.out.println("R");
                counter ++ ; count++ ;
                //looping throw array
            for(int i = 0 ; i < savedData.length ; i++){
                //passing Elements that will be in the array and not put it again
                // System.out.println("############################");
                // System.out.println("Saved Data = " + savedData[i][0] + " new i = " + new_i);
                // System.out.println("Saved Data = " + savedData[i][1] + " new j = " + new_j);
                if(new_i == savedData[i][0] && new_j == savedData[i][1] )
                    continue;
                
                //Putting new Elements in array
                int[] saved_arr = {new_i , new_j};
                savedData[count] = saved_arr;
                saved.add(saved_arr);
                // System.out.println(Arrays.deepToString(savedData));
            }
                recursiveFind(arr, new_i , new_j+1 , caller_i , caller_j );
            }
            // else {
            //   arr[i][j+1]--;
            // }
    
            //-- search below --
            if( (new_i != (arr.length - 1) ) && (arr[new_i+1][new_j] == 1  ) ){
                System.out.println("B");
                counter ++ ; count++ ;
                        //looping throw array
            for(int i = 0 ; i < savedData.length ; i++){
                //passing Elements that will be in the array and not put it again
                // System.out.println("############################");
                // System.out.println("Saved Data = " + savedData[i][0] + " new i = " + new_i);
                // System.out.println("Saved Data = " + savedData[i][1] + " new j = " + new_j);
                if(new_i == savedData[i][0] && new_j == savedData[i][1] )
                    continue;
                
                //Putting new Elements in array
                int[] saved_arr = {new_i , new_j};
                savedData[count] = saved_arr;
                saved.add(saved_arr);
                // System.out.println(Arrays.deepToString(savedData));
            }
                // int[] saved_arr = {new_i , new_j};
                // Test.saved.add(saved_arr);
                // savedData[count] = saved_arr;
                recursiveFind(arr, new_i + 1, new_j , caller_i , caller_j );
            }
            // else {
            //   arr[i+1][j]--;
            // }
    
            //-- search left --
            if((new_j!=0) && (arr[new_i][new_j-1] == 1  ) ){
                System.out.println("L");
                counter ++ ; count++ ;
                        //looping throw array
            for(int i = 0 ; i < savedData.length ; i++){
                //passing Elements that will be in the array and not put it again
                // System.out.println("############################");
                // System.out.println("Saved Data = " + savedData[i][0] + " new i = " + new_i);
                // System.out.println("Saved Data = " + savedData[i][1] + " new j = " + new_j);
                if(new_i == savedData[i][0] && new_j == savedData[i][1] )
                    continue;
                
                //Putting new Elements in array
                int[] saved_arr = {new_i , new_j};
                savedData[count] = saved_arr;
                saved.add(saved_arr);
                // System.out.println(Arrays.deepToString(savedData));
            }
                // int[] saved_arr = {new_i , new_j};
                // Test.saved.add(saved_arr);
                // savedData[count] = saved_arr;
                
                recursiveFind(arr, new_i, new_j-1 , caller_i , caller_j);
            }
            // else {
            //   arr[i][j-1]--;
            // }
    
            //-- search up --
            if((new_i!=0) && (arr[new_i-1][new_j] == 1  ) ){ 
                System.out.println("U");
                counter ++ ; count++ ;
                        //looping throw array
            for(int i = 0 ; i < savedData.length ; i++){
                //passing Elements that will be in the array and not put it again
                // System.out.println("############################");
                // System.out.println("Saved Data = " + savedData[i][0] + " new i = " + new_i);
                // System.out.println("Saved Data = " + savedData[i][1] + " new j = " + new_j);
                if(new_i == savedData[i][0] && new_j == savedData[i][1] )
                    continue;
                
                //Putting new Elements in array
                int[] saved_arr = {new_i , new_j};
                savedData[count] = saved_arr;
                saved.add(saved_arr);
                // System.out.println(Arrays.deepToString(savedData));
            }
                // int[] saved_arr = {new_i , new_j};
                // Test.saved.add(saved_arr);
                // savedData[count] = saved_arr;
                
                recursiveFind(arr, new_i-1, new_j , caller_i , caller_j );
            }
            // else {
            //   arr[i-1][j]--;
            // }
    
            //Return to bast element if not found any 1 for new element
            else {
                
                // //for one time put element that not have any adjacent number = 1 
                // if(flag == true){
                // }
                //change flag to true
                // flag = false ;

                System.out.println("Back");
                count++;
                
                int size = saved.size() ;
                if ( (size > 1 && flag == true) || (flag == false && savedData[new_i][new_j] == 2) || (flag == false && savedData[new_i][new_j] == 1) ){
                            //looping throw array
                for(int i = 0 ; i < savedData.length ; i++){
                    //passing Elements that will be in the array and not put it again
                    // System.out.println("############################");
                    // System.out.println("Saved Data = " + savedData[i][0] + " new i = " + new_i);
                    // System.out.println("Saved Data = " + savedData[i][1] + " new j = " + new_j);
                    if(new_i == savedData[i][0] && new_j == savedData[i][1] ){
                        System.out.println("1");
                        continue;
                    }
                    
                    //Putting new Elements in array
                    int[] saved_arr = {new_i , new_j};
                    savedData[count] = saved_arr;
                    saved.add(saved_arr);
                    // System.out.println(Arrays.deepToString(savedData));
                }
                    flag = false;
                    //put element that not have any adjacent number = 1 
                    // int[] saved_arr = {new_i , new_j};
                    // Test.saved.add(saved_arr);
                    // savedData[count] = saved_arr;
                    //Out on screen
                    System.out.println( "New******=> " +" "+ new_i + " " + new_j);
                    System.out.println(Arrays.deepToString(savedData));
                    
                    //Get row & col of Element before current Element
                    int row = savedData[count-1][0];
                    int col = savedData[count-1][1];
                    //that only for out on screen
                    System.out.println("row = "+ row + " col = " + col);
                    //Recursion with Element before current Element
                    recursiveFind(arr, row , col , caller_i , caller_j );
                }
                    
                
                
            }
        }
    
        catch(Exception e){
            System.out.println("[Error]: exception in recursiveFind()");
        }
        return arr ;
        }

    /***************************************************Traves Array Method**********************************************************************/
    public static void traverseArr(int[][]arr , int threshold){
        /*****That Method for deal with every chain appears in array */
        //variables for row && cols
        int rows = arr.length;
        int cols = arr[0].length ;
        //Create result array
        int[][] result = new int[arr.length][arr[0].length];
        // loop Throw array until find 1 
        for(int i = 0 ; i < rows; i++){
            for(int j = 0 ; j < cols ; j++){
                if( arr[i][j] == 1){
                    //call function that make recursive
                    result = recursiveFind(arr, i, j , i, j );
                    //for test only
                    System.out.println(Arrays.deepToString(savedData));
                    //Then call Function that check if chain is valid or not comparing to threshold value
                    thershDetection(counter , threshold);
                    
                    //Initialization all variables 
                    //Make all counters as default
                    counter = 1  ; count = -1; flag = true ; counting = 0;
                    //clear array
                    for(int count1 = 0 ; count1 < savedData.length ; count1 ++){
                        int[] clear = {0,0};
                        savedData[count1] = clear;
                    }
                    //clear saved 
                    saved.clear();
                }
            }
        }

        //print result array
        System.out.println(Arrays.deepToString(result));

        //That for output result
        int[][] meansArray = new int[counterChain][2];
        int c = 0;
        //test only
        System.out.println("CounterChain = " + counterChain);
        // print final result
        for(int i = 0 ; i < counterChain  ; i++){
            for(int j = 0 ; j < 2 ; j++ ){
                meansArray[i][j] = finalResult.get(c);
                c++;
            }
        }
        //test only
        System.out.println();
        System.out.println("***********************************");
        Arrays.sort(meansArray, Comparator.comparingDouble(o -> o[0]));
        System.out.println(Arrays.deepToString(meansArray));
        
        // for(int i = 0 ; i < finalResult.size() ; i++){
        //     System.out.print (finalResult.get(i) + " ");
        // }
    
        }

    public static void IPlayersFinder(String[][] arr , int id , int thresh) {
        /* Here will parsing array with value of team then do all operations */
        
        //Create sparse array 
        int[][] sparseArray = new int[arr.length][arr[0].length];
        
        //loop throw array then make 1 at the same value of team in sparse array
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = 0 ; j < arr[0].length ; j++){
                //check if value Equaled to value of team
                if( arr[i][j].equals(Integer.toString(id)) ){
                    //parsing array to be 1 at the same value of team
                    sparseArray[i][j] = 1 ;
                }
            }
        }
        
        // //printing only
        printingArrayInt(sparseArray);

        //Calling trav Function
        // traverseArr(sparseArray, thresh);


        

    }

    

    /**********************************************Printing Array**********************************************************************/
    public static void printingArrayInt(int[][] arr){
        /*That method will printing array*/
        System.out.print("{");
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = 0 ; j < arr[0].length ; j++){
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println("}");
    }

    /**********************************************Printing Array**********************************************************************/
    public static void printingArrayString(String[][] arr){
        /*That method will printing array*/
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = 0 ; j < arr[0].length ; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    
    public static void main(String[] args) {
        /* Implement main method to parse the input from stdin and print output to stdout */
        //Create object from scanning class
        Scanner input = new Scanner(System.in) ;
        
        /*********************************COLS & ROWS**********************************************/
        try{
            //taking cols and rows of 2d array from user
            String[] Cols_Rows = input.nextLine().split(",");
            //Assign cols and rows from user
            int rows = Integer.parseInt(Cols_Rows[0].trim());
            int cols = Integer.parseInt(Cols_Rows[1].trim());
            //Create 2D array that have information with getting cols and rows from user
            String[][] info = new String[rows][cols];

            /******************************Extract information from user to 2d array****************/
            //Getting info from user
            ArrayList<String> inputInfo = new ArrayList<>();
            
            for (int i = 0 ; i < rows ; i++ ){
                String[] information = input.nextLine().split("");
                //check for length must be equal to the cols
                // if (information.length == cols) {
                    for (int j = 0 ; j < cols ; j++){
                        inputInfo.add(information[j].trim());
                    }
                // }  

                // else 
                //     System.out.println("Error");
                //     return;
            }
            //variable that will increase for array  inputInfo access
            int counter = 0 ;
            //loop on array from user that have every element in it
            for(int i = 0 ; i < rows ; i++  ){
                for(int j = 0 ; j < cols ; j++ , counter++){
                    //Assign information to 2D array
                    info[i][j] = inputInfo.get(counter) ;
                }
            }
            
            // //printing array 
            // PlayersFinder.printingArrayString(info);

            /***********************************Get team value********************************/
            //variable that have team id
            int idTeam = input.nextInt();

            
            /***********************************Get threshold value********************************/
            //variable that have team id
            int thresh = input.nextInt();

            /**********************************Find players Function*******************************/
            PlayersFinder.IPlayersFinder(info, idTeam,thresh);
        }

        catch(Exception e){
            System.out.println("Error");
        }
        
    }
}
