package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        console.start();
    }
}

class Console {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        String input = scanner.nextLine();
        boolean isValidInput = isValidInput(input);
        if (!isValidInput) {
            scanner.close();
            return;
        }

        int n = Integer.parseInt(input);
        NaturalNumber naturalNumber = new NaturalNumber(n);
        if (naturalNumber.isEven()) {
            System.out.println("This number is Even.");
        } else {
            System.out.println("This number is Odd.");
        }

        if (naturalNumber.isBuzz()) {
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation:");
            if (naturalNumber.isDivisibleBy7() && naturalNumber.isEndWith7()) {
                System.out.printf("%d is divisible by 7 and ends with 7.\n", naturalNumber.getValue());
            } else if (naturalNumber.isDivisibleBy7()) {
                System.out.printf("%d is divisible by 7.\n", naturalNumber.getValue());
            } else {
                System.out.printf("%d ends with 7.\n", naturalNumber.getValue());
            }
        } else {
            System.out.println("It is not a Buzz number.");
            System.out.println("Explanation:");
            System.out.printf("%d is neither divisible by 7 nor does it end with 7.\n", naturalNumber.getValue());
        }
        scanner.close();
    }

    private boolean isValidInput(String input) {

        int n = Integer.parseInt(input);
        if (n < 1) {
            System.out.println("This number is not natural!");
            return false;
        } 

        return true;
    }
}

class NaturalNumber {
    private int value;

    NaturalNumber(int value) {
        this.value = value;
    }    

    boolean isDivisibleBy7() {
        return (value % 7 == 0);
    }

    boolean isEndWith7() {
        String digits = Integer.toString(value);
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

    public int getValue() {
        return value;
    }
}