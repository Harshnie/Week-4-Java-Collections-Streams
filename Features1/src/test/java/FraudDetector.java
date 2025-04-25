import java.util.*;
import java.util.stream.Collectors;

class Transaction {
    String transactionId;
    String policyNumber;
    double amount;
    Date transactionDate;
    boolean isFraudulent;

    public Transaction(String transactionId, String policyNumber, double amount, Date transactionDate, boolean isFraudulent) {
        this.transactionId = transactionId;
        this.policyNumber = policyNumber;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.isFraudulent = isFraudulent;
    }
}

class FraudSummary {
    String policyNumber;
    long fraudCount;
    double totalFraudAmount;

    public FraudSummary(String policyNumber, long fraudCount, double totalFraudAmount) {
        this.policyNumber = policyNumber;
        this.fraudCount = fraudCount;
        this.totalFraudAmount = totalFraudAmount;
    }

    @Override
    public String toString() {
        return "FraudSummary{" +
                "policyNumber='" + policyNumber + '\'' +
                ", fraudCount=" + fraudCount +
                ", totalFraudAmount=" + totalFraudAmount +
                '}';
    }
}

public class FraudDetector {

    public static List<FraudSummary> detectFraud(List<Transaction> transactions) {

        List<Transaction> filtered = transactions.stream()
                .filter(t -> t.isFraudulent && t.amount > 10000)
                .collect(Collectors.toList());

        Map<String, FraudSummary> summaryMap = filtered.stream()
                .collect(Collectors.groupingBy(
                        t -> t.policyNumber,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                txns -> {
                                    long count = txns.size();
                                    double total = txns.stream().mapToDouble(t -> t.amount).sum();
                                    return new FraudSummary(txns.get(0).policyNumber, count, total);
                                }
                        )
                ));

        return summaryMap.values().stream()
                .filter(fs -> fs.fraudCount > 5 || fs.totalFraudAmount > 50000)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("T001", "P001", 12000, new Date(), true),
                new Transaction("T002", "P001", 13000, new Date(), true),
                new Transaction("T003", "P001", 11000, new Date(), true),
                new Transaction("T004", "P001", 17000, new Date(), true),
                new Transaction("T005", "P001", 15000, new Date(), true),
                new Transaction("T006", "P001", 18000, new Date(), true),
                new Transaction("T007", "P002", 5000, new Date(), true),
                new Transaction("T008", "P003", 20000, new Date(), true),
                new Transaction("T009", "P003", 30000, new Date(), true),
                new Transaction("T010", "P003", 7000, new Date(), true),
                new Transaction("T011", "P004", 60000, new Date(), true),
                new Transaction("T012", "P005", 15000, new Date(), false)
        );

        List<FraudSummary> alerts = detectFraud(transactions);

        System.out.println("Fraud Alerts:");
        alerts.forEach(System.out::println);
    }
}

