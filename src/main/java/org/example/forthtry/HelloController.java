package org.example.forthtry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class HelloController {
    @FXML private Text bun;
    @FXML private ChoiceBox<String> bunBox;
    @FXML private Text cheese;
    @FXML private Text pattie;
    @FXML private ChoiceBox<String> cheeseBox;
    @FXML private ChoiceBox<String> pattyBox;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private Text burgerInfo;

    final ToggleGroup chsAmn = new ToggleGroup();
    @FXML RadioButton rb1 = new RadioButton();
    @FXML RadioButton rb2 = new RadioButton();
    @FXML RadioButton rb3 = new RadioButton();
    @FXML RadioButton rb4 = new RadioButton();
    final ToggleGroup pattyAmnt = new ToggleGroup();
    @FXML RadioButton rb5 = new RadioButton();
    @FXML RadioButton rb6 = new RadioButton();
    @FXML RadioButton rb7 = new RadioButton();
    @FXML RadioButton rb8 = new RadioButton();
    @FXML CheckBox lettuce = new CheckBox();
    @FXML CheckBox tomato = new CheckBox();
    @FXML CheckBox onion = new CheckBox();
    @FXML CheckBox pickle = new CheckBox();
    @FXML CheckBox bacon = new CheckBox();
    @FXML CheckBox avocado = new CheckBox();
    @FXML CheckBox egg = new CheckBox();
    @FXML CheckBox grilledO = new CheckBox();

    @FXML Button create = new Button();
    @FXML Button resetBurger = new Button();
    @FXML Button displayBurgers = new Button();

    ArrayList<BurgerMain> burgers = new ArrayList<BurgerMain>();

    @FXML public void initialize() {
        bunBox.getItems().addAll("Sesame Seed", "Brioche", "Potato", "Pretzel");
        cheeseBox.getItems().addAll("American", "Swiss", "Cheddar", "PepperJack");
        pattyBox.getItems().addAll("Beef","Chicken","Pork","Steak");
        togGroup(rb1, chsAmn, rb2, rb3, rb4);
        togGroup(rb5, pattyAmnt, rb6, rb7, rb8);
        bunBox.setValue("Choose");
        cheeseBox.setValue("Choose");
        pattyBox.setValue("Choose");
        lettuce.setText("Lettuce");
        tomato.setText("Tomato");
        onion.setText("Onion");
        pickle.setText("Pickle");
        bacon.setText("Bacon");
        avocado.setText("Avocado");
        egg.setText("Fried Egg");
        grilledO.setText("Grilled Onion");

        //submit and new burger buttons
        create.setText("Submit Burger");
        resetBurger.setText("Create New Burger");
        displayBurgers.setText("Display Burgers");

        CheckBox[] myCheckboxes = {lettuce, tomato, onion, pickle, bacon, avocado, egg, grilledO};
        int maxSel = 4;

        for (CheckBox cb : myCheckboxes) {
            cb.selectedProperty().addListener((o, oldV, newV) -> {
                int sel = 0;

                for (CheckBox c : myCheckboxes) {
                    if (c.isSelected()) sel++;
                }

                if (sel > maxSel) {
                    cb.setSelected(false); // Prevent selection if limit exceeded
                }
            });
        }

        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                burgers.add(createBurger());

            }
        });

        resetBurger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bunBox.setValue("Choose");
                pattyBox.setValue("Choose");
                cheeseBox.setValue("Choose");
                rb1.setSelected(false);
                rb2.setSelected(false);
                rb3.setSelected(false);
                rb4.setSelected(false);
                rb5.setSelected(false);
                rb6.setSelected(false);
                rb7.setSelected(false);
                rb8.setSelected(false);
                lettuce.setSelected(false);
                tomato.setSelected(false);
                onion.setSelected(false);
                pickle.setSelected(false);
                bacon.setSelected(false);
                egg.setSelected(false);
                avocado.setSelected(false);
                egg.setSelected(false);
                grilledO.setSelected(false);
            }
        });

        displayBurgers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                StringBuilder burgerDetails = new StringBuilder();
                for(BurgerMain burg : burgers)
                {
                    burgerDetails.append(burg.displayBurger(burgerAmount(), cheeseAmount())).append("\n\n");
                }
                burgerInfo.setText("Created Burger(s)\n" + burgerDetails.toString());

                try {
                    setReceiptScene(actionEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });
    }

    public void setBurgers(ArrayList<BurgerMain> savedBurgers) {
        this.burgers = savedBurgers;
    }

    private BurgerMain createBurger()
    {
        BurgerMain newBurger = new BurgerMain(null, null, null, null, 0.00);
        addBun(newBurger);
        addPatty(newBurger);
        addCheese(newBurger);
        addGarnish(newBurger);

        return newBurger;
    }



    private void setReceiptScene(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("receipt.fxml"));
        Parent root = loader.load();

        receiptController controller = loader.getController();
        //FXMLLoader controller = loader.getController();
        controller.setBurgerInfo(burgers, burgerAmount(), cheeseAmount());
        controller.setBurgerAmount(burgerAmount());
        controller.setCheeseAmount(cheeseAmount());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void togGroup(RadioButton first, ToggleGroup group, RadioButton second, RadioButton third, RadioButton forth) {
        first.setToggleGroup(group);
        second.setToggleGroup(group);
        third.setToggleGroup(group);
        forth.setToggleGroup(group);
        first.setText("1");
        second.setText("2");
        third.setText("3");
        forth.setText("4");
    }

    private void addBun(BurgerMain burger)
    {
        burger.setBun(bunBox.getValue());
        burger.setPrice(burger.getPrice() + (.99));
    }

    public int cheeseAmount()
    {
        int cheeseCount = 1;
        if(rb1.isSelected()) {
            cheeseCount = 1;
        } else if (rb2.isSelected()) {
            cheeseCount = 2;
        } else if (rb3.isSelected()) {
            cheeseCount = 3;
        } else if (rb4.isSelected()) {
            cheeseCount = 4;
        }
        return cheeseCount;
    }

    public int burgerAmount()
    {
        int pattyCount = 1;
        if(rb5.isSelected()) {
            pattyCount = 1;
        } else if (rb6.isSelected()) {
            pattyCount = 2;
        } else if (rb7.isSelected()) {
            pattyCount = 3;
        } else if (rb8.isSelected()) {
            pattyCount = 4;
        }

        return pattyCount;
    }


    private void addCheese(BurgerMain burger)
    {
        ArrayList<String> cheese = new ArrayList<>();
        //String[] cheese = new String[cheeseCount];


        cheese.add(cheeseBox.getValue());
        burger.setCheese(cheese);
        burger.setPrice(burger.getPrice() + (cheeseAmount() * .99));
    }

    private void addPatty(BurgerMain burger)
    {
        //String[] patties = new String[pattyCount];
        ArrayList<String> patties = new ArrayList<>();

        patties.add(pattyBox.getValue());

        burger.setBurger(patties);
        burger.setPrice(burger.getPrice() + (burgerAmount() * .99));
    }

    private void addGarnish(BurgerMain burger)
    {
        //String[] garnish = new String[4];
        ArrayList<String> garnish = new ArrayList<>();
        int i = 0;
        int[] selected = new int[4];
        for(int j = 0; j < 4; j++)
        {
            if (lettuce.isSelected()) {
                garnish.add("Lettuce");
                selected[i] = 1;
                i++;
                lettuce.setSelected(false);

                burger.setPrice(burger.getPrice() + .49);
            } else if (tomato.isSelected()) {
                garnish.add("Tomato");
                selected[i] = 2;
                i++;
                tomato.setSelected(false);
                burger.setPrice(burger.getPrice() + .49);
            } else if (onion.isSelected()) {
                garnish.add("Onion");
                selected[i] = 3;
                i++;
                onion.setSelected(false);
                burger.setPrice(burger.getPrice() + .49);
            } else if (pickle.isSelected()) {
                garnish.add("Pickle");
                selected[i] = 4;
                i++;
                pickle.setSelected(false);
                burger.setPrice(burger.getPrice() + .49);
            } else if (bacon.isSelected()) {
                garnish.add("Bacon");
                selected[i] = 5;
                i++;
                bacon.setSelected(false);
                burger.setPrice(burger.getPrice() + .49);
            } else if (avocado.isSelected()) {
                garnish.add("Avocado");
                selected[i] = 6;
                i++;
                avocado.setSelected(false);
                burger.setPrice(burger.getPrice() + .49);
            } else if (egg.isSelected()) {
                garnish.add("Fried Egg");
                selected[i] = 7;
                i++;
                egg.setSelected(false);
                burger.setPrice(burger.getPrice() + .49);
            } else if (grilledO.isSelected()) {
                garnish.add("Grilled Onion");
                selected[i] = 8;
                i++;
                grilledO.setSelected(false);

                burger.setPrice(burger.getPrice() + .49);
            }
        }

        for (int value : selected) {
            if (value == 1) {
                lettuce.setSelected(true);
            }
            if (value == 2) {
                tomato.setSelected(true);
            }
            if (value == 3) {
                onion.setSelected(true);
            }
            if (value == 4) {
                pickle.setSelected(true);
            }
            if (value == 5) {
                bacon.setSelected(true);
            }
            if (value == 6) {
                avocado.setSelected(true);
            }
            if (value == 7) {
                egg.setSelected(true);
            }
            if (value == 8) {
                grilledO.setSelected(true);
            }
        }

        burger.setGarnish(garnish);

    }



}