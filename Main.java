import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public void daySequence(){
        System.out.println("How many days are there in the month?");
        Scanner s = new Scanner(System.in);
        int daysInMonth = s.nextInt();
        //options of storing data: 2d array
        String[][] calendar;
        if(daysInMonth > 28){
            calendar = new String[5][7];
        } else {
            calendar = new String[4][7];
        }

        System.out.println("Which day number does the 1st fall on? eg. Monday=0,Tuesday=1...");
        int firstDateDay = s.nextInt();
        for(int i=0;i< calendar.length; i++){
            for(int j=0;j< calendar[0].length; j++){
                if(i==0 && j==firstDateDay){
                   //print from 1-30
                }
            }
        }
    }
}
