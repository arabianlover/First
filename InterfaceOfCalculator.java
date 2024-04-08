package com.financeCalculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;

public class InterfaceOfCalculator extends Application {

    private ArrayList<Purchase> listOfPurchases= new ArrayList<>();

    public Group root = new Group();
    public VBox strings = new VBox();//VBox для всех блоков
    public VBox stringsAddBlock = new VBox();//VBox для блока ввода новых данных
    public VBox stringsSearchBlock = new VBox();//VBox для блока поиска данных
    public VBox stringsOutBlock = new VBox();//VBox для блока вывода данных
    //ComboBox<Purchase> datePurchasesComboBox = new ComboBox<>();//выпадающий список со всеми покупками за все даты


    public void createAddBlock() {
        //здесь создается блок ввода даты, а также список имен-цен продуктов на эту дату

        Button buttonAddPurchase = new Button("Add purchase");    //кнопка добавления новой покупки
        TextField addName = new TextField();                         //поле для добавления имени продукта
        TextField addPrice = new TextField();                        //поле для добавления цены
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        ComboBox <String> allTypesComboBox = new ComboBox();
        allTypesComboBox.getItems().addAll("Food", "Clothes", "Travels", "Sweets", "Alcohol",
                "House service", "Medicine", "For friends", "Hygiene", "Connection", "Others");//список для выбора типа покупки
        allTypesComboBox.setValue("Food");

        HBox enterDateHBox = new HBox();
        stringsAddBlock.getChildren().add(enterDateHBox);

        stringsAddBlock.getChildren().add(new Text("Enter the new purchase"));
        HBox enterNameAndPriceHBox = new HBox();
        enterNameAndPriceHBox.getChildren().add(new Text("Name: "));
        enterNameAndPriceHBox.getChildren().add(addName);
        enterNameAndPriceHBox.getChildren().add(new Text("Price: "));
        enterNameAndPriceHBox.getChildren().add(addPrice);
        enterNameAndPriceHBox.getChildren().add(new Text("Date: "));
        enterNameAndPriceHBox.getChildren().add(datePicker);
        enterNameAndPriceHBox.getChildren().add(allTypesComboBox);
        enterNameAndPriceHBox.getChildren().add(buttonAddPurchase);
        stringsAddBlock.getChildren().add(enterNameAndPriceHBox);

        buttonAddPurchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                LocalDate date = datePicker.getValue();
                Purchase purchase = new Purchase(date, addName.getText(), Integer.parseInt(addPrice.getText()),
                        (String)allTypesComboBox.getSelectionModel().getSelectedItem());

                listOfPurchases.add(purchase);
                //datePurchasesComboBox.getItems().addAll(purchase);
                addName.clear();
                addPrice.clear();
            }
        });
    }

    public void createSearchBlock() {
        //здесь создается блок поиска по типу покупки и по имени покупки в указанном диапазоне дат

        ComboBox comboBoxTypes = new ComboBox();
        DatePicker fromDatePicker = new DatePicker();
        DatePicker toDatePicker = new DatePicker();
        fromDatePicker.setValue(LocalDate.now());
        toDatePicker.setValue(LocalDate.now());

        Button buttonGetDateInfo = new Button("Ok");
        stringsSearchBlock.getChildren().add(new Text("Search "));
        HBox dateRangeHBox = new HBox();
        dateRangeHBox.getChildren().add(new Text("from:"));
        dateRangeHBox.getChildren().add(fromDatePicker);
        dateRangeHBox.getChildren().add(new Text("to:"));
        dateRangeHBox.getChildren().add(toDatePicker);

        comboBoxTypes.getItems().addAll("All", "Food", "Clothes", "Travels", "Sweets", "Alcohol",
                "House service", "Medicine", "For friends", "Hygiene", "Connection", "Others");
        comboBoxTypes.setValue("All");

        dateRangeHBox.getChildren().add(comboBoxTypes);
        dateRangeHBox.getChildren().add(buttonGetDateInfo);
        stringsSearchBlock.getChildren().add(dateRangeHBox);

        VBox searchType = new VBox();
        HBox searchInfo = new HBox();
        Text textInfo = new Text();

        searchInfo.getChildren().add(textInfo);
        searchType.getChildren().add(searchInfo);
        stringsSearchBlock.getChildren().add(searchType);

        Text dateInfo = new Text();
        TextArea allPurchasesInPeriod = new TextArea();
        ArrayList<String> allPurchasesList = new ArrayList<>();
        Text sumOut = new Text();

        stringsOutBlock.getChildren().add(dateInfo);
        stringsOutBlock.getChildren().add(allPurchasesInPeriod);
        stringsOutBlock.getChildren().add(sumOut);

        buttonGetDateInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                refresh(allPurchasesList);

                LocalDate fromDate = fromDatePicker.getValue();//переменные для указания диапазона дат в поиске
                LocalDate toDate = toDatePicker.getValue();

                if(!fromDate.equals(null) && !toDate.equals(null)){

                    dateInfo.setText("Your purchases from " + fromDatePicker.getValue() + " to " + toDatePicker.getValue());
                } else {
                    dateInfo.setText("Enter the dates, please");
                }

                double sum = 0;
                for(Purchase purchase : listOfPurchases){
                    if(purchase.getDate().isAfter(fromDate) && purchase.getDate().isBefore(toDate)){

                        if(comboBoxTypes.getValue().equals("All")){
                            allPurchasesList.add(purchase.getName() + " - " + purchase.getPrice());
                            sum = sum + purchase.getPrice();
                            sumOut.setText("Sum is: " + sum);
                        } else if(comboBoxTypes.getValue().equals(purchase.getType())){
                            allPurchasesList.add(purchase.getName() + " - " + purchase.getPrice());
                            sum = sum + purchase.getPrice();
                            sumOut.setText("Sum is: " + sum);
                        }
                    }
                }
                String separator = "\n";
                String res = String.join(separator, allPurchasesList);
                allPurchasesInPeriod.setText(res);
            }
        });
    }

    public static void refresh(ArrayList<String> stringArrayList){
        stringArrayList.clear();
    }

    @Override
    public void start(Stage stage) throws Exception {
        createAddBlock();
        strings.getChildren().add(stringsAddBlock);
        createSearchBlock();
        strings.getChildren().add(stringsSearchBlock);
        strings.getChildren().add(stringsOutBlock);

        root.getChildren().add(strings);
        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("FinanceCalculator");
        stage.setScene(scene);
        stage.show();
    }
}
