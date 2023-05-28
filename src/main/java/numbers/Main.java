package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        Scanner scanner = new Scanner(System.in);
        console.start(scanner);
        scanner.close();
    }
}

class Console {
    public void start(Scanner scanner) {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");

        String input = "";
        while (true) {      
            System.out.print("\nEnter a request: ");      
            input = scanner.nextLine();
            System.out.println();
            if (input.equals("0")) break;

            String[] params = input.split(" ");
            String param1 = params[0];
            String param2 = params.length > 1 ? params[1] : null;
            String param3 = params.length > 2 ? params[2] : null;
            
            if (!isValidInput(param1)) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }

            if (param2 != null && !isValidInput(param2)) {
                System.out.println("The second parameter should be a natural number.");
                continue;
            }

            if (param3 != null && !isValidProperty(param3)) {
                System.out.printf("The property [%s] is wrong.\n", param3.toUpperCase());
                String[] properties = NaturalNumber.getProperties();
                StringBuilder props = new StringBuilder(); 
                props.append("Available properties: [");
                for (int i = 0; i < properties.length; i++) {
                    props.append(properties[i]);
                    if (i != properties.length - 1) {
                        props.append(", ");
                    } else {
                        props.append("]");                        
                    }                    
                }
                System.out.println(props.toString());
            }

            long n = Long.parseLong(param1);

            if (param2 != null) {
                int count = Integer.parseInt(param2);
                for (int i = 0 ; i < count; i++) {
                    NaturalNumber naturalNumber = new NaturalNumber(n + i);
                    StringBuilder output = new StringBuilder(naturalNumber.toString());
                    output.append(" is ");
                    output.append(naturalNumber.isBuzz() ? "buzz, " : "");
                    output.append(naturalNumber.isDuck() ? "duck, " : "");
                    output.append(naturalNumber.isPalindromic() ? "palindromic, " : "");
                    output.append(naturalNumber.isGapful() ? "gapful, " : "");
                    output.append(naturalNumber.isSpy() ? "spy, " : "");
                    output.append(naturalNumber.isEven() ? "even, " : "");
                    output.append(naturalNumber.isOdd() ? "odd, " : "");
                    output.delete(output.length() - 2, output.length());
                    System.out.println(output.toString());
                }
            } else {                
                NaturalNumber naturalNumber = new NaturalNumber(n);
                System.out.printf("Properties of %s\n", naturalNumber);
                System.out.printf("        buzz: %s\n", naturalNumber.isBuzz());
                System.out.printf("        duck: %s\n", naturalNumber.isDuck());
                System.out.printf(" palindromic: %s\n", naturalNumber.isPalindromic());
                System.out.printf("      gapful: %s\n", naturalNumber.isGapful());
                System.out.printf("         spy: %s\n", naturalNumber.isSpy());
                System.out.printf("        even: %s\n", naturalNumber.isEven());
                System.out.printf("         odd: %s\n", naturalNumber.isOdd());
            }  
        }
        
        System.out.println("Goodbye!");
        
    }

    private boolean isValidInput(String input) {

        long n = -1;
        try {
            n = Long.parseLong(input);
        } catch (NumberFormatException e) {
            return false;
        }        
        
        if (n < 1) {
            return false;
        } 

        return true;
    }

    private boolean isValidProperty(String input) {
        String[] properties = NaturalNumber.getProperties();
        for (String prop : properties) {
            if (input.equalsIgnoreCase(prop)) {
                return true;
            }
        }

        return false;
    }


}

class NaturalNumber {
    private long value;

    private static final String[] properties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY"};


    NaturalNumber(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Long.toString(this.value);
    }

    boolean isDivisibleBy7() {
        return (value % 7 == 0);
    }

    boolean isEndWith7() {
        String digits = this.toString();
        if (digits.charAt(digits.length() - 1) == '7') {
            return true;
        }

        return false;
    }

    boolean isBuzz() {
        return (isDivisibleBy7() || isEndWith7());
    }

    boolean isEven() {
        return (value % 2 == 0);
    }

    boolean isOdd() {
        return (!isEven());
    }

    boolean isDuck() {
        return(this.toString().indexOf("0") > 0);
    }

    boolean isPalindromic() {
        if (this.value < 10) {
            return true;
        }

        String num = this.toString();
        for (int i = 0, j = num.length()  - 1; i < num.length() / 2; i++, j--) {
            if (num.charAt(i) != num.charAt(j)) return false;
        }

        return true;
    }

    boolean isGapful() {
        String numString = this.toString();
        if (numString.length() < 3) {
            return false;
        }

        String gap = numString.substring(0, 1) + numString.charAt(numString.length() - 1);
        if (this.value % Long.parseLong(gap) != 0) {
            return false;
        }

        return true;
    }

    long getValue() {
        return value;
    }

    static String[] getProperties() {
        return properties;
    }

    boolean isSpy() {
        long sum = 0;
        long product = 1;

        String[] digits = this.toString().split("");
        for (String digit : digits) {
            int n = Integer.parseInt(digit);
            sum += n;
            product *= n;
        }

        return (sum == product);
    }
}