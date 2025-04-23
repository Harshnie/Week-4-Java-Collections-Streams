import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative");
            }
            this.balance = initialBalance;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit must be positive");
            }
            balance += amount;
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal must be positive");
            }
            if (amount > balance) {
                throw new IllegalStateException("Insufficient funds");
            }
            balance -= amount;
        }

        public double getBalance() {
            return balance;
        }
    }

    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount(1000.0);  // Initial balance
    }

    @Test
    public void testDeposit() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        account.withdraw(300.0);
        assertEquals(700.0, account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            account.withdraw(2000.0);
        });
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    public void testNegativeDeposit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-100);
        });
        assertEquals("Deposit must be positive", exception.getMessage());
    }

    @Test
    public void testNegativeWithdrawal() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-100);
        });
        assertEquals("Withdrawal must be positive", exception.getMessage());
    }
}
