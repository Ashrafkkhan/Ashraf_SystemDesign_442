public class SavingsAccount extends BankAccount implements Withdrawable {
    public SavingsAccount(int accountNumber, String name, int age, double balance, NotificationService notificationService) {
        super(accountNumber, name, age, balance, "Savings", notificationService);
    }
}
