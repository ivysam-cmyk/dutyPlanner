import java.util.*;

public class Main {
    private int[][] calendar;
    Dictionary<Integer, Integer> pointToDaysDict= new Hashtable<>();
    Dictionary<Integer, Integer> pointToPersonDict= new Hashtable<>();
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
    public Double pointsPerPerson() {
        System.out.print("How many people? ");
        Scanner s = new Scanner(System.in);
        int numberMedics = s.nextInt();
        s.close();
        //go through the dictionary
        Enumeration<Integer> p = pointToDaysDict.keys();
        Double d = null;
        while (p.hasMoreElements()) {
            int key = p.nextElement();
            totalPoints += pointToDaysDict.get(key);
        }
        System.out.println("The total value: " + totalPoints);
        d = ((double)totalPoints / (double)numberMedics);
        //if d is not a whole number,
        for(int i=0;i<numberMedics;i++){
            //create the dict from 1->n
            pointToPersonDict.put(i+1,totalPoints/numberMedics);
        }
        //if totalPoints not divisible by numberMedics
        if (d%10 != 0){
            System.out.println("Some will have to do more duty points than others");
            int excessPoints = totalPoints%10;
            //get excessPoints number of medics
            for(int i=0; i<excessPoints; i++){
                //get random keys from 1 -> n
                int key = (int) (Math.random() *numberMedics) +1;
                //and add 1 point to each of their existing points
                //only add if wasnt added before
                if (pointToPersonDict.get(key) == totalPoints/numberMedics) {
                    pointToPersonDict.put(key, (pointToPersonDict.get(key) + 1));
                }
                //if it goes to add the points already added, restart the for loop
                else{
                    i--;
                }

            }
        }
        System.out.println("The dict linking points to medics: "+pointToPersonDict);
        System.out.println("Average number of points: "+ d);
        return d;
    }


    public static void main(String[] args){
        Main newMonth = new Main();
        newMonth.daySequence();
        newMonth.printCalendar(newMonth.getCalendar());
        newMonth.pointsToDays(newMonth.getCalendar());
        System.out.println(newMonth.pointsPerPerson());
    }
}
