package com.financeCalculator;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class Purchase {

    //Этот класс содержит поля: название, цена покупки, сумма цен. Список всех имен покупок


    private LocalDate date;
    private String name;
    private double price;
    private String type;

    private static double sumOfPrices;

    private static ArrayList<String> listOfNames = new ArrayList<>();

    public Purchase(short day, short month, short year, String name, double price, String type){
        this.date = LocalDate.of(year, month, day);
        this.name = name;
        this.price = price < 0 ? 0 : price;
        listOfNames.add(name);
    }


    public String toString(){
        return name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price < 0 ? 0 : price;
    }

    public Short getDay(){
        return (short) date.getDayOfMonth();
    }
    public Short getMonth(){
        return (short) date.getMonthValue();
    }
    public Short getYear(){
        return (short) date.getYear();
    }
    public LocalDate getDate(){
        return date;
    }
    public String getType(){
        return type;
    }


    /*public static ArrayList<Purchase> autoCreateArrayListOfPurchases(){
        ArrayList<Purchase> listOfPurchases = new ArrayList<>();
        Purchase purchase = new Purchase("water", 300);
        Purchase purchase1 = new Purchase("bread", 100);
        Purchase purchase2 = new Purchase("honey", 2000);
        Purchase purchase3 = new Purchase("milk", 500);
        Purchase purchase4 = new Purchase("chicken", 1500);
        listOfPurchases.add(purchase);
        listOfPurchases.add(purchase1);
        listOfPurchases.add(purchase2);
        listOfPurchases.add(purchase3);
        listOfPurchases.add(purchase4);

        return listOfPurchases;
    }*/

    /*public static ArrayList<Purchase> createArrayListOfPurchases(){

        //Этот метод создает список объектов Purchase
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        ArrayList<Purchase> listOfPurchases = new ArrayList<>();

        while (true) {
            String name;
            System.out.println("Enter the name of purchase:");
            name = scanner.nextLine();
            if (name.equals("out"))
                break;
            System.out.println("Enter the price of purchase:");
            listOfPurchases.add(new Purchase(name, scanner1.nextDouble()));
        }

        return listOfPurchases;
    }*/

    public static void printListOfPurchases(ArrayList<Purchase> listOfPurchases){

        //Этот метод выводит список объектов Purchase на экран
        for (Purchase purchase : listOfPurchases) {
            System.out.println(purchase.getName() + "\t " + purchase.getPrice());
        }
    }

    public static double calculateSumOfPrices(ArrayList<Purchase> listOfPurchases){

        //Этот метод считает сумму аргументов price в списке объектов Purchase
        for(Purchase purchase : listOfPurchases){
            sumOfPrices += purchase.getPrice();
        }
        System.out.println("Sum of purchases is " + sumOfPrices);
        return sumOfPrices;
    }

    public static void printNames(){
        for (int i = 0; i < listOfNames.size(); i++) {
            System.out.println(listOfNames.get(i));
        }
    }


}
