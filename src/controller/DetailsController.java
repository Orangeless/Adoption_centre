package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Application.AdoptionCentre;
import model.Animals.Animal;
import model.Users.Customer;

public class DetailsController extends Controller<AdoptionCentre> {
    @FXML private ImageView       banner;
    @FXML private Label           nameLabel;
    @FXML private ListView<Animal> adoptedList;
     @FXML private VBox      root;

    @FXML
    private void initialize() {
        
        banner.setImage(new Image("file:src/view/image/cat_banner.jpg"));

         banner.setFitWidth(400);
banner.setPreserveRatio(true);
banner.setSmooth(true);
       
        Customer customer = (Customer)AdoptionCentre.getLoggedInUser();

        
        nameLabel.setText(customer.getName());
        adoptedList.setItems(customer.adoptedAnimals().getAnimals());
    }

    @FXML
    private void onClose() {
        stage.close();
    }
}
