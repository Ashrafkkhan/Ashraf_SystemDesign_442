public class InterestCalculator {

    public double calculateInterest(String accountType, double balance) {
        
        /*
         * PREDICTION: To add a 4th account type (e.g., "Salary Account"):
         * 1. We would have to open this exact InterestCalculator class.
         * 2. We would have to modify the calculateInterest() method below.
         * 3. We would need to add a new 'else if (accountType.equals("Salary"))' line.
         * 4. We would need to add a line returning the specific math for it (e.g., return balance * 0.03).
         * 
         */

        if (accountType.equals("Savings")) {
            return balance * 0.04;
        } else if (accountType.equals("Current")) {
            return balance * 0.01;
        } else {
            return 0.0;
        }
    }
}
