import java.util.ArrayList;
import java.util.List;

/** 
 * GreenLeaf Bank — Legacy BankAccount class 
 *  
 * Job Description: Manages a customer's account balance and processes core financial transactions like deposits and withdrawals. 
 *
 * Wrap-up Summary: We ended up with 4 distinct classes to handle accounts, databases, emails, and statements separately. This makes testing much easier because we can check one piece at a time without relying on the others. For example, we can test withdrawals without needing a real database or email server connected. If something stops working, it is now much simpler to track down exactly which class caused the problem.
 * 
 * This class is intentionally messy. 
 * It mixes account state, validation, persistence, notification, 
 * statement formatting, and interest calculation all in one place. 
 * Refactor this across by implementing the lab tasks onward. 
 * 
 * Reasons to change (Violations of Single Responsibility Principle): 
 * 1. Database/Persistence: Changes if we move from MySQL to another DB or change how we save. 
 * 2. Notification/Email: Changes if we switch from email to SMS, or change the email provider. 
 * 3. Statement Formatting: Changes if the statement layout changes, or if we export to PDF instead of console. 
 * 4. Interest Calculation: Changes if interest rates change or new account types are introduced. 
 * 5. Business/Validation Rules: Changes if the minimum age or minimum balance rules change. 
 */ 
public class BankAccount { 
 
    private int accountNumber; 
    private String name; 
    private int age; 
    private double balance; 
    private String status; 
    private Integer pin; 
    private String accountType; // "Savings" or "Current" 
 
    // Every deposit/withdrawal gets logged here as a plain string — 
    // logging logic is mixed directly into deposit()/withdraw(). 
    private List<String> transactionLog = new ArrayList<>(); 
 
    private AccountRepository accountRepository;
    private NotificationService notificationService;

    public BankAccount(int accountNumber, String name, int age, double balance, String accountType) { 
 
        // Validation logic mixed directly into the constructor 
        if (age < 18) { 
            System.out.println("Age was below 18, correcting to 18"); 
            age = 18; 
        } 
 
        double minimumBalance = accountType.equals("Savings") ? 500.0 : 1000.0; 
        if (balance < minimumBalance) { 
            System.out.println("Initial balance below minimum, correcting to " + minimumBalance); 
            balance = minimumBalance; 
        } 
 
        this.accountNumber = accountNumber; 
        this.name = name; 
        this.age = age; 
        this.balance = balance; 
        this.accountType = accountType; 
        this.status = "Active"; 
        this.pin = null;

        this.accountRepository = new AccountRepository();
        this.notificationService = new NotificationService();
    } 
 
    // ---------------------------------------------------- 
    // Account operations, tangled with logging + notification 
    // ---------------------------------------------------- 
 
    public boolean deposit(double amount) { 
 
        if (!status.equals("Active")) { 
            System.out.println("Account is not active"); 
            return false; 
        } 
 
        if (amount <= 0) { 
            System.out.println("Invalid deposit amount"); 
            return false; 
        } 
 
        balance += amount; 
 
        // Logging responsibility, baked directly into deposit() 
        transactionLog.add("DEPOSIT: Rs. " + amount + " | New balance: " + balance); 
 
        // Notification responsibility, baked directly into deposit() 
        notificationService.send(
            "Your deposit of Rs. " + amount + " was successful. New balance: " + balance
        );

        // Persistence responsibility, baked directly into deposit() 
        accountRepository.save(this);

        return true; 
    } 
 
    public boolean withdraw(double amount, Integer enteredPin) { 
 
        if (!status.equals("Active")) { 
            System.out.println("Account is not active"); 
            return false; 
        } 
 
        if (pin != null) { 
            if (enteredPin == null || !enteredPin.equals(pin)) { 
                System.out.println("Incorrect PIN"); 
                return false; 
            } 
        } 
 
        if (amount <= 0) { 
            System.out.println("Invalid withdrawal amount"); 
            return false; 
        } 
 
        double minimumBalance = accountType.equals("Savings") ? 500.0 : 1000.0; 
        if (balance - amount < minimumBalance) { 
            System.out.println("Withdrawal would breach minimum balance"); 
            return false; 
        } 
 
        balance -= amount; 
 
        transactionLog.add("WITHDRAW: Rs. " + amount + " | New balance: " + balance); 
 
        notificationService.send(
            "Your withdrawal of Rs. " + amount + " was successful. New balance: " + balance
        );

        accountRepository.save(this);

        return true; 
    } 
 
    public boolean closeAccount() { 
        if (status.equals("Inactive")) return false; 
        status = "Inactive"; 
        notificationService.send(name + " Your account has been closed.");
        accountRepository.save(this);
        return true; 
    } 
 
    public boolean reopenAccount() { 
        if (status.equals("Active")) return false; 
        status = "Active"; 
        notificationService.send(name + " Your account has been reopened.");
        accountRepository.save(this);
        return true; 
    } 
 
    public boolean setPin(int newPin) { 
        if (newPin >= 1000 && newPin <= 9999) { 
            this.pin = newPin; 
            return true; 
        } 
        return false; 
    } 
 
    public boolean verifyPin(int enteredPin) { 
        return pin != null && pin.equals(enteredPin); 
    } 
 
    // ---------------------------------------------------- 
    // Interest calculation — an if/else chain baked into the account itself 
    // ---------------------------------------------------- 
 
    public double calculateInterest() { 
        if (accountType.equals("Savings")) { 
            return balance * 0.04; 
        } else if (accountType.equals("Current")) { 
            return balance * 0.01; 
        } else { 
            return 0.0; 
        } 
    } 
 
    // ---------------------------------------------------- 
    // "Persistence" — pretend database logic living inside the account 
    // ---------------------------------------------------- 
 
    // Persistence logic has been extracted to AccountRepository.
 
    // ---------------------------------------------------- 
    // "Notification" — pretend email logic living inside the account 
    // ---------------------------------------------------- 
 
    // Notification logic has been extracted to NotificationService.
 
    // ---------------------------------------------------- 
    // "Statement generation" — formatting logic living inside the account 
    // ---------------------------------------------------- 
 
    public void printStatement() { 
        StatementGenerator statementGenerator = new StatementGenerator();
        System.out.println(statementGenerator.generate(this));
    } 
 
    // ---------------------------------------------------- 
    // Getters 
    // ---------------------------------------------------- 
 
    public int getAccountNumber() { return accountNumber; } 
    public String getName() { return name; } 
    public int getAge() { return age; } 
    public double getBalance() { return balance; } 
    public String getStatus() { return status; } 
    public String getAccountType() { return accountType; } 
    public boolean hasPin() { return pin != null; }

    public List<String> getTransactionLog() {
        return transactionLog;
    }
}