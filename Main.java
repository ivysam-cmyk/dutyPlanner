import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void daySequence() {
        System.out.println("How many days are there in the month?");
        Scanner s = new Scanner(System.in);
        int daysInMonth = s.nextInt();
        ArrayList<Integer> dateList = new ArrayList<Integer>();
        for (int i = 0; i < daysInMonth; i++) {
            dateList.add(i + 1);
        }

        //options of storing data: 2d array
        int[][] calendar;
        if (daysInMonth > 28) {
            calendar = new int[5][7];
        } else {
            calendar = new int[4][7];
        }
        System.out.println("The length of the calender is " + calendar.length);
        System.out.println("The length of the calender week is " + calendar[0].length);

        int firstDateDay;
        do {
            System.out.print("Which day number does the 1st fall on? eg. Monday=0,Tuesday=1... ");
            firstDateDay = s.nextInt();
        } while (firstDateDay > 7 && firstDateDay<0);

        for (int i = 0; i < calendar.length; i++) {
            for (int j = 0; j < calendar[0].length; j++) {
                if (i == 0 && j >= firstDateDay) {
                    //start from the firstDateDay
                    //print from 1-30 inside the 2d list
                    calendar[i][j] = dateList.remove(0);
                } else if (i > 0) {
                    //after the first row of the calendar, fill as per normal
                    calendar[i][j] = dateList.remove(0);
                }
            }

        }
        for (int[] row : calendar) {
            System.out.println(Arrays.toString(row));
        }
    }
    public static void main(String[] args){
       daySequence();
    }
}
