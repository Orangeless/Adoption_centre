package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Application.AdoptionCentre;
import model.Animals.Animal;
import model.Users.Customer;

public class CustomerDashboardController extends Controller<AdoptionCentre> {
    @FXML private ImageView        banner;
    @FXML private Label            welcomeLabel;
    @FXML private ListView<Animal> animalListView;

    private FilteredList<Animal> available;
    private Customer customer;

    @FXML
    private void initialize() {
        
        banner.setImage(new Image("file:src/view/image/cat_banner.jpg"));
        

        
        customer = (Customer)AdoptionCentre.getLoggedInUser();
        welcomeLabel.setText("Welcome " + customer.getFirstName());

        
        available = new FilteredList<>(
            model.getAnimals().getAnimals(),
            a -> !a.isAdopted()
        );
        animalListView.setItems(available);

        
         animalListView.setCellFactory(lv -> new ListCell<Animal>() {
        @Override
        protected void updateItem(Animal a, boolean empty) {
            super.updateItem(a, empty);
            setText(empty || a == null
                ? null
                : String.format("%s (Age: %d)", a.getName(), a.ageProperty().get()));
        }
    });
    }

    @FXML
    private void onAdopt() {
        Animal sel = animalListView.getSelectionModel().getSelectedItem();
        if (sel != null && customer.canAdopt(sel)) {
            sel.adopt();
            customer.adoptedAnimals().add(sel);
            // refresh to drop adopted ones
            available.setPredicate(a -> !a.isAdopted());
        }
    }

    @FXML
    private void onMyDetails() throws Exception {
        ViewLoader.showStage(
            model,
            "/view/DetailsView.fxml",
            customer.getName(),
            new Stage()
        );
    }

    @FXML
    private void onClose() {
        stage.close();
    }
}
