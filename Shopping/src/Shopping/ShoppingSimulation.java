package Shopping;

import java.io.FileNotFoundException;

/**
 * @version Fall 2019
 * @author ITCS 2214
 */
public class ShoppingSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "list1.txt";
        ShoppingListADT sl;

        try {
            sl = new ShoppingListArray(filename);
// Change to ShoppingListArrayList when implemented
            // sl = new ShoppingListArrayList(filename);

            sl.add(new Grocery("Pistachio Ice Cream", "Dairy", 10, 2.99f, 1));
            System.out.println("Does list contain Pistachio Ice Cream? "
                    + sl.contains(new Grocery("Pistachio Ice Cream", "Dairy", 10, 2.99f, 1)));
            System.out.println("Does list contain Dill Pickles? " 
                    + sl.contains(new Grocery("Dill Pickles", "Condiments", 4, 2.99f, 1)));
            System.out.println("Let's add 3 jars of pickles.");
            sl.add(new Grocery("Dill Pickles", "Condiments", 4, 2.99f, 3));
            // remove ice cream
            System.out.println("Let's remove the ice cream.");
            sl.remove(new Grocery("Pistachio Ice Cream", "Dairy", 10, 2.99f, 1));
            System.out.println("Does list contain Pistachio Ice Cream? " 
                    + sl.contains(new Grocery("Pistachio Ice Cream", "Dairy", 10, 2.99f, 1)));
            System.out.println("Here is the whole shopping list: \n" + sl + "\n");

        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file. Closing simulation.");
        }
    }

}
