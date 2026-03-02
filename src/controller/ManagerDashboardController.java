package controller;

import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Application.AdoptionCentre;
import model.Animals.Animal;

public class ManagerDashboardController extends Controller<AdoptionCentre> {
    @FXML private ImageView banner;
    @FXML private TabPane   filterTabs;
    @FXML private TableView<Animal> animalTable;
    @FXML private TableColumn<Animal,String> nameCol;
    @FXML private TableColumn<Animal,String> typeCol;
    @FXML private TableColumn<Animal,Number> ageCol;
    @FXML private TableColumn<Animal,String> statusCol;

    private FilteredList<Animal> filteredAnimals;

    @FXML
    private void initialize() {
        
        banner.setImage(
          new Image("file:src/view/image/cat_banner.jpg")
        );

        
        filteredAnimals = new FilteredList<>(
            model.getAnimals().getAnimals(), a -> true
        );

        nameCol  .setCellValueFactory(c -> c.getValue().nameProperty());
        typeCol  .setCellValueFactory(c -> c.getValue().typeProperty());
        ageCol   .setCellValueFactory(c -> c.getValue().ageProperty());
        statusCol.setCellValueFactory(c -> c.getValue().isAdoptedProperty());

        animalTable.setItems(filteredAnimals);

        
        filterTabs.getTabs().forEach(tab ->
            tab.setOnSelectionChanged(e -> {
                if (tab.isSelected()) {
                    String t = tab.getText();
                    filteredAnimals.setPredicate(
                        animal -> "All".equals(t)
                                   || animal
                                        .getClass()
                                        .getSimpleName()
                                        .equals(t)
                    );
                }
            })
        );
    }

    @FXML
    private void onAdd() throws IOException {
        ViewLoader.showStage(model,
                             "/view/AddAnimalView.fxml",
                             "Add Animal",
                             new Stage());
    }

    @FXML
    private void onRemove() {
        Animal sel = animalTable.getSelectionModel().getSelectedItem();
        if (sel != null) {
            model.getAnimals().remove(sel);
        }
    }

    @FXML
    private void onUserList() throws IOException {
        ViewLoader.showStage(model,
                             "/view/UserListView.fxml",
                             "User List",
                             new Stage());
    }

    @FXML
    private void onClose() {
        stage.close();
    }
}
