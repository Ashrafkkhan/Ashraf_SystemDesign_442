import java.util.ArrayList;
import java.util.List;

public class LspCrashTest {
    public static void main(String[] args) {
        NotificationService ns = new NotificationService();
        
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount(101, "Alice", 30, 1000, ns));
        accounts.add(new FixedDepositAccount(102, "Bob", 40, 5000, ns));
        
        System.out.println("Processing withdrawals for all accounts...");
        for (BankAccount account : accounts) {
            System.out.println("Withdrawing from account: " + account.getName());
            account.setPin(1234);
            account.withdraw(100, 1234); // Will crash on FixedDepositAccount
        }
    }
}
