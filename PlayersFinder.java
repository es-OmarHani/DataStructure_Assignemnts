import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;


// interface IPlayersFinder {
// /**
// * Search for players locations at the given photo
// * @param photo
// * Two dimension array of photo contents
// * Will contain between 1 and 50 elements, inclusive
// * @param team
// * Identifier of the team
// * @param threshold
// * Minimum area for an element
// * Will be between 1 and 10000, inclusive
// * @return
// * Array of players locations of the given team
// */
// java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
// }



// class InputValues{
// /**
// Read input strings from the user and get the required values.
// @attribute rows:
// >>number of elements of String type to be stored in photo.
// @attribute cols:
// >>number of characters in each String element in photo.
// @attribute photo:
// >>two dimentional array of type String.
// >>number of elements between 1-50 (inclusive).
// @attribute team:
// >>team identifier.
// @attribute threshold:
// >>minimum area of an element (player to be detected)
// **/

//   protected int rows;
//   protected int cols;

//   protected String[] photo = new String[rows];

//   protected int team;
//   protected int threshold;

//   public void setValues(int rows, int cols, String[] photo, int team, int threshold){
//     this.rows = rows;
//     this.cols = cols;
//     this.photo = photo;
//     this.team = team;
//     this.threshold = threshold;
//   }
// }



//-------------------------- main class ----------------------------//
class PlayersFinder {

  public static InputValues readInput(){
  /*
  Read the input from the user.
  @return:
  >>an object of the class InputValues storing all the input values.
  */

    //-- instantiate an object of the class Scanner to read the input lines --
    Scanner sc = new Scanner(System.in);

    //-- read rows and cols --
    String sin = sc.nextLine().replaceAll(" ", "");
    String[] dim = sin.split(",");
    int rows = Integer.parseInt(dim[0]);
    //rows must be between 1 and 50 (inclusive)
    int cols = Integer.parseInt(dim[1]);

    //-- read photo --
    String[] photo = new String[rows];
    for(int i=0; i<rows; i++){
      sin = sc.nextLine().replaceAll(" ", "");
      photo[i] = sin;
    }

    //-- read team --
    sin = sc.nextLine().replaceAll(" ", "");
    int team = Integer.parseInt(sin);

    //-- read threshold --
    sin = sc.nextLine().replaceAll(" ", "");
    int threshold = Integer.parseInt(sin);
    //threshold must be between 1 and 10000 (inclusive)

    //-- instantiate an object of the class InputValues to store the input values --
    InputValues input = new InputValues();

    input.setValues(rows, cols, photo, team, threshold);

    return(input);
  }


  public static int[][] recursiveFind(int[][] arr, int new_i, int new_j , int old_i , int old_j , int caller_i , int caller_j  ){
    /*
    >> documentation goes here
    */
    //base case
    if(old_i == caller_i && old_j == caller_j && arr[old_i][old_j+1] != 1 && arr[old_i+1][old_j] != 1 && arr[old_i][old_j-1] != 1 && arr[old_i-1][old_j] != 1)
      return arr ;

    //Increment Element array
    arr[new_i][new_j] ++;

    try {

        //-- search right --
        if(arr[new_i][new_j+1] == 1 && ( new_j != (arr[0].length - 1) ) ){
          PlayersFinder.recursiveFind(arr, new_i , new_j+1 , new_i , new_j , caller_i , caller_j );
        }
        // else {
        //   arr[i][j+1]--;
        // }

        //-- search below --
        else if(arr[new_i+1][new_j] == 1 && ( new_i != (arr.length - 1) ) ){
          PlayersFinder.recursiveFind(arr, new_i + 1, new_j , new_i , new_j , caller_i , caller_j);
        }
        // else {
        //   arr[i+1][j]--;
        // }

        //-- search left --
        else if(arr[new_i][new_j-1]==1 && (new_j!=0) ){
          PlayersFinder.recursiveFind(arr, new_i, new_j-1 , new_i , new_j , caller_i , caller_j);
        }
        // else {
        //   arr[i][j-1]--;
        // }

        //-- search up --
        else if(arr[new_i-1][new_j]==1 && (new_i!=0) ){ 
          PlayersFinder.recursiveFind(arr, new_i-1, new_j , new_i , new_j , caller_i , caller_j);
        }
        // else {
        //   arr[i-1][j]--;
        // }

        //Return to bast element if not found any 1 for new element
        else {
          PlayersFinder.recursiveFind(arr, new_i , new_j , new_i , new_j , caller_i , caller_j);
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
          result = PlayersFinder.recursiveFind(arr, i, j , i, j , i , j);
        }
      }
    }
    //print result array
    System.out.println(Arrays.deepToString(result));

  }

  public java.awt.Point[] findPlayers(String[] photo, int team, int threshold){
    /*
    >> documentation goes here
    */

    int rows = photo.length;
    int cols = photo[0].length();

    //-- define the (rows x cols) binary array
    int[][] arr = new int[rows][cols];

    //-- parse the input string and populate the binary array --
    for(int i=0; i<rows; i++){

      for(int j=0; j<cols; j++){

        char c = photo[i].charAt(j);

        if(Character.isDigit(c)){

          if(Character.getNumericValue (c) == team){
            arr[i][j] = 1;
          }
        }

        else{
          arr[i][j] = 0;
        }
      }
    }

    //print arr for debuging
    System.out.println("arr:");

    for(int i=0; i<arr.length; i++){
      for(int j=0; j<arr[i].length; j++){
        System.out.print(arr[i][j] + " ");
      }
      System.out.println("");
    }


    return(null);
  }


  public static void printOutput(int[][] positions){
    /*
    Print out the positions of the players after softing them
    @parameter positions:
    >>the output 2-dimentional integer array representing the
    positions of the players to be printed.
    *sample argument: [[1, 2], [3, 4]]
    *sample output: [(1, 2), (3, 4)]
    */

    //-- number of positions satisfying the threshold --
    int len = positions.length;
    int temp = 0;

    //-- sort the positions with increasing order of x coordinate --
    for(int i=1; i<len+1; i++){

      for(int j=0; j<(len-i); j++){

        if(positions[j][0] > positions[j+1][0]){
          temp = positions[j+1][0];
          positions[j+1][0] = positions[j][0];
          positions[j][0] = temp;
        }
      }
    }

    //-- sort the position with increasing order of y coordinate --
    for(int i=1; i<len+1; i++){

      for(int j=0; j<(len-i); j++){

        if(positions[j][1] > positions[j+1][1]){
          temp = positions[j+1][1];
          positions[j+1][1] = positions[j][1];
          positions[j][1] = temp;
        }
      }
    }


    //-- print out the positions --
    System.out.print("[");

    for(int i=0; i<(len-1); i++){
      System.out.print("(" + positions[i][0] + ", " + positions[i][1] + ")");
      System.out.print(", ");
    }

    //-- print the last position --
    System.out.print("(" + positions[len-1][0] + ", " + positions[len-1][1] + ")");
    System.out.println("]");
  }



//--------------------------- main function -------------------------//
  public static void main(String[] args) {

    //-- instantiate an object of class InputValues to read the input from the user --
    InputValues input = new InputValues();
    input = readInput();

    //-- check for input restrictions --
    boolean outOfBounds = true;
    if(input.rows<1 || input.rows>50){
      System.out.println("Error");
      outOfBounds = false;
    }
    else if(input.threshold<1 || input.threshold>10000){
      System.out.println("Error");
      outOfBounds = false;
    }

    //-- print input attributes for debugging --
    System.out.println("rows = " + input.rows);
    System.out.println("photo[0] = " + input.photo[0]);
    System.out.println("team = " + input.team);
    System.out.println("threshold = " + input.threshold);


    //sample argument = [[1, 2], [3, 4]]
    //result >> [(1, 2), (3, 4)]
    //-- test case --
    //int[][] positions = {{1, 2}, {3, 4}, {2, 3}, {1, 1}};
    //print out the output of the program
    //PlayersFinder.printOutput(positions);

    //-- define input values --
    String[] photo = input.photo;
    int team = input.team;
    int threshold = input.threshold;
    //-- instantiate an object of class PlayersFinder
    PlayersFinder PlayersFinder = new PlayersFinder();
    //-- call the findPlayers() function for testing
    PlayersFinder.findPlayers(photo, team, threshold);
  }
}
