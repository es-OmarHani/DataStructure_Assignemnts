import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Test {

    private static ArrayList<Integer[]> saved = new ArrayList<>();
    private static ArrayList<Integer> finalResult = new ArrayList<>();
    // private static int[][] savedData = new int[50][2];
    private static int counter = 1 , count = -1 , counting = 0 ;
    private static int counterChain = 0;
    private static boolean flag = true ;
    // private static int count = 0;

    public static void thershDetection(int count , int threshValue ) {

        //variables min and max y
        // int minY = saved.get(0)[0] , maxY = saved.get(0)[0] , minX = saved.get(0)[1] , maxX = saved.get(0)[1] ;
        int minY = 0 , maxY = 0 , minX = 0 , maxX = 0 ;
        System.out.println();
        // System.out.println(Arrays.deepToString(savedData));
        //counter for valid points
        // for(int i = 0 ; i < savedData.length ; i++){
        //     if(  i != 0 && savedData[i][0] == 0  && savedData[i][1] == 0  ){
        //         Test.counting ++;
        //     }
        // }
        // counting = 50 - counting ;
        // System.out.println("counting = " + counting);
        
        //Check if all cells is greater than thresholdValue
        if ((4 * counter) >= threshValue){
            
            //show array for me
            // System.out.println(Arrays.deepToString(savedData));
            
            //Now will get min y of chain and max y 
            for (int i = 0 ; i < saved.size() ; i++){
                //get minY
                if (saved.get(i)[0] < minY ){
                    minY = saved.get(i)[1];
                }
                
                // get minX
                if (saved.get(i)[1] < minX )
                    minX = saved.get(i)[1];    
                
                //get maxY
                if(saved.get(i)[0] > maxY ){
                    maxY = saved.get(i)[0];
                }    

                //get maxX
                if(saved.get(i)[1] > maxX)
                    maxX = saved.get(i)[1];
            }

            System.out.println("Min y = " + minY + " && Max y => " + maxY);
            System.out.println("Min X => " + minX + "&& Max X => " + maxX);

            //Updated Value of minY and maxY
            minY = minY + (minY+1) ;
            maxY = maxY + 1 + (maxY+1) ;

            //Updated Value of minX and maxX
            minX = minX + (minX+1) ;
            maxX = maxX + 1 + (maxX+1) ;

            System.out.println("Y => " + minY + " : " + maxY);
            System.out.println("X => " + minX + " : " + maxX);
            
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
        
        //looping throw array
        if (count >= 0 ){
            for(Integer[] var : saved){
                //check if point is there isn't inside the array then will put it 
                System.out.print("[" + var[0] + " , " + var[1] + "]  ");
            }
        }
        // && (arr[new_i][new_j+1] == 0 || arr[new_i][new_j+1] == 2 ) && (arr[new_i+1][new_j] == 0 || arr[new_i+1][new_j] == 2 ) && ( arr[new_i][new_j-1] == 0 || arr[new_i][new_j-1] == 2 ) && (arr[new_i-1][new_j] == 0 || arr[new_i-1][new_j] == 2) 
        //base case
        try{
            System.out.println("*******************************");
            if(new_i == saved.get(0)[0] && new_j == saved.get(0)[1])
            {
                System.out.println("Doneeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                return arr ;
            }
        }

        catch(Exception e){
            System.out.println("7mmmmmmmmmmmmmmmmar");
        }
    
        //Increment Element array
        if(arr[new_i][new_j] == 1)
            arr[new_i][new_j] ++;
        

        try {
    
            //-- search right --
            if ( new_j < (arr[0].length - 1)  && (arr[new_i][new_j+1] == 1  ) ) {
                System.out.println("...............Right");
                counter ++ ; count++ ;
                
                //looping throw array
                for(Integer[] var : saved){
                    System.out.println("Done....R.........");
                    //check if point is there isn't inside the array then will put it 
                    //Putting new Elements in array
                    Integer[] saved_arr = {new_i , new_j};
                    if (saved.contains(saved_arr) == false){
                        saved.add(saved_arr);

                        for(Integer[] var1 : saved){
                            //check if point is there isn't inside the array then will put it 
                            System.out.print("[" + var1[0] + " , " + var1[1] + "]  ");
                        }
                    }
                }
                //Calling recursion with new location "Right"
                recursiveFind(arr , new_i , new_j+1 , caller_i , caller_j );
            }
    
            //-- search below --
            if( (new_i < (arr.length - 1) ) && (arr[new_i+1][new_j] == 1  ) ){
                System.out.println("...............Below");
                counter ++ ; count++ ;
                
                //looping throw array
                for(Integer[] var : saved){
                    System.out.println("Done.......B......");
                    //check if point is there isn't inside the array then will put it 
                    //Putting new Elements in array
                    Integer[] saved_arr = {new_i , new_j};
                    if (saved.contains(saved_arr) == false){
                        saved.add(saved_arr);
                    }
                }
                
                for(Integer[] var1 : saved){
                    //check if point is there isn't inside the array then will put it 
                    System.out.print("[" + var1[0] + " , " + var1[1] + "]  ");
                }

                //Calling recursion with new location "Below"
                recursiveFind(arr, new_i + 1, new_j , caller_i , caller_j );
            }

            //-- search left --
            if((new_j > 0) && (arr[new_i][new_j-1] == 1  ) ){
                System.out.println("Done.....L.......");
                System.out.println("...............Left");
                counter ++ ; count++ ;
                
                
                //looping throw array
                for(Integer[] var : saved){
                    //check if point is there isn't inside the array then will put it 
                    //Putting new Elements in array
                    Integer[] saved_arr = {new_i , new_j};
                    if (saved.contains(saved_arr) == false){
                        saved.add(saved_arr);
                    }

                    for(Integer[] var1 : saved){
                        //check if point is there isn't inside the array then will put it 
                        System.out.print("[" + var1[0] + " , " + var1[1] + "]  ");
                    }
                }
                //Calling recursion with new location "Left"
                recursiveFind(arr, new_i, new_j-1 , caller_i , caller_j);
            }
    
            //-- search up --
            if((new_i > 0) && (arr[new_i-1][new_j] == 1  ) ){ 
                System.out.println("Done......U......");
                System.out.println("U");
                counter ++ ; count++ ;
                
                //looping throw array
                for(Integer[] var : saved){
                    //check if point is there isn't inside the array then will put it 
                    //Putting new Elements in array
                    Integer[] saved_arr = {new_i , new_j};
                    if (saved.contains(saved_arr) == false){
                        saved.add(saved_arr);
                    }

                    for(Integer[] var1 : saved){
                        //check if point is there isn't inside the array then will put it 
                        System.out.print("[" + var1[0] + " , " + var1[1] + "]  ");
                    }
                }
                
                //Calling recursion with new location "Up"
                recursiveFind(arr, new_i-1, new_j , caller_i , caller_j );
            }
    
            //Return to bast element if not found any 1 for new element
            else {

                System.out.println("..............Back");

                //Putting new Elements in array
                Integer[] saved_arr = {new_i , new_j};
                
                
                if (saved.contains(saved_arr) == false){
                    System.out.println("Done..............");
                    //looping throw array
                    for(Integer[] var : saved){
                        //check if point is there isn't inside the array then will put it 
                        if (saved.contains(saved_arr) == false){
                            saved.add(saved_arr);
                        }

                        for(Integer[] var1 : saved){
                            //check if point is there isn't inside the array then will put it 
                            System.out.print("[" + var1[0] + " , " + var1[1] + "]  ");
                        }
                    }

                    //Out on screen
                    System.out.println("New => " +" "+ new_i + " " + new_j);

                     //looping throw array
                    for(Integer[] var : saved){
                        //check if point is there isn't inside the array then will put it 
                        System.out.print("[" + var[0] + " , " + var[1] + "]  ");
                    }

                    //Get row & col of Element before current Element
                    int row = saved.get(count-1)[0];
                    int col = saved.get(count-1)[1];
                    count -- ;

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
    
    
    public static void traverseArr(int[][]arr){

        int rows = arr.length;
        int cols = arr[0].length ;
        int[][] result = new int[arr.length][arr[0].length];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(arr[i][j]==1){
                    result = recursiveFind(arr, i, j , i, j );
                    // System.out.println(Arrays.deepToString(savedData));
                    
                    //looping throw array
                    for(Integer[] var : saved){
                        //check if point is there isn't inside the array then will put it 
                        System.out.print("[" + var[0] + " , " + var[1] + "]  ");
                        }
                    
                    Test.thershDetection(counter , 0);
                    
                    //Initialization all variables && make all counters as default
                    counter = 1  ; count = -1; flag = true ; counting = 0;
                    //clear array
                    // for(int count1 = 0 ; count1 < savedData.length ; count1 ++){
                    //     int[] clear = {0,0};
                    //     savedData[count1] = clear;
                    // }
                    //clear saved 
                    saved.clear();
                }
            }
        }

        //print result array
        // System.out.println(Arrays.deepToString(result));
        int[][] meansArray = new int[counterChain][2];
        int c = 0;
        System.out.println("CounterChain = " + counterChain);
        
        // Loop throw list to extract means then put them into 2d array
        for(int i = 0 ; i < counterChain  ; i++){
            for(int j = 0 ; j < 2 ; j++ ){
                meansArray[i][j] = finalResult.get(c);
                c++;
            }
        }

        //printing array that have means
        System.out.println();
        System.out.println("***********************************");
        Arrays.sort(meansArray, Comparator.comparingDouble(o -> o[0]));
        System.out.println(Arrays.deepToString(meansArray));
        // for(int i = 0 ; i < finalResult.size() ; i++){
        //     System.out.print (finalResult.get(i) + " ");
        // }

    }

    public static void main(String[] args) {

        // int[][] arr =  {{1,1,0,0,0,0,1,1},
        //                 {1,0,1,0,0,0,1,1},
        //                 {0,1,1,0,0,0,0,0},
        //                 {0,0,1,0,0,0,0,1},
        //                 {0,0,0,0,0,1,1,1},
        //                 {0,0,0,0,0,1,1,0}} ;
        int[][] arr =  {{0,0,0,0},
                        {0,0,0,1},
                        {0,1,1,1},
                        {0,1,1,0}};
        // int[][] arr =  {{1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1}, 
        //                 {1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1}, 
        //                 {1, 1, 1, 0, 0, 2, 0, 0, 1, 1, 0, 0, 1, 0, 0}, 
        //                 {0, 0, 0, 2, 2, 2, 0, 1, 0, 0, 1, 0, 0, 0, 1}, 
        //                 {1, 1, 1, 0, 0, 2, 2, 0, 1, 0, 0, 1, 1, 0, 0}, 
        //                 {1, 1, 0, 0, 0, 2, 2, 0, 1, 1, 0, 0, 0, 1, 0}, 
        //                 {0, 0, 1, 1, 0, 0, 2, 0, 1, 0, 0, 1, 1, 0, 1}, 
        //                 {1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1}, 
        //                 {1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
        //                 {1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        //                 {0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
        //                 {1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1},
        //                 {1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
        //                 {0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
        //                 {1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1}};

        traverseArr(arr);

        
    }
}
