package controller;

import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Animals.*;
import model.Application.AdoptionCentre;
import model.Exceptions.InvalidOperationException;

public class AddAnimalController extends Controller<AdoptionCentre> {
    @FXML private ImageView banner;
    @FXML private ChoiceBox<String> typeChoice;
    @FXML private TextField nameField;
    @FXML private TextField ageField;

    @FXML
    private void initialize() {
        
        banner.setImage(new Image("file:src/view/image/cat_banner.jpg"));
        typeChoice.getItems().addAll("Cat", "Dog", "Rabbit");
        typeChoice.getSelectionModel().selectFirst();
    }

    @FXML
    private void onAdd() {
        try {
            String type = typeChoice.getValue();
            String name = nameField.getText().trim();
            int age     = Integer.parseInt(ageField.getText().trim());

            Animal a;
            switch (type) {
                case "Cat":    a = new Cat(name, age);    break;
                case "Dog":    a = new Dog(name, age);    break;
                default:       a = new Rabbit(name, age); break;
            }

            model.getAnimals().add(a);
            stage.close();
        }
        catch (NumberFormatException nfe) {
            
            try {
                ViewLoader.showStage(
                  new InvalidOperationException("Age must be an integer"),
                  "/view/ErrorView.fxml",
                  "Error",
                  new Stage()
                );
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    @FXML
    private void onClose() {
        stage.close();
    }
}
