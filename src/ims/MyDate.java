package ims;

import java.util.Calendar;

public class MyDate {
    public static void main(String[] args) {
      Calendar c = Calendar.getInstance(); // c hold the current OS Calendar. 
      System.out.printf("Date is - %02d / %02d / %d\n",c.get(c.DATE),c.get(c.MONTH)+1,c.get(c.YEAR));
      System.out.printf("Time is - %02d : %02d : %02d\n",c.get(c.HOUR_OF_DAY),c.get(c.MINUTE),c.get(c.SECOND));
        System.out.println("Week of the year - "+c.get(c.WEEK_OF_YEAR));
        System.out.println("Week of the month - "+c.get(c.WEEK_OF_MONTH));
        
    }
   
}
