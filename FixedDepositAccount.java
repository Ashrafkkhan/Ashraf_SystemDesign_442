public class FixedDepositAccount extends BankAccount {
    public FixedDepositAccount(int accountNumber, String name, int age, double balance, NotificationService notificationService) {
        super(accountNumber, name, age, balance, "FixedDeposit", notificationService);
    }

    @Override
    public boolean withdraw(double amount, Integer enteredPin) {
        throw new UnsupportedOperationException("Cannot withdraw from a Fixed Deposit account early.");
    }
}
