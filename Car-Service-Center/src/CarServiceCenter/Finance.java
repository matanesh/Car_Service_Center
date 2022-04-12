package CarServiceCenter;

import java.util.*;

import javax.swing.JOptionPane;

public class Finance {
	
	protected HashMap<Integer,Payment> Payments;
	private int Profit;
	
	// Default Constructor
	protected Finance() {
		this.Payments = new HashMap<Integer,Payment>();
		this.Profit = 0;
	}
    // Gets & Sets
	protected int getProfit() {
		return Profit;
	}
	protected void setProfit(int profit) {
		Profit = profit;
	}
	// This function add payment to payments hashMap (called by the threads)
	protected void addPayment(int Bill, String OwnerName,String OwnerCellPhone, String PaymentDescription) throws CloneNotSupportedException {
		Random rand = new Random();
		// Generate random integers in range 0 to 99999
		Integer PaymentID = rand.nextInt(99999);
		while(Payments.containsKey(PaymentID)) {PaymentID = rand.nextInt(99999);}
		Person temp = new Person(OwnerName,OwnerCellPhone);
		Calendar c = Calendar.getInstance();
		Date date = c.getTime(); // Initialize date
		Payment p = new Payment(PaymentID, Bill, PaymentDescription, date, temp);
		Payment p1 = p.clone();
		this.Payments.put(PaymentID, p1);
		Profit+=Bill;
		this.sendReceiptInSMS(temp, p1.toString());
	}
	// This function delete payment by ID
    protected void deletePayment() throws InputMismatchException,NumberFormatException{
    	int PaymentID = Integer.parseInt(JOptionPane.showInputDialog("PaymentID:")); // Read user input
	    if(PaymentID < 0 || PaymentID > 99999) { // Input integrity check
	    	JOptionPane.showMessageDialog(null,"InValid input","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	return;
	    }
	    if(!(Payments.containsKey(PaymentID))) {
	    	JOptionPane.showMessageDialog(null,"No Such PaymentID","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	return;
	    }
	    Profit-=Payments.get(PaymentID).getPaymentBill();
	    this.Payments.remove(PaymentID);
	}
    // This function search payment by ID and print it
    protected void searchPaymentById() throws InputMismatchException,NumberFormatException{
    	int PaymentID = Integer.parseInt(JOptionPane.showInputDialog("PaymentID:")); // Read user input
	    if(PaymentID < 0 || PaymentID > 99999) { // Input integrity check
	    	JOptionPane.showMessageDialog(null,"InValid input","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	return;
	    }
	    if(!(Payments.containsKey(PaymentID))) {
	    	JOptionPane.showMessageDialog(null,"No Such PaymentID","ERROR",JOptionPane.PLAIN_MESSAGE);
	    	return;
	    }
	    JOptionPane.showMessageDialog(null,Payments.get(PaymentID),"Note",JOptionPane.PLAIN_MESSAGE);
	}
	@Override
	public String toString() {
	    return "Profit: " + this.getProfit() + "\n" + this.Payments.toString();
	}
	// Print all payments
	protected void printPayments() {
		JOptionPane.showMessageDialog(null,this,"Payments",JOptionPane.PLAIN_MESSAGE);
	}
	// Sort By Payment ID
	protected void SortByPaymentID() {
		List<Payment> paymentsByID = new ArrayList<>(Payments.values());
		Collections.sort(paymentsByID);
		JOptionPane.showMessageDialog(null,paymentsByID,"Sort By Payment ID",JOptionPane.PLAIN_MESSAGE);
	}
	// Sort By Payment Bill
	protected void SortByPaymentBill() {
		List<Payment> paymentsByBill = new ArrayList<>(Payments.values());
		Comparator<Payment> PaymentBillComparator = new PaymentBillComparator();
		Collections.sort(paymentsByBill, PaymentBillComparator);
		JOptionPane.showMessageDialog(null,paymentsByBill,"Sort By Payment Bill",JOptionPane.PLAIN_MESSAGE);
	}
	// Sort By Payment Date
	protected void SortByPaymentDate() {
		List<Payment> paymentsByDate = new ArrayList<>(Payments.values());
		Comparator<Payment> PaymentDateComparator = new PaymentDateComparator();
		Collections.sort(paymentsByDate, PaymentDateComparator);
		JOptionPane.showMessageDialog(null,paymentsByDate,"Sort By Payment Date",JOptionPane.PLAIN_MESSAGE);
	}
	// Send receipt in SMS
	protected void sendReceiptInSMS(Person p, String text) {
		// If the person is new
		MobilePhone.pb.addPerson(p.getName(), p.getphoneNumber());
		// Send SMS
		MobilePhone.smsSystem.addCorresponding(MobilePhone.pb, p.getName(), text);
	}
	// User menu
	protected void menu() {
		while(true) {
			switch (CarServiceCenter.menu.waitForOption(this.printMenu())) {
				 case "1":
					 try {this.deletePayment();}
					 catch(Exception e) {
						 JOptionPane.showMessageDialog(null,"InValid input","ERROR",JOptionPane.PLAIN_MESSAGE);
					   }
				       break;
				 case "2":
					 try {this.searchPaymentById();}
					 catch(Exception e) {
						 JOptionPane.showMessageDialog(null,"InValid input","ERROR",JOptionPane.PLAIN_MESSAGE);
					   }
				       break;
				 case "3":
					   JOptionPane.showMessageDialog(null,this.getProfit(),"Profit",JOptionPane.PLAIN_MESSAGE); // Print the profit
				       break;
				 case "4":
					   this.printPayments();
					   break;
				 case "5":
					   this.SortByPaymentID();
					   break;
				 case "6":
					   this.SortByPaymentBill();
					   break;
				 case "7":
					   this.SortByPaymentDate();
					   break;
				 case "8":
					   return;
				 default:
					   JOptionPane.showMessageDialog(null,"No such option","ERROR",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	// Printing the menu
	protected String printMenu() {
		String str1,str2,str3,str4,str5,str6,str7,str8;
		str1 = "1. Delete Payment\n";
		str2 = "2. Search Payment By Payment ID\n";
		str3 = "3. Print Total Profit\n";
		str4 = "4. Print Finance Department\n";
		str5 = "5. Sort Finance Department By PaymentID\n";
		str6 = "6. Sort Finance Department By PaymentBill\n";
		str7 = "7. Sort Finance Department By PaymentDate\n";
		str8 = "8. Exit\n";
		return str1+str2+str3+str4+str5+str6+str7+str8;
	}
}
