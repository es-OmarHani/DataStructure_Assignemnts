package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            //take input from user
            Scanner scan = new Scanner(System.in);
            String arr = scan.nextLine().replaceAll("\\[|\\]", "");
            String [] numbs = arr.split(", ");

            //put the numbers into queue
            ArrayQueue queue = new ArrayQueue();
            if (!arr.isEmpty()) {
                for (int i = numbs.length-1 ; i >= 0 ; i-- )
                    queue.enqueue(Integer.parseInt(numbs[i]));
            }

            //Take methode from user
            String methode = scan.nextLine();

            switch (methode) {
                case "enqueue":
                    int element = scan.nextInt();
                    queue.enqueue(element);
                    queue.display();
                    break;

                case "dequeue":
                    queue.dequeue();
                    queue.display();
                    break;


                case "isEmpty":
                    if(queue.isEmpty())
                        System.out.println("True");
                    else
                        System.out.println("False");

                    break;

                case "size":
                    System.out.println(queue.size());
                    break;


            }

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}



