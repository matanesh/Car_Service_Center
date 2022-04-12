// Students: Matan Eshel,Gil Ya'akov, Ron Gandelman, Eliyahu Latin
// Project Name: Task2
// Date: 29/04/2021
// Group number: 17

package CarServiceCenter;

import java.util.Scanner; // Import the Scanner class to read text files
import javax.swing.*;

public class MobilePhone {
	
	protected static PhoneBook pb;
	protected static MsgSystem smsSystem;
	protected static Diary myDiary;
	protected static Player myPlayer;
	public static Scanner myObj = new Scanner(System.in);
	
	// Default Constructor
	protected MobilePhone() {
		pb = new PhoneBook();
		smsSystem = new MsgSystem();
		myDiary = new Diary();
		myPlayer = new Player();
		
	}
	protected void PhoneBookControl() {
		MobilePhone.pb.menu();
		return;
	}
	protected void smsControl() {
		MobilePhone.smsSystem.menu(MobilePhone.pb);
		return;
	}
	protected void DiaryControl() {
		MobilePhone.myDiary.menu();
		return;
	}
	protected void MediaControl() {
		MobilePhone.myPlayer.menu();
		return;
	}
	// User menu
	protected void menu() {
		while(true) {
			switch (CarServiceCenter.menu.waitForOption(this.printMenu())) {
			  case "1":
				    this.PhoneBookControl();
			        break;
			  case "2":
				    this.smsControl();
			        break;
			  case "3":
				    this.DiaryControl();
			        break;
			  case "4":
				    this.MediaControl();
			        break;
			  case "5":
				    JOptionPane.showMessageDialog(null,pb,"Phone Book",JOptionPane.PLAIN_MESSAGE);
				    JOptionPane.showMessageDialog(null,myDiary,"Diary",JOptionPane.PLAIN_MESSAGE);
				    JOptionPane.showMessageDialog(null,smsSystem.printAllCorresponding(),"Messages",JOptionPane.PLAIN_MESSAGE);
				    JOptionPane.showMessageDialog(null,myPlayer.playAll(),"Player",JOptionPane.PLAIN_MESSAGE);
			        break;
			  case "6":
				    myObj.close();
				    return;
			  default:
				    JOptionPane.showMessageDialog(null,"No such option","ERROR",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	// Printing the menu
	protected String printMenu() {
		String str1,str2,str3,str4,str5,str6;
		str1 = "1. PhoneBook\n";
		str2 = "2. SMS\n";
		str3 = "3. Diary\n";
		str4 = "4. Media\n";
		str5 = "5. Print\n";
		str6 = "6. Exit\n";
		return str1+str2+str3+str4+str5+str6;
	}
}
