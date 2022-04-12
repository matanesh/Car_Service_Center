// Students: Matan Eshel,Gil Ya'akov, Ron Gandelman, Eliyahu Latin
// Project Name: Task2
// Date: 29/04/2021
// Group number: 17
package CarServiceCenter;

import  java.util.Date; // import the Date class
import java.util.Objects;
import java.text.DateFormat; // import the Date Format class
import java.text.SimpleDateFormat; // import the Date Format class

public abstract class Meeting implements Comparable<Meeting> {
	
	private Date date;
	private Date end;
	private int Length;
	
	// Default Constructor
	protected Meeting() {
		date = new Date();
		end = new Date();
		Length = 0;
	}
	// Constructor
	protected Meeting(Date date,int Length, Date end) {
		this.date = date;
		this.Length = Length;
		this.end = end;
	}
	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    return df.format(date) + " " + Length;
	}
	// Equals override
	@Override
	public boolean equals(Object other) {
		// Self check
		if (this == other)
		    return true;
		// Null check
		if (other == null)
		    return false;
		// Type check and cast
		if (getClass() != other.getClass())
		    return false;
		    Meeting m = (Meeting) other;
		    // Field comparison
		    return Objects.equals(date, m.date) && Objects.equals(end, m.end) && Objects.equals(Length, m.Length);
		}
	// Return Date function
	protected Date getDate() {
		return this.date;
	}
	// Return end Date function
		protected Date getEnd() {
			return this.end;
		}
	// Return Length function
	protected int getLength() {
		return this.Length;
	}
	@Override
	public int compareTo(Meeting o) {
		return date.compareTo(o.date);
	}
}
