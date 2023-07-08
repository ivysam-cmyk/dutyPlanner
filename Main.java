import java.util.*;

public class Main {
    private int[][] calendar;
    Dictionary<Integer, Integer> pointToDaysDict= new Hashtable<>();
    Dictionary<Integer, Integer> pointToPersonDict= new Hashtable<>();
    Dictionary<Integer, ArrayList<Integer>> personToDaysDict= new Hashtable<>();
    public int totalPoints = 0;
    int daysInMonth=0;
    public int[][] getCalendar(){
        //ask the user the structure of the calendar
        return calendar;
    }
    public int[][] daySequence() {
        System.out.println("How many days are there in the month?");
        Scanner s = new Scanner(System.in);
        daysInMonth = s.nextInt();
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
        //loop through each day in the calendar, for index [x][5] and [x][6] the points are double
        //create a dictionary
        for (int i = 0; i < calendar.length; i++) {
            for (int j = 0; j < calendar[0].length; j++) {
                if (j==5 || j==6){
                   pointToDaysDict.put(calendar[i][j],4);
                } else{
                    pointToDaysDict.put(calendar[i][j],2);
                }
            }
        }
        pointToDaysDict.remove(0);
        System.out.println(pointToDaysDict);
    }
    public void pointsPerPerson() {
        System.out.print("How many people? ");
        Scanner s = new Scanner(System.in);
        int numberMedics = s.nextInt();
        s.close();
        //go through the dictionary
        Enumeration<Integer> p = pointToDaysDict.keys();
        //calculate the total number of points in a month
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
            int excessPoints = totalPoints%10;
            System.out.println("Most have to do " + totalPoints/numberMedics+" points, while " + excessPoints +" others have to do 1 extra point.");
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
    }
    public Dictionary<Integer, ArrayList<Integer>> assignDuty(){
       //using pointToPersonDict...
        //create a list of all the medics from the dictionary
        ArrayList<Integer> personList = Collections.list(pointToPersonDict.keys());
        for(int i=0; i<calendar.length; i++){
            for (int j =0; j<calendar[0].length; j++){
                //get the list of 2 medics
                for(int i=0; i<2;i++){
                    ArrayList<Integer> listof2medics = new ArrayList<Integer>();

                    //get 2 unique medics
                    if(i==1) {
                        int medicChoice;
                        do {
                            medicChoice = (int) (Math.random() * personList.size()) + 0;
//                            find another medic, if the medic is the same, then find again.
                        } while (medicChoice == listof2medics.get(0));
                        listof2medics.add(medicChoice);
                    } else {
                        listof2medics.add(personList.get((int) (Math.random()*personList.size())+ 0));
                    }
                    //insert key(date), value(list of 2 medics) inside the personToDaysDict
                    // this is to be done everyday of the month
                    personToDaysDict.put(calendar[i][j], listof2medics);
                }
            }
        }
        //check that there are no consecutive day same medics
        return personToDaysDict;
    }

    public void finalDictChecker(){
       //go through personToDaysDict, make sure that there are no consecutive
        ArrayList<Integer> finalDictKeysArray = Collections.list(personToDaysDict.keys());
        for(int i = 0; i< daysInMonth-1; i++ ){
            ArrayList<Integer> eachDayArray = personToDaysDict.get(finalDictKeysArray.get(i));
            ArrayList<Integer> eachDayArray2 = personToDaysDict.get(finalDictKeysArray.get(i+1));
            //check the consecutive days
            for(int j=0;j<2;j++){
                if (eachDayArray.get(j)== eachDayArray2.get(0) || eachDayArray.get(j)== eachDayArray2.get(1))
            }
        }
    }
    public static void main(String[] args){
        Main newMonth = new Main();
        newMonth.daySequence();
        newMonth.printCalendar(newMonth.getCalendar());
        newMonth.pointsToDays(newMonth.getCalendar());
        newMonth.pointsPerPerson();

        System.out.println((int) Math.random() * 10);

    }
}
