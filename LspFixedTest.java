import java.util.ArrayList;
import java.util.List;

public class LspFixedTest {
    public static void main(String[] args) {
        NotificationService ns = new NotificationService();
        
        List<Withdrawable> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount(101, "Alice", 30, 1000, ns));
        accounts.add(new CurrentAccount(102, "Charlie", 35, 2000, ns));
        
        // FixedDepositAccount cannot be added to this list because it does not implement Withdrawable
        // accounts.add(new FixedDepositAccount(103, "Bob", 40, 5000, ns)); // Compilation Error!
        
        System.out.println("Processing withdrawals for all withdrawable accounts...");
        for (Withdrawable account : accounts) {
            // We know every account in this list can honestly perform a withdrawal.
            account.withdraw(100, 1234); 
        }
        System.out.println("Withdrawals processed successfully without crashes!");
    }
}
