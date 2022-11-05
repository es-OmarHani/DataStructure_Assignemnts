import java.util.ArrayList;

public class Testtt {
    public static void main(String[] args){
        ArrayList <Integer[]> list = new ArrayList<>();

        Integer[] arr = {1,2};
        Integer[] arr2 = {1,3};
        list.add(arr);

        // for(Integer[] var : list){
        //     System.out.println(var[0]);
        // }

        System.out.println(list.get(0)[0]); 

        // for(Integer[] var : list){
        //     //check if point is there isn't inside the array then will put it 
        //     System.out.print("[" + var[0] + " , " + var[1] + "]  ");
        // }

        if (list.contains(arr2) == false)
            System.out.println("yes");
        else
            System.out.println("no");    

    }
}
