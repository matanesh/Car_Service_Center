// Students: Matan Eshel,Gil Ya'akov, Ron Gandelman, Eliyahu Latin
// Project Name: Task2
// Date: 29/04/2021
// Group number: 17
package CarServiceCenter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

/*
 * Fields:
 * 	1. msgingData - MyData array
 * 	2. contactAmount - number of contacts I'm messaging with
 * Methods:
 * 	3. existMessaging - Return true if there are messages with the contact - input: Name
 *	4. findContactIndex - Find the index of the contact by name in msgingData, return -1 if not there
 *	5. addCorresponding - Adds a message to the given name | Create new if needed - check if contact exists on phone book
 * 	6. deleteCorresponding - Delete the messages with the contact name given
 * 	7. printCorresponding - Print the messages with the given name
 * 	8. printAllCorresponding - Print the entire message data base
 * 	9. searchMsg - search the message given and return a contact list containing the message in the corresponding
 * 	10. menu - the control method according to the user inputs
 * 	11. printMenu - Prints the SMS Application menu to the user
 * 
 * 
 * */

public class MsgSystem {
	
	public class MyData 
	/*Fields:
	 *	 1. contactName - the name of the contact - Can change to person if number is needed
	 *	 2. personCorrespondence - ArrayList of strings, saving the messages from that contact
	 *	 3. msgAmount - The number of messages with that contact
	 * Methods:
	 *	 1.addMsg - Adds a message to personCorrespondence  
	 *	 2.getName - return the name of the contact
	 *	 3.getMessgse - return the message list
	 * */
	{
		private String contactName;		
		private CopyOnWriteArrayList<String> personCorrespondence;	
		private int msgAmount;
		
		protected MyData(String pers,String str) 
		{
			this.contactName = pers;
			this.personCorrespondence = new CopyOnWriteArrayList<String>();
			this.personCorrespondence.add(str);
			this.msgAmount = 0;
		}
		
		protected void addMsg(String msg)
		{
			this.personCorrespondence.add(msg);
			this.msgAmount++;
		}
		protected String getName()
		{
			return this.contactName;	
		}
		
		protected CopyOnWriteArrayList<String> getMessgses()
		{
			return this.personCorrespondence;
		}
	}
	
	private CopyOnWriteArrayList<MyData> msgingData;
	private int contactAmount;
	
	protected MsgSystem()
	{
		this.msgingData = new CopyOnWriteArrayList<MyData>();
		this.contactAmount = 0;	
	}
	
	
	protected boolean existMessaging(String name) 
	{
		for(MyData a : msgingData)
		{
			if(a.getName().equals(name)) 	
			{
				return true;
			}
		}
		return false;
	}
	
	
	protected int findContactIndex(String name)
	{
        int count = 0;
		for(MyData a : msgingData)
		{
			if(a.getName().equals(name)) 
			{
				return count;
			}
			else
			{
				count++;
			}
		}
		return -1;
	}
	
	
	protected void addCorresponding(PhoneBook phoneBook, String name, String msg)
	{
		// First search if I already messaging this contact
		if (existMessaging(name))
		{
			int index = findContactIndex(name); // index > -1 always here 
			msgingData.get(index).personCorrespondence.add(msg);
		}
		else
		{
			// Else create a new object - ONLY IF CONTACT EXISTS IN PHONEBOOK
			// TODO: Check how to access our phone book in MobilePhone class
			if (!phoneBook.existPerson(name))
			{
				JOptionPane.showMessageDialog(null,"No contact with that name exists","ERROR",JOptionPane.PLAIN_MESSAGE);
			}
			else
			{	
				MyData info = new MyData(name,msg);
				this.msgingData.add(info);
				this.contactAmount++;
					
			}
		}
	}
	
	
	protected void updateMsgSystem(PhoneBook pb)
	{
		String name;
		for(MyData data : this.msgingData)
		{
			name = data.getName();
			if(!pb.existPerson(name))
			{
				this.deleteCorresponding(name);
			}		
		}	
	}
	
	
	protected void deleteCorresponding(String name)
	{
		// First handle the case the contact has no Corresponding
		if (!existMessaging(name))
		{
			JOptionPane.showMessageDialog(null,"No corresponding with that contact","ERROR",JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			int index = findContactIndex(name);
			msgingData.remove(index);
			JOptionPane.showMessageDialog(null,"Contact corresponding removed","Note",JOptionPane.PLAIN_MESSAGE);
			this.contactAmount--;
		}	
	}

	
	protected String printCorresponding(String name)
	{
		// First handle the case the contact has no Corresponding
		int index;
		String temp = "";
		if (!existMessaging(name))
		{
			JOptionPane.showMessageDialog(null,"No corresponding with that contact","ERROR",JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			index = findContactIndex(name);
			temp = "Printing " + name + "'s corresponding:\n"; 	
		    for (String str : msgingData.get(index).personCorrespondence)
		    { 		      
		    	temp += str + "\n";
		    }
		}
		return temp;
	}
	
	
	protected String printAllCorresponding()
	{
		String temp = "Printing the entire corresponding history:\n"; 	
		for (int i=0; i<this.contactAmount; i++)
		{
			temp += printCorresponding(msgingData.get(i).contactName);	
		}	
		return temp;
	}
	
	
	protected void searchMessage(String msg)
	{
		boolean exist = false;
		String temp = "The following contacts correspondence contain the given expression:\n";
		for(MyData element : msgingData)
		{
			for(String str : element.personCorrespondence)
			{
				if (str.contains(msg))
				{
					temp += element.getName() + "\n";
					exist = true;
				}
			}	
		}	
		if(!exist)
		{
			JOptionPane.showMessageDialog(null,"None!","ERROR",JOptionPane.PLAIN_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null,temp,"Note",JOptionPane.PLAIN_MESSAGE);
	}
	
	
	protected void menu(PhoneBook pb)
	{
		while(true) 
		{
			//  update our SMS contacts according to the new phone book
			if(this.contactAmount >0) {updateMsgSystem(pb);}
			String name;
			switch (CarServiceCenter.menu.waitForOption(this.printMenu())) 
			{
			  case "1":
				  try {
					name = JOptionPane.showInputDialog("Enter the contact's name:"); // Read user input
					String userMsgGiven = JOptionPane.showInputDialog("Type the message - press Enter to send"); // Read user input
					this.addCorresponding(pb,name,userMsgGiven);
				  }catch(Exception e) {
					  JOptionPane.showMessageDialog(null,"Invalid input","ERROR",JOptionPane.PLAIN_MESSAGE);
				  }
			        break;
			  case "2":
				    name = JOptionPane.showInputDialog("Enter the contact's name:"); // Read user input
					this.deleteCorresponding(name);
			        break;
			  case "3":
				    name = JOptionPane.showInputDialog("Enter the contact's name:"); // Read user input
					JOptionPane.showMessageDialog(null,this.printCorresponding(name),"Messages",JOptionPane.PLAIN_MESSAGE);
			        break;
			  case "4":
				    String searchMsg = JOptionPane.showInputDialog("Enter expression - press Enter when done"); // Read user input
				  	this.searchMessage(searchMsg);
			        break;
			  case "5":
				    JOptionPane.showMessageDialog(null,this.printAllCorresponding(),"Messages",JOptionPane.PLAIN_MESSAGE);
			        break;
			  case "6":
			        return;
			  default:
				    JOptionPane.showMessageDialog(null,"No such option","ERROR",JOptionPane.PLAIN_MESSAGE);
			}
		}	
	}
	protected String printMenu() 
	{
		String str1,str2,str3,str4,str5,str6;
		str1 = "1. Add corresponding with contact\n";
		str2 = "2. Delete corresponding with contact\n";
		str3 = "3. Print corresponding with contact\n";
		str4 = "4. Search an expression\n";
		str5 = "5. Print all corresponding\n";
		str6 = "6. Exit\n";
		return str1+str2+str3+str4+str5+str6;
	}
}

