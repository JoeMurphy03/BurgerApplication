package org.example.forthtry;

import java.util.ArrayList;
import java.util.Arrays;

public class BurgerMain {
    //need to create lists for bun, patties, cheese, let tom pic oni
    String bun;
    ArrayList<String> burger;
    ArrayList<String> cheese;
    ArrayList<String> garnish;
    double price;
    int burgerAmount;
    int cheeseAmount;

    public BurgerMain(String bun, ArrayList<String> burger, ArrayList<String> cheese, ArrayList<String> garnish, double price, int burgerAmount, int cheeseAmount)
    {
        this.bun = bun;
        this.burger = burger;
        this.cheese = cheese;
        this.garnish = garnish;
        this.price = price;
        this.burgerAmount = burgerAmount;
        this.cheeseAmount = cheeseAmount;
    }

    //need to set and get them based on inputs from user

    public String getBun() {
        return bun;
    }

    public void setBun(String bun) {
        this.bun = bun;
    }

    public ArrayList<String> getBurger() {
        return burger;
    }

    public void setBurger(ArrayList<String> burger) {
        this.burger = burger;
    }

    public ArrayList<String> getCheese()
    {
        return cheese;
    }


    public void setCheese(ArrayList<String> cheese)
    {
        this.cheese = cheese;
    }

    public ArrayList<String> getGarnish() {
        return garnish;
    }

    public void setGarnish(ArrayList<String> garnish) {
        this.garnish = garnish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getBurgerAmount() {
        return burgerAmount;
    }

    public void setBurgerAmount(int burgerAmount) {
        this.burgerAmount = burgerAmount;
    }

    public int getCheeseAmount() {
        return cheeseAmount;
    }

    public void setCheeseAmount(int cheeseAmount) {
        this.cheeseAmount = cheeseAmount;
    }

    public String sort()
    {
        int garnishAmount = getGarnish().size();

        String burgerString = String.format("%s Price: .99 @ %d : %.2f\n", getBurger().toString(), getBurgerAmount(), .99 * getBurgerAmount());
        String cheeseString = String.format("%s Price: .99 @ %d : %.2f\n", getCheese().toString(), getCheeseAmount(), .99 * getCheeseAmount());
        String garnishString = String.format("%s Price: .99 @ %d : %.2f\n", getGarnish().toString(), getGarnish().size(), .99 * getGarnish().size());


        if((getBurgerAmount() <= getCheeseAmount()) && (getCheeseAmount() <= garnishAmount))
        {
            if(getCheeseAmount() <= garnishAmount)
            {
                //order is burg chz garnish
                return (burgerString + cheeseString + garnishString);
            }
            if(garnishAmount <= getCheeseAmount())
            {
                //order is burg garnish chz
                return (burgerString + garnishString + cheeseString);
            }
        }
        if((getCheeseAmount() <= getBurgerAmount()) && (getCheeseAmount() <= garnishAmount))
        {
            if(getBurgerAmount() <= garnishAmount)
            {
                //order is cheese burg garnish
                return (cheeseString + burgerString + garnishString);
            }
            if(garnishAmount <= getBurgerAmount())
            {
                //order is cheese garnish burger
                return (cheeseString + garnishString + burgerString);
            }
        }
        if((garnishAmount <= getBurgerAmount()) && (garnishAmount <= getCheeseAmount()))
        {
            if(getCheeseAmount() <= getBurgerAmount())
            {
                //order is garnish chs burg
                return (garnishString + cheeseString + burgerString);
            }
            if(getBurgerAmount() <= getCheeseAmount())
            {
                //order is garnish burg chz
                return (garnishString + burgerString + cheeseString);
            }
        }

        return null;
    }





    //need to display burger price sorted
    public String displayBurger()
    {

        System.out.print("Created Burger \n\n");
        System.out.println(getBurger().toString() + "Price: .99 @ " + getBurgerAmount() + " : " + formatDouble(.99 * getBurgerAmount()));
        System.out.println(getCheese().toString() + "Price: .99 @ " + getCheeseAmount() + " : " + formatDouble(.99 * getCheeseAmount()));
        System.out.println(getGarnish().toString() + "Price: .99 @ " + getGarnish().size() + " : " + formatDouble(.99 * getGarnish().size()));
        System.out.println("Total Price : " + formatDouble(getPrice()));

        String bunString = String.format("%s Price: .49\n", getBun());
        String burgerString = String.format("%s Price: .99 @ %d : %.2f\n", getBurger().toString(), getBurgerAmount(), .99 * getBurgerAmount());
        String cheeseString = String.format("%s Price: .99 @ %d : %.2f\n", getCheese().toString(), getCheeseAmount(), .99 * getCheeseAmount());
        String garnishString = String.format("%s Price: .99 @ %d : %.2f\n", getGarnish().toString(), getGarnish().size(), .99 * getGarnish().size());
        String totalString = ("Total Price: " + formatDouble(getPrice()));

        if(sort() == null)
        {
            return bunString + burgerString + cheeseString + garnishString + totalString;
        }
        return bunString + sort() + totalString;
    }

    public static Double formatDouble(double num) {
        return Double.parseDouble(String.format("%." + 2 + "f", num));
    }

    public String toString() {
        return "Burger Details:\n" +
                "Bun: " + bun + "\n" +
                "Burger: " + burger + "\n" +
                "Cheese: " + cheese + "\n" +
                "Garnish: " + garnish + "\n" +
                "Total Price: $" + formatDouble(price);
    }


}