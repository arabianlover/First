package com.financeCalculator;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        ArrayList<Purchase> listOfPurchases = new ArrayList<Purchase>(Purchase.autoCreateArrayListOfPurchases());
        Purchase.printListOfPurchases(listOfPurchases);
        Purchase.calculateSumOfPrices(listOfPurchases);


        Application.launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 400);

        stage.setTitle("FinanceCalculator");
        stage.setScene(scene);
        stage.show();
    }
}
