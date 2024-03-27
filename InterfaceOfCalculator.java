package com.financeCalculator;

import javafx.application.Application;
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

        Button buttonAddPurchase = new Button("Add purchase"); //кнопка добавления новой покупки
        TextField dateAdd = new TextField();                        //поле для добавления даты
        TextField dateName = new TextField();                       //поле для добавления имени продукта
        TextField datePrice = new TextField();                      //поле для добавления цены

        stringsAddBlock.getChildren().add(new Text("Enter the date"));
        HBox enterDateHBox = new HBox();
        enterDateHBox.getChildren().add(new Text("Date "));
        enterDateHBox.getChildren().add(dateAdd);
        stringsAddBlock.getChildren().add(enterDateHBox);


        stringsAddBlock.getChildren().add(new Text("Enter the new purchase"));
        HBox enterNameAndPriceHBox = new HBox();
        enterNameAndPriceHBox.getChildren().add(new Text("Name "));
        enterNameAndPriceHBox.getChildren().add(dateName);
        enterNameAndPriceHBox.getChildren().add(new Text("Price "));
        enterNameAndPriceHBox.getChildren().add(datePrice);
        enterNameAndPriceHBox.getChildren().add(buttonAddPurchase);
        stringsAddBlock.getChildren().add(enterNameAndPriceHBox);


        buttonAddPurchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Purchase purchase = new Purchase(dateName.getText(), Integer.parseInt(datePrice.getText()));
                listOfPurchases.add(purchase);
                datePurchasesComboBox.getItems().addAll(purchase);
                dateName.clear();
                datePrice.clear();


            }
        });

    }

    public void createSearchBlock() {
        //здесь создается блок поиска по типу покупки и по имени покупки в указанном диапазоне дат

        Button buttonSearchPurchaseType = new Button("Search purchase type");       //кнопка поиска типа покупки
        Button buttonSearchPurchaseName = new Button("Search purchase name");        //кнопка поиска наименования покупки
        TextField fromDate = new TextField();                                           //поле ввода начальной даты
        TextField toDate = new TextField();                                             //поле ввода конечной даты

        stringsSearchBlock.getChildren().add(new Text("Search in a date range"));
        HBox dateRangeHBox = new HBox();
        dateRangeHBox.getChildren().add(new Text("from:"));
        dateRangeHBox.getChildren().add(fromDate);
        dateRangeHBox.getChildren().add(new Text("to:"));
        dateRangeHBox.getChildren().add(toDate);
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
                    textInfo.setText("Prise is " + purchase.getPrice());
                } else {
                    textInfo.setText("Purchase is not selected");
                }
            }
        });



    }

    public void createOutBlock() {
        //здесь создается блок вывода данных

        Text fromDateInfo = new Text();
        Text toDateInfo = new Text();
        Text dateInfo = new Text("Your purchases from " + fromDateInfo + " to " + toDateInfo);
        TextArea allPurchasesInPeriod = new TextArea();
        Text sum = new Text();
        Text sumOut = new Text("Sum is " + sum);

        stringsOutBlock.getChildren().add(dateInfo);
        stringsOutBlock.getChildren().add(allPurchasesInPeriod);
        stringsOutBlock.getChildren().add(sumOut);


    }

    @Override
    public void start(Stage stage) throws Exception {
        createAddBlock();
        strings.getChildren().add(stringsAddBlock);
        createSearchBlock();
        strings.getChildren().add(stringsSearchBlock);
        createOutBlock();
        strings.getChildren().add(stringsOutBlock);



        root.getChildren().add(strings);
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("FinanceCalculator");
        stage.setScene(scene);
        stage.show();

    }


}
