package main;

import controller.InventoryGenerator;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class SampleOrder {
    private ArrayList<AbstractBike> bikeOrder;
    private ArrayList<Accessories>  accessoryOrder;
    private Scanner userIn;
    private InventoryGenerator inventory;
    private AbstractBike bikeChoice;
    private Accessories bikeExtra;

    public SampleOrder() {
        bikeOrder = new ArrayList<>();
        accessoryOrder = new ArrayList<>();
        userIn = new Scanner(System.in);
        inventory = new InventoryGenerator();
    }

    public void welcomeMessage() {
        System.out.println("Welcome to ACME Bicycle Company");
        System.out.println("Your one stop shop for Bicycles and Accessories.");
        buySomething();
    }

    private void buySomething() {
        System.out.println("Are you here to buy accessories or a bike or both?");
        System.out.println("Your options are:\n1 for Accessories\n2 for a Bike");
        String type = userIn.next();
        validateSomething(type);
    }
    private void buyBike() {
        System.out.println("What kind of bike would you like?");
        System.out.println("Your options are:\nc - Cross Country\n" +
                "d - DownHill\nt - Touring\nv - Vintage");
        String type = userIn.next();
        validateBikeType(type);
        frameColorOffer(bikeChoice);
        offerUpgrades();
        bikeOrder.add(bikeChoice);
        anotherBike();
        orderDone();

    }

    private void orderDone() {
        // Create Sample Recipt
        new SampleRecept(bikeOrder, accessoryOrder);
    }

    private void anotherBike() {
        System.out.println("Do you want to add another bike? yes or no");
        String answer = userIn.next();
        validateAnotherBike(answer);
    }

    private void offerAccessories() {
        System.out.println("Do you want to buy any Accessories?");
        String answer = userIn.next();
        validateOffer(answer);
    }

    private void buyAccessories() {
        System.out.println("What Accessories are you interested in?");
        System.out.println("Your options are:\nh - Helmet\ng - Gloves\n" +
                "b - bell\nbk - basket\nt - tire pump\nbh - bottle + holder\n" +
                "l - lights");
        String item = userIn.next();
        validateAccessory(item.toLowerCase());
        accessoryOrder.add(bikeExtra);
        moreAccessories();
    }

    private void offerUpgrades() {
        System.out.println("We offer upgrades for your bicycle besides the gold frame color");
        System.out.println("We offer the following:\nls - leather seat\nlg - leather grips\n" +
                "wt - white tires\nnone - no upgrades");
        String upgrade = userIn.next();
        validateUpgrade(upgrade);
    }

    private void frameColorOffer(AbstractBike bike) {
        System.out.println("Our bikes come with a default paint color.");
        System.out.println("Your current frame color is "+bike.getColor().toString().toLowerCase());
        System.out.println("Would you like this bike with a different frame color? yes or no");
        String choice = userIn.next();
        switch (choice.toLowerCase()) {
            case "yes":
                frameColorChange(bike);
                break;
            case "no":
                break;
            default:
                System.out.println("You have entered an invalid choice.");
                frameColorOffer(bike);
        }
    }

    private void frameColorChange(AbstractBike bike) {
        System.out.println("What color frame would you like?");
        System.out.println("Your choices are:\nbk - black\nbl - blue\n" +
                "ch - chrome\ngr - green\nrd - red\ngd - gold (extra charge)");
        String color = userIn.next();
        validateColorChoice(color);
    }

    private void moreAccessories() {
        System.out.println("Do you want to add more Accessories? yes or no");
        String val = userIn.next();

        switch (val) {
            case "yes":
                bikeExtra = null;
                buyAccessories();
                break;
            case "no":
                orderDone();
                break;
            default:
                System.out.println("You have entered an invalid choice");
                moreAccessories();
        }
    }

    private void validateOffer(String answer) {
        switch(answer) {
            case "yes":
                buyAccessories();
                break;
            case "no":
                break;
            default:
                System.out.println("You have entered an invalid choice");
                offerAccessories();
        }
    }

    private void validateAnotherBike(String answer) {
        switch (answer.toLowerCase()) {
            case "yes":
                bikeChoice = null;
                buyBike();
                break;
            case "no":
                offerAccessories();
                break;
            default:
                System.out.println("You have entered an invalid choice");
                anotherBike();
        }
    }


    private void validateUpgrade(String upgrade) {
        switch (upgrade.toLowerCase()){
            case "ls":
                bikeChoice = new LeatherSeatOption(bikeChoice);
                break;
            case "lg":
                bikeChoice = new LeatherGripsOption(bikeChoice);
                break;
            case "wt":
                bikeChoice = new WhiteTireOption(bikeChoice);
                break;
            case "none":
                break;
            default:
                System.out.println("You have made an invalid choice.");
                offerUpgrades();
        }
    }

    private void validateColorChoice(String color) {
        switch(color) {
            case "bk":
                bikeChoice.paintFrame(FrameColors.BLACK);
                break;
            case "bl":
                bikeChoice.paintFrame(FrameColors.BLUE);
                break;
            case "ch":
                bikeChoice.paintFrame(FrameColors.CHROME);
                break;
            case "gr":
                bikeChoice.paintFrame(FrameColors.GREEN);
                break;
            case "rd":
                bikeChoice.paintFrame(FrameColors.RED);
                break;
            case "gd":
                bikeChoice = new GoldFrameOption(bikeChoice);
                break;
            default:
                System.out.println("You have entered and incorrect color.");
                frameColorChange(bikeChoice);
        }
    }

    private void validateBikeType(String type) {
        switch (type) {
            case "c":
                bikeChoice = inventory.getCrossCountryList().get(0);
                inventory.getCrossCountryList().remove(0);
                break;
            case "d":
                bikeChoice = inventory.getDownhillList().get(0);
                inventory.getDownhillList().remove(0);
                break;
            case "t":
                bikeChoice = inventory.getTouringList().get(0);
                inventory.getTouringList().remove(0);
                break;
            case "v":
                bikeChoice = inventory.getVintageList().get(0);
                inventory.getVintageList().remove(0);
                break;
            default:
                System.out.println("You have entered an incorrect type");
                buyBike();
        }
    }

    private void validateAccessory(String choice) {
        switch (choice) {
            case "h":
                bikeExtra = new Accessories("Helmet", 29.99);
                break;
            case "g":
                bikeExtra = new Accessories("Gloves", 19.99);
                break;
            case "b":
                bikeExtra = new Accessories("Bell", 9.99);
                break;
            case "bk":
                bikeExtra = new Accessories("Basket", 29.99);
                break;
            case "t":
                bikeExtra = new Accessories("Tire Pump", 39.99);
                break;
            case "bh":
                bikeExtra = new Accessories("Bottle & Holder", 34.99);
                break;
            case "l":
                bikeExtra = new Accessories("Lights", 36.99);
                break;
            default:
                System.out.println("You have entered an invalid choice");
                buyAccessories();
        }
    }

    private void validateSomething(String type) {
        switch (type) {
            case "1":
                buyAccessories();
                break;
            case "2":
                buyBike();
                break;
            default:
                System.out.println("You have entered an invalid choice");
                buySomething();
        }
    }
}
