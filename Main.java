public class Main {

    public static void main(String[] args) {

        System.out.println("--- Creating Account ---");
        BankAccount account = new BankAccount(101, "Ravi", 17, 200, "Savings");
        // Age corrected to 18, balance corrected to 500 — printed by the constructor

        account.setPin(1234);

        System.out.println("\n--- Performing Transactions ---");
        // These operations will use NotificationService and AccountRepository
        account.deposit(1000);
        account.withdraw(500, 1234);
        account.withdraw(500, 9999); // wrong PIN, should fail

        System.out.println("\n--- Generating Statement ---");
        // This operation will use StatementGenerator
        account.printStatement();

        System.out.println("\nInterest earned: Rs. " + account.calculateInterest());
    }
}