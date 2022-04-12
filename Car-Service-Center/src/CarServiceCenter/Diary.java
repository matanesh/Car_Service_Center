// Students: Matan Eshel,Gil Ya'akov, Ron Gandelman, Eliyahu Latin
// Project Name: Task2
// Date: 29/04/2021
// Group number: 17
package CarServiceCenter;
import java.util.ArrayList; // import the ArrayList class
import java.util.Calendar; // import the Calendar class
import java.util.Collections;
import  java.util.Date; // import the Date class
import java.util.InputMismatchException;
import java.util.Iterator; // import the Iterator class
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

public class Diary {

	protected CopyOnWriteArrayList<CopyOnWriteArrayList<Meeting>> d;
	
	// Default Constructor
	protected Diary() {
		d = new CopyOnWriteArrayList<CopyOnWriteArrayList<Meeting>>();
		for(int i = 0; i<30; i++) {
		    CopyOnWriteArrayList<Meeting> e = new CopyOnWriteArrayList<Meeting>(); 
		    d.add(e);
		}
	}
	// Function for adding a meeting or event
	protected void addMeeting() throws InputMismatchException,NumberFormatException{
		Calendar c = Calendar.getInstance();
		int day = Integer.parseInt(JOptionPane.showInputDialog("Enter day (1-30):")); // Read user input
	    if(day < 1 || day > 30) { // Input integrity check
	    	JOptionPane.showMessageDialog(null,"No such day","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	return;
	    }
	    int hour = Integer.parseInt(JOptionPane.showInputDialog("Enter Hour (00-23):")); // Read user input
	    if(hour < 00 || hour > 23) { // Input integrity check
	    	JOptionPane.showMessageDialog(null,"No such hour","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	return;
	    }
	    int minute = Integer.parseInt(JOptionPane.showInputDialog("Enter Minute (00-59):")); // Read user input
	    if(minute < 00 || minute > 59) { // Input integrity check
	    	JOptionPane.showMessageDialog(null,"No such minute","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	return;
	    }
	    c.set(2021, 05, day, hour, minute, 00);
	    Date date = c.getTime(); // Initialize date
	    int length = Integer.parseInt(JOptionPane.showInputDialog("Enter Length (1-60):")); // Read user input
	    if(length < 1 || length > 60) { // Input integrity check
	    	JOptionPane.showMessageDialog(null,"No such length","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	return;
	    }
	    c.add(Calendar.MINUTE, length);
	    Date end = c.getTime();
	    String option = JOptionPane.showInputDialog("Enter option: 1. Event 2. Meeting with person"); // Read user input
		switch (option) {
		  case "1":
			       String description = JOptionPane.showInputDialog("Enter Description:"); // Read user input
	               Event e = new Event(date, length, end, description);
	               d.get(day-1).add(e);
		           break;
		  case "2":
			       String name = JOptionPane.showInputDialog("Enter name:"); // Read user input
		           if(!MobilePhone.pb.existPerson(name)) {
		        	  JOptionPane.showMessageDialog(null,"The contact doesn't exist","ERROR",JOptionPane.PLAIN_MESSAGE);
		    	      return;
		              }
		           int index = MobilePhone.pb.searchPerson2(name);
		           String phoneNumber = MobilePhone.pb.PersonsPhoneBook.get(index).getphoneNumber();
		           PersonMeeting pm = new PersonMeeting(date, length, end, name ,phoneNumber);
		           d.get(day-1).add(pm);
		           break;
		  default:
			       JOptionPane.showMessageDialog(null,"No such option","ERROR",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	// Function for delete a meeting or event
	protected void deleteMeeting() {
		int day = Integer.parseInt(JOptionPane.showInputDialog("Enter day (1-30) of the event you want to delete:")); // Read user input
	    if(day < 1 || day > 30) { // Input integrity check
	    	JOptionPane.showMessageDialog(null,"No such day","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	return;
	    }
	    int number = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of event you want to delete:")); // Read user input
	    day = day - 1;
	    number = number - 1;
	    try {
	    	  //  Block of code to try
	    	  this.d.get(day).remove(number);
	    	}
	    	catch(Exception e) {
	    	  //  Block of code to handle errors
	    	  JOptionPane.showMessageDialog(null,"No such event","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	}
	}
	// Function for delete all the meetings of a person (very very clever function)
	protected void deleteMeetingPerson(String name) {
		    Iterator<CopyOnWriteArrayList<Meeting>> iter0 = d.iterator();
		    while (iter0.hasNext()) {
		    	CopyOnWriteArrayList<Meeting> temp = iter0.next();
				if(!temp.isEmpty()) {
					Iterator<Meeting> iter = temp.iterator();
					while (iter.hasNext()) {
						Meeting m = iter.next();
						if(m instanceof PersonMeeting) {
							// Down casting
							PersonMeeting pm = (PersonMeeting)m;
							if(pm.getName().equals(name)) {
							   temp.remove(m);
							}
					    }
				    }
				}
		    }
	}
	// Function for print all the meetings with a person
	protected void printByPerson(String name) {
	    Iterator<CopyOnWriteArrayList<Meeting>> iter0 = d.iterator();
	    while (iter0.hasNext()) {
	    	CopyOnWriteArrayList<Meeting> temp = iter0.next();
			if(!temp.isEmpty()) {
				Iterator<Meeting> iter = temp.iterator();
				while (iter.hasNext()) {
					Meeting m = iter.next();
					if(m instanceof PersonMeeting) {
						// Down casting
						PersonMeeting pm = (PersonMeeting)m;
						if(pm.getName().equals(name)) {
							JOptionPane.showMessageDialog(null,m,"Note",JOptionPane.PLAIN_MESSAGE);
						}
				    }
			    }
			}
	    }
    }
	// Function to sort the diary
	protected void sortDiary() {
	    Iterator<CopyOnWriteArrayList<Meeting>> iter0 = d.iterator();
	    while (iter0.hasNext()) {
	    	CopyOnWriteArrayList<Meeting> temp = iter0.next();
			if(!temp.isEmpty()) {
				Collections.sort(temp);
			}
	    }	
	}
	// Handling collisions (also amazing function)
    protected void collisionsHandling() {
    	Iterator<CopyOnWriteArrayList<Meeting>> iter0 = d.iterator();
	    while (iter0.hasNext()) {
	    	CopyOnWriteArrayList<Meeting> temp = iter0.next();
			if(!temp.isEmpty()) {
				Iterator<Meeting> iter = temp.iterator();
				while (iter.hasNext()) {
					Meeting m = iter.next();
					Iterator<Meeting> iter1 = temp.iterator();
					while (iter1.hasNext()) {
						Meeting check = iter1.next();
						if((m.equals(check) == false) && (m.getEnd().compareTo(check.getDate()) == 1) && (check.getEnd().compareTo(m.getDate()) == 1)) {				
							temp.remove(check);
							iter = temp.iterator();
							iter1 = temp.iterator();
						}
				    }
			    }
			}
	    }
    }
	// toString Override
	@Override
	public String toString(){
		String str = "";
		for(int i = 0; i<30; i++) {
			if(!d.get(i).isEmpty()) {
			    for(Meeting a: d.get(i)) {
					str += a.toString() + "\n";
			        }
			}
		}
		return str;
	}
	// User menu
	protected void menu() {
		while(true) {
			switch (CarServiceCenter.menu.waitForOption(this.printMenu())) {
			  case "1":
				  try {
				    this.addMeeting();
				  }catch(Exception e) {
					  JOptionPane.showMessageDialog(null,"InValid input","ERROR",JOptionPane.PLAIN_MESSAGE);
				  }
				    this.sortDiary();
			        break;
			  case "2":
				    this.deleteMeeting();
			        break;
			  case "3":
				    int day = Integer.parseInt(JOptionPane.showInputDialog("Enter day (1-30) for print:")); // Read user input
				    if(day < 1 || day > 30) { // Input integrity check
				    	JOptionPane.showMessageDialog(null,"No such day","ERROR",JOptionPane.PLAIN_MESSAGE);
				    	break;
				    }
				    day = day - 1;
				    JOptionPane.showMessageDialog(null,d.get(day).toString(),"Note",JOptionPane.PLAIN_MESSAGE); // Print the date
			        break;
			  case "4":
				    String name = JOptionPane.showInputDialog("Enter name:"); // Read user input
				    this.printByPerson(name);
			        break;
			  case "5":
				    this.collisionsHandling();
			        break;
			  case "6":
				    JOptionPane.showMessageDialog(null,d,"Diary",JOptionPane.PLAIN_MESSAGE); // Print the diary
			        break;
			  case "7":
				    return;
			  default:
				    JOptionPane.showMessageDialog(null,"No such option","ERROR",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	// Printing the menu
	protected String printMenu() {
		String str1,str2,str3,str4,str5,str6,str7;
		str1 = "1. Add Event\n";
		str2 = "2. Delete Event\n";
		str3 = "3. Print Date schedule\n";
		str4 = "4. Print Contact schedule\n";
		str5 = "5. Identify if there are colliding events\n";
		str6 = "6. Print diary\n";
		str7 = "7. Exit\n";
		return str1+str2+str3+str4+str5+str6+str7;
	}
}
