import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] products = {"Bread", "Apples", "Milk"}; //Normal
        int[] prices = {100, 200, 300};
        String[] productsEvent = {"Chocolate", "Bananas", "Lemons"}; //Event(3 for the price of 2)"
        int[] pricesEvent = {400, 500, 600};

        int[] amountProduct = new int[products.length + pricesEvent.length];
        int sumProducts = 0;
        int sumCount;
        int sumCountEvent;

        System.out.println("List of possible products to buy:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " +
                    products[i] + " - " +
                    prices[i] + " rub/piece.");
        }

        for (int i = 0; i < productsEvent.length; i++) {
            System.out.println((i + 1 + products.length) + ". " +
                    productsEvent[i] + " - " +
                    pricesEvent[i] + " rub/piece. Event(3 for the price of 2)");
        }

        while (true) {
            System.out.println("Select the product and quantity or enter `end` to exit.");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("The program is completed!");
                System.out.println("Your shopping cart:");

                for (int i = 0; i < (amountProduct.length - productsEvent.length); i++) { // ++
// The following code has been added to view make the basket clear
                    //      if (amountProduct[i] == 0) {
                    //        amountProduct[i] = 0;
                    //      sumProducts += amountProduct[i];
                    //    sumCount = prices[i] * amountProduct[i];
//
                    //                      System.out.println(products[i] + " " +
                    //                            amountProduct[i] + " piece by " +
                    //                          prices[i] + " rub/piece, " +
                    //                        sumCount + " rub in total");
                    //          }
// Code end

                    if (amountProduct[i] != 0) {
                        sumCount = prices[i] * amountProduct[i];
                        System.out.println(products[i] + " " +
                                amountProduct[i] + " piece by " +
                                prices[i] + " rub/piece, " +
                                sumCount + " rub in total");
                        sumProducts += sumCount;
                    }
                }

                for (int i = products.length; i < amountProduct.length; i++) {
                    if (amountProduct[i] != 0) {
                        if (amountProduct[i] == 1 || amountProduct[i] == 2) {
                            sumCountEvent = pricesEvent[i - products.length] * amountProduct[i];
                            shoppingCartEvent(sumCountEvent, i, pricesEvent, products,
                                    amountProduct, productsEvent);
                        } else {
                            sumCountEvent = pricesEvent[i - products.length]
                                    * (amountProduct[i] - (amountProduct[i] / 3));
                            shoppingCartEvent(sumCountEvent, i, pricesEvent, products,
                                    amountProduct, productsEvent);
                        }
                        sumProducts += sumCountEvent;
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
                if (countProd == 0) {
                    amountProduct[numProd] = countProd; // Product[i] in basket equals zero
                    continue;
                }

                amountProduct[numProd] += countProd;

                if (amountProduct[numProd] < 0) {
                    amountProduct[numProd] -= countProd;
                    System.out.println("there are not so many items in your cart");
                    continue;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("incorrect product number input or product quantity not entered");
            } catch (NumberFormatException e) {
                System.out.println("only 'end' can be entered in letters");
            }
        }
    }

    static void shoppingCartEvent(int sumCountEvent, int i, int[] pricesEvent, String[] products,
                                  int[] amountProduct, String[] productsEvent) {
        System.out.println(productsEvent[i - products.length] + " " +
                amountProduct[i] + " piece by " +
                pricesEvent[i - products.length] + " rub/piece, " +
                sumCountEvent + " rub in total");
    }
}
