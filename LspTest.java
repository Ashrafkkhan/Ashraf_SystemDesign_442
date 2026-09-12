public class LspTest {
    public static void main(String[] args) {
        Rectangle rect = new Square();
        rect.setWidth(10);
        rect.setHeight(20);
        
        System.out.println("Expected Area: 200 (10 * 20)");
        System.out.println("Actual Area: " + rect.getArea());
        
        /*
         * Explanation for LSP Violation:
         * The Square/Rectangle example breaks the Liskov Substitution Principle (LSP) because 
         * calling code assumes that for a Rectangle, changing the height does NOT affect the width.
         * A Square violates this behavioral assumption by changing both dimensions simultaneously, 
         * causing unexpected side effects and incorrect calculations when substituted for a Rectangle.
         */
    }
}
