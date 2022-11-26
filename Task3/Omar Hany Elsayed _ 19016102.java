import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Test {

    /*****************************************Attributes Of class**************************************************/
    //Array that save location of value ID in it
    private static ArrayList<Integer[]> locPoints = new ArrayList<>();
    //Array that save means of each chain to print it
    private static ArrayList<Integer> finalResult = new ArrayList<>();
    //Counter that variable will increased as long as chain is increased 
    //count only for access when need to back the last location if cannot go forward in chain 
    private static int counter = 1 , count = -1 ;
    //that count number out points every chain that can get min and max for it
    private static int counterChain = 0;

    /*********************************************** Threshold Detection Function Function *********************************************************/
    public static void threshDetection(int count , int threshValue ) {
        /*******That Method take chain and get minX & minY and maxX & maxY if chain has area > threshold value*/
        
        //variables min and max y
        int minY = locPoints.get(0)[0] , maxY = locPoints.get(0)[0] , minX = locPoints.get(0)[1] , maxX = locPoints.get(0)[1] ;
        
        //Check if all cells is greater than thresholdValue
        if ((4 * counter) >= threshValue){
            
            //show array for me
            // System.out.println(Arrays.deepToString(locPointsData));
            
            //Now will get min y of chain and max y 
            for (int i = 0 ; i < locPoints.size() ; i++){
                //get minY
                if (locPoints.get(i)[0] < minY ){
                    minY = locPoints.get(i)[1];
                }
                
                // get minX
                if (locPoints.get(i)[1] < minX )
                    minX = locPoints.get(i)[1];    
                
                //get maxY
                if(locPoints.get(i)[0] > maxY ){
                    maxY = locPoints.get(i)[0];
                }    

                //get maxX
                if(locPoints.get(i)[1] > maxX)
                    maxX = locPoints.get(i)[1];
            }

            //Updated Value of minY and maxY
            minY = minY + (minY+1) ;
            maxY = maxY + 1 + (maxY+1) ;

            //Updated Value of minX and maxX
            minX = minX + (minX+1) ;
            maxX = maxX + 1 + (maxX+1) ;

            //get main of cols
            int meanCols = (minY + maxY ) / 2  ;
            int meanRows = (minX + maxX ) / 2  ;

            //adding means to array to can print out it
            finalResult.add(meanRows);
            finalResult.add(meanCols);

            counterChain++;
        }


    }
    
    /***********************************************Recursive Function *********************************************************/
    public static void recursiveFind(int[][] arr, int new_i, int new_j ) {
        /*
        The Whole Algorithm is Here ===> Briefly , I used recursion To handle with every chain found in sparse array "Here passed sparse array not original"
            If found any 1 at any index will do 
            First  => Increasing value of 1 to 2 not to make location entered again into locPoints Array 
            Second => Check if right element is == 1 then will recursive to it and do the same as in First
            Third  => If Second is not Done will check below element of it is == 1 then will recursive on it and do the same as in First
            Fourth => If second and third not Done will check left element of it is == 1 then will recursive on it and do the same as in First
            Fifth  => If second and third and fourth not Done will check up element of it is == 1 then will recursive on it and do the same as in First
            After all of that if any case not achieved that means Element not has any adjacent 1 so will back to last location that has already pass to it 
                and do all this operations again  
        */
        
        //Putting new Elements in array to append it in array list
        Integer[] locPoints_arr = {new_i , new_j};

        //here every time check if Element is at first location of chain and there is no any adjacent 1 that means all chain is reading
        if(locPoints.size() > 0 &&  new_i == locPoints.get(0)[0] && new_j == locPoints.get(0)[1])
        {
            //Then will out method 
            return ;
        }
    
        //Increment Element array
        if(arr[new_i][new_j] == 1)
            arr[new_i][new_j] ++;
        
        try {

            //==================search right====================//
            // That check if right element == 1 then will recursive to that element 
            if ( new_j < (arr[0].length - 1)  && (arr[new_i][new_j+1] == 1  ) ) {
                // System.out.println("...............Right");
                counter ++ ; count++ ;
                
                //If array haven't location of that Element that equaled 1 in chain will append it
                    if (locPoints.contains(locPoints_arr) == false || counter == 1){
                        locPoints.add(locPoints_arr);
                    }
                
                //Calling recursion with new location "Right"
                recursiveFind(arr , new_i , new_j+1 );
            }
    
            //==================search Below====================//
            // That check if below element == 1 then will recursive to that element 
            if( (new_i < (arr.length - 1) ) && (arr[new_i+1][new_j] == 1  ) ){
                // System.out.println("...............Below");
                counter ++ ; count++ ;
                
                //If array haven't location of that Element that equaled 1 in chain will append it
                    if (counter == 1 || locPoints.contains(locPoints_arr) == false ){
                        locPoints.add(locPoints_arr);
                    }

                //Calling recursion with new location "Below"
                recursiveFind(arr, new_i + 1, new_j );
            }

            //==================search Left====================//
            // That check if Left element == 1 then will recursive to that element 
            if((new_j > 0) && (arr[new_i][new_j-1] == 1  ) ){
                // System.out.println("Done.....L.......");
                // System.out.println("...............Left");
                counter ++ ; count++ ;
                
                
                //If array haven't location of that Element that equaled 1 in chain will append it
                    if (locPoints.contains(locPoints_arr) == false || counter == 1){
                        locPoints.add(locPoints_arr);
                    }

                //Calling recursion with new location "Left"
                recursiveFind(arr, new_i, new_j-1 );
            }
    
            //==================search Up====================//
            // That check if Up element == 1 then will recursive to that element 
            if((new_i > 0) && (arr[new_i-1][new_j] == 1  ) ){ 
                // System.out.println("Done......U......");
                // System.out.println("U");
                counter ++ ; count++ ;
                
                //If array haven't location of that Element that equaled 1 in chain will append it
                    if (locPoints.contains(locPoints_arr) == false || counter == 1){
                        locPoints.add(locPoints_arr);
                    }

                //Calling recursion with new location "Up"
                recursiveFind(arr, new_i-1, new_j  );
            }
    
            //Return to last element if not found any 1 for new element
            else {

                //If array haven't location of that Element that equaled 1 in chain will append it
                if (locPoints.contains(locPoints_arr) == false || counter == 1){
                    locPoints.add(locPoints_arr);
                }

                    //Get row & col of Element before current Element
                    int row = locPoints.get(count-1)[0];
                    int col = locPoints.get(count-1)[1];
                    count -- ;
                    
                    recursiveFind(arr, row , col );
            }
        }
    
        catch(Exception e){
            // System.out.println("[Error]: exception in recursiveFind()");
        }

        return ;

    }
    
/*********************************************** Traversal Function *********************************************************/
    public static void traverseArr(int[][]arr , int thresh){
        /*****That Method Taking Original array and convert it to sparse array ********/

        //If user input Array with size of cols > 1
        if(arr[0].length >= 1 ){
            //variables for row && cols
            int rows = arr.length;
            int cols = arr[0].length ;
            //Create result array
            int[][] result = new int[arr.length][arr[0].length];
            // loop Throw array until find 1 
            for(int i = 0 ; i < rows ; i++){
                for(int j = 0 ; j < cols ; j++){
                    if(arr[i][j]==1){
                        //call function that make recursive
                        recursiveFind(arr, i, j );
                        //Call Function that get means of chain
                        threshDetection(counter , thresh);
                        //Initialization all variables && make all counters as default
                        counter = 1  ; count = -1;
                        //clear locPoints 
                        locPoints.clear();
                    }
                }
            }
            
            //Create 2D array that will save means row and cols
            int[][] meansArray = new int[counterChain][2];
            int c = 0;

            // Loop throw list to extract means then put them into 2d array
            for(int i = 0 ; i < counterChain  ; i++){
                for(int j = 0 ; j < 2 ; j++ ){
                    meansArray[i][j] = finalResult.get(c);
                    c++;
                }
            }

            //Sorting array that have means
            Arrays.sort(meansArray, Comparator.comparingDouble(o -> o[0]));
            
            //If length of cols is > 1 will print array that have means & printing array that have means
            if (meansArray[0].length > 1 ){

                //formatting output
                System.out.print("[");

                for(int i = 0 ; i < (meansArray.length - 1 ) ; i++){
                    System.out.print("(" + meansArray[i][0] + ", " + meansArray[i][1] + ")");
                    System.out.print(", ");
                }

                System.out.print("(" + meansArray[meansArray.length - 1][0] + ", " + meansArray[meansArray.length - 1][1] + ")");
                System.out.println("]");
                
            }
        
            //If user input or with one Element
            else {
                System.out.println("[]");
            }

        }
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
        // printingArrayInt(sparseArray);

        //Calling trave Function
        traverseArr(sparseArray, thresh);

    }

    public static void main(String[] args) {

        // int[][] arr =  {{1,1,0,0,0,0,1,1},
        //                 {1,0,1,0,0,0,1,1},
        //                 {0,1,1,0,0,0,0,0},
        //                 {0,0,1,0,0,0,0,1},
        //                 {0,0,0,0,0,1,1,1},
        //                 {0,0,0,0,0,1,1,0}} ;
        // int[][] arr =  {{0,0,0,0},
        //                 {0,0,0,1},
        //                 {0,1,1,1},
        //                 {0,1,1,0}};
        // int[][] arr =  {{1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1}, 
        //                 {1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1}, 
        //                 {1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0}, 
        //                 {0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1}, 
        //                 {1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0}, 
        //                 {1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0}, 
        //                 {0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1}, 
        //                 {1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1}, 
        //                 {1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
        //                 {1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        //                 {0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
        //                 {1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1},
        //                 {1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
        //                 {0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
        //                 {1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1}};
        // int[][] arr = {}; 
         /* Implement main method to parse the input from stdin and print output to stdout */
        //Create object from scanning class
        Scanner input = new Scanner(System.in) ;
        
        /*********************************COLS & ROWS**********************************************/
        try{
            //taking cols and rows of 2d array from user
            String[] Cols_Rows = input.nextLine().split(", ");
            //Assign cols and rows from user
            int rows = Integer.parseInt(Cols_Rows[0]);
            int cols = Integer.parseInt(Cols_Rows[1]);

            if (rows > 0 && cols > 0 ){
                
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
            IPlayersFinder(info, idTeam , thresh);

            }

            else{
                System.out.println("[]");
            }

        }

        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("[]");
        }

        catch(Exception e){
            System.out.println("[]");
        }

    }

}
