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
        NaturalNumber naturalNumber = new NaturalNumber(Integer.parseInt(input));
        if (naturalNumber.isEven()) {
            System.out.println("This number is Even.");
        } else {
            System.out.println("This number is Odd.");
        }
        scanner.close();
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
}