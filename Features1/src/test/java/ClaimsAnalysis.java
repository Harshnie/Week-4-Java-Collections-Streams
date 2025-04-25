import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Claim {
    String claimId;
    String policyNumber;
    double claimAmount;
    LocalDate claimDate;
    String status;

    public Claim(String claimId, String policyNumber, double claimAmount, LocalDate claimDate, String status) {
        this.claimId = claimId;
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.status = status;
    }
}

class PolicySummary {
    String policyNumber;
    double totalClaimAmount;
    double averageClaimAmount;

    public PolicySummary(String policyNumber, double totalClaimAmount, double averageClaimAmount) {
        this.policyNumber = policyNumber;
        this.totalClaimAmount = totalClaimAmount;
        this.averageClaimAmount = averageClaimAmount;
    }

    @Override
    public String toString() {
        return "PolicySummary{" +
                "policyNumber='" + policyNumber + '\'' +
                ", totalClaimAmount=" + totalClaimAmount +
                ", averageClaimAmount=" + averageClaimAmount +
                '}';
    }
}

public class ClaimsAnalysis {

    public static List<PolicySummary> analyzeClaims(List<Claim> claims) {

        List<Claim> filteredClaims = claims.stream()
                .filter(c -> c.status.equalsIgnoreCase("Approved") && c.claimAmount > 5000)
                .collect(Collectors.toList());

        Map<String, List<Claim>> groupedByPolicy = filteredClaims.stream()
                .collect(Collectors.groupingBy(c -> c.policyNumber));

        List<PolicySummary> summaries = groupedByPolicy.entrySet().stream()
                .map(entry -> {
                    String policy = entry.getKey();
                    List<Claim> policyClaims = entry.getValue();
                    double total = policyClaims.stream().mapToDouble(c -> c.claimAmount).sum();
                    double avg = policyClaims.stream().mapToDouble(c -> c.claimAmount).average().orElse(0);
                    return new PolicySummary(policy, total, avg);
                })
                .collect(Collectors.toList());

        return summaries.stream()
                .sorted(Comparator.comparingDouble((PolicySummary ps) -> ps.totalClaimAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Claim> claims = Arrays.asList(
                new Claim("C001", "P001", 6000, LocalDate.of(2024, 1, 10), "Approved"),
                new Claim("C002", "P001", 3000, LocalDate.of(2024, 2, 15), "Approved"),
                new Claim("C003", "P002", 8000, LocalDate.of(2024, 3, 20), "Approved"),
                new Claim("C004", "P003", 10000, LocalDate.of(2024, 4, 25), "Approved"),
                new Claim("C005", "P003", 2000, LocalDate.of(2024, 5, 30), "Rejected"),
                new Claim("C006", "P004", 9000, LocalDate.of(2024, 6, 5), "Approved"),
                new Claim("C007", "P002", 7000, LocalDate.of(2024, 6, 15), "Approved"),
                new Claim("C008", "P005", 4500, LocalDate.of(2024, 7, 1), "Approved")
        );

        List<PolicySummary> topPolicies = analyzeClaims(claims);
        System.out.println("Top 3 Policies by Total Claim Amount:");
        topPolicies.forEach(System.out::println);
    }
}
