package CarServiceCenter;

import java.util.Comparator;

public class PaymentDateComparator implements Comparator<Payment> {
	@Override
	public int compare(Payment first,Payment second) {
	   return first.getPaymentDate().compareTo(second.getPaymentDate());
	}
}