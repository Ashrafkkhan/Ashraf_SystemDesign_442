public class Main {

    public static void main(String[] args) {

        System.out.println("--- Creating Notification Service ---");
        NotificationService notificationService = new NotificationService();

        System.out.println("\n--- Creating Savings Account ---");
        BankAccount savingsAccount = new BankAccount(101, "Ravi", 17, 200, "Savings", notificationService);
        // Age corrected to 18, balance corrected to 500 — printed by the constructor

        savingsAccount.setPin(1234);

        System.out.println("\n--- Performing Transactions on Savings ---");
        savingsAccount.deposit(1000);
        savingsAccount.withdraw(500, 1234);

        System.out.println("\n--- Generating Savings Statement ---");
        savingsAccount.printStatement();

        // OCP: Computing interest via policy
        InterestPolicy savingsPolicy = new SavingsInterestPolicy();
        System.out.println("\nInterest earned (Savings): Rs. " + savingsPolicy.calculate(savingsAccount.getBalance()));

        System.out.println("\n==========================================\n");

        System.out.println("--- Creating Salary Account ---");
        SalaryAccount salaryAccount = new SalaryAccount(102, "Alice", 25, 2000, notificationService);
        
        salaryAccount.deposit(500);

        System.out.println("\n--- Generating Salary Statement ---");
        salaryAccount.printStatement();

        // OCP: Computing interest via salary policy
        InterestPolicy salaryPolicy = new SalaryInterestPolicy();
        System.out.println("\nInterest earned (Salary): Rs. " + salaryPolicy.calculate(salaryAccount.getBalance()));
    }
}