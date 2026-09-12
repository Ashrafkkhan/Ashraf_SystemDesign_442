public class CurrentAccount extends BankAccount implements Withdrawable {
    public CurrentAccount(int accountNumber, String name, int age, double balance, NotificationService notificationService) {
        super(accountNumber, name, age, balance, "Current", notificationService);
    }
}
