package CarServiceCenter;

import java.util.Comparator;

public class PaymentBillComparator implements Comparator<Payment> {
	@Override
	public int compare(Payment first,Payment second) {
	   return Integer.compare(first.getPaymentBill(), second.getPaymentBill());
	}
}
