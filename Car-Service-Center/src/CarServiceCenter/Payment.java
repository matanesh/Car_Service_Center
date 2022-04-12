package CarServiceCenter;
import  java.util.*;

public class Payment implements Cloneable, Comparable<Payment>{
	
	private Integer PaymentID;
	private Integer PaymentBill;
	private String PaymentDescription;
	protected Date PaymentDate;
	protected Person PaymentContact;
	
	// Default constructor
	protected Payment() {
		this.PaymentID = 0;
		this.PaymentBill = 0;
		this.PaymentDescription = "No Description";
		this.PaymentDate = new Date();
		this.PaymentContact = new Person();
	}
	// Constructor
	protected Payment(int PaymentID, int PaymentBill, String PaymentDescription, Date PaymentDate, Person PaymentContact) {
		this.PaymentID = PaymentID;
		this.PaymentBill = PaymentBill;
		this.PaymentDescription = PaymentDescription;
		this.PaymentDate = PaymentDate;
		this.PaymentContact = PaymentContact;
	}
	// Gets & Sets
	protected int getPaymentID() {
		return this.PaymentID;
	}
	protected int getPaymentBill() {
		return this.PaymentBill;
	}
	protected String getPaymentDescription() {
		return this.PaymentDescription;
	}
	protected Date getPaymentDate() {
		return this.PaymentDate;
	}
	protected Person getPaymentContact() {
		return this.PaymentContact;
	}
	protected void setPaymentID(int PaymentID) {
		this.PaymentID = PaymentID;
		return;
	}
	protected void setPaymentBill(int PaymentBill) {
		this.PaymentBill = PaymentBill;
		return;
	}
	protected void setPaymentDescription(String PaymentDescription) {
		this.PaymentDescription = PaymentDescription;
		return;
	}
	protected void setPaymentDate(Date PaymentDate) {
		this.PaymentDate = PaymentDate;
		return;
	}
	protected void setPaymentContact(Person PaymentContact) {
		this.PaymentContact = PaymentContact;
		return;
	}
	@Override
	public String toString() {
	    return "PaymentID: " + PaymentID + "\n" + "PaymentBill: " + PaymentBill + "\n" + "PaymentDescription: " + PaymentDescription + "\n" + "PaymentDate: " + PaymentDate.toString() + "\n" + "PaymentContact: " + PaymentContact.toString() + "\n";
	}
	@Override
	public int compareTo(Payment p) {
		return Integer.compare(this.PaymentID, p.PaymentID);
	}
	@Override
    protected Payment clone() throws CloneNotSupportedException {
		Payment temp = (Payment)super.clone();
		temp.PaymentContact = PaymentContact.clone(); 
		temp.PaymentDate = (Date)PaymentDate.clone();
        return temp;
    }
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
	    Payment p = (Payment) other;
	    
	    // Field comparison
	    return Objects.equals(PaymentID, p.PaymentID) && Objects.equals(PaymentBill, p.PaymentBill) && Objects.equals(PaymentDescription, p.PaymentDescription) && Objects.equals(PaymentDate, p.PaymentDate) && Objects.equals(PaymentContact, p.PaymentContact);
	}
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + PaymentID.hashCode();
	    result = prime * result + (int) (PaymentBill ^ (PaymentBill >>> 32));
	    result = prime * result + PaymentDescription.hashCode();
	    result = prime * result + PaymentDate.hashCode();
	    result = prime * result + PaymentContact.hashCode();
	    return result;
	}
}
