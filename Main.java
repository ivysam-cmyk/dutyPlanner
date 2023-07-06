import java.util.*;

public class Main {
    private int[][] calendar;
    Dictionary<Integer, Integer> pointToDaysDict= new Hashtable<>();
    public int totalPoints = 0;
    public int[][] getCalendar(){
        //ask the user the structure of the calendar
        return calendar;
    }
    public int[][] daySequence() {
        System.out.println("How many days are there in the month?");
        Scanner s = new Scanner(System.in);
        int daysInMonth = s.nextInt();
        ArrayList<Integer> dateList = new ArrayList<Integer>();
        for (int i = 0; i < daysInMonth; i++) {
            dateList.add(i + 1);
        }

        //options of storing data: 2d array
        if (daysInMonth > 28) {
            calendar = new int[5][7];
        } else {
            calendar = new int[4][7];
        }
        System.out.println("The length of the calendar is " + calendar.length);

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
                    if (dateList.size()<1){
                        return calendar;
                    }
                } else if (i > 0) {
                    //after the first row of the calendar, fill as per normal
                    calendar[i][j] = dateList.remove(0);
                    if (dateList.size()<1){
                        return calendar;
                    }
                }
            }
        }
        for (int[] row : calendar) {
            System.out.println(Arrays.toString(row));
        }
        return calendar;
    }
    public void printCalendar(int[][] calendar){
        System.out.println("---The calendar---");
        for(int[] row : calendar){
            System.out.println(Arrays.toString(row));
        }
        System.out.println(" ");
    }
    public void pointsToDays(int[][] calendar){
        //this function assigns points to days
        // and returns a dictionary where key is the date and the points is the value
        //loop through each day in the calendar, for index [x][5] and [x][6] the points are 2
        //create a dictionary
        for (int i = 0; i < calendar.length; i++) {
            for (int j = 0; j < calendar[0].length; j++) {
                if (j==5 || j==6){
                   pointToDaysDict.put(calendar[i][j],2);
                } else{
                    pointToDaysDict.put(calendar[i][j],1);
                }
            }
        }
        pointToDaysDict.remove(0);
        System.out.println(pointToDaysDict);
    }
    public int pointsPerPerson(){
        System.out.print("How many people? ");
        Scanner s = new Scanner(System.in);
        int numberMedics = s.nextInt();
        //go through the dictionary
        Enumeration<Integer> p = pointToDaysDict.keys();
        while(p.hasMoreElements()){
            int key = p.nextElement();
            totalPoints += pointToDaysDict.get(key);
            System.out.println("The key: "+ key+ " The accum value: " + totalPoints);//works till here
        }
        return Double(totalPoints/numberMedics);
    }

    public static void main(String[] args){
        Main newMonth = new Main();
        newMonth.daySequence();
        newMonth.printCalendar(newMonth.getCalendar());
        newMonth.pointsToDays(newMonth.getCalendar());
        System.out.println(newMonth.pointsPerPerson());
    }
}
