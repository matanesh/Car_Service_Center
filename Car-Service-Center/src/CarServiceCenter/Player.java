// Students: Matan Eshel,Gil Ya'akov, Ron Gandelman, Eliyahu Latin
// Project Name: Task2
// Date: 29/04/2021
// Group number: 17
package CarServiceCenter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Player {
	
	    // ArrayList declaration
		protected static ArrayList<Playable> mediaPlayer;
		
		// Default Constructor
		protected Player() {
			mediaPlayer = new ArrayList<Playable>(); // Create an ArrayList object
		}
		// Add media function
		protected void addMedia() throws InputMismatchException,NumberFormatException{
			String name = JOptionPane.showInputDialog("Enter name:"); // Read user input
			int Length = Integer.parseInt(JOptionPane.showInputDialog("Enter Length:")); // Read user input
			String option = JOptionPane.showInputDialog("Enter option: 1. Song 2. VideoClip"); // Read user input
			switch (option) {
			  case "1":
		               Song s = new Song(name,Length);
		               mediaPlayer.add(s);
			           break;
			  case "2":
				       VideoClip c = new VideoClip(name,Length);
	                   mediaPlayer.add(c);
			           break;
			  default:
				       JOptionPane.showMessageDialog(null,"No such option","ERROR",JOptionPane.PLAIN_MESSAGE);
			}
		}
		// Play all function
		protected String playAll(){
			String temp = "";
			Iterator<Playable> iter = mediaPlayer.iterator();
		    while (iter.hasNext()) {
		    	Playable m = iter.next();
		    	temp += m.play();
		    }
		    return temp;
		}
		// Play by name function
				protected String playName() throws InputMismatchException,NumberFormatException{
					String name = JOptionPane.showInputDialog("Enter name:"); // Read user input
					String temp = "";
					Iterator<Playable> iter = mediaPlayer.iterator();
				    while (iter.hasNext()) {
				    	Playable m = iter.next();
				    	if(m.getName().equals(name)) {
				    	   temp += m.play();
				    	   return temp;
				    	}
				    }
				    JOptionPane.showMessageDialog(null,"No such media","ERROR",JOptionPane.PLAIN_MESSAGE);
				    return temp;
				}
	    // User menu
		protected void menu() {
			while(true) {
				switch (CarServiceCenter.menu.waitForOption(this.printMenu())) {
				        case "1":
				        	     try {
							          this.addMedia();
							     }catch(Exception e) {
							      JOptionPane.showMessageDialog(null,"InValid input","ERROR",JOptionPane.PLAIN_MESSAGE);
							  }
					             break;
					    case "2":
					    	     try {
					    	    	 JOptionPane.showMessageDialog(null,this.playName(),"Play By Name",JOptionPane.PLAIN_MESSAGE);
						         }catch(Exception e) {
						          JOptionPane.showMessageDialog(null,"InValid input","ERROR",JOptionPane.PLAIN_MESSAGE);
						      }
					             break;
					    case "3":
					    	JOptionPane.showMessageDialog(null,this.playAll(),"Player",JOptionPane.PLAIN_MESSAGE);
					             break;
					    case "4":
						         return;
					     default:
					    	     JOptionPane.showMessageDialog(null,"No such option","ERROR",JOptionPane.PLAIN_MESSAGE);
					    }
			    }
		}
		// Printing the menu
		protected String printMenu() {
			String str1,str2,str3,str4;
			str1 = "1. Add Media\n";
			str2 = "2. Play Media by name\n";
			str3 = "3. Play all\n";
			str4 = "4. Exit\n";
			return str1+str2+str3+str4;
		}
}
