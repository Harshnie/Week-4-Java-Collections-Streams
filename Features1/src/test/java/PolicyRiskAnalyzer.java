import java.util.*;
import java.util.stream.Collectors;

class PolicyHolder {
    String holderId;
    String name;
    int age;
    String policyType;
    double premiumAmount;

    public PolicyHolder(String holderId, String name, int age, String policyType, double premiumAmount) {
        this.holderId = holderId;
        this.name = name;
        this.age = age;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
    }
}

class RiskAssessment {
    String holderId;
    String name;
    double riskScore;

    public RiskAssessment(String holderId, String name, double riskScore) {
        this.holderId = holderId;
        this.name = name;
        this.riskScore = riskScore;
    }

    @Override
    public String toString() {
        return "RiskAssessment{" +
                "holderId='" + holderId + '\'' +
                ", name='" + name + '\'' +
                ", riskScore=" + riskScore +
                '}';
    }
}

public class PolicyRiskAnalyzer {

    public static Map<String, List<RiskAssessment>> assessRisk(List<PolicyHolder> holders) {
        // Step 1: Filter
        List<PolicyHolder> filtered = holders.stream()
                .filter(ph -> ph.policyType.equalsIgnoreCase("Life") && ph.age > 60)
                .collect(Collectors.toList());

        // Step 2: Transform
        List<RiskAssessment> assessments = filtered.stream()
                .map(ph -> new RiskAssessment(ph.holderId, ph.name, ph.premiumAmount / ph.age))
                .collect(Collectors.toList());

        // Step 3: Sort descending by riskScore
        List<RiskAssessment> sorted = assessments.stream()
                .sorted(Comparator.comparingDouble((RiskAssessment r) -> r.riskScore).reversed())
                .collect(Collectors.toList());

        // Step 4: Categorize into "High Risk" and "Low Risk"
        return sorted.stream()
                .collect(Collectors.groupingBy(r -> r.riskScore > 0.5 ? "High Risk" : "Low Risk"));
    }

    public static void main(String[] args) {
        List<PolicyHolder> holders = Arrays.asList(
                new PolicyHolder("H001", "Alice", 65, "Life", 40000),
                new PolicyHolder("H002", "Bob", 62, "Health", 25000),
                new PolicyHolder("H003", "Charlie", 70, "Life", 36000),
                new PolicyHolder("H004", "David", 75, "Life", 30000),
                new PolicyHolder("H005", "Eve", 58, "Life", 28000),
                new PolicyHolder("H006", "Frank", 80, "Life", 42000)
        );

        Map<String, List<RiskAssessment>> categorized = assessRisk(holders);

        System.out.println("Risk Categories:");
        categorized.forEach((category, list) -> {
            System.out.println(category + ":");
            list.forEach(System.out::println);
            System.out.println();
        });
    }
}
