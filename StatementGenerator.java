public class StatementGenerator {

    public String generate(BankAccount account) {
        StringBuilder statement = new StringBuilder();

        statement.append("---- Statement for Account #")
                 .append(account.getAccountNumber())
                 .append(" (")
                 .append(account.getName())
                 .append(") ----\n");

        for (String entry : account.getTransactionLog()) {
            statement.append(entry).append("\n");
        }

        statement.append("Current Balance: Rs. ")
                 .append(account.getBalance())
                 .append("\n");

        statement.append("-----------------------------------------------------");

        return statement.toString();
    }
}
