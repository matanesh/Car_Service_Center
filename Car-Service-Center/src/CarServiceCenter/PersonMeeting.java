// Students: Matan Eshel,Gil Ya'akov, Ron Gandelman, Eliyahu Latin
// Project Name: Task2
// Date: 29/04/2021
// Group number: 17
package CarServiceCenter;

import java.util.Date;

public class PersonMeeting extends Meeting {
	
	private String name, phoneNumber;
	
	    // Default Constructor
        protected PersonMeeting() {
			super();
			name = "Ploni";
			phoneNumber = "0000000000";
		}
		// Constructor
        protected PersonMeeting(Date date, int Length, Date end, String name, String phoneNumber) {
			super(date,Length, end);
			this.name = name;
			this.phoneNumber = phoneNumber;
		}
		@Override
		public String toString() {
		    return super.toString() + " " + name + " " + phoneNumber;
		}
		// Return name function
		protected String getName() {
			return this.name;
		}
		// Return phoneNumber function
		protected String getPhoneNumber() {
			return this.phoneNumber;
		}
		// Set name function
	    protected void setName(String s) {
			this.name = s;
		}
	    // Set phoneNumber function
	    protected void setPhoneNumber(String s) {
			this.phoneNumber = s;
		}
}
