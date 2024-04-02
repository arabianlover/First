package com.financeCalculator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class InterfaceOfCalculator extends Application {

    private ArrayList<Purchase> listOfPurchases= new ArrayList<>();

    public Group root = new Group();
    public VBox strings = new VBox();//VBox для всех блоков
    final private int WIDTH = 1000;
    final private int HEIGHT = 600;

    public VBox stringsAddBlock = new VBox();//VBox для блока ввода новых данных
    public VBox stringsSearchBlock = new VBox();//VBox для блока поиска данных
    public VBox stringsOutBlock = new VBox();//VBox для блока вывода данных

    ComboBox<Purchase> datePurchasesComboBox = new ComboBox<>();//выпадающий список со всеми покупками за все даты


    public void createAddBlock() {
        //здесь создается блок ввода даты, а также список имен-цен продуктов на эту дату

        Button buttonAddPurchase = new Button("Add purchase");    //кнопка добавления новой покупки
        TextField addDay = new TextField();                          //поле для добавления дня
        TextField addMonth = new TextField();                        //поле для добавления месяца
        TextField addYear = new TextField();                         //поле для добавления года
        TextField addName = new TextField();                         //поле для добавления имени продукта
        TextField addPrice = new TextField();                        //поле для добавления цены

        ObservableList<String> allTypes = FXCollections.observableArrayList
                ("Food", "Clothes", "Travels", "Sweets", "Alcohol", "House service", "Medicine", "For friends", "Hygiene",
                        "Connection", "Others");
        ComboBox <String> allTypesComboBox = new ComboBox(allTypes);                 //список для выбора типа покупки




        stringsAddBlock.getChildren().add(new Text("Enter the date"));
        HBox enterDateHBox = new HBox();
        enterDateHBox.getChildren().add(new Text("Day: "));
        enterDateHBox.getChildren().add(addDay);
        enterDateHBox.getChildren().add(new Text("Month: "));
        enterDateHBox.getChildren().add(addMonth);
        enterDateHBox.getChildren().add(new Text("Year: "));
        enterDateHBox.getChildren().add(addYear);
        stringsAddBlock.getChildren().add(enterDateHBox);


        stringsAddBlock.getChildren().add(new Text("Enter the new purchase"));
        HBox enterNameAndPriceHBox = new HBox();
        enterNameAndPriceHBox.getChildren().add(new Text("Name "));
        enterNameAndPriceHBox.getChildren().add(addName);
        enterNameAndPriceHBox.getChildren().add(new Text("Price "));
        enterNameAndPriceHBox.getChildren().add(addPrice);
        enterNameAndPriceHBox.getChildren().add(allTypesComboBox);
        enterNameAndPriceHBox.getChildren().add(buttonAddPurchase);
        stringsAddBlock.getChildren().add(enterNameAndPriceHBox);


        buttonAddPurchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Purchase purchase = new Purchase(Short.parseShort(addDay.getText()), Short.parseShort(addMonth.getText()),
                        Short.parseShort(addYear.getText()), addName.getText(), Integer.parseInt(addPrice.getText()),
                        (String)allTypesComboBox.getSelectionModel().getSelectedItem());
                listOfPurchases.add(purchase);
                datePurchasesComboBox.getItems().addAll(purchase);
                addName.clear();
                addPrice.clear();


            }
        });

    }

    public void createSearchBlock() {
        //здесь создается блок поиска по типу покупки и по имени покупки в указанном диапазоне дат

        Button buttonSearchPurchaseType = new Button("Search purchase type");       //кнопка поиска типа покупки
        Button buttonSearchPurchaseName = new Button("Search purchase name");        //кнопка поиска наименования покупки
        TextField fromDay = new TextField();//поля ввода начальной даты
        TextField fromMonth = new TextField();
        TextField fromYear = new TextField();
        TextField toDay = new TextField();//поля ввода конечной даты
        TextField toMonth = new TextField();
        TextField toYear = new TextField();



        Button buttonGetDateInfo = new Button("Ok");
        stringsSearchBlock.getChildren().add(new Text("Search in a date range"));
        HBox dateRangeHBox = new HBox();
        dateRangeHBox.getChildren().add(new Text("from:"));
        dateRangeHBox.getChildren().add(new Text("day: "));
        dateRangeHBox.getChildren().add(fromDay);
        dateRangeHBox.getChildren().add(new Text("month: "));
        dateRangeHBox.getChildren().add(fromMonth);
        dateRangeHBox.getChildren().add(new Text("year: "));
        dateRangeHBox.getChildren().add(fromYear);

        dateRangeHBox.getChildren().add(new Text("to:"));
        dateRangeHBox.getChildren().add(new Text("day: "));
        dateRangeHBox.getChildren().add(toDay);
        dateRangeHBox.getChildren().add(new Text("month: "));
        dateRangeHBox.getChildren().add(toMonth);
        dateRangeHBox.getChildren().add(new Text("year: "));
        dateRangeHBox.getChildren().add(toYear);
        dateRangeHBox.getChildren().add(buttonGetDateInfo);
        stringsSearchBlock.getChildren().add(dateRangeHBox);



        VBox searchType = new VBox();
        HBox searchInfo = new HBox();
        Button buttonGetInfo = new Button("Info");
        Text textInfo = new Text();
        searchType.getChildren().add(new Text("Enter the type of purchase:"));
        searchInfo.getChildren().add(datePurchasesComboBox);
        searchInfo.getChildren().add(buttonGetInfo);
        searchInfo.getChildren().add(textInfo);
        searchType.getChildren().add(searchInfo);
        stringsSearchBlock.getChildren().add(searchType);
        
        

        buttonGetInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Purchase purchase = (Purchase) datePurchasesComboBox.getSelectionModel().getSelectedItem();
                if (purchase != null){
                    textInfo.setText("Type is " + purchase.getType() + " " + "Prise is " + purchase.getPrice() + " " +
                            "Date is " + purchase.getDay() + ":" + purchase.getMonth() + ":" + purchase.getYear());
                } else {
                    textInfo.setText("Purchase is not selected");
                }
            }
        });

        //здесь создается блок вывода данных


        Text dateInfo = new Text();
        TextArea allPurchasesInPeriod = new TextArea();
        ArrayList<String> stringArrayList = new ArrayList<>();
        Text sum = new Text();

        stringsOutBlock.getChildren().add(dateInfo);
        stringsOutBlock.getChildren().add(allPurchasesInPeriod);
        stringsOutBlock.getChildren().add(sum);






        buttonGetDateInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                LocalDate fromDate = LocalDate.now();//переменные для указания диапазона дат в поиске
                LocalDate toDate = LocalDate.now();

                if ((!fromDay.getText().isEmpty()) && (!fromMonth.getText().isEmpty()) && (!fromYear.getText().isEmpty())
                        && (!toDay.getText().isEmpty()) && (!toMonth.getText().isEmpty()) && (!toYear.getText().isEmpty())){

                    fromDate = fromDate.plusYears(Integer.parseInt(fromYear.getText()) - fromDate.getYear());
                    fromDate = fromDate.plusMonths(Integer.parseInt(fromMonth.getText()) - fromDate.getMonthValue());
                    fromDate = fromDate.plusDays(Integer.parseInt(fromDay.getText()) - fromDate.getDayOfMonth());

                    toDate = toDate.plusYears(Integer.parseInt(toYear.getText()) - toDate.getYear());
                    toDate = toDate.plusMonths(Integer.parseInt(toMonth.getText()) - toDate.getMonthValue());
                    toDate = toDate.plusDays(Integer.parseInt(toDay.getText()) - toDate.getDayOfMonth());




                    dateInfo.setText("Your purchases from " + fromDate.getDayOfMonth() + ":" + fromDate.getMonth()
                            + ":" + fromDate.getYear() + " to " + toDate.getDayOfMonth() + ":" + toDate.getMonth()
                            + ":" + toDate.getYear());


                } else {
                    dateInfo.setText("Enter the dates, please");
                }
                double summa = 0;
                for(Purchase purchase : listOfPurchases){
                    if(purchase.getDate().isAfter(fromDate) && purchase.getDate().isBefore(toDate)){
                        stringArrayList.add(purchase.getName() + " - " + purchase.getPrice());
                        summa = summa + purchase.getPrice();

                        sum.setText("Sum is: " + summa);
                    }
                }


                String separator = "\n";
                String res = String.join(separator, stringArrayList);

                allPurchasesInPeriod.setText(res);







            }
        });







    }



    @Override
    public void start(Stage stage) throws Exception {
        createAddBlock();
        strings.getChildren().add(stringsAddBlock);
        createSearchBlock();
        strings.getChildren().add(stringsSearchBlock);
        strings.getChildren().add(stringsOutBlock);



        root.getChildren().add(strings);
        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("FinanceCalculator");
        stage.setScene(scene);
        stage.show();

    }


}
