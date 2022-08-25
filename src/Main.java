import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] products = {"Bread", "Apples", "Milk"};
        int[] prices = {100, 200, 300};
        int[] amountProduct = new int[3];
        int sumProducts = 0;

        System.out.println("List of possible products to buy:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " +
                    products[i] + " - " +
                    prices[i] + " rub/piece.");
        }

        while (true) {
            System.out.println("Select the product and quantity or enter `end` to exit.");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("The program is completed!");
                System.out.println("Your shopping cart:");

                for (int i = 0; i < amountProduct.length; i++) {
                    if (amountProduct[i] != 0) {
                        sumProducts += amountProduct[i] * prices[i];
                        int sumCount = prices[i] * amountProduct[i];

                        System.out.println(products[i] + " " +
                                amountProduct[i] + " piece by " +
                                prices[i] + " rub/piece, " +
                                sumCount + " rub in total");
                    }
                }
                System.out.println("Total: " + sumProducts + " rub");
                break;
            }
            try {
                String[] parts = input.split(" ");
                int numProd = Integer.parseInt(parts[0]) - 1;
                int countProd = Integer.parseInt(parts[1]);

                if (parts.length > 2) {
                    System.out.println("More than two variables have been introduced");
                    continue;
                }
                if (countProd <= 0) {
                    System.out.println("incorrect input of the product quantity");
                    continue;
                }
                amountProduct[numProd] += countProd;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("incorrect product number input or product quantity not entered");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("only 'end' can be entered in letters");
                continue;
            }
        }
    }
}
