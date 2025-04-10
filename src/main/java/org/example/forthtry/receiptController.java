package org.example.forthtry;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class receiptController {

    @FXML public Text burgerInfoDisplay;
    public String burgerInfo;
    @FXML public Button createNewBurger;
    @FXML public Button displayBurgers;
    @FXML public TextArea burgerDisplay;
    int burgerAmount;
    int cheeseAmount;

    public ArrayList<BurgerMain> burgers = new ArrayList<>();

    public String getBurgerInfo()
    {
        return burgerInfo;
    }

    public void setBurgerInfo(ArrayList<BurgerMain> burgers, int burgerAmount, int cheeseAmount)
    {
        //System.out.print(burgers.toString());
        //burgerInfo = burgers.toString();
        //this.burgerInfo = burgerInfo;

        StringBuilder burgerDetails = new StringBuilder();
        for(BurgerMain burger : burgers) {
            burgerDetails.append(burger.displayBurger(burgerAmount, cheeseAmount)).append("\n\n");
            //burgerDetails.append(burger.toString());
        }
        this.burgerInfo = burgerDetails.toString();
        this.burgers = burgers;
    }


    private void setBurgerScene(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        HelloController controller = loader.getController();
        controller.setBurgers(burgers);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void setBurgerAmount(int burgerAmount)
    {
        this.burgerAmount = burgerAmount;
    }

    public int getBurgerAmount()
    {
        return burgerAmount;
    }

    public void setCheeseAmount(int cheeseAmount)
    {
        this.cheeseAmount = cheeseAmount;
    }

    public int getCheeseAmount()
    {
        return cheeseAmount;
    }







    @FXML public void initialize() {
        //welcomeText.setText("Your Created Burger");
        //burgerInfoDisplay.setText(burgerInfo);
        //System.out.println("\n\n Burger Info from receipt Controller");
        //System.out.println(getBurgerInfo());
        createNewBurger.setText("Create Another Burger");
        displayBurgers.setText("Display Receipt");
        //burgerDisplay.setText(burgerInfo);


        createNewBurger.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                try {
                    setBurgerScene(actionEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        displayBurgers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(getBurgerInfo());
                //System.out.println("Get burger Amount" + getCheeseAmount());
                //System.out.println("Get Burger Amount" + getBurgerAmount());
                burgerDisplay.setText(getBurgerInfo());
            }
        });
        //burgerDisplay.setText(getBurgerInfo());
    }




}
