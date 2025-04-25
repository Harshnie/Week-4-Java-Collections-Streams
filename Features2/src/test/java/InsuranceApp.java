import java.util.*;
import java.util.stream.Collectors;

class Policy {
    private final String policyNumber;
    private final String holderName;
    private final double premiumAmount;

    public Policy(String policyNumber, String holderName, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.holderName = holderName;
        this.premiumAmount = premiumAmount;
    }

    public String getPolicyNumber() { return policyNumber; }
    public String getHolderName() { return holderName; }
    public double getPremiumAmount() { return premiumAmount; }

    @Override
    public String toString() {
        return String.format("Policy{number='%s', holder='%s', premium=$%.2f}", policyNumber, holderName, premiumAmount);
    }
}

class InsuranceUtils {
    public static List<Policy> filterHighPremium(List<Policy> policies) {
        return policies.stream().filter(p -> p.getPremiumAmount() > 1200).collect(Collectors.toList());
    }

    public static List<Policy> sortByHolderName(List<Policy> policies) {
        return policies.stream().sorted(Comparator.comparing(Policy::getHolderName)).collect(Collectors.toList());
    }

    public static double totalPremium(List<Policy> policies) {
        return policies.stream().mapToDouble(Policy::getPremiumAmount).sum();
    }

    public static void printPolicies(List<Policy> policies) {
        policies.forEach(System.out::println);
    }

    public static List<Policy> filterPremiumRange(List<Policy> policies) {
        return policies.stream().filter(p -> p.getPremiumAmount() >= 1000 && p.getPremiumAmount() <= 2000).collect(Collectors.toList());
    }

    public static Optional<Policy> highestPremiumPolicy(List<Policy> policies) {
        return policies.stream().max(Comparator.comparingDouble(Policy::getPremiumAmount));
    }

    public static Map<Character, List<Policy>> groupByHolderInitial(List<Policy> policies) {
        return policies.stream().collect(Collectors.groupingBy(p -> p.getHolderName().charAt(0)));
    }

    public static double averagePremium(List<Policy> policies) {
        return policies.stream().mapToDouble(Policy::getPremiumAmount).average().orElse(0.0);
    }

    public static void sortByPremiumAndPrint(List<Policy> policies) {
        policies.stream().sorted(Comparator.comparingDouble(Policy::getPremiumAmount)).forEach(System.out::println);
    }

    public static boolean anyPolicyExceeds(List<Policy> policies) {
        return policies.stream().anyMatch(p -> p.getPremiumAmount() > 2000);
    }

    public static Map<String, Long> countByPremiumRange(List<Policy> policies) {
        return policies.stream().collect(Collectors.groupingBy(p -> {
            double prem = p.getPremiumAmount();
            if (prem <= 1000) return "$0–$1 000";
            else if (prem <= 2000) return "$1 001–$2 000";
            else return ">$2 000";
        }, Collectors.counting()));
    }

    public static List<String> uniqueHolderNames(List<Policy> policies) {
        return policies.stream().map(Policy::getHolderName).distinct().collect(Collectors.toList());
    }

    public static List<Policy> findByHolderSubstring(List<Policy> policies, String substring) {
        String needle = substring.toLowerCase();
        return policies.stream().filter(p -> p.getHolderName().toLowerCase().contains(needle)).collect(Collectors.toList());
    }

    public static Map<String, Double> policyNumberToPremium(List<Policy> policies) {
        return policies.stream().collect(Collectors.toMap(Policy::getPolicyNumber, Policy::getPremiumAmount));
    }
}

public class InsuranceApp {
    public static void main(String[] args) {
        List<Policy> policies = Arrays.asList(
                new Policy("P-1001", "Alice Smith", 1500),
                new Policy("P-1002", "Bob Johnson", 950),
                new Policy("P-1003", "Carla Gomez", 2350),
                new Policy("P-1004", "David Kim", 1850),
                new Policy("P-1005", "Emma Patel", 1100),
                new Policy("P-1006", "Frank Smith", 700)
        );

        List<Policy> high = InsuranceUtils.filterHighPremium(policies);
        System.out.println("Premium > $1 200: " + high);

        System.out.println("\nSorted by holder name:");
        InsuranceUtils.sortByHolderName(policies).forEach(System.out::println);

        System.out.printf("%nTotal premium: $%.2f%n", InsuranceUtils.totalPremium(policies));

        System.out.println("\nAll policies:");
        InsuranceUtils.printPolicies(policies);

        System.out.println("\nPremium between $1 000 and $2 000:");
        InsuranceUtils.filterPremiumRange(policies).forEach(System.out::println);

        InsuranceUtils.highestPremiumPolicy(policies).ifPresent(p -> System.out.println("\nHighest premium: " + p));

        System.out.println("\nGrouped by holder initial:");
        InsuranceUtils.groupByHolderInitial(policies).forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.printf("%nAverage premium: $%.2f%n", InsuranceUtils.averagePremium(policies));

        System.out.println("\nSorted by premium ascending:");
        InsuranceUtils.sortByPremiumAndPrint(policies);

        System.out.println("\nAny policy over $2 000? " + InsuranceUtils.anyPolicyExceeds(policies));

        System.out.println("\nCounts by premium range:");
        InsuranceUtils.countByPremiumRange(policies).forEach((range, count) -> System.out.println(range + ": " + count));

        System.out.println("\nUnique holder names: " + InsuranceUtils.uniqueHolderNames(policies));

        System.out.println("\nPolicies with holder containing \"Smith\":");
        InsuranceUtils.findByHolderSubstring(policies, "Smith").forEach(System.out::println);

        System.out.println("\nMap policyNumber → premium:");
        InsuranceUtils.policyNumberToPremium(policies).forEach((k, v) -> System.out.printf("%s : $%.2f%n", k, v));
    }
}
