package com.arana.ds;
/* Date.java */

class Date {

  /* Put your private data fields here. */
  private int month;
  private int day;
  private int year;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
  	if(!Date.isValidDate(month,day,year)){
  		System.out.println("Not a valid date");
  		System.exit(0);
  	} else{
  		this.month = month;
	  	this.day = day;
	  	this.year = year; 		
  	}  	
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
  	this.month = Integer.parseInt(s.substring(0,s.indexOf("/")));
  	this.day = Integer.parseInt(s.substring(s.indexOf("/") + 1,s.lastIndexOf("/")));
  	this.year = Integer.parseInt(s.substring(s.lastIndexOf("/") + 1,s.length()));
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
   	return (((year % 4)== 0)&&!((year % 100) == 0)&&((year % 400) == 0))||(((year % 4)== 0)&&!((year % 100) == 0)&&!((year % 400) == 0));                        // replace this line with your solution
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
	int days;
	switch(month){
		case 2:
			if(isLeapYear(year)){
				days = 29;
			} else {
				days = 28;
			}
			break;
		case 4: 
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		default:
			days = 31;
			break;
	}
	return days;		
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
  	return daysInMonth(month, year) >= day;                       
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are printed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
  	String texto;
  	texto = new String(month + "/" + day + "/" +year);
    return texto;                     
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
  	return this.year < d.year || (this.year == d.year && this.month <= d.month && this.day < d.day);
  	}
    /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
  	return !isBefore(d) && !(this.year == d.year && this.month == d.month && this.day == d.day); 
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
  h *  is the nth day of its year.  (366 is only used for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
  	int i = 1;
  	int day = 0;
  	for(i = 1; i < this.month; i++){
  		day = day + Date.daysInMonth(i,this.year);
  	}
  	day = day + this.day;
    return day;                     
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/1997 and d is 12/14/1997, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
  	
  	int yrdiff = 0;
  	int diff = 0;
  	int firstyr,lastyr;
  	
  	// Checks if the dates are equal  	
  	if(this.year == d.year && this.month == d.month && this.day == d.day){
  		return diff;
  	}
  	
  	// Determines which year is the minimun 
  	if(this.year == d.year){
  		return yrdiff;
  	}
  	
  	if(this.year < d.year){
  		firstyr = this.year;
  		lastyr = d.year;
  	} else {
  		firstyr = d.year;
  		lastyr = this.year;
  	}
  	
  	yrdiff = lastyr - firstyr;
  	
  	if(this.isBefore(d)){
  		diff = 365*yrdiff + (Date.numLeapYears(this,d)) + d.dayInYear() - this.dayInYear();
  		return -diff;
  	} else if(this.isAfter(d)){
  		diff = 365*yrdiff + (Date.numLeapYears(this,d)) - d.dayInYear() + this.dayInYear();
  		return diff;
  	} else {
  		return diff;
  	}  	  	
  }
  
  /** Determines the number of leap years between two Dates. 
   */
  public static int numLeapYears(Date date1, Date date2){
  	
  	int i = 0;
  	int leapYears = 0;
  	int firstyr,lastyr;
  	
  	if(date1.year == date2.year){
  		return leapYears;
  	}
  	
  	if(date1.year < date2.year){
  		firstyr = date1.year;
  		lastyr = date2.year;
  	} else {
  		firstyr = date2.year;
  		lastyr = date1.year;
  	}
  	
  	for(i = firstyr; i< lastyr; i++){
  		if(Date.isLeapYear(i)){
  			leapYears++;
  		}
  	}
  	return leapYears;
  }
  
  public static int numNonLeapYears(Date date1, Date date2){
  	
  	int nonleapYears = 0;
  	int firstyr,lastyr;
  	
  	if(date1.year == date2.year){
  		return nonleapYears;
  	}
  	
  	if(date1.year < date2.year){
  		firstyr = date1.year;
  		lastyr = date2.year;
  	} else {
  		firstyr = date2.year;
  		lastyr = date1.year;
  	}
  	
  	return lastyr - firstyr - Date.numLeapYears(date1, date2);  	
  }

  public static void main(String[] argv) {
  	
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);
    
    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);
	
	Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */
    
    System.out.println("\nTesting before and after.");
    
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));
    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));
    
    // Testing Difference
    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + d2.difference(d1));    
    System.out.println(d3 + " - " + d1  + " should be 2: " + d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + d5.difference(d4));    
  }
}
