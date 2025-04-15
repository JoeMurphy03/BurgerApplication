package org.example.forthtry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderTest {

    @Test
    void testDisplayBurgerTotalPrice() {
        ArrayList<String> burgers = new ArrayList<>(Arrays.asList("Beef", "Chicken"));
        ArrayList<String> cheese = new ArrayList<>(Arrays.asList("Cheddar", "Swiss"));
        ArrayList<String> garnish = new ArrayList<>(Arrays.asList("Lettuce", "Tomato"));

        int burgerAmount = 2;
        int cheeseAmount = 2;
        double expectedTotal = 0.49 + (0.99 * burgerAmount) + (0.99 * cheeseAmount) + (0.99 * garnish.size());

        BurgerMain burger = new BurgerMain("Sesame Bun", burgers, cheese, garnish, expectedTotal, burgerAmount, cheeseAmount);

        String result = burger.displayBurger();

        assertTrue(result.contains("Total Price: " + BurgerMain.formatDouble(expectedTotal)));
    }

    @Test
    void testSortOutputNotNull() {
        ArrayList<String> burgers = new ArrayList<>(Arrays.asList("Beef"));
        ArrayList<String> cheese = new ArrayList<>(Arrays.asList("Cheddar", "Swiss"));
        ArrayList<String> garnish = new ArrayList<>(Arrays.asList("Lettuce", "Tomato", "Pickle"));

        BurgerMain burger = new BurgerMain("Brioche Bun", burgers, cheese, garnish, 0.0, 1, 2);

        String sortedOutput = burger.displayBurger();

        assertNotNull(sortedOutput);
        assertTrue(sortedOutput.contains("Beef"));
        assertTrue(sortedOutput.contains("Cheddar"));
        assertTrue(sortedOutput.contains("Lettuce"));
    }
}
