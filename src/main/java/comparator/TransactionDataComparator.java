package comparator;

import model.TransactionData;

import java.util.Comparator;

public class TransactionDataComparator implements Comparator<TransactionData> {

    @Override
    public int compare(TransactionData o1, TransactionData o2) {
        return (o1.getTimestamp() < o2.getTimestamp()) ? -1 : ((o1.getTimestamp() == o2.getTimestamp())? 0 : 1);
    }
}