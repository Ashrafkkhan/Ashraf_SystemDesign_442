public class SalaryAccount extends BankAccount {
    
    public SalaryAccount(int accountNumber, String name, int age, double balance, NotificationService notificationService) {
        // Pass "Salary" as the accountType and inject the NotificationService
        super(accountNumber, name, age, balance, "Salary", notificationService);
    }
}
